package com.proyecto.ed.ProyectoED.Controllers;
import com.proyecto.ed.ProyectoED.Services.ClienteService;
import org.springframework.web.bind.annotation.CrossOrigin;
import com.proyecto.ed.ProyectoED.Models.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin(origins = "http://localhost:3000/")
@RestController
@RequestMapping("/api/v1")

public class ClienteController {


    @Autowired
    private ClienteService serviceCliente;

    @GetMapping("/1")
    public String comienzo(){
        return "Hola Mundo";
    }

    @GetMapping("/clientes")
    public List<Cliente> listarClientes() {
        return serviceCliente.listarClientes();
    }

    @GetMapping("/clientes/{dni}")
    public List<Cliente> listarPorDni(@PathVariable String dni) {
        return serviceCliente.listarPorDni(dni);
    }

    @PostMapping("/clientes")
    public Cliente registrarCliente(@RequestBody Cliente cliente) {
        return serviceCliente.registrarCliente(cliente);
    }

    @PutMapping("/clientes/{id}")
    public Cliente modificarCliente(@PathVariable Long id, @RequestBody Cliente cliente) {
        cliente.setId(id);
        return serviceCliente.modificarCliente(cliente);
    }

    @DeleteMapping("/clientes/{id}")
    public ResponseEntity<Void> eliminarCliente(@PathVariable Long id) {
        boolean eliminado = serviceCliente.eliminarCliente(id);
        if (eliminado) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}


