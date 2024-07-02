package com.proyecto.ed.ProyectoED.Dao;

import com.proyecto.ed.ProyectoED.Models.Cliente;

import java.util.List;

public interface ICliente {
    List<Cliente> listarClientes();
    List<Cliente> listarPorDni(String dni);

    Cliente registrarCliente(Cliente cliente);

    Cliente modificarCliente(Cliente cliente);

    boolean eliminarCliente(Long id);

    Cliente findByEmail(String email);
}
