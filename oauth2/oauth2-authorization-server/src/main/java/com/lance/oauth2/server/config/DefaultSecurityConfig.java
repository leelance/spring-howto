package com.lance.oauth2.server.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.authorization.OAuth2AuthorizationServerConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
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
public class DefaultSecurityConfig {
  /**
   * The default endpoint {@code URI} for access token requests.
   */
  private static final String TOKEN_ENDPOINT_URI = "/oauth2/token";

  /*@Bean
  public OAuth2TokenEndpointFilter oAuth2TokenEndpointFilter(AuthenticationManager authenticationManager) {
    OAuth2TokenEndpointFilter filter = new OAuth2TokenEndpointFilter(authenticationManager, TOKEN_ENDPOINT_URI);
    filter.setAuthenticationFailureHandler(new CustomAuthenticationFailureHandler());
    return filter;
  }*/

  @Bean
  SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
    OAuth2AuthorizationServerConfigurer<HttpSecurity> authorizationServerConfigurer = new OAuth2AuthorizationServerConfigurer<>();
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
        .apply(authorizationServerConfigurer);
    ;
    return http.build();
  }

  @Bean
  UserDetailsService users() {
    UserDetails user = User.withDefaultPasswordEncoder()
        .username("admin")
        .password("123456")
        .roles("USER")
        .build();
    return new InMemoryUserDetailsManager(user);
  }
}
