package com.proyecto.ed.ProyectoED.Authentication;

import com.proyecto.ed.ProyectoED.Dao.IAdministrador;
import com.proyecto.ed.ProyectoED.Dao.ICliente;
import com.proyecto.ed.ProyectoED.Models.Administrador;
import com.proyecto.ed.ProyectoED.Models.Cliente;
import com.proyecto.ed.ProyectoED.Models.Role;
import com.proyecto.ed.ProyectoED.Models.Usuario;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    @Autowired
    private final IAdministrador administradorRepository;
    @Autowired
    private final ICliente clienteRepository;


    private final JwtUtil jwtUtil;

    public AuthResponse login(String email, String password) throws Exception {
        Usuario usuario = null;

        try {
            usuario = administradorRepository.findByEmail(email);
        } catch (EmptyResultDataAccessException e) {
            try {
                usuario = clienteRepository.findByEmail(email);
            } catch (EmptyResultDataAccessException ex) {
                throw new Exception("Usuario no encontrado");
            }
        }

        if (usuario == null) {
            throw new Exception("Usuario no encontrado");
        }

        if (!verifyPassword(password, usuario.getPassword())) {
            throw new Exception("Contraseña incorrecta");
        }

        String token = jwtUtil.generateToken(usuario);
        return new AuthResponse(token);
    }

    private boolean verifyPassword(String rawPassword, String hashedPassword) {
        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
        return argon2.verify(hashedPassword, rawPassword);
    }

    public AuthResponse registerCliente(RegisterRequest request) {
        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
        String hash = argon2.hash(1, 1024, 1, request.getPassword());
        Cliente cliente = new Cliente(null, request.getNombre(), request.getApellido(), request.getDni(), request.getTelefono(), request.getEmail(), hash, Role.CLIENT);
        clienteRepository.registrarCliente(cliente);
        String token = jwtUtil.generateToken(cliente);
        return new AuthResponse(token);
    }

    public AuthResponse registerAdministrador(RegisterRequest request) {
        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
        String hash = argon2.hash(1, 1024, 1, request.getPassword());
        Administrador administrador = new Administrador(null, request.getNombre(), request.getApellido(), request.getDni(), request.getTelefono(), request.getEmail(), hash, Role.ADMIN);
        administradorRepository.registrarAdministrador(administrador);
        String token = jwtUtil.generateToken(administrador);
        return new AuthResponse(token);
    }
}