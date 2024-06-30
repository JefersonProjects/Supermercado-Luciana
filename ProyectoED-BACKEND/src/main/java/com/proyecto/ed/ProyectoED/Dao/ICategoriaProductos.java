package com.proyecto.ed.ProyectoED.Dao;

import com.proyecto.ed.ProyectoED.Models.CategoriaProducto;
import com.proyecto.ed.ProyectoED.Models.Producto;

import java.util.List;

public interface ICategoriaProductos {
    List<CategoriaProducto> listarCategorias();

    CategoriaProducto listarPorId(Long id);

    void guardarCategoria(CategoriaProducto categoriaProducto);
    void actualizarCategoria(CategoriaProducto categoriaProducto);
    void eliminarCategoria(Long id);
}
