package com.proyecto.ed.ProyectoED.Controllers;
import com.proyecto.ed.ProyectoED.Services.ClienteService;
import org.springframework.web.bind.annotation.CrossOrigin;
import com.proyecto.ed.ProyectoED.Models.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin(origins = "http://localhost:5173/")
@RestController
@RequestMapping("/api/clientes")

public class ClienteController {


    @Autowired
    private ClienteService serviceCliente;

    @GetMapping
    public List<Cliente> listarClientes() {
        return serviceCliente.listarClientes();
    }

    @GetMapping("/dni/{dni}")
    public List<Cliente> listarPorDni(@PathVariable String dni) {
        return serviceCliente.listarPorDni(dni);
    }

    @PostMapping("/registro")
    public Cliente registrarCliente(@RequestBody Cliente cliente) {
        return serviceCliente.registrarCliente(cliente);
    }

    @PutMapping("/{id}")
    public Cliente modificarCliente(@PathVariable Long id, @RequestBody Cliente cliente) {
        cliente.setId(id);
        return serviceCliente.modificarCliente(cliente);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCliente(@PathVariable Long id) {
        boolean eliminado = serviceCliente.eliminarCliente(id);
        if (eliminado) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}


