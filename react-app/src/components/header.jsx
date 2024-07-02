import React, { useState } from 'react';
import { Link } from 'react-router-dom';
import '../assets/css/header.css';
import logo from '../assets/images/Logos/logo.png';

const Header = ({ isLoggedIn, isAdmin, toggleAdminMode }) => {
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
                    LUCIANA SUPERMERCADO
                </Link>
                <ul className={`nav-menu-luciana ${menuVisible ? "nav-menu_visible" : ""}`}>
                    <li className="nav-menu-item-luciana">
                        <Link to="/" className="nav-menu-link-luciana nav-link-luciana" onClick={handleLinkClick}>Inicio</Link>
                    </li>
                    {!isLoggedIn && (
                        <li className="nav-menu-item-luciana despegable-header">
                            <Link to="/inicioSesion" className="nav-menu-link-luciana nav-link-luciana" onClick={handleLinkClick}>Iniciar Sesión</Link>
                            <ul className="contenido-despegable">
                                <li>
                                    <Link to="/registro" className="nav-menu-link-luciana nav-link-luciana" onClick={handleLinkClick}>Registrar</Link>
                                </li>
                            </ul>
                        </li>
                    )}
                    {isLoggedIn && (
                        <li className="nav-menu-item-luciana">
                            <Link to="/cuenta" className="nav-menu-link-luciana nav-link-luciana" onClick={handleLinkClick}>Mi Cuenta</Link>
                        </li>
                    )}
                    <li className="nav-menu-item-luciana">
                        <Link to="/products" className="nav-menu-link-luciana nav-link-luciana" onClick={handleLinkClick}>Productos</Link>
                    </li>
                    <li className="nav-menu-item-luciana">
                        <Link to="/contacto" className="nav-menu-link-luciana nav-link-luciana" onClick={handleLinkClick}>Contáctanos</Link>
                    </li>
                    <li className="nav-menu-item-luciana carrito">
                        <Link to="/carrito" className="nav-menu-link-luciana nav-link-luciana" onClick={handleLinkClick}>Carrito de Compras</Link>
                    </li>
                </ul>
                <div className="nav-icons-luciana">
                    {isAdmin && (
                        <Link to="/headerAdmin" className="iconoTools" onClick={toggleAdminMode}>
                            <i className="fa-solid fa-screwdriver-wrench"></i>
                        </Link>
                    )}
                    <Link to="/carritoShow" className="iconoCarrito">
                        <i className="fa-solid fa-cart-shopping"></i>
                    </Link>
                    {isLoggedIn && (
                        <Link to="/cuenta" className="iconoUser">
                            <i className="fa-solid fa-user"></i>
                        </Link>
                    )}
                </div>
                <button className="nav-toggle-menu toggle-custom" aria-label={menuVisible ? "Cerrar menú" : "Abrir menú"} onClick={toggleMenu}>
                    <i className="fa-solid fa-bars"></i>
                </button>
            </nav>
        </header>
    );
};

export default Header;

