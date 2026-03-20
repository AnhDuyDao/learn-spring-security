package com.duyanh.springboot.service;

import com.duyanh.springboot.dto.request.LoginRequest;
import com.duyanh.springboot.dto.response.LoginResponse;
import com.duyanh.springboot.model.LoginUserDeatils;
import com.duyanh.springboot.model.User;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public AuthenticationService(AuthenticationManager authenticationManager, JwtService jwtService) {
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    public LoginResponse login(LoginRequest request) {
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword());
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);

        LoginUserDeatils principal = (LoginUserDeatils) authenticate.getPrincipal();
        User user = principal.getUser();
        String accessToken = jwtService.generationAccessToken(user);
        String refreshToken = jwtService.generationRefreshToken(user);

        return LoginResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }
}
