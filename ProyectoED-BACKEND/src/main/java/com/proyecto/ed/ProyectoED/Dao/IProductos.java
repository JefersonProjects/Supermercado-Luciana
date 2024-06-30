package com.proyecto.ed.ProyectoED.Dao;

import com.proyecto.ed.ProyectoED.Models.Producto;

import java.util.List;

public interface IProductos {
    List<Producto> listarProductos();
    Producto listarPorId(Long id);
    List<Producto> listarPorCategoria(String categoriaNombre);

    List <Producto> buscarPorNombre(String nombre);

    List <Producto> filtrarPorPrecio(Double min,Double max);
    public List<Producto> ordenarProductos(String criterio);

    void guardarProducto(Producto producto);
    void actualizarProducto(Producto producto);
    void eliminarProducto(Long id);

}
