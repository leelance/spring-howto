package com.lance.oauth2.server.repository;

import com.lance.oauth2.server.json.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.jose.jws.SignatureAlgorithm;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.config.TokenSettings;

import java.time.Duration;
import java.time.Instant;
import java.time.Period;
import java.util.UUID;

/**
 * RegisteredClientRepository
 *
 * @author lance
 * @date 2022/3/15 21:03
 */
@Slf4j
@SpringBootTest
class RegisteredClientRepositoryTests {
	@Autowired
	private RegisteredClientRepository registeredClientRepository;

	@Test
	@Disabled
	void findByClientId() {
		String clientId = "8000000010";
		RegisteredClient client = registeredClientRepository.findByClientId(clientId);

		log.info("===>{}", JsonUtils.toJsonString(client));
	}

	@Test
	@Disabled
	void findById() {
		String id = "833cec50-fc11-4488-b29c-d3bb7fe7da98";
		RegisteredClient client = registeredClientRepository.findById(id);

		log.info("===>{}", JsonUtils.toJsonString(client));
	}

	@Test
	@Disabled
	void save() {
		String id = UUID.randomUUID().toString().replaceAll("-", "");

		TokenSettings tokenSettings = TokenSettings.builder()
				.reuseRefreshTokens(true)
				.refreshTokenTimeToLive(Duration.ofDays(7))
				.accessTokenTimeToLive(Duration.ofHours(8))
				.idTokenSignatureAlgorithm(SignatureAlgorithm.RS256)
				.reuseRefreshTokens(false)
				.build();

		RegisteredClient client = RegisteredClient.withId(id)
				.clientId("8000000011")
				.clientIdIssuedAt(Instant.now())
				.clientSecret("{noop}secret")
				.clientSecretExpiresAt(Instant.now().plus(Period.ofDays(20)))
				.clientName("Test有限公司的")
				.clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_POST)
				.authorizationGrantType(AuthorizationGrantType.PASSWORD)
				.authorizationGrantType(AuthorizationGrantType.REFRESH_TOKEN)
				.scope("server")
				.scope("web")
				.tokenSettings(tokenSettings)
				.build();
		registeredClientRepository.save(client);

		log.info("===>{}", JsonUtils.toJsonString(client));
	}
}
