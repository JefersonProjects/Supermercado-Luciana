import React from 'react';
import { Link } from 'react-router-dom'; 
import LogoPasillos from "../assets/images/Logos/pasillos.png";
import "../assets/css/inicioSesion.css";

const Registro =()=>{

    const handleLinkClick = () => {
        //  aqui ponemso la la logica para operaciones futuras si es necesario
        
    };

    return(
        <main className="main-Logins">
        <div className="contenedor">
        <div className="imagen-supermercado">
            <img src={LogoPasillos} alt="Supermercado Luciana" />
        </div>
        <div className="formulario-login">
            <h1>REGISTRO</h1>
            <form id="formularioRegistro">
                <label for="nombre">Nombre   </label>
                <input type="text" id="nombre" name="nombre" required />
                <label for="apellidos">Apellidos</label>
                <input type="text" id="apellidos" name="apellidos" required />
                <label for="dni">DNI</label>
                <input type="text" id="dni" name="dni" required />
                <label for="telefono">Teléfono</label>
                <input type="text" id="telefono" name="telefono" required />
                <label for="email">Correo</label>
                <input type="email" id="email" name="email" required />
                <label for="password">Contraseña</label>
                <input type="password" id="password" name="password" required />
                <label for="confirmarPassword">Confirmar Contraseña</label>
                <input type="password" id="confirmarPassword" name="confirmarPassword" required />
                <button className="button-login" type="submit">REGISTRARSE</button>
                <h2>Ya tienes una cuenta? <Link  to="/inicioSesion"  onClick={handleLinkClick}>Inicia sesion aqui</Link></h2>
            </form>
        </div>
    </div>
    </main>
    )
}

export default Registro;