package com.lance.oauth2.server.config.user;

import com.lance.oauth2.server.domain.UserInfo;
import com.lance.oauth2.server.mapper.UserInfoMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * custom define user detail service
 *
 * @author lance
 * @date 2022/3/25 17:19
 */
@Slf4j
@AllArgsConstructor
@Service(value = "userDetailsService")
public class CustomUserDetailsServiceImpl implements UserDetailsService {
  private final UserInfoMapper userInfoMapper;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    UserInfo user = userInfoMapper.findByUsername(username);
    if (Objects.isNull(user)) {
      throw new UsernameNotFoundException("");
    }

    List<GrantedAuthority> grantedAuthorities = AuthorityUtils.NO_AUTHORITIES;
    return User.builder()
        .username(username)
        .password(user.getPassword())
        .authorities(grantedAuthorities)
        .accountLocked(user.getStatus() == 0)
        .accountExpired(true)
        .disabled(false)
        .build();
  }
}
