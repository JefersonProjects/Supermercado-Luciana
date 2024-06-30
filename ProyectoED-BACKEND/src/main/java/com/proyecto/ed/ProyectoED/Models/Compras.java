package com.proyecto.ed.ProyectoED.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class Compras {


    private Long id;


    private Long clienteId;


    private LocalDateTime fecha;

    private Double total;

}
