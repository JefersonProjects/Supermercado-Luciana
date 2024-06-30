package com.proyecto.ed.ProyectoED.Controllers;

import com.proyecto.ed.ProyectoED.Models.CategoriaProducto;
import com.proyecto.ed.ProyectoED.Models.Producto;
import com.proyecto.ed.ProyectoED.Services.CategoriaProductosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "http://localhost:5173/")
@RestController
@RequestMapping("/api/categorias")
public class CategoriaProductoController {

    @Autowired
    private CategoriaProductosService categoriaProductosService;

    @GetMapping
    public List<CategoriaProducto> listarCategoria(){
        return categoriaProductosService.listarCategorias();
    }


    @GetMapping("/id/{id}")
    public CategoriaProducto listarPorId(@PathVariable Long  id){

        return categoriaProductosService.listarPorId(id);
    }


    @PostMapping
    public void guardarCategoria(@RequestBody  CategoriaProducto categoriaProducto){
        categoriaProductosService.guardarCategoria(categoriaProducto);
    }
    @PutMapping("/update/{id}")
    public void actualizarCategoria( @PathVariable Long id,@RequestBody CategoriaProducto categoriaProducto){
        categoriaProducto.setId(id);
        categoriaProductosService.actualizarCategoria(categoriaProducto);
    }
    @DeleteMapping("/{id}")
    public void eliminarCategoria(@PathVariable Long id){
       categoriaProductosService.eliminarCategoria(id);
    }


}
