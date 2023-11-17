package com.mysite.sbb.global.security.service;

import com.mysite.sbb.domain.user.dao.UserRepository;
import com.mysite.sbb.domain.user.entity.SiteUser;
import com.mysite.sbb.global.security.role.UserRole;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class UserSecurityService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SiteUser user = this.userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("사용자를 찾을수 없습니다."));

        List<GrantedAuthority> authorities = new ArrayList<>();
        // 나중에 관리자 권한을 처리할 때 수정이 필요.
        authorities.add(new SimpleGrantedAuthority(UserRole.USER.getValue()));

        return new User(user.getUsername(), user.getPassword(), authorities);
    }
}
