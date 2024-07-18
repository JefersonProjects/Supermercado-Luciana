import React, { useState } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import ReCAPTCHA from 'react-google-recaptcha';
import LogoPasillos from '../assets/images/Logos/pasillos.png';
import '../assets/css/inicioSesion.css';
import AuthService from '../services/authService';

const InicioSesion = ({ onLogin }) => {
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const [mensaje, setMensaje] = useState('');
    const [captchaToken, setCaptchaToken] = useState(null);
    const navigate = useNavigate();

    const handleEmailChange = (e) => setEmail(e.target.value);
    const handlePasswordChange = (e) => setPassword(e.target.value);
    const handleCaptchaChange = (token) => setCaptchaToken(token);

    const handleSubmit = async (e) => {
        e.preventDefault();
        if (!captchaToken) {
            setMensaje('Por favor, completa el captcha.');
            return;
        }
        try {
            const response = await AuthService.login({ email, password, captchaToken });
            const { token, role, nombre, apellido, dni, telefono, email: userEmail } = response.data; // Cambiado para evitar conflicto de nombres
            const user = { nombre, apellido, dni, telefono, email: userEmail };
            onLogin(token, role, user);
            setMensaje('Inicio de sesión exitoso');
            navigate('/');
        } catch (error) {
            if (error.response && error.response.status === 401) {
                setMensaje('Correo o contraseña incorrectas. Verifique sus credenciales.');
            } else {
                setMensaje('Error inesperado. Inténtelo de nuevo más tarde.');
            }
        }
    };

    const handleLinkClick = () => {
        setMensaje('');
    };

    return (
        <main className="main-Logins">
            <div className="contenedor">
                <div className="imagen-supermercado">
                    <img src={LogoPasillos} alt="Supermercado Luciana" />
                </div>
                <div className="formulario-login">
                    <h1>INICIO SESIÓN</h1>
                    <form id="formularioInicioSesion" onSubmit={handleSubmit}>
                        <label htmlFor="email">Correo</label>
                        <input type="text" id="email" name="email" value={email} onChange={handleEmailChange} required />
                        <label htmlFor="password">Contraseña:</label>
                        <input type="password" id="password" name="password" value={password} onChange={handlePasswordChange} required />
                        <ReCAPTCHA sitekey="6LeDLAYqAAAAAKkNrnzVc56Bw3p6TW7MRdgJM2Vb" onChange={handleCaptchaChange} />
                        <button className="button-login" type="submit">INICIAR SESIÓN</button>
                        {mensaje && <p>{mensaje}</p>}
                        <h2>No tienes una cuenta? <Link to="/registro" onClick={handleLinkClick}>Crea una ahora</Link></h2>
                        <br />
                        <h2><Link to="/recuperacionPassword" onClick={handleLinkClick}>¿Olvidaste tu contraseña?</Link></h2>
                    </form>
                </div>
            </div>
        </main>
    );
};

export default InicioSesion;


