import React, { useState } from "react";
import { Link } from "react-router-dom";
import "../assets/css/header.css";
import logo from "../assets/images/Logos/logo.png"; // Importa la imagen del logo

const Header = ({ enterAdminMode }) => {
    const [menuVisible, setMenuVisible] = useState(false);

    const toggleMenu = () => {
        setMenuVisible(!menuVisible);
    };

    const handleLinkClick = () => {
        setMenuVisible(false);
    };

    return (
        <header className="header-luciana">
            <nav className="nav-luciana">
                <Link to="/">
                    <img id="logoLuciana" src={logo} alt="LogoLuciana" />
                </Link>
                <Link to="/" className="logo-luciana nav-link-luciana">
                    UCIANA SUPERMERCADO
                </Link>
                <ul className={`nav-menu-luciana ${menuVisible ? "nav-menu_visible" : ""}`}>
                    <li className="nav-menu-item-luciana">
                        <Link to="/" className="nav-menu-link-luciana nav-link-luciana" onClick={handleLinkClick}>Inicio</Link>
                    </li>
                    <li className="nav-menu-item-luciana despegable-header">
                        <Link to="/inicioSesion" className="nav-menu-link-luciana nav-link-luciana" onClick={handleLinkClick}>Iniciar Sesion</Link>
                        <ul className="contenido-despegable">
                            <li>
                                <Link to="/registro" className="nav-menu-link-luciana nav-link-luciana" onClick={handleLinkClick}>Registrar</Link>
                            </li>
                            <li>
                                <Link to="/inicioSesion" className="nav-menu-link-luciana nav-link-luciana" onClick={handleLinkClick}>Mi Cuenta</Link>
                            </li>
                        </ul>
                    </li>
                    <li className="nav-menu-item-luciana">
                        <Link to="/products" className="nav-menu-link-luciana nav-link-luciana" onClick={handleLinkClick}>Productos</Link>
                    </li>
                    <li className="nav-menu-item-luciana">
                        <Link to="/contacto" className="nav-menu-link-luciana nav-link-luciana" onClick={handleLinkClick}>Contactanos</Link>
                    </li>
                    <li className="nav-menu-item-luciana carrito">
                        <Link to="/carrito" className="nav-menu-link-luciana nav-link-luciana" onClick={handleLinkClick}>Carrito de Compras</Link>
                    </li>
                </ul>
                <div className="nav-icons-luciana">
                    <Link to="/headerAdmin"  onClick={enterAdminMode}  className="iconoTools">
                        <i className="fa-solid fa-screwdriver-wrench"></i>
                    </Link> 
                    <Link to="/carritoShow" className="iconoCarrito">
                        <i className="fa-solid fa-cart-shopping"></i>
                    </Link>
                    <Link to="/cuenta" className="iconoUser">
                        <i className="fa-solid fa-user"></i>
                    </Link>
                </div>
                <button className="nav-toggle-menu toggle-custom" aria-label={menuVisible ? "Cerrar menú" : "Abrir menú"} onClick={toggleMenu}>
                    <i className="fa-solid fa-bars"></i>
                </button>
            </nav>    
        </header>
        
    );
};

export default Header;
