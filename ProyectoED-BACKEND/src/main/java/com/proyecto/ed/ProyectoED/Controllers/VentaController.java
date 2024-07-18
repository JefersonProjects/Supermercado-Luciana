package com.proyecto.ed.ProyectoED.Controllers;

import com.proyecto.ed.ProyectoED.Dao.IVenta;
import com.proyecto.ed.ProyectoED.Models.Venta;
import com.proyecto.ed.ProyectoED.Services.CompraService;
import com.proyecto.ed.ProyectoED.Services.VentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@CrossOrigin(origins = "http://localhost:5173/")
@RequestMapping("/api/ventas")
public class VentaController {

    @Autowired
     private  VentaService ventaService;

    @PostMapping
    public void saveVenta(@RequestBody Venta venta) {
        ventaService.saveVenta(venta);
    }

    @GetMapping
    public List<Venta> getAllVentas() {
        return ventaService.findAllVentas();
    }

    @GetMapping("/{id}")
    public Venta getVentaById(@PathVariable Long id) {
        return ventaService.findVentaById(id);
    }
}
