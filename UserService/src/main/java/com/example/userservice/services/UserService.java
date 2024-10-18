package com.example.userservice.services;

import com.example.userservice.models.Role;
import com.example.userservice.models.User;
import com.example.userservice.repositories.RoleRepository;
import com.example.userservice.repositories.UserRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@Primary
public class UserService  {
    private UserRepository userRepository;
    private final RoleRepository roleRepository;

    public UserService(UserRepository userRepository,
                       RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    public User getUserDetails(Long userId) {
        System.out.println("Received Request");
        return new User();
//        Optional<User> userdetails = userRepository.findById(userId);
//
//        if(userdetails.isEmpty()){
//            return null;
//        }
//        return userdetails.get();
    }

    public User setRoles(Long userId, List<Long> roleids) {
        Optional<User> userOptional = userRepository.findById(userId);
        List<Role> roles = roleRepository.findAllByIdIn(roleids);

        User user = userOptional.get();
        user.setRoles(Set.copyOf(roles));

        return userRepository.save(user);

    }

}



// getUserById
// getRoles for Users
