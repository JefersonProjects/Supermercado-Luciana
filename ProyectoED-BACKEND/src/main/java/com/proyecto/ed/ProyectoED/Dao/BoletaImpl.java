package com.proyecto.ed.ProyectoED.Dao;

import com.proyecto.ed.ProyectoED.Models.Boleta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
@Repository
public class BoletaImpl implements IBoleta {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Override
    public int save(Boleta boleta) {
        return jdbcTemplate.update(
                "INSERT INTO Boletas (boleta, codigo_compra) VALUES (?, ?)",
                boleta.getBoleta(), boleta.getCodigoCompra()
        );
    }

    @Override
    public List<Boleta> findAll() {
        return jdbcTemplate.query("SELECT * FROM Boletas", new BoletaRowMapper());
    }

    @Override
    public Boleta findById(Long id) {
        return jdbcTemplate.queryForObject(
                "SELECT * FROM Boletas WHERE id = ?",
                new BoletaRowMapper(),
                id
        );
    }

    private static final class BoletaRowMapper implements RowMapper<Boleta> {
        @Override
        public Boleta mapRow(ResultSet rs, int rowNum) throws SQLException {
            Boleta boleta = new Boleta();
            boleta.setId(rs.getLong("id"));
            boleta.setBoleta(rs.getBytes("boleta"));
            boleta.setCodigoCompra(rs.getLong("codigo_compra"));
            return boleta;
        }
    }
}
