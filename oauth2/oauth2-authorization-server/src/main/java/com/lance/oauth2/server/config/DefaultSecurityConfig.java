package com.lance.oauth2.server.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

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

  @Bean
  SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
    http
        //.authorizeRequests(authorizeRequests -> authorizeRequests.anyRequest().authenticated())
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
        .formLogin(withDefaults());
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
