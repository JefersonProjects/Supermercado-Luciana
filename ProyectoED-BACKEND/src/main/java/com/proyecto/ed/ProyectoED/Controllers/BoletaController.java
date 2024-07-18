package com.proyecto.ed.ProyectoED.Controllers;

import com.proyecto.ed.ProyectoED.Models.Boleta;
import com.proyecto.ed.ProyectoED.Services.BoletaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:5173/")
@RestController
@RequestMapping("/api/boletas")
public class BoletaController {

    @Autowired
    private BoletaService boletaService;

    @GetMapping("/{codigoCompra}")
    public ResponseEntity<byte[]> obtenerBoletaPorCodigoCompra(@PathVariable Long codigoCompra) {
        byte[] boletaPDF = boletaService.obtenerBoletaPorCodigoCompra(codigoCompra);
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_PDF)
                .body(boletaPDF);
    }
    @Autowired
    public BoletaController(BoletaService boletaService) {
        this.boletaService = boletaService;
    }

    @PostMapping
    public void saveBoleta(@RequestBody Boleta boleta) {
        boletaService.saveBoleta(boleta);
    }

    @GetMapping
    public List<Boleta> getAllBoletas() {
        return boletaService.findAllBoletas();
    }

    @GetMapping("/porId/{id}")
    public Boleta getBoletaById(@PathVariable Long id) {
        return boletaService.findBoletaById(id);
    }
}
