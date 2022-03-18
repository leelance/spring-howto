package com.lance.oauth2.server.common;

import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSSigner;
import com.nimbusds.jose.JWSVerifier;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jose.crypto.RSASSASigner;
import com.nimbusds.jose.jwk.JWK;
import com.nimbusds.jose.jwk.KeyUse;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.security.oauth2.core.DelegatingOAuth2TokenValidator;
import org.springframework.security.oauth2.core.OAuth2TokenValidator;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jose.jws.SignatureAlgorithm;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.util.CollectionUtils;

import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
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
   * 对client_secret_jwt进行jwt进行签名, jose签名
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

  /**
   * 基于公私钥对, 生成jwt
   */
  @Test
  @Disabled
  void priPubKeyJwt() throws Exception {
    String clientId = "8000000014";

    KeyPair kp = keyPair();
    RSAPublicKey publicKey = (RSAPublicKey) kp.getPublic();
    RSAPrivateKey privateKey = (RSAPrivateKey) kp.getPrivate();

    JWSSigner signer = new RSASSASigner(privateKey);
    JWTClaimsSet claimsSet = new JWTClaimsSet.Builder()
        .subject(clientId)
        .issuer(clientId)
        .claim("username", "19000000000")
        .claim("password", "abc@123")
        .audience("http://auth-server:9000")
        .expirationTime(new Date(new Date().getTime() + 60 * 60 * 60 * 1000))
        .build();

    SignedJWT signedJWT = new SignedJWT(new JWSHeader(JWSAlgorithm.RS256), claimsSet);
    signedJWT.sign(signer);

    String token = signedJWT.serialize();
    log.info("===>token: {}", token);


    NimbusJwtDecoder jwtDecoder = NimbusJwtDecoder.withPublicKey(publicKey).signatureAlgorithm(SignatureAlgorithm.RS256).build();

    jwtDecoder.setJwtValidator(createJwtValidator(clientId));
    Jwt jwtDecode = jwtDecoder.decode(token);

    log.info("===>{}", jwtDecode.getClaims());
  }

  /**
   * 对private_key_jwt进行jwt进行签名, jose签名
   */
  @Test
  //@Disabled
  void priKeyJwt() throws Exception {
    String clientId = "8000000014";
    String jwkSetUri = "http://127.0.0.1:9000/uc/resources";

    RSAPrivateKey privateKey = PemFile.readPrivateKey(PemFile.dir + "id_rsa");
    JWSSigner signer = new RSASSASigner(privateKey);
    JWTClaimsSet claimsSet = new JWTClaimsSet.Builder()
        .subject(clientId)
        .issuer(clientId)
        .claim("username", "19000000000")
        .claim("password", "abc@123")
        .audience("http://auth-server:9000")
        .expirationTime(new Date(new Date().getTime() + 60 * 60 * 60 * 1000))
        .build();

    JWSHeader header = new JWSHeader.Builder(JWSAlgorithm.RS256)
        .keyID(clientId)
        .build();

    SignedJWT signedJWT = new SignedJWT(header, claimsSet);
    signedJWT.sign(signer);

    String token = signedJWT.serialize();
    log.info("===>token: {}", token);

    NimbusJwtDecoder jwtDecoder = NimbusJwtDecoder.withJwkSetUri(jwkSetUri).jwsAlgorithm(SignatureAlgorithm.RS256).build();
    jwtDecoder.setJwtValidator(createJwtValidator(clientId));

    log.info("===>token to jwt");
    Jwt jwtDecode = jwtDecoder.decode(token);

    log.info("===>{}", jwtDecode.getClaims());
  }

  /**
   * 产生公钥jwk json字符串,
   * 并产生私钥文件 /Users/lance/home/id_rsa
   */
  @Test
  @Disabled
  void generateJwk() throws Exception {
    String keyId = "8000000014";
    KeyPair keyPair = keyPair();

    JWK jwk = new RSAKey.Builder((RSAPublicKey) keyPair.getPublic())
        .privateKey((RSAPrivateKey) keyPair.getPrivate())
        .keyUse(KeyUse.SIGNATURE)
        .keyID(keyId)
        .algorithm(JWSAlgorithm.RS256)
        .build();

    log.info("===>jwk: {}", jwk.toJSONString());
    writePemFile(jwk.toRSAKey().toPrivateKey(), "RSA PRIVATE KEY", "id_rsa");
  }

  /**
   * 生成KeyPair
   */
  private KeyPair keyPair() throws NoSuchAlgorithmException {
    KeyPairGenerator gen = KeyPairGenerator.getInstance("RSA");
    gen.initialize(2048);
    return gen.generateKeyPair();
  }

  /**
   * 公私钥写文件
   */
  private void writePemFile(Key key, String description, String filename) throws Exception {
    PemFile pemFile = new PemFile(key, description);
    pemFile.write(filename);

    log.info("{} successfully write in file {}.", description, filename);
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
