package com.proyecto.ed.ProyectoED.Services;

import com.proyecto.ed.ProyectoED.Dao.IBoleta;
import com.proyecto.ed.ProyectoED.Models.Boleta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoletaService {

    @Autowired
    private IBoleta boletaRepository;


    @Autowired
    private JdbcTemplate jdbcTemplate;

    public byte[] obtenerBoletaPorCodigoCompra(Long codigoCompra) {
        return jdbcTemplate.queryForObject(
                "SELECT boleta FROM Boletas WHERE codigo_compra = ?",
                new Object[]{codigoCompra},
                byte[].class
        );

    }

    public void saveBoleta(Boleta boleta) {
        boletaRepository.save(boleta);
    }

    public List<Boleta> findAllBoletas() {
        return boletaRepository.findAll();
    }

    public Boleta findBoletaById(Long id) {
        return boletaRepository.findById(id);
    }
}
