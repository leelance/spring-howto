package com.lance.oauth2.server.config;

import com.lance.oauth2.server.config.result.failure.CustomAuthenticationFailureHandler;
import com.lance.oauth2.server.config.result.success.CustomAuthenticationSuccessHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.authorization.OAuth2AuthorizationServerConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.RequestMatcher;

import static org.springframework.security.config.Customizer.withDefaults;

/**
 * DefaultSecurityConfig
 *
 * @author lance
 * @date 2022/3/14 23:32
 */
@Slf4j
@EnableWebSecurity
@RequiredArgsConstructor
public class DefaultSecurityConfig {

  @Bean
  @Order(Ordered.HIGHEST_PRECEDENCE)
  SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
    OAuth2AuthorizationServerConfigurer<HttpSecurity> authorizationServerConfigurer = new OAuth2AuthorizationServerConfigurer<>();
    authorizationServerConfigurer.tokenEndpoint(endpointConfigurer -> {
      endpointConfigurer.errorResponseHandler(authenticationFailureHandler());
      endpointConfigurer.accessTokenResponseHandler(authenticationSuccessHandler());
    });
    authorizationServerConfigurer.oidc(oidcConfigurer -> {

    });

    RequestMatcher endpointsMatcher = authorizationServerConfigurer.getEndpointsMatcher();

    http
        .requestMatcher(endpointsMatcher)
        .authorizeRequests(
            authorizeRequests -> {
              try {
                authorizeRequests.antMatchers("/resources").permitAll()
                    .and()
                    .authorizeRequests().anyRequest().authenticated()
                ;
              } catch (Exception e) {
                log.error("===>Default security filter fail: ", e);
              }
            })
        .formLogin(withDefaults())
        .csrf(csrf -> csrf.ignoringRequestMatchers(endpointsMatcher))
        .apply(authorizationServerConfigurer)
    ;

    return http.build();
  }

  /**
   * custom authentication failure handler
   */
  private AuthenticationSuccessHandler authenticationSuccessHandler() {
    log.info("===>Init custom success handler");
    return new CustomAuthenticationSuccessHandler();
  }

  /**
   * custom authentication failure handler
   */
  private AuthenticationFailureHandler authenticationFailureHandler() {
    log.info("===>Init custom failure handler");
    return new CustomAuthenticationFailureHandler();
  }
}
