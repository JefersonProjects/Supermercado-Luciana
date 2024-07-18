package com.proyecto.ed.ProyectoED.Services;

import com.proyecto.ed.ProyectoED.Dao.IVenta;
import com.proyecto.ed.ProyectoED.Models.Venta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VentaService {
    @Autowired
    private IVenta ventaRepository;

    public void saveVenta(Venta venta) {
        ventaRepository.save(venta);
    }

    public List<Venta> findAllVentas() {
        return ventaRepository.findAll();
    }

    public Venta findVentaById(Long id) {
        return ventaRepository.findById(id);
    }
}
