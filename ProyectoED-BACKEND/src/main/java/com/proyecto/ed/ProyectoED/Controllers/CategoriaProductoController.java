package com.proyecto.ed.ProyectoED.Controllers;

import com.proyecto.ed.ProyectoED.Models.CategoriaProducto;
import com.proyecto.ed.ProyectoED.Models.Producto;
import com.proyecto.ed.ProyectoED.Services.CategoriaProductosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categorias")
public class CategoriaProductoController {

    @Autowired
    private CategoriaProductosService categoriaProductosService;

    @GetMapping
    public List<CategoriaProducto> listarCategoria(){
        return categoriaProductosService.listarCategorias();
    }


    @PostMapping
    public void guardarCategoria(CategoriaProducto categoriaProducto){
        categoriaProductosService.guardarCategoria(categoriaProducto);
    }
    @PutMapping
    public void actualizarCategoria(@RequestBody CategoriaProducto categoriaProducto){
        categoriaProductosService.actualizarCategoria(categoriaProducto);
    }
    @DeleteMapping("/{id}")
    public void eliminarCategoria(@PathVariable Long id){
       categoriaProductosService.eliminarCategoria(id);
    }


}
