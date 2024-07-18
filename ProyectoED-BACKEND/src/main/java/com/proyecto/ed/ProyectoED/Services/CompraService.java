package com.proyecto.ed.ProyectoED.Services;

import com.proyecto.ed.ProyectoED.Dao.IBoleta;
import com.proyecto.ed.ProyectoED.Dao.ICompra;
import com.proyecto.ed.ProyectoED.Dao.IVenta;
import com.proyecto.ed.ProyectoED.Models.Boleta;
import com.proyecto.ed.ProyectoED.Models.Compra;
import com.proyecto.ed.ProyectoED.Models.Producto;
import com.proyecto.ed.ProyectoED.Models.Venta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class CompraService {


    @Autowired
    private ICompra compraRepository;

    @Autowired
    private IBoleta boletaRepository;

    @Autowired
    private IVenta ventaRepository;


    @Transactional
    public long realizarCompra(Compra compra, byte[] boletaPDF) {
        // Insertar compra y obtener el ID generado
        long compraId = compraRepository.compra(compra);

        // Insertar venta
        Venta venta = new Venta();
        venta.setFecha(compra.getFecha());
        venta.setCodigoBoleta(compra.getCodigoBoleta());
        venta.setCodigoCompra(compraId); // Usa el ID generado de la compra
        ventaRepository.save(venta);

        // Insertar boleta
        Boleta boleta = new Boleta();
        boleta.setBoleta(boletaPDF);
        boleta.setCodigoCompra(compraId); // Usa el ID generado de la compra
        boletaRepository.save(boleta);

        // Devolver el ID de la compra
        return compraId;
    }
    public byte[] obtenerBoletaPDF(Long idCompra) {
        return compraRepository.obtenerBoletaPDF(idCompra);
    }
    public List<Compra> findAllCompras() {
        return compraRepository.findAll();
    }

    public List<Compra> findComprasByClienteId(Long idCliente) {
        return compraRepository.findByClienteId(idCliente);
    }
}
