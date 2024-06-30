package com.proyecto.ed.ProyectoED.Dao;

import com.proyecto.ed.ProyectoED.Models.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ClienteImpl implements ICliente {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Cliente> listarClientes() {
        String SQL = "SELECT * FROM Cliente";
        return jdbcTemplate.query(SQL, (rs, rowNum) -> new Cliente(
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
    public List<Cliente> listarPorDni(String dni) {
        String sql = "SELECT * FROM Cliente WHERE dni = ?";
        return jdbcTemplate.query(sql, new Object[]{dni}, (rs, rowNum) -> new Cliente(
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
    public Cliente registrarCliente(Cliente cliente) {
        String sql = "INSERT INTO Cliente (nombre, apellido, dni, telefono," +
                " email, password) VALUES (?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, cliente.getNombre(), cliente.getApellido(),
                cliente.getDni(), cliente.getTelefono(), cliente.getEmail(),
                cliente.getPassword());
        return cliente;
    }

    @Override
    public Cliente modificarCliente(Cliente cliente) {
        String sql = "UPDATE Cliente SET nombre = ?, apellido = ?, dni = ?," +
                " telefono = ?, email = ?, password = ? WHERE id = ?";
        jdbcTemplate.update(sql, cliente.getNombre(), cliente.getApellido(), cliente.getDni(), cliente.getTelefono(), cliente.getEmail(), cliente.getPassword(), cliente.getId());
        return cliente;
    }

    @Override
    public boolean eliminarCliente(Long id) {
        String sql = "DELETE FROM Cliente WHERE id = ?";
        int resultado = jdbcTemplate.update(sql, id);
        return resultado > 0;
    }

}
