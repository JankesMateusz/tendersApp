package com.jankes.tendersApp.security;

import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    private final UserRepository repository;

    public AuthenticationService(UserRepository repository) {
        this.repository = repository;
    }

    public AuthenticationResponse register(RegisterRequest request) {

        return null;
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {

        return null;
    }
}
