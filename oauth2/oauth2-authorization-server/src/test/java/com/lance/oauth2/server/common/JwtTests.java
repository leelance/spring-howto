package com.lance.oauth2.server.common;

import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSSigner;
import com.nimbusds.jose.JWSVerifier;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.security.oauth2.core.DelegatingOAuth2TokenValidator;
import org.springframework.security.oauth2.core.OAuth2TokenValidator;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.util.CollectionUtils;

import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;

/**
 * jwt
 *
 * @author lance
 * @date 2022/3/17 0:57
 */
@Slf4j
class JwtTests {

  @Test
  @Disabled
  void signer() throws Exception {
    // Generate random 256-bit (32-byte) shared secret
    SecureRandom random = new SecureRandom();
    byte[] sharedSecret = new byte[32];
    random.nextBytes(sharedSecret);

    // Create HMAC signer
    JWSSigner signer = new MACSigner(sharedSecret);

    // Prepare JWT with claims set
    JWTClaimsSet claimsSet = new JWTClaimsSet.Builder()
        .subject("alice")
        .issuer("https://c2id.com")
        .claim("username", "19000000000")
        .claim("password", "abc@123")
        .expirationTime(new Date(new Date().getTime() + 60 * 1000))
        .build();

    SignedJWT signedJWT = new SignedJWT(new JWSHeader(JWSAlgorithm.HS256), claimsSet);

    // Apply the HMAC protection
    signedJWT.sign(signer);

    // Serialize to compact form, produces something like
    // eyJhbGciOiJIUzI1NiJ9.SGVsbG8sIHdvcmxkIQ.onO9Ihudz3WkiauDO2Uhyuz0Y18UASXlSc1eS0NkWyA
    String s = signedJWT.serialize();
    log.info("===>sign: {}", s);

    // On the consumer side, parse the JWS and verify its HMAC
    signedJWT = SignedJWT.parse(s);
    log.info("===>Claim: {}", signedJWT.getJWTClaimsSet().getClaims());
    JWSVerifier verifier = new MACVerifier(sharedSecret);

    Assertions.assertTrue(signedJWT.verify(verifier));

    // Retrieve / verify the JWT claims according to the app requirements
    Assertions.assertEquals("alice", signedJWT.getJWTClaimsSet().getSubject());
    Assertions.assertEquals("https://c2id.com", signedJWT.getJWTClaimsSet().getIssuer());
    Assertions.assertTrue(new Date().before(signedJWT.getJWTClaimsSet().getExpirationTime()));
  }

  /**
   * 对client_security进行jwt进行签名, jose签名
   */
  @Test
  @Disabled
  void encode() throws Exception {
    String clientId = "8000000014";
    String clientSecret = "a5a0ddb27da70b41d31954d0c51419d8";
    SecretKeySpec secretKeySpec = new SecretKeySpec(clientSecret.getBytes(StandardCharsets.UTF_8), "HmacSHA256");

    JWSSigner signer = new MACSigner(secretKeySpec);
    JWTClaimsSet claimsSet = new JWTClaimsSet.Builder()
        .subject(clientId)
        .issuer(clientId)
        .claim("username", "19000000000")
        .claim("password", "abc@123")
        .audience("http://auth-server:9000")
        .expirationTime(new Date(new Date().getTime() + 60 * 60 * 60 * 1000))
        .build();

    SignedJWT signedJWT = new SignedJWT(new JWSHeader(JWSAlgorithm.HS256), claimsSet);
    signedJWT.sign(signer);

    String token = signedJWT.serialize();
    log.info("===>token: {}", token);

    NimbusJwtDecoder jwtDecoder = NimbusJwtDecoder.withSecretKey(secretKeySpec).macAlgorithm(MacAlgorithm.HS256).build();
    jwtDecoder.setJwtValidator(createJwtValidator(clientId));
    Jwt jwtDecode = jwtDecoder.decode(token);

    log.info("===>{}", jwtDecode.getClaims());
  }

  private OAuth2TokenValidator<Jwt> createJwtValidator(String clientId) {
    return new DelegatingOAuth2TokenValidator<>(
        new JwtClaimValidator<>(JwtClaimNames.ISS, clientId::equals),
        new JwtClaimValidator<>(JwtClaimNames.SUB, clientId::equals),
        new JwtClaimValidator<>(JwtClaimNames.AUD, containsProviderAudience()),
        new JwtClaimValidator<>(JwtClaimNames.EXP, Objects::nonNull),
        new JwtTimestampValidator()
    );
  }

  private Predicate<List<String>> containsProviderAudience() {
    return (audienceClaim) -> {
      if (CollectionUtils.isEmpty(audienceClaim)) {
        return false;
      }
      return true;
    };
  }
}
