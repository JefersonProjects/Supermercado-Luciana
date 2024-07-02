package com.proyecto.ed.ProyectoED.Models;


import lombok.*;

@Data
@NoArgsConstructor

public class Cliente extends Usuario{

    public Cliente(Long id, String nombre, String apellido, String dni, String telefono, String email, String password, Role role) {
        super(id, nombre, apellido, dni, telefono, email, password, role);
    }
}
