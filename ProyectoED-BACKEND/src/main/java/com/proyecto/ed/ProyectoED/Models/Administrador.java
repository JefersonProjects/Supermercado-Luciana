package com.proyecto.ed.ProyectoED.Models;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor

public class Administrador extends Usuario {
    public Administrador(Long id, String nombre, String apellido, String dni, String telefono, String email, String password, Role role) {
        super(id, nombre, apellido, dni, telefono, email, password, role);
    }
}
