package com.proyecto.ed.ProyectoED.Dao;

import com.proyecto.ed.ProyectoED.Models.Compra;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.util.List;

@Repository
public class CompraImpl implements ICompra{
    @Autowired
    private JdbcTemplate jdbcTemplate;


    @Override
    public int compra(Compra compra) {

        if (compra.getIdCliente() == null) {
            throw new IllegalArgumentException("El id del cliente no puede ser nulo");
        }

        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(
                    "INSERT INTO Compras (fecha, codigo_boleta, id_cliente) VALUES (?, ?, ?)",
                    new String[] {"id"}
            );
            ps.setTimestamp(1, new Timestamp(compra.getFecha().getTime()));
            ps.setString(2, compra.getCodigoBoleta());
            ps.setLong(3, compra.getIdCliente());
            return ps;
        }, keyHolder);
        return keyHolder.getKey().intValue();
    }

    @Override
    public byte[] obtenerBoletaPDF(Long idCompra) {
        return jdbcTemplate.queryForObject(
                "SELECT boleta_pdf FROM Compras WHERE id = ?",
                new Object[]{idCompra},
                byte[].class
        );
    }

    @Override
    public List<Compra> findAll() {
        return jdbcTemplate.query(
                "SELECT * FROM Compras",
                (rs, rowNum) -> new Compra(
                        rs.getLong("id"),
                        rs.getTimestamp("fecha"),
                        rs.getString("codigo_boleta"),
                        rs.getLong("id_cliente")
                )
        );
    }

    @Override
    public List<Compra> findByClienteId(Long idCliente) {
        return jdbcTemplate.query(
                "SELECT * FROM Compras WHERE id_cliente = ?",
                new Object[] { idCliente },
                (rs, rowNum) -> new Compra(
                        rs.getLong("id"),
                        rs.getTimestamp("fecha"),
                        rs.getString("codigo_boleta"),
                        rs.getLong("id_cliente")
                )
        );
    }
}
