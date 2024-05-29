package com.proyecto.ed.ProyectoED.Dao;

import com.proyecto.ed.ProyectoED.Models.CategoriaProducto;

import java.util.List;

public interface ICategoriaProductos {
    List<CategoriaProducto> listarCategorias();
    void guardarCategoria(CategoriaProducto categoriaProducto);
    void actualizarCategoria(CategoriaProducto categoriaProducto);
    void eliminarCategoria(Long id);
}
