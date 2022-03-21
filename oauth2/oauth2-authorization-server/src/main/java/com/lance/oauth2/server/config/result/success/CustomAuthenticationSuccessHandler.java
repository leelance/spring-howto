package com.lance.oauth2.server.config.result.success;

import com.lance.oauth2.server.common.result.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.security.oauth2.core.OAuth2RefreshToken;
import org.springframework.security.oauth2.core.endpoint.OAuth2AccessTokenResponse;
import org.springframework.security.oauth2.server.authorization.authentication.OAuth2AccessTokenAuthenticationToken;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.util.CollectionUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.temporal.ChronoUnit;
import java.util.Map;

/**
 * custom authentication success
 *
 * @author lance
 * @date 2022/3/21 21:25
 */
@Slf4j
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
  private final HttpMessageConverter<Object> accessTokenHttpResponseConverter = new MappingJackson2HttpMessageConverter();

  @Override
  public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
    OAuth2AccessTokenAuthenticationToken accessTokenAuthentication =
        (OAuth2AccessTokenAuthenticationToken) authentication;

    OAuth2AccessToken accessToken = accessTokenAuthentication.getAccessToken();
    OAuth2RefreshToken refreshToken = accessTokenAuthentication.getRefreshToken();
    Map<String, Object> additionalParameters = accessTokenAuthentication.getAdditionalParameters();

    OAuth2AccessTokenResponse.Builder builder =
        OAuth2AccessTokenResponse.withToken(accessToken.getTokenValue())
            .tokenType(accessToken.getTokenType())
            .scopes(accessToken.getScopes());
    if (accessToken.getIssuedAt() != null && accessToken.getExpiresAt() != null) {
      builder.expiresIn(ChronoUnit.SECONDS.between(accessToken.getIssuedAt(), accessToken.getExpiresAt()));
    }
    if (refreshToken != null) {
      builder.refreshToken(refreshToken.getTokenValue());
    }
    if (!CollectionUtils.isEmpty(additionalParameters)) {
      builder.additionalParameters(additionalParameters);
    }
    OAuth2AccessTokenResponse accessTokenResponse = builder.build();
    ServletServerHttpResponse httpResponse = new ServletServerHttpResponse(response);
    //custom R
    R<OAuth2AccessTokenResponse> result = R.data(accessTokenResponse);
    this.accessTokenHttpResponseConverter.write(result, null, httpResponse);
  }
}
