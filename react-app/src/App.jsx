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
import Carrito from './components/carrito.jsx';
import PaypalButton from './components/PaypalButton/paypal.jsx';
import ListVenta from './components/AdminComponents/listaVenta.jsx';

function App() {
  const [isAdmin, setIsAdmin] = useState(false);
  const [isLoggedIn, setIsLoggedIn] = useState(false);
  const [adminMode, setAdminMode] = useState(false);
  const [carrito, setCarrito] = useState(() => {
    const savedCarrito = localStorage.getItem('carrito');
    return savedCarrito ? JSON.parse(savedCarrito) : [];
  });

  const [userData, setUserData] = useState(null);

  useEffect(() => {
    const token = localStorage.getItem('token');
    const role = localStorage.getItem('role');
    const storedUserData = localStorage.getItem('userData');
    if (token && role === 'ADMIN') {
      setIsAdmin(true);
      setIsLoggedIn(true);
    } else if (token) {
      setIsLoggedIn(true);
    }
    if (storedUserData) {
      setUserData(JSON.parse(storedUserData));
    }
  }, []);

  useEffect(() => {
    const actualizarPreciosCarrito = async () => {
      try {
        const response = await ProductoService.getAllProductos();
        const productosActuales = response.data;
        setCarrito(prevCarrito => {
          return prevCarrito.map(item => {
            const productoActual = productosActuales.find(p => p.id === item.id);
            return productoActual ? { ...item, precio: productoActual.precio } : item;
          });
        });
      } catch (error) {
        console.error('Error al obtener los productos:', error);
      }
    };

    actualizarPreciosCarrito();
  }, []);

  useEffect(() => {
    localStorage.setItem('carrito', JSON.stringify(carrito));
  }, [carrito]);

  const handleLogin = (token, role, user) => {
    localStorage.setItem('token', token);
    localStorage.setItem('role', role);
    localStorage.setItem('userData', JSON.stringify(user));
    if (role === 'ADMIN') {
      setIsAdmin(true);
    }
    setIsLoggedIn(true);
    setUserData(user);
  };

  const handleLogout = () => {
    localStorage.removeItem('token');
    localStorage.removeItem('role');
    localStorage.removeItem('carrito');
    localStorage.removeItem('userData');
    setIsAdmin(false);
    setIsLoggedIn(false);
    setAdminMode(false);
    setCarrito([]);
  };

  const toggleAdminMode = () => {
    setAdminMode(!adminMode);
  };

  const agregarAlCarrito = (producto) => {
    setCarrito(prevCarrito => {
      const productoExistente = prevCarrito.find(item => item.id === producto.id);
      if (productoExistente) {
        return prevCarrito.map(item =>
          item.id === producto.id ? { ...item, stock: item.stock + 1 } : item
        );
      }
      return [...prevCarrito, { ...producto, stock: 1 }];
    });
  };

  console.log(userData);

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
          <Route path="/products" element={<Products carrito={carrito} agregarAlCarrito={agregarAlCarrito} />} />
          <Route path="/carrito" element={<Carrito carrito={carrito} setCarrito={setCarrito} isLoggedIn={isLoggedIn} userData={userData} />} />
          <Route path='/paypal-button' element={<PaypalButton setCarrito={setCarrito} />} />

          {/* rutas admin */}
          <Route path="/listClientes" element={
            <PrivateRoute 
              isLoggedIn={isLoggedIn} isAdmin={isAdmin}>
              <ListCliente />
            </PrivateRoute>
          } />
          <Route path="/listCategorias" element={
            <PrivateRoute 
              isLoggedIn={isLoggedIn} isAdmin={isAdmin}>
              <ListCategoria />
            </PrivateRoute>
          } />
          <Route path="/listVentas" element={
            <PrivateRoute 
              isLoggedIn={isLoggedIn} isAdmin={isAdmin}>
              <ListVenta />
            </PrivateRoute>
          } />
          <Route path="/listProductos" element={
            <PrivateRoute 
              isLoggedIn={isLoggedIn} isAdmin={isAdmin}>
              <ListProducto />
            </PrivateRoute>
          } />
          <Route path="/agregarItems" element={
            <PrivateRoute 
              isLoggedIn={isLoggedIn} isAdmin={isAdmin}>
              <AgregarItems />
            </PrivateRoute>
          } />
          <Route path="/agregarCategorias" element={
            <PrivateRoute 
              isLoggedIn={isLoggedIn} isAdmin={isAdmin}>
              <AgregarCategorias />
            </PrivateRoute>
          } />
          <Route path="/actualizarItems/:id" element={
            <PrivateRoute 
              isLoggedIn={isLoggedIn} isAdmin={isAdmin}>
              <ActualizarItems />
            </PrivateRoute>
          } />
          <Route path="/actualizarCategorias/:id" element={
            <PrivateRoute 
              isLoggedIn={isLoggedIn} isAdmin={isAdmin}>
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


