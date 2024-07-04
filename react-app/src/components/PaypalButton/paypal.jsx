import React from 'react';
import ReactDOM from 'react-dom';
import '../../assets/css/paypalButton.css';
import { useLocation } from 'react-router-dom';

const PayPalButtonComponent = window.paypal.Buttons.driver("react", { React, ReactDOM });

const PaypalButton = () => {
    const location = useLocation();
    const queryParams = new URLSearchParams(location.search);
    const price = queryParams.get('total');

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
        return actions.order.capture();
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
                <button className="back-button" onClick={() => window.history.back()}>Volver al carrito de compras</button>
            </div>
        </div>
    );
};

export default PaypalButton;


