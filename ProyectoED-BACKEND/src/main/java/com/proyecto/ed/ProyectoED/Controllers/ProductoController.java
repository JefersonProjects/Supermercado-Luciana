package com.proyecto.ed.ProyectoED.Controllers;

import com.proyecto.ed.ProyectoED.Models.Producto;
import com.proyecto.ed.ProyectoED.Services.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {
    @Autowired
    private ProductoService productoService;

    @GetMapping
    List<Producto> listarProductos(){
        return productoService.listarProductos();
    }

    @GetMapping("/{id}")
    Producto listarPorId(Long id){
        return productoService.listarPorId(id);
    }
    @GetMapping("/categoria/{categoriaId}")
    List<Producto> listarPorCategoria(Long categoriaId){
        return productoService.listarPorCategoria(categoriaId);
    }

    @PostMapping
    void guardarProducto(Producto producto){
        productoService.guardarProducto(producto);
    }

    @PutMapping
    void actualizarProducto(@RequestBody Producto producto){
        productoService.actualizarProducto(producto);
    }
    @DeleteMapping("/{id}")
    void eliminarProducto(@PathVariable Long id){
        productoService.eliminarProducto(id);
    }
}
