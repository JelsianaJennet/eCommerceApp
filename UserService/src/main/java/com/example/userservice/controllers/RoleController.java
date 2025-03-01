package com.example.userservice.controllers;

import com.example.userservice.dtos.CreateRoleRequestDto;
import com.example.userservice.models.Role;
import com.example.userservice.services.RoleService;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/roles")
public class RoleController {

    private RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @PostMapping
    public ResponseEntity<Role> createRole(CreateRoleRequestDto createRoleRequestDto) {
        Role role = roleService.createRole(createRoleRequestDto.getName());

        return new ResponseEntity<>(role, HttpStatus.OK);
    }
}
