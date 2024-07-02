package com.proyecto.ed.ProyectoED.Authentication;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RegisterRequest {
    String nombre;
    String apellido;
    String dni;
    String telefono;
    String email;
    String password;
}
