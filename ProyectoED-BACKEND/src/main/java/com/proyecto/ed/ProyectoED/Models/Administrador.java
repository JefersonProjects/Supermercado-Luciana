package com.proyecto.ed.ProyectoED.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Administrador")
public class Administrador {

    @Column(name ="id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name ="nombre")
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
