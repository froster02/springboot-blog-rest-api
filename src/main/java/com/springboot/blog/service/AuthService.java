package com.springboot.blog.service;

import com.springboot.blog.payload.LoginDto;
import com.springboot.blog.payload.LoginResponse;

public interface AuthService {
    LoginResponse login(LoginDto loginDto);
}
