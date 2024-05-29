package com.proyecto.ed.ProyectoED.Dao;

import com.proyecto.ed.ProyectoED.Models.Producto;

import java.util.List;

public interface IProductos {
    List<Producto> listarProductos();
    Producto listarPorId(Long id);
    List<Producto> listarPorCategoria(Long categoriaId);

    void guardarProducto(Producto producto);
    void actualizarProducto(Producto producto);
    void eliminarProducto(Long id);

}
