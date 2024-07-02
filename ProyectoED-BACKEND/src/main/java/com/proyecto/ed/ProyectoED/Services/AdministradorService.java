package com.proyecto.ed.ProyectoED.Services;

import com.proyecto.ed.ProyectoED.Dao.IAdministrador;
import com.proyecto.ed.ProyectoED.Dao.ICliente;
import com.proyecto.ed.ProyectoED.Models.Administrador;
import com.proyecto.ed.ProyectoED.Models.Cliente;
import com.proyecto.ed.ProyectoED.Models.Role;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class AdministradorService {

    @Autowired
    private IAdministrador administradorRepository;
    public List<Administrador> listarAdministrador(){
        return administradorRepository.listarAdministrador();
    }


    public List<Administrador> listarPorId(Long id) {
        return administradorRepository.listarPorId(id);
    }

    public Administrador registrarAdministrador(Administrador administrador){
        //Encriptamos la contraseña//
        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
        String hash = argon2.hash(1, 1024, 1, administrador.getPassword());
        administrador.setPassword(hash);
        administrador.setRole(Role.ADMIN);
        return administradorRepository.registrarAdministrador(administrador);
    }

    public Administrador modificarAdministrador(Administrador administrador) {
        //Encriptamos la contraseña//
        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
        String hash = argon2.hash(1, 1024, 1, administrador.getPassword());
        administrador.setPassword(hash);
        return administradorRepository.modificarAdministrador(administrador);
    }

    public boolean eliminarAdministrador(Long id) {
        return administradorRepository.eliminarAdministrador(id);
    }
}
