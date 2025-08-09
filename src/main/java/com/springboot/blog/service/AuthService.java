package com.springboot.blog.service;

import com.springboot.blog.payload.LoginDto;
import com.springboot.blog.payload.LoginResponse;
import com.springboot.blog.payload.RegisterDto;

public interface AuthService {
    LoginResponse login(LoginDto loginDto);

    String register(RegisterDto registorDto);
}
