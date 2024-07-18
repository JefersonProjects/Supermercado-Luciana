package com.proyecto.ed.ProyectoED.Dao;

import com.proyecto.ed.ProyectoED.Models.Venta;

import java.util.List;

public interface IVenta {int save(Venta venta);
    List<Venta> findAll();
    Venta findById(Long id);
}
