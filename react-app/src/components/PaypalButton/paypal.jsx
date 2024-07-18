import React, { useState } from 'react';
import ReactDOM from 'react-dom';
import CompraService from '../../services/compraService';
import jsPDF from 'jspdf';
import 'jspdf-autotable';
import { saveAs } from 'file-saver';
import '../../assets/css/paypalButton.css';
import { useLocation, useNavigate } from 'react-router-dom';

const PayPalButtonComponent = window.paypal.Buttons.driver("react", { React, ReactDOM });

const PaypalButton = ({ setCarrito }) => {
    const location = useLocation();
    const navigate = useNavigate();
    const { formData, carrito, total } = location.state || { formData: {}, carrito: [], total: 0 };
    const [showModal, setShowModal] = useState(false);

    const createOrder = (data, actions) => {
        return actions.order.create({
            purchase_units: [{
                amount: {
                    value: total.toString()
                },
            }],
        });
    };

    const guardarCompra = (boletaPDF, codigoBoleta) => {
        const compra = {
            fecha: new Date(),
            codigoBoleta: codigoBoleta,
            idCliente: formData.idCliente
        };

        CompraService.guardarCompra(compra, boletaPDF)
            .then(() => {
                setShowModal(true);
                setTimeout(() => {
                    setCarrito([]);
                    navigate('/carrito');
                }, 3000);
            })
            .catch(error => {
                console.error("Error al guardar la compra:", error);
            });
    };

    const onApprove = (data, actions) => {
        return actions.order.capture().then(() => {
            const codigoBoleta = `BOL${Date.now()}`;
            const codigoCompra = `COM${Date.now(4)}`;
            generarBoletaPDF(codigoBoleta, codigoCompra).then(boletaPDF => {
                saveAs(boletaPDF, 'boleta.pdf'); // Descargar automáticamente la boleta
                guardarCompra(boletaPDF, codigoBoleta, codigoCompra);
            });
        });
    };

    const generarBoletaPDF = (codigoBoleta, id) => {
        const doc = new jsPDF();
    
        // Título y fecha
        doc.setFontSize(20);
        doc.text('FACTURA - BOLETA DE PAGO', 105, 20, null, null, 'center');
        doc.setFontSize(12);
        doc.text(`CODIGO DE BOLETA ${codigoBoleta} | FECHA: ` + new Date().toLocaleDateString(), 105, 30, null, null, 'center');
    
        // Detalles del cliente
        doc.setFontSize(14);
        doc.text('CLIENTE:', 14, 50);
        doc.setFontSize(12);
        doc.text(`Nombre: ${formData.nombre} ${formData.apellido}`, 14, 60);
        doc.text(`DNI: ${formData.dni}`, 14, 70);
        doc.text(`Correo: ${formData.email}`, 14, 80);
        doc.text(`Teléfono: ${formData.telefono}`, 14, 90);
    
        // Detalles de envío
        doc.setFontSize(14);
        doc.text('ENVÍO:', 105, 50);
        doc.setFontSize(12);
        doc.text(`Método: ${formData.metodoEnvio}`, 105, 60);
        if (formData.metodoEnvio === 'Delivery') {
            const address = doc.splitTextToSize(`Dirección: ${formData.direccion}`, 80);
            doc.text(address, 105, 70);
        }
        doc.text(`ID DE COMPRA: ${id}`, 105, 90);
    
        // Tabla
        doc.autoTable({
            startY: 110,
            head: [['Descripción', 'Cantidad', 'Precio', 'Total']],
            body: carrito.map(item => [
                item.nombre,
                item.stock,
                `S/. ${item.precio.toFixed(2)}`,
                `S/. ${(item.stock * item.precio).toFixed(2)}`
            ]),
            styles: { halign: 'center' },
            headStyles: { fillColor: [100, 180, 255] }, // Azul oscuro
        });
    
        // Total
        const totalAmount = carrito.reduce((sum, item) => sum + item.stock * item.precio, 0);
    
        doc.setFontSize(16);
        doc.setTextColor(255, 100, 100); 
        doc.text(`TOTAL: S/. ${totalAmount.toFixed(2)}`, 14, doc.autoTable.previous.finalY + 20);
    
        return Promise.resolve(doc.output('blob'));
    };

    return (
        <div className="paypal-button-container">
            <div className="paypal-button-overlay">
                <h1>MONTO TOTAL: S/. {total}</h1>
                <div className="paypal-buttons">
                    <PayPalButtonComponent
                        createOrder={(data, actions) => createOrder(data, actions)}
                        onApprove={(data, actions) => onApprove(data, actions)}
                    />
                </div>
                <h2>NOTA : Al pagar su boleta se descargara automáticamente</h2>
                <button className="back-button" onClick={() => window.history.back()}>Volver al carrito de compras</button>
            </div>
            {showModal && (
                <div className="modal">
                    <div className="modal-content">
                        <div className="checkmark">
                            <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 52 52">
                                <circle cx="26" cy="26" r="25" fill="none" />
                                <path fill="none" stroke="#4CAF50" strokeWidth="5" d="M14 27l7 7 16-16" />
                            </svg>
                        </div>
                        <p>Pago exitoso</p>
                    </div>
                </div>
            )}
        </div>
    );
};

export default PaypalButton;


