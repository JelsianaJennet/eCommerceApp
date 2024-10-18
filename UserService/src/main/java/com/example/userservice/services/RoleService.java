package com.example.userservice.services;

import com.example.userservice.models.Role;
import com.example.userservice.repositories.RoleRepository;
import org.springframework.stereotype.Service;

@Service
public class RoleService {

    private RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository){
        this.roleRepository = roleRepository;
    }

    // saves new role for the app
    public Role createRole(String role){
        Role roleObj = new Role();
        roleObj.setRole(role);

        roleRepository.save(roleObj);
        return roleObj;
    }
}
