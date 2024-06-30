package com.proyecto.ed.ProyectoED.Dao;

import com.proyecto.ed.ProyectoED.Models.Administrador;
import com.proyecto.ed.ProyectoED.Models.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class AdministradroImpl implements IAdministrador{
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Override
    public List<Administrador> listarAdministrador() {
        String SQL = "SELECT * FROM Administrador";
        return jdbcTemplate.query(SQL, (rs, rowNum) -> new Administrador(
                rs.getLong("id"),
                rs.getString("nombre"),
                rs.getString("apellido"),
                rs.getString("dni"),
                rs.getString("telefono"),
                rs.getString("email"),
                rs.getString("password")
        ));
    }

    @Override
    public List<Administrador> listarPorId(Long id) {
        String sql = "SELECT * FROM Cliente WHERE id = ?";
        return jdbcTemplate.query(sql, new Object[]{id}, (rs, rowNum) -> new Administrador(
                rs.getLong("id"),
                rs.getString("nombre"),
                rs.getString("apellido"),
                rs.getString("dni"),
                rs.getString("telefono"),
                rs.getString("email"),
                rs.getString("password")
        ));
    }

    @Override
    public Administrador registrarAdministrador(Administrador administrador) {
        String sql = "INSERT INTO Administrador (nombre, apellido, dni, telefono," +
                " email, password) VALUES (?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, administrador.getNombre(), administrador.getApellido(),
                administrador.getDni(), administrador.getTelefono(), administrador.getEmail(),
                administrador.getPassword());
        return administrador;
    }

    @Override
    public Administrador modificarAdministrador(Administrador administrador) {
        String sql = "UPDATE Cliente SET nombre = ?, apellido = ?, dni = ?," +
                " telefono = ?, email = ?, password = ? WHERE id = ?";
        jdbcTemplate.update(sql, administrador.getNombre(), administrador.getApellido(),
                administrador.getDni(), administrador.getTelefono(), administrador.getEmail(),
                administrador.getPassword(), administrador.getId());

        return administrador;
    }

    @Override
    public boolean eliminarAdministrador(Long id) {
        String sql = "DELETE FROM Administrador WHERE id = ?";
        int resultado = jdbcTemplate.update(sql, id);
        return resultado > 0;    }
}
