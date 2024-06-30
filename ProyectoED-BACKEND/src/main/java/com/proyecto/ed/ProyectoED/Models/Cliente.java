package com.proyecto.ed.ProyectoED.Models;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor


public class Cliente {

    private Long id;

    private String nombre;

    private String apellido;

    private String dni;

    private String telefono;

    private String email;

    private String password;


}
