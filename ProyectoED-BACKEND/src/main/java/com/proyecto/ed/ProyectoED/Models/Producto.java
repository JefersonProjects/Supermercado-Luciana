package com.proyecto.ed.ProyectoED.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Producto {

    private Long id;

    private String nombre;

    private String descripcion;

    private Double precio;

    private Integer stock;

    private Long categoriaId;

    private String categoriaNombre;

    private byte [] imagen;



}
