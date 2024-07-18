import React, { useEffect, useState } from 'react';
import VentaService from '../../services/ventaService.js';
import { Link, useNavigate } from 'react-router-dom';
import '../../assets/css/tablaClientes.css';

const ListaVentas = () => {
    const [venta, setVenta] = useState([]);
    const [noVentaEncontrada, setNoVentaEncontrada] = useState(false);

    useEffect(() => {
        listarVentas();
    }, []);

    const listarVentas = () => {
        VentaService.getAllVentas().then(response => {
            setVenta(response.data);
            setNoVentaEncontrada(false);
        }).catch(error => {
            console.log(error);
        });
    };

    const formatearFecha = (fechaString) => {
        const fecha = new Date(fechaString);
        return fecha.toLocaleDateString('es-ES', {
            day: '2-digit',
            month: '2-digit',
            year: 'numeric',
        }) + ' ' + fecha.toLocaleTimeString('es-ES', {
            hour: '2-digit',
            minute: '2-digit',
        });
    };

    const verDetalle = (codigoCompra) => {
        VentaService.obtenerBoletaPDF(codigoCompra).then(response => {
            const file = new Blob([response.data], { type: 'application/pdf' });
            const fileURL = URL.createObjectURL(file);
            window.open(fileURL);
        }).catch(error => {
            console.error("Error al obtener el PDF:", error);
        });
    };
    
    
    return (
        <div className="container">
            <h2 className="text-center">Lista De Ventas</h2>
            <div className="search-bar">
                <input
                    type="text"
                    placeholder="Busca por Id o Codigo de Boleta ..."
                    className="search-input"
                />
                <button className="search-button">
                    <i className="fa-solid fa-magnifying-glass"></i>
                </button>
            </div>
            <table className="table">
                <thead>
                    <tr>
                        <th>Id</th>
                        <th>Fecha</th>
                        <th>Codigo De Boleta</th>
                        <th>ID/Numero De Compra</th>
                        <th>Acciones</th>
                    </tr>
                </thead>
                <tbody>
                    {venta.length > 0 ? (
                        venta.map((venta) => (
                            <tr key={venta.id}>
                                <td data-cell="Id">{venta.id}</td>
                                <td data-cell="Fecha">{formatearFecha(venta.fecha)}</td>
                                <td data-cell="Codigo de Boleta">{venta.codigoBoleta}</td>
                                <td data-cell="Codigo de Compra">{venta.codigoCompra}</td>
                                <td data-cell="Acciones" className="actions">
                                    <button className="btn btn-info" onClick={() => verDetalle(venta.codigoCompra)}>Ver Detalle</button>
                                </td>
                            </tr>
                        ))
                    ) : (
                        <tr>
                            <td colSpan="6" className="text-center">No se encuentra Ventas</td>
                        </tr>
                    )}
                </tbody>
            </table>
        </div>
    );
};

export default ListaVentas;