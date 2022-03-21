package com.lance.oauth2.server.config.error;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.OAuth2Error;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * custom authentication failure handler
 *
 * @author lance
 * @date 2022/3/19 16:30
 */
@Slf4j
public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler {
  private final CustomOauth2ErrorHttpMessageConverter customOauth2ErrorHttpMessageConverter = new CustomOauth2ErrorHttpMessageConverter();

  @Override
  public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
    OAuth2Error error = ((OAuth2AuthenticationException) exception).getError();
    log.info("===>CustomAuthenticationFailureHandler: {}", error);
    ServletServerHttpResponse httpResponse = new ServletServerHttpResponse(response);
    httpResponse.setStatusCode(HttpStatus.OK);
    customOauth2ErrorHttpMessageConverter.write(error, null, httpResponse);
  }
}
