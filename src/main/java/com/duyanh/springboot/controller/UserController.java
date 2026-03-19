package com.duyanh.springboot.controller;

import com.duyanh.springboot.dto.request.UserCreateRequest;
import com.duyanh.springboot.dto.response.UserCreateResponse;
import com.duyanh.springboot.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/users")
    public UserCreateResponse createUser(@RequestBody UserCreateRequest userCreateRequest) {
        return userService.createUser(userCreateRequest);
    }
}
