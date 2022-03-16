package com.lance.oauth2.server.common;

import com.nimbusds.jose.jwk.source.ImmutableSecret;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.*;

import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

/**
 * jwt
 *
 * @author lance
 * @date 2022/3/17 0:57
 */
@Slf4j
class JwtTests {


	@Test
	void encoder() {
		//Jwt jwt = Jwt.withTokenValue("33").build();
		String clientSecret = "abc123456789";
		SecretKeySpec secretKeySpec = new SecretKeySpec(clientSecret.getBytes(StandardCharsets.UTF_8), "HmacSHA256");
		//Jwt jwt = Jwt.withTokenValue(clientSecret).build();
		JWKSource<SecurityContext> jwkSource = new ImmutableSecret<>(secretKeySpec);
		NimbusJwtEncoder encoder = new NimbusJwtEncoder(jwkSource);
		Map<String, Object> claims = new HashMap<>(8);
		claims.put("secret", clientSecret);

		JwsHeader jwsHeader = JwsHeader.with(MacAlgorithm.HS256).build();
		JwtClaimsSet claimsSet = JwtClaimsSet.builder().claims(c -> c.putAll(claims)).build();
		JwtEncoderParameters parameters = JwtEncoderParameters.from(jwsHeader, claimsSet);
		Jwt jwtEncode = encoder.encode(parameters);
		log.info("===>encoder: {}", jwtEncode.getClaims());

		String token = encoder.toString();
		NimbusJwtDecoder decoder = NimbusJwtDecoder.withSecretKey(secretKeySpec).macAlgorithm(MacAlgorithm.HS256).build();
		Jwt jwtDecode = decoder.decode(token);
		log.info("===>decoder: {}", jwtDecode.getClaims());
	}
}
