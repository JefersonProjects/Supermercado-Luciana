package com.proyecto.ed.ProyectoED.Controllers;

import com.proyecto.ed.ProyectoED.Models.Administrador;
import com.proyecto.ed.ProyectoED.Models.Cliente;
import com.proyecto.ed.ProyectoED.Services.AdministradorService;
import jakarta.security.auth.message.ClientAuth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:5173/")
@RestController
@RequestMapping("/api/administradores")
public class AdministradorController {
    @Autowired
    private AdministradorService administradorService;


    @GetMapping
    public List<Administrador> listarAdministrador() {
        return administradorService.listarAdministrador();
    }

    @GetMapping("listarPorId/{id}")
    public List<Administrador> listarPorId(@PathVariable Long id) {
        return administradorService.listarPorId(id);
    }

    @PostMapping("/registrar")
    public Administrador registrarAdministrador(@RequestBody Administrador administrador) {
        return administradorService.registrarAdministrador(administrador);
    }

    @PutMapping("/update/{id}")
    public Administrador modificarCliente(@PathVariable Long id, @RequestBody Administrador administrador) {
        administrador.setId(id);
        return administradorService.modificarAdministrador(administrador);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> eliminarAdministrador(@PathVariable Long id) {
        boolean eliminado = administradorService.eliminarAdministrador(id);
        if (eliminado) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
