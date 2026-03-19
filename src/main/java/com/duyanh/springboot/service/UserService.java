package com.duyanh.springboot.service;

import com.duyanh.springboot.dto.request.UserCreateRequest;
import com.duyanh.springboot.dto.response.UserCreateResponse;
import com.duyanh.springboot.model.User;
import com.duyanh.springboot.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserCreateResponse createUser(UserCreateRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email is already exist");
        }

        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        User user = User.builder()
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .build();

        userRepository.save(user);
        return UserCreateResponse.builder()
                .email(user.getEmail())
                .build();
    }
}
