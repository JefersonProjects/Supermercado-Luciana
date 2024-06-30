package com.proyecto.ed.ProyectoED.Models;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoriaProducto {

    private Long id;

    private String nombre;

    private String descripcion;

}
