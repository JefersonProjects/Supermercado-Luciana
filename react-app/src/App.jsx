import React, { useState, useEffect } from 'react';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import ScrollToTop from './components/Utils/scrollToTop.jsx';
import Header from './components/header.jsx';
import HeaderAdmin from './components/headerAdmin';
import Index from './components/index.jsx';
import Footer from './components/footer.jsx';
import InicioSesion from './components/inicioSesion.jsx';
import Registro from './components/registro.jsx';
import RecuperacionPassword from './components/recuperarContraseña.jsx';
import Contacto from './components/contacto.jsx';
import ListCliente from './components/AdminComponents/listaCliente.jsx';
import ListCategoria from './components/AdminComponents/listaCategoria.jsx';
import ListProducto from './components/AdminComponents/listaProducto.jsx';
import AgregarItems from './components/AdminComponents/agregarItems.jsx';
import AgregarCategorias from './components/AdminComponents/agregarCategoria.jsx';
import ActualizarItems from './components/AdminComponents/actualizarItems.jsx';
import ActualizarCategorias from './components/AdminComponents/actualizarCategoria.jsx';
import Products from './components/products.jsx';
import MiCuenta from './components/miCuenta.jsx';
import PrivateRoute from './components/Utils/privateRoute.jsx';

function App() {
  const [isAdmin, setIsAdmin] = useState(false);
  const [isLoggedIn, setIsLoggedIn] = useState(false);
  const [adminMode, setAdminMode] = useState(false);

  useEffect(() => {
    const token = localStorage.getItem('token');
    const role = localStorage.getItem('role');
    if (token && role === 'ADMIN') {
      setIsAdmin(true);
      setIsLoggedIn(true);
    } else if (token) {
      setIsLoggedIn(true);
    }
  }, []);

  const handleLogin = (token, role) => {
    localStorage.setItem('token', token);
    localStorage.setItem('role', role);
    if (role === 'ADMIN') {
      setIsAdmin(true);
    }
    setIsLoggedIn(true);
  };

  const handleLogout = () => {
    localStorage.removeItem('token');
    localStorage.removeItem('role');
    setIsAdmin(false);
    setIsLoggedIn(false);
    setAdminMode(false);
  };

  const toggleAdminMode = () => {
    setAdminMode(!adminMode);
  };

  return (
    <Router>
      <ScrollToTop />
      <div className="App">
        {adminMode ? (
          <HeaderAdmin exitAdminMode={toggleAdminMode} />
        ) : (
          <Header isLoggedIn={isLoggedIn} isAdmin={isAdmin} toggleAdminMode={toggleAdminMode} />
        )}
        <div className="main-content"></div>
        <Routes>
          <Route path="/" element={<Index />} />
          <Route path="/inicioSesion" element={<InicioSesion onLogin={handleLogin} />} />
          <Route path="/registro" element={<Registro />} />
          <Route path="/recuperacionPassword" element={<RecuperacionPassword />} />
          <Route path="/contacto" element={<Contacto />} />
          <Route path="/cuenta" element={<MiCuenta handleLogout={handleLogout} />} />
          <Route path="/products" element={<Products />} />
          {/* rutas admin */}
          <Route path="/listClientes" element={
            <PrivateRoute 
            isLoggedIn={isLoggedIn} isAdmin={isAdmin}><ListCliente />
          </PrivateRoute>
        } />

          <Route path="/listCategorias" element={
            <PrivateRoute isLoggedIn={isLoggedIn} isAdmin={isAdmin}>
            <ListCategoria />
          </PrivateRoute>
        } />

          <Route path="/listProductos" element={
            <PrivateRoute isLoggedIn={isLoggedIn} isAdmin={isAdmin}>
            <ListProducto />
          </PrivateRoute>
        } />


          <Route path="/agregarItems" element={
            <PrivateRoute isLoggedIn={isLoggedIn} isAdmin={isAdmin}>
            <AgregarItems />
          </PrivateRoute>
        } />

          <Route path="/agregarCategorias" element={
            <PrivateRoute isLoggedIn={isLoggedIn} isAdmin={isAdmin}>
            <AgregarCategorias />
          </PrivateRoute>
        } />

          <Route path="/actualizarItems/:id" element={
            <PrivateRoute isLoggedIn={isLoggedIn} isAdmin={isAdmin}>
            <ActualizarItems />
          </PrivateRoute>
        } />

          <Route path="/actualizarCategorias/:id" element={
            <PrivateRoute isLoggedIn={isLoggedIn} isAdmin={isAdmin}>
            <ActualizarCategorias />
          </PrivateRoute>
        } />
        
        </Routes>
        <Footer />
      </div>
    </Router>
  );
}

export default App;


