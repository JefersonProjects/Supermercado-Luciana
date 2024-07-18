package com.proyecto.ed.ProyectoED.Authentication;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


public class AuthResponse {
    private String token;
    private String role;
    private Long id;
    private String nombre;

    private String apellido;

    private String dni;

    private String telefono;

    private String email;

    // Constructor, getters y setters
    public AuthResponse(String token, String role, Long id,String nombre, String apellido, String dni, String telefono, String email) {
        this.token = token;
        this.role = role;
        this.id=id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.telefono = telefono;
        this.email = email;
    }

    public AuthResponse(String token, String role) {
        this.token = token;
        this.role = role;
    }

    public AuthResponse(String token) {
        this.token = token;
        this.role = null;
    }

    // Getters y setters
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}