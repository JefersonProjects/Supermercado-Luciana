package com.proyecto.ed.ProyectoED.Services;

import com.proyecto.ed.ProyectoED.Dao.IProductos;
import com.proyecto.ed.ProyectoED.Models.Producto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoService {
    @Autowired
    IProductos iProductos;


    public List<Producto> listarProductos(){
        return iProductos.listarProductos();
    }

    public Producto listarPorId(Long id){
        return iProductos.listarPorId(id);
    }
    public List<Producto> listarPorCategoria(Long categoriaId){
        return iProductos.listarPorCategoria(categoriaId);
    }

    public void guardarProducto(Producto producto){
        iProductos.guardarProducto(producto);
    }
    public void actualizarProducto(Producto producto){
        iProductos.actualizarProducto(producto);
    }
    public void eliminarProducto(Long id){
        iProductos.eliminarProducto(id);
    }
}
