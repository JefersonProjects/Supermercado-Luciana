import React from 'react';
import { Link } from "react-router-dom";
import LogoPasillos from "../assets/images/Logos/pasillos.png";
import "../assets/css/inicioSesion.css";

const InicioSesion = () =>{
    const handleLinkClick = () => {
        //  aqui ponemso la la logica para operaciones futuras si es necesario
        
    };

    return(
        <main className="main-Logins">
        <div className="contenedor">
        <div className="imagen-supermercado">
            <img src={LogoPasillos} alt="Supermercado Luciana"/>
        </div>
        <div className="formulario-login">
            <h1>INICIO SESIÓN</h1>
            <form id="formularioInicioSesion">
                <label for="usuario">Correo</label>
                <input type="text" id="usuario" name="usuario" required />
                <label for="contrasena">Contraseña:</label>
                <input type="password" id="contrasena" name="contrasena" required />
                <button className="button-login" type="submit">INICIAR SESIÓN</button>
                <h2>No tienes una cuenta? <Link to="/registro" onClick={handleLinkClick}>Crea una ahora</Link></h2>
                <br />
                <h2><Link to="/recuperacionPassword"  onClick={handleLinkClick}>¿Olvidaste tu contraseña?</Link></h2>
            </form>
        </div>
    </div>
    </main>
    );
};

export default InicioSesion;