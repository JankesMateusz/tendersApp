package com.jankes.tendersApp.security;

class AuthenticationResponse {

    private final String token;

    AuthenticationResponse(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }
}
