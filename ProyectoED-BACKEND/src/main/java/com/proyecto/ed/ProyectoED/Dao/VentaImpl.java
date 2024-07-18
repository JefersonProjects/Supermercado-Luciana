package com.proyecto.ed.ProyectoED.Dao;

import com.proyecto.ed.ProyectoED.Models.Venta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

@Repository
public class VentaImpl implements IVenta{

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int save(Venta venta) {
        return jdbcTemplate.update(
                "INSERT INTO Ventas (fecha, codigo_boleta, codigo_compra) VALUES (?, ?, ?)",
                new Timestamp(venta.getFecha().getTime()), venta.getCodigoBoleta(), venta.getCodigoCompra()
        );
    }

    @Override
    public List<Venta> findAll() {
        return jdbcTemplate.query("SELECT * FROM Ventas", new VentaRowMapper());
    }

    @Override
    public Venta findById(Long id) {
        return jdbcTemplate.queryForObject(
                "SELECT * FROM Ventas WHERE id = ?",
                new VentaRowMapper(),
                id
        );
    }

    private static final class VentaRowMapper implements RowMapper<Venta> {
        @Override
        public Venta mapRow(ResultSet rs, int rowNum) throws SQLException {
            Venta venta = new Venta();
            venta.setId(rs.getLong("id"));
            venta.setFecha(rs.getTimestamp("fecha"));
            venta.setCodigoBoleta(rs.getString("codigo_boleta"));
            venta.setCodigoCompra(rs.getLong("codigo_compra"));
            return venta;
        }
    }
}
