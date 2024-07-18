package com.proyecto.ed.ProyectoED.Dao;

import com.proyecto.ed.ProyectoED.Models.Compra;

import java.util.List;

public interface ICompra {
    public int compra(Compra compra);
    public byte[] obtenerBoletaPDF(Long idCompra);
    public List<Compra> findAll();

    public List<Compra> findByClienteId(Long idCliente);
}
