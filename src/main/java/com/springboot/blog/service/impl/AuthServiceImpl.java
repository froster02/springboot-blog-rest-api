package com.springboot.blog.service.impl;

import com.springboot.blog.payload.LoginDto;
import com.springboot.blog.payload.LoginResponse;
import com.springboot.blog.service.AuthService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    private AuthenticationManager authenticationManager;

    public AuthServiceImpl(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public LoginResponse login(LoginDto loginDto) {

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginDto.getUsernameOrEmail(), loginDto.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Create and return proper JSON response
        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setMessage("User logged in successfully!");
        loginResponse.setSuccess(true);
        loginResponse.setUsername(authentication.getName());
        loginResponse.setAccessToken("temporary-token"); // You'll implement JWT later
        
        return loginResponse;
    }
}
