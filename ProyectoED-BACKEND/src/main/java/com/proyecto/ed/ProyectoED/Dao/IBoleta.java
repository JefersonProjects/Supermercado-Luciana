package com.proyecto.ed.ProyectoED.Dao;

import com.proyecto.ed.ProyectoED.Models.Boleta;

import java.util.List;

public interface IBoleta {
    int save(Boleta boleta);
    List<Boleta> findAll();
    Boleta findById(Long id);
}
