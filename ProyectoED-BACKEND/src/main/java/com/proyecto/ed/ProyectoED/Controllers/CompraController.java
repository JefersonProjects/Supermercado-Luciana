package com.proyecto.ed.ProyectoED.Controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.proyecto.ed.ProyectoED.Models.Compra;
import com.proyecto.ed.ProyectoED.Services.CompraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:5173/")
@RequestMapping("/api/compras")
public class CompraController {

    @Autowired
    private CompraService compraService;


    @PostMapping("/guardar")
    public ResponseEntity<Long> guardarCompra(
            @RequestParam("compra") String compraJson,
            @RequestParam("boletaPDF") MultipartFile boletaPDF) throws IOException {

        Compra compra = new ObjectMapper().readValue(compraJson, Compra.class);
        byte[] boletaPDFBytes = boletaPDF.getBytes();

        long compraId = compraService.realizarCompra(compra, boletaPDFBytes);
        return ResponseEntity.ok(compraId);
    }

    @GetMapping
    public List<Compra> getAllCompras() {
        return compraService.findAllCompras();
    }

    @GetMapping("/cliente/{idCliente}")
    public List<Compra> getComprasByClienteId(@PathVariable Long idCliente) {
        return compraService.findComprasByClienteId(idCliente);
    }

}
