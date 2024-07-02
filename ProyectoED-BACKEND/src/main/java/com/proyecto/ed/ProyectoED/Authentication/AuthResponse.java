package com.proyecto.ed.ProyectoED.Authentication;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


public class AuthResponse {
    private String token;
    private String role;

    // Constructor, getters y setters
    public AuthResponse(String token, String role) {
        this.token = token;
        this.role = role;
    }

    // Constructor que acepta solo token
    public AuthResponse(String token) {
        this.token = token;
        this.role = null;
    }
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

}
