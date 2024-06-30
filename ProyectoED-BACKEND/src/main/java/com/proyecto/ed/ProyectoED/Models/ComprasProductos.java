package com.proyecto.ed.ProyectoED.Models;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class ComprasProductos {

    private Long id;

    private Long productoId;

    private int cantidad;

    private double precio;
}
