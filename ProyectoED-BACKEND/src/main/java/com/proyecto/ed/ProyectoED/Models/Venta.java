package com.proyecto.ed.ProyectoED.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Venta {
    private Long id;
    private Date fecha;
    private String codigoBoleta;
    private Long codigoCompra;
}
