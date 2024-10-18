package com.example.userservice.controllers;

import com.example.userservice.dtos.*;
import com.example.userservice.models.SessionStatus;
import com.example.userservice.models.User;
import com.example.userservice.services.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private AuthService authService;

    public AuthController(AuthService authService){
        this.authService = authService;
    }

    @PostMapping("/signUp")
    public ResponseEntity<UserDto> signUp(@RequestBody SignUpRequestDto request) {
        User user = authService.signUp(request.getEmail(), request.getPassword());

        return new ResponseEntity<>(new UserDto().from(user), HttpStatus.OK);
    }

    // Spring Security will handle it
//    @PostMapping("/login")
//    public ResponseEntity<UserDto> login(@RequestBody LoginRequestDto request) {
//        return authService.login(request.getEmail(), request.getPassword());
//    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(@RequestBody LogoutRequestDto request) {
        return authService.logout(request.getUserId(), request.getToken());
    }

    @PostMapping("/validate")
    public ResponseEntity<SessionStatus> validateToken(ValidateTokenRequestDto request) {
        SessionStatus sessionStatus = authService.validate(request.getToken(), request.getUserId());

        return new ResponseEntity<>(sessionStatus, HttpStatus.OK);
    }


}

// sign up, login, logout,