import React, { useState } from 'react';
import { Link } from 'react-router-dom';
import '../assets/css/headerAdmin.css';
import logoAdmin from '../assets/images/Logos/logoAdmin.png';

const HeaderAdmin = ({ exitAdminMode }) => {
    const [menuVisible, setMenuVisible] = useState(false);

    const toggleMenu = () => {
        setMenuVisible(!menuVisible);
    };

    const handleLinkClick = () => {
        setMenuVisible(false);
    };

    return (
        <header className="header-luciana-Admin">
            <nav className="nav-luciana-Admin">
                <Link to="/">
                    <img id="logoLuciana-Admin" src={logoAdmin} alt="LogoLuciana" />
                </Link>
                <ul className={`nav-menu-luciana-Admin ${menuVisible ? 'nav-menu_visible' : ''}`}>
                    <li className="nav-menu-item-luciana-Admin"><Link to="/listProductos" className="nav-menu-link-luciana-Admin nav-link-luciana-Admin" onClick={handleLinkClick}>INVENTARIO</Link></li>
                    <li className="nav-menu-item-luciana-Admin">
                        <Link to="/listCategorias" className="nav-menu-link-luciana-Admin nav-link-luciana-Admin" onClick={handleLinkClick}>CATEGORIA</Link>
                    </li>
                    <li className="nav-menu-item-luciana-Admin">
                        <Link to="/listClientes" className="nav-menu-link-luciana-Admin nav-link-luciana-Admin" onClick={handleLinkClick}>CLIENTES</Link>
                    </li>
                    <li className="nav-menu-item-luciana-Admin">
                        <Link to="/listVentas" className="nav-menu-link-luciana-Admin nav-link-luciana-Admin" onClick={handleLinkClick}>VENTAS</Link>
                    </li>
                    <li className="nav-menu-item-luciana-Admin">
                        <Link to="#" onClick={exitAdminMode} className="nav-menu-link-luciana-Admin nav-link-luciana-Admin">SALIR DEL MODO ADMIN</Link>
                    </li>
                </ul>
                <div className="nav-icons-luciana-Admin">
                    <Link to="/cuenta" target="_blank" className="iconoUser">
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

export default HeaderAdmin;
