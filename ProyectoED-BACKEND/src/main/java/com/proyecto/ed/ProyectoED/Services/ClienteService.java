package com.proyecto.ed.ProyectoED.Services;

import com.proyecto.ed.ProyectoED.Dao.ICliente;
import com.proyecto.ed.ProyectoED.Models.Cliente;
import com.proyecto.ed.ProyectoED.Models.Role;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class ClienteService {

    @Autowired
    private ICliente clienteRepository;

    public List<Cliente> listarClientes(){
        return clienteRepository.listarClientes();
    }


    public List<Cliente> listarPorDni(String dni) {
        return clienteRepository.listarPorDni(dni);
    }

    public Cliente registrarCliente(Cliente cliente){
        //Encriptamos la contraseña//
        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
        String hash = argon2.hash(1, 1024, 1, cliente.getPassword());
        cliente.setPassword(hash);
        cliente.setRole(Role.CLIENT);
        return clienteRepository.registrarCliente(cliente);
    }

    public Cliente modificarCliente(Cliente cliente) {
        //Encriptamos la contraseña//
        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
        String hash = argon2.hash(1, 1024, 1, cliente.getPassword());
        cliente.setPassword(hash);
        return clienteRepository.modificarCliente(cliente);
    }

    public boolean eliminarCliente(Long id) {
        return clienteRepository.eliminarCliente(id);
    }




}
