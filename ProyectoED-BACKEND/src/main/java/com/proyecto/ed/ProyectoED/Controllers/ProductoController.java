package com.proyecto.ed.ProyectoED.Controllers;

import com.proyecto.ed.ProyectoED.Models.Producto;
import com.proyecto.ed.ProyectoED.Services.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/productos")
public class ProductoController {
    @Autowired
    private ProductoService productoService;

    @GetMapping
    List<Producto> listarProductos(){
        return productoService.listarProductos();
    }

    @GetMapping("/id/{id}")
    Producto listarPorId(@PathVariable  Long id){
        return productoService.listarPorId(id);
    }

    @GetMapping("/categoria/{nombre}")
    List<Producto> listarPorCategoria(@PathVariable String nombre){ return productoService.listarPorCategoria(nombre); }

    @GetMapping("/buscar/{nombre}")
    public List<Producto> buscarPorNombre(@PathVariable String nombre) { return productoService.buscarPorNombre(nombre); }

    @GetMapping("/precio")
    public List<Producto> filtrarPorPrecio(@RequestParam Double min, @RequestParam Double max) { return productoService.filtrarPorPrecio(min, max);}

    @GetMapping("/ordenar/{criterio}")
    public List<Producto> ordenarProductos(@PathVariable String criterio) { return productoService.ordenarProductos(criterio);}

    @PostMapping
    public ResponseEntity<String> guardarProducto(@RequestParam("nombre") String nombre,
                                                  @RequestParam("categoriaId") Long categoriaId,
                                                  @RequestParam("precio") Double precio,
                                                  @RequestParam("descripcion") String descripcion,
                                                  @RequestParam("stock") Integer stock,
                                                  @RequestParam(value = "imagen", required = false) MultipartFile imagen) {
        Producto producto = new Producto();
        producto.setNombre(nombre);
        producto.setCategoriaId(categoriaId);
        producto.setPrecio(precio);
        producto.setDescripcion(descripcion);
        producto.setStock(stock);

        if (imagen != null && !imagen.isEmpty()) {
            try {
                // Convertir la imagen a un array de bytes
                byte[] imagenBytes = imagen.getBytes();
                // Guardar los bytes de la imagen en el producto
                producto.setImagen(imagenBytes);
            } catch (IOException e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body("Error al guardar la imagen");
            }
        }

        productoService.guardarProducto(producto);
        return ResponseEntity.status(HttpStatus.CREATED).body("Producto guardado correctamente");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> actualizarProducto(@PathVariable Long id,
                                                     @RequestParam("nombre") String nombre,
                                                     @RequestParam("descripcion") String descripcion,
                                                     @RequestParam("precio") Double precio,
                                                     @RequestParam("stock") Integer stock,
                                                     @RequestParam("categoriaId") Long categoriaId,
                                                     @RequestParam(value = "imagen", required = false) MultipartFile imagen) {
        Producto productoExistente = productoService.listarPorId(id);
        if (productoExistente == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Producto no encontrado");
        }
        productoExistente.setNombre(nombre);
        productoExistente.setDescripcion(descripcion);
        productoExistente.setPrecio(precio);
        productoExistente.setStock(stock);
        productoExistente.setCategoriaId(categoriaId);

        if (imagen != null && !imagen.isEmpty()) {
            try {
                // Convertir la imagen a un array de bytes
                byte[] imagenBytes = imagen.getBytes();
                // Guardar los bytes de la imagen en el producto
                productoExistente.setImagen(imagenBytes);
            } catch (IOException e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body("Error al guardar la imagen");
            }
        }

        productoService.actualizarProducto(productoExistente);
        return ResponseEntity.status(HttpStatus.OK).body("Producto actualizado correctamente");
    }
    @DeleteMapping("/{id}")
    void eliminarProducto(@PathVariable Long id){
        productoService.eliminarProducto(id);
    }
}
