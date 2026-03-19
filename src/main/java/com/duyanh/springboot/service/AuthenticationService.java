package com.duyanh.springboot.service;

import com.duyanh.springboot.dto.request.LoginRequest;
import com.duyanh.springboot.dto.response.LoginResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    private final AuthenticationManager authenticationManager;

    public AuthenticationService(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    public LoginResponse login(LoginRequest request) {
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword());
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);

        return LoginResponse.builder()
                .accessToken("Accesstoken1234")
                .refreshToken("Refreshtoken1234")
                .build();
    }
}
