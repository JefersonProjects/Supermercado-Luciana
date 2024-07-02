package com.proyecto.ed.ProyectoED.Dao;

import com.proyecto.ed.ProyectoED.Models.Administrador;
import com.proyecto.ed.ProyectoED.Models.Cliente;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;

import java.util.List;

public interface IAdministrador {

    List<Administrador> listarAdministrador();

    List<Administrador> listarPorId(Long id);

    Administrador registrarAdministrador(Administrador administrador);

    Administrador modificarAdministrador(Administrador administrador);

    boolean eliminarAdministrador(Long id);


    Administrador findByEmail(String email);
}
