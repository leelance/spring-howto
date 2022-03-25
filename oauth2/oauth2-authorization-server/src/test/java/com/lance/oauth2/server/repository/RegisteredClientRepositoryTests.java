package com.lance.oauth2.server.repository;

import com.lance.oauth2.server.common.json.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jose.jws.SignatureAlgorithm;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.config.ClientSettings;
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
  @Autowired
  private PasswordEncoder passwordEncoder;

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
  void saveBase() {
    String id = UUID.randomUUID().toString().replaceAll("-", "");

    TokenSettings tokenSettings = TokenSettings.builder()
        .reuseRefreshTokens(true)
        .refreshTokenTimeToLive(Duration.ofDays(7))
        .accessTokenTimeToLive(Duration.ofHours(8))
        .idTokenSignatureAlgorithm(SignatureAlgorithm.RS256)
        .reuseRefreshTokens(false)
        .build();

    RegisteredClient client = RegisteredClient.withId(id)
        .clientId("8000000013")
        .clientIdIssuedAt(Instant.now())
        .clientSecret("{noop}secret")
        .clientSecretExpiresAt(Instant.now().plus(Period.ofDays(20)))
        .clientName("Client credentials client_secret_basic有限公司")
        .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
        .authorizationGrantType(AuthorizationGrantType.CLIENT_CREDENTIALS)
        .authorizationGrantType(AuthorizationGrantType.REFRESH_TOKEN)
        .scope("server")
        .tokenSettings(tokenSettings)
        .build();
    registeredClientRepository.save(client);

    log.info("===>{}", JsonUtils.toJsonString(client));
  }

  /**
   * 目前不支持password模型
   */
  @Test
  @Disabled
  void savePassword() {
    String id = UUID.randomUUID().toString().replaceAll("-", "");
    String password = "d1921aa0ca3c1146a01520c04e6caa9e";

    TokenSettings tokenSettings = TokenSettings.builder()
        .reuseRefreshTokens(true)
        .refreshTokenTimeToLive(Duration.ofDays(7))
        .accessTokenTimeToLive(Duration.ofHours(8))
        .idTokenSignatureAlgorithm(SignatureAlgorithm.RS256)
        .build();

    RegisteredClient client = RegisteredClient.withId(id)
        .clientId("8000000016")
        .clientIdIssuedAt(Instant.now())
        .clientSecret(passwordEncoder.encode(password))
        .clientSecretExpiresAt(Instant.now().plus(Period.ofDays(20)))
        .clientName("Client password client_secret_basic有限公司")
        .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
        .authorizationGrantType(AuthorizationGrantType.PASSWORD)
        .authorizationGrantType(AuthorizationGrantType.CLIENT_CREDENTIALS)
        .authorizationGrantType(AuthorizationGrantType.REFRESH_TOKEN)
        .scope("server")
        .tokenSettings(tokenSettings)
        .build();
    registeredClientRepository.save(client);

    log.info("===>{}", JsonUtils.toJsonString(client));
  }

  /**
   * client_secret_jwt
   */
  @Test
  @Disabled
  void saveJwt() {
    String id = UUID.randomUUID().toString().replaceAll("-", "");

    TokenSettings tokenSettings = TokenSettings.builder()
        .reuseRefreshTokens(true)
        .refreshTokenTimeToLive(Duration.ofDays(7))
        .accessTokenTimeToLive(Duration.ofHours(8))
        .idTokenSignatureAlgorithm(SignatureAlgorithm.RS256)
        .reuseRefreshTokens(false)
        .build();

    ClientSettings clientSettings = ClientSettings.builder()
        .tokenEndpointAuthenticationSigningAlgorithm(MacAlgorithm.HS256)
        .build();

    RegisteredClient client = RegisteredClient.withId(id)
        .clientId("8000000014")
        .clientIdIssuedAt(Instant.now())
        .clientSecret("a5a0ddb27da70b41d31954d0c51419d8")
        .clientSecretExpiresAt(Instant.now().plus(Period.ofDays(20)))
        .clientName("Client credentials client_secret_jwt有限公司")
        .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_JWT)
        .authorizationGrantType(AuthorizationGrantType.CLIENT_CREDENTIALS)
        .authorizationGrantType(AuthorizationGrantType.REFRESH_TOKEN)
        .scope("server")
        .tokenSettings(tokenSettings)
        .clientSettings(clientSettings)
        .build();
    registeredClientRepository.save(client);

    log.info("===>{}", JsonUtils.toJsonString(client));
  }

  /**
   * private_key_jwt
   */
  @Test
  @Disabled
  void savePriKeyJwt() {
    String id = UUID.randomUUID().toString().replaceAll("-", "");
    String jwkSetUri = "http://127.0.0.1:9000/uc/resources";


    TokenSettings tokenSettings = TokenSettings.builder()
        .reuseRefreshTokens(true)
        .refreshTokenTimeToLive(Duration.ofDays(7))
        .accessTokenTimeToLive(Duration.ofHours(8))
        .idTokenSignatureAlgorithm(SignatureAlgorithm.RS256)
        .reuseRefreshTokens(false)
        .build();

    ClientSettings clientSettings = ClientSettings.builder()
        .tokenEndpointAuthenticationSigningAlgorithm(SignatureAlgorithm.RS256)
        .jwkSetUrl(jwkSetUri)
        .build();

    RegisteredClient client = RegisteredClient.withId(id)
        .clientId("8000000015")
        .clientIdIssuedAt(Instant.now())
        .clientSecret("")
        .clientSecretExpiresAt(Instant.now().plus(Period.ofDays(20)))
        .clientName("Client credentials private_key_jwt有限公司")
        .clientAuthenticationMethod(ClientAuthenticationMethod.PRIVATE_KEY_JWT)
        .authorizationGrantType(AuthorizationGrantType.CLIENT_CREDENTIALS)
        .authorizationGrantType(AuthorizationGrantType.REFRESH_TOKEN)
        .scope("server")
        .tokenSettings(tokenSettings)
        .clientSettings(clientSettings)
        .build();
    registeredClientRepository.save(client);

    log.info("===>{}", JsonUtils.toJsonString(client));
  }
}
