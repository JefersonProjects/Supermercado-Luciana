import React from 'react';
import { Link } from 'react-router-dom'; 
import "../assets/css/contacto.css";

const Contacto = () =>{
    return(
        <main id="maincont">
        <div id="form">
            <h1 className="contact-saludo">¡Contáctenos!</h1>
            <form action="CONFIG/formulario.php" method="post">
                <label for="name">Nombre:</label><br />
                <input type="text" name="name" id="name" required /><br />
                <label for="apellidos">Apellidos:</label><br />
                <input type="text" name="apellidos" id="apellidos" required /><br/>
                <label for="email">Correo Electrónico:</label><br/>
                <input type="email" name="email" id="email" required /><br/>
                <label for="telefono">Teléfono:</label><br/>
                <input type="tel" name="telefono" id="telefono" /><br/>
                <label for="consulta">Tipo de Consulta:</label><br/>
                <select name="consulta" id="consulta">
                    <option value="asesoria">Asesoría</option>
                    <option value="productos">Productos</option>
                    <option value="reclamos">Reclamos</option>
                    <option value="servicio-tecnico">Servicio Técnico</option>
                    <option value="otros-problemas">Otros Problemas</option>

                </select><br />
                <label for="mensaje">Mensaje:</label><br/>
                <textarea name="mensaje" id="mensaje" cols="30" rows="10"></textarea><br />
                <button className="contact-button" type="submit">Enviar</button>
            </form>
        </div>
    </main>
    );  
};
export	default Contacto;