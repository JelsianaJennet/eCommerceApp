package com.example.userservice.services;

import com.example.userservice.dtos.UserDto;
import com.example.userservice.models.Role;
import com.example.userservice.models.Session;
import com.example.userservice.models.SessionStatus;
import com.example.userservice.models.User;
import com.example.userservice.repositories.SessionRepository;
import com.example.userservice.repositories.UserRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMapAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.crypto.SecretKey;
import java.util.HashMap;
import java.util.Optional;
import io.jsonwebtoken.Jwts;
import javax.crypto.SecretKey;
import java.util.Random;
import java.util.random.RandomGenerator;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.time.LocalDate;
import java.util.*;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.MacAlgorithm;

@Service
public class AuthService {
    private UserRepository userRepository;
    private SessionRepository sessionRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public AuthService(UserRepository userRepository, SessionRepository sessionRepository, BCryptPasswordEncoder bCryptPasswordEncoder){
        this.userRepository = userRepository;
        this.sessionRepository = sessionRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public User signUp(String email, String password) {
        User user = new User();
        user.setEmail(email);
//      user.setPassword(password);

        user.setPassword(bCryptPasswordEncoder.encode(password));

        User savedUser = userRepository.save(user);

        return savedUser;
    }

    public ResponseEntity<UserDto> login(String email, String password) {
        Optional<User> userOptional = userRepository.findUserByEmail(email);

        if(userOptional.isEmpty()) return null;

        User user = userOptional.get();

//        if(!user.getPassword().equals(password)) {
//            return null;
//        }

        if(!bCryptPasswordEncoder.matches(password, user.getPassword())) {
            throw new RuntimeException("Wrong username Password");
        }

        String token = RandomStringUtils.randomAlphanumeric(30);

        MacAlgorithm alg = Jwts.SIG.HS256; //or HS384 or HS256
        SecretKey key = alg.key().build();

        Map<String, Object>  jsonForJwt = new HashMap<>();
        jsonForJwt.put("email", user.getEmail());
//        jsonForJwt.put("roles", user.getRoles());
        jsonForJwt.put("createdAt", new Date());
        jsonForJwt.put("expiryAt", new Date(LocalDate.now().plusDays(3).toEpochDay()));


        token = Jwts.builder()
                .claims(jsonForJwt)
                .signWith(key, alg)
                .compact();


        Session session = new Session();
        session.setUser(user);
        session.setToken(token);
        session.setSessionStatus(SessionStatus.ACTIVE);
        sessionRepository.save(session);

        MultiValueMapAdapter<String, String> headers = new MultiValueMapAdapter<>(new HashMap<>());
        headers.add(HttpHeaders.SET_COOKIE,"auth-token:" +token);

        ResponseEntity<UserDto> response = new ResponseEntity<>(UserDto.from(user), headers, HttpStatus.OK);

        return response;
    }

    public ResponseEntity<Void> logout(Long userId, String token) {

        Optional<Session> sessionOptional = sessionRepository.findByTokenAndUser_Id(token,userId);

        if(sessionOptional.isEmpty()) return null;

        Session session = sessionOptional.get();

        session.setSessionStatus(SessionStatus.ENDED);

        sessionRepository.save(session);

        return ResponseEntity.ok().build();

    }

    public SessionStatus validate(String token, Long userId) {
        Optional<Session> sessionOptional = sessionRepository.findByTokenAndUser_Id(token, userId);

        if (sessionOptional.isEmpty()) {
            return SessionStatus.ENDED;
//            return null;
        }

        Session session = sessionOptional.get();

        if (!session.getSessionStatus().equals(SessionStatus.ACTIVE)) {
            return SessionStatus.ENDED;
        }


        Jws<Claims> claimsJws = Jwts.parser()
                .build()
                .parseSignedClaims(token);

        String email = (String) claimsJws.getPayload().get("email");
        List<Role> roles = (List<Role>) claimsJws.getPayload().get("roles");
        Date createdAt = (Date) claimsJws.getPayload().get("createdAt");

        if (createdAt.before(new Date())) {
            return SessionStatus.ENDED;
        }


//        if (!session.)

        return SessionStatus.ACTIVE;
    }
}

// Signup, Login, Logout,
