package com.example.userservice.controllers;

import com.example.userservice.dtos.SetUserRolesRequestDto;
import com.example.userservice.dtos.UserDto;
import com.example.userservice.models.User;
import com.example.userservice.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
    private UserService userService;

    private UserController(UserService userService){
        this.userService = userService;
    }


    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserDetails(@PathVariable("id") Long userId) {
        UserDto userDto = UserDto.from(userService.getUserDetails(userId));

        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }

    @PostMapping("/{id}/roles")
    public ResponseEntity<UserDto> setUserRoles(@PathVariable("id") Long userId, @RequestBody SetUserRolesRequestDto request) {

        UserDto userDto = UserDto.from(userService.setRoles(userId, request.getRoleIds()));

        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }
}
