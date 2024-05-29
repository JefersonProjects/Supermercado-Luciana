package com.proyecto.ed.ProyectoED.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "cliente")// nombre de la tabla//
public class Cliente {

    @Column(name ="id") //indica que el campo id de la clase Cliente está mapeado a la columna "id" en la tabla "cliente".//
    @Id //  Clave primaria
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name ="apellido")
    private String apellido;

    @Column(name ="dni")
    private String dni;

    @Column(name ="telefono")
    private String telefono;

    @Column(name ="email")
    private String email;

    @Column(name ="password")
    private String password;


}
