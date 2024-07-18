import React, { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import AuthService from '../services/authService';
import "../assets/css/miCuenta.css";

const MiCuenta = ({ handleLogout }) => {
    const navigate = useNavigate();
    const [userData, setUserData] = useState(() => JSON.parse(localStorage.getItem('userData')));

    const handleLogoutClick = () => {
        handleLogout();
        navigate('/');
    };

    const handleUpdateUserData = async () => {
        try {
            const response = await AuthService.getUserData();
            setUserData(response.data);
            localStorage.setItem('userData', JSON.stringify(response.data));
        } catch (error) {
            console.error('Error al actualizar los datos del usuario:', error);
        }
    };

    return (
        <div className="mi-cuenta-container">
            <h1 className="mi-cuenta-title">Mi Cuenta</h1>
            {userData && (
                <>
                    <p className="mi-cuenta-info">Id: {userData.id}</p>
                    <p className="mi-cuenta-info">Nombre: {userData.nombre}</p>
                    <p className="mi-cuenta-info">Apellido: {userData.apellido}</p>
                    <p className="mi-cuenta-info">DNI: {userData.dni}</p>
                    <p className="mi-cuenta-info">Teléfono: {userData.telefono}</p>
                    <p className="mi-cuenta-info">Email: {userData.email}</p>
                </>
            )}
            <div className="mi-cuenta-buttons">
                <button className="mi-cuenta-button" onClick={handleLogoutClick}>
                    Cerrar Sesión
                </button>
                <button className="mi-cuenta-button" onClick={handleUpdateUserData}>
                    Actualizar Datos
                </button>
            </div>
        </div>
    );
};

export default MiCuenta;
