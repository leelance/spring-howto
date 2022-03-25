package com.lance.oauth2.server.config.result.failure;

import com.lance.oauth2.server.common.result.R;
import com.lance.oauth2.server.common.result.ResultCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
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
 * private final CustomOauth2ErrorHttpMessageConverter customOauth2ErrorHttpMessageConverter = new CustomOauth2ErrorHttpMessageConverter();
 *
 * @author lance
 * @date 2022/3/19 16:30
 */
@Slf4j
public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler {
  private final HttpMessageConverter<Object> accessTokenHttpResponseConverter = new MappingJackson2HttpMessageConverter();

  @Override
  public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
    log.warn("custom authentication failure: ", exception);

    OAuth2Error error = ((OAuth2AuthenticationException) exception).getError();
    ServletServerHttpResponse httpResponse = new ServletServerHttpResponse(response);
    httpResponse.setStatusCode(HttpStatus.OK);

    R<OAuth2Error> result = R.fail(ResultCode.PARAM_VALID_ERROR);
    result.setData(error);
    accessTokenHttpResponseConverter.write(result, null, httpResponse);
  }
}
