import React from 'react';
import { useNavigate } from 'react-router-dom';
import './InicioDeSesion.css';  // Asegúrate de que la ruta sea correcta

const LoginComponent = () => {
    const navigate = useNavigate();

    const manejarInicioSesion = (event) => {
        event.preventDefault();
        // Aquí puedes agregar lógica de validación si es necesario
        navigate('/clientes');
    };

    const Registrar = () => {
        window.location.href = "http://127.0.0.1:5500/Registro.html";
    };

    const Volver = () => {
        let Respuesta = confirm("¿Desea regresar a la página principal?.");
        if (Respuesta) {
            alert("Redireccionando.");
            window.location.href = "http://127.0.0.1:5500/Bienvenida.html";
        } else {
            alert("No se preocupe, a veces yo también me olvido mi contraseña 😁.");
        }
    };

    return (
        <div className="contenedor-login">
            <div className="encabezado-login">
                <h1>Bienvenido a</h1>
                <img src="/imgs/logo.png" alt="Supermercado Luciana" height="150px" />
            </div>
            <div className="formulario-login">
                <form id="formularioInicioSesion" onSubmit={manejarInicioSesion}>
                    <div className="grupo-formulario">
                        <label htmlFor="usuario">Usuario</label>
                        <input type="text" id="usuario" name="usuario" required />
                    </div>
                    <div className="grupo-formulario">
                        <label htmlFor="contrasena">Contraseña</label>
                        <input type="password" id="contrasena" name="contrasena" required />
                    </div>
                    <button type="submit">Iniciar Sesión</button>
                    <p>
                        <button type="button" onClick={Registrar}>Registrar Usuario</button>
                    </p>
                    <p>
                        <button type="button" onClick={Volver}>Regresar</button>
                    </p>
                </form>
            </div>
        </div>
    );
};

export default LoginComponent;