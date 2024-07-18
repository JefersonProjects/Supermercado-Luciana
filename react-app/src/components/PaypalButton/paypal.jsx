import React, { useState } from 'react';
import ReactDOM from 'react-dom';
import '../../assets/css/paypalButton.css';
import { useLocation, useNavigate } from 'react-router-dom';

const PayPalButtonComponent = window.paypal.Buttons.driver("react", { React, ReactDOM });

const PaypalButton = ({ setCarrito }) => {
    const location = useLocation();
    const navigate = useNavigate();
    const queryParams = new URLSearchParams(location.search);
    const price = queryParams.get('total');
    const [showModal, setShowModal] = useState(false);

    const createOrder = (data, actions) => {
        return actions.order.create({
            purchase_units: [{
                amount: {
                    value: price.toString()
                },
            }],
        });
    };

    const onApprove = (data, actions) => {
        return actions.order.capture().then(() => {
            setShowModal(true);
            setTimeout(() => {
                setShowModal(false);
                setCarrito([]); // Limpiar el carrito
                localStorage.removeItem('carrito');
                navigate('/carrito'); // Redirigir al carrito
            }, 3000);
        });
    };

    return (
        <div className="paypal-button-container">
            <div className="paypal-button-overlay">
                <h1>MONTO TOTAL: S/. {price}</h1>
                <div className="paypal-buttons">
                    <PayPalButtonComponent
                        createOrder={(data, actions) => createOrder(data, actions)}
                        onApprove={(data, actions) => onApprove(data, actions)}
                    />
                </div>
                <h2>NOTA : Al pagar su boleta se descargara automaticamente</h2>
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


