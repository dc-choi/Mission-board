package com.mysite.sbb.domain.user.application;

import com.mysite.sbb.domain.user.dao.UserRepository;
import com.mysite.sbb.domain.user.dto.AddUserRequest;
import com.mysite.sbb.domain.user.dto.AddUserResponse;
import com.mysite.sbb.domain.user.dto.FindUserResponse;
import com.mysite.sbb.domain.user.entity.SiteUser;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
@Transactional
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AddUserResponse add(AddUserRequest request) {
        SiteUser user = AddUserRequest.toEntity(request);
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        return AddUserResponse.of(this.userRepository.save(user));
    }

    public FindUserResponse getByUsername(String name) {
        SiteUser user = this.userRepository.findByUsername(name)
                .orElseThrow(() -> new UsernameNotFoundException("사용자를 찾을수 없습니다."));
        return FindUserResponse.of(user);
    }
}
