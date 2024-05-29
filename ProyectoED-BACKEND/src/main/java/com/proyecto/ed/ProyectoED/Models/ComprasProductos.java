package com.proyecto.ed.ProyectoED.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Compras_Productos")
public class ComprasProductos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    @Column(name = "id")
    private Long id;

    @Column(name = "producto_id")
    private Long productoId;

    @Column(name = "cantidad")
    private int cantidad;

    @Column(name = "precio")
    private double precio;
}
