package com.proyecto.ed.ProyectoED.Services;

import com.proyecto.ed.ProyectoED.Dao.IProductos;
import com.proyecto.ed.ProyectoED.Models.Producto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

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
    public List<Producto> listarPorCategoria(String categoriaNombre){
        return iProductos.listarPorCategoria(categoriaNombre);
    }

    public List<Producto> buscarPorNombre(@PathVariable String nombre) {
        return iProductos.buscarPorNombre(nombre);
    }

    public List<Producto> filtrarPorPrecio(@RequestParam Double min, @RequestParam Double max) {
        return iProductos.filtrarPorPrecio(min, max);
    }


    public List<Producto> ordenarProductos(String criterio) {
        return iProductos.ordenarProductos(criterio);
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
