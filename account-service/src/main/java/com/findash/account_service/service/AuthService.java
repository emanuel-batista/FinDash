package com.findash.account_service.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.findash.account_service.dto.LoginRequest;
import com.findash.account_service.model.User;
import com.findash.account_service.repository.UserRepository;

@Service
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }


    public void registerUser(String name, String email, String password){
        if (userRepository.findByEmail(email).isPresent()) {
            throw new IllegalArgumentException("Email already in use");
        }
        String encodedPassword = passwordEncoder.encode(password);
        User user = new User(name, email, encodedPassword);
        userRepository.save(user);
    }

    public void loginUser(LoginRequest request) {
        User user = userRepository.findByEmail(request.email())
            .orElseThrow(() -> new IllegalArgumentException("Email or password incorrect!"));
        

        if (passwordEncoder.matches(request.password(), user.getPassword())){
            
        }
    }
        
}
