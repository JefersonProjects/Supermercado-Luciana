package com.proyecto.ed.ProyectoED.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Boleta {
    private Long id;
    private byte[] boleta;
    private Long codigoCompra;
}
