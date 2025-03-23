package com.codingshuttle.SecurityApp.SecurityApplication.controllers;

import com.codingshuttle.SecurityApp.SecurityApplication.dto.LoginDto;
import com.codingshuttle.SecurityApp.SecurityApplication.dto.SignUpDto;
import com.codingshuttle.SecurityApp.SecurityApplication.dto.UserDto;
import com.codingshuttle.SecurityApp.SecurityApplication.services.AuthService;
import com.codingshuttle.SecurityApp.SecurityApplication.services.UserServiceImpl;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final UserServiceImpl userService;
    private final AuthService authService;
    @PostMapping("/signup")
    public ResponseEntity<UserDto>  signup(@RequestBody SignUpDto signUpDto){
        UserDto userDto = userService.signUp(signUpDto);

        return ResponseEntity.ok(userDto);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginDto loginDto,
                                        HttpServletRequest request,
                                        HttpServletResponse response){
        String token = authService.login(loginDto);

        Cookie cookie = new Cookie("token", token);
        cookie.setHttpOnly(true);
        response.addCookie(cookie);

        return ResponseEntity.ok(token);
    }
}
