package com.proyecto.ed.ProyectoED.Services;

import com.proyecto.ed.ProyectoED.Dao.ICategoriaProductos;
import com.proyecto.ed.ProyectoED.Models.CategoriaProducto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaProductosService {

    @Autowired
    private ICategoriaProductos iCategoriaProductos;

    public List<CategoriaProducto> listarCategorias() {
      return iCategoriaProductos.listarCategorias();
    }

    public CategoriaProducto listarPorId(Long id){
        return iCategoriaProductos.listarPorId(id);
    }
    public void guardarCategoria(CategoriaProducto categoriaProducto) {
        iCategoriaProductos.guardarCategoria(categoriaProducto);
    }

    public void actualizarCategoria(CategoriaProducto categoriaProducto) {
        iCategoriaProductos.actualizarCategoria(categoriaProducto);
    }

    public void eliminarCategoria(Long id) {
        iCategoriaProductos.eliminarCategoria(id);
    }

}
