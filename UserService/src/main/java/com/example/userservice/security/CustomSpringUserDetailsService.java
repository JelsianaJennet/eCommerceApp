package com.example.userservice.security;

import com.example.userservice.models.User;
import com.example.userservice.repositories.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomSpringUserDetailsService implements UserDetailsService {

    private UserRepository userRepository;

    public CustomSpringUserDetailsService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<User> userOptional = userRepository.findUserByEmail(username);

        if(userOptional.isEmpty()) throw new UsernameNotFoundException("Username doesn't exsist");

        User user = userOptional.get();

        return new CustomSpringUserDetails(user);
    }
}
