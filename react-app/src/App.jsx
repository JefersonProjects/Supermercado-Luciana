// src/App.jsx
import React, { useState} from 'react';
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




function App() {

  const [isAdmin, setIsAdmin] = useState(false);

  const enterAdminMode = () => {
    setIsAdmin(true);
  };

  const exitAdminMode = () => {
    setIsAdmin(false);
  };

  return (
    <Router>
      <ScrollToTop />
      <div className="App">
        <Header/>
        <div className="main-content"></div>

        {/* de aca empieza el contenido */}
        {isAdmin ? <HeaderAdmin exitAdminMode={exitAdminMode} /> : <Header enterAdminMode={enterAdminMode} />}
        <Routes>
          <Route path="/" element={<Index />} />{/* ruta por defecto */}
          {/* Agrega más rutas según sea necesario */}
          <Route path="/inicioSesion" element={<InicioSesion />} />
          <Route path="/registro" element={<Registro />} />
          <Route path="/recuperacionPassword" element={<RecuperacionPassword />} />
          <Route path="/contacto" element={<Contacto />} />
          <Route path="/products" element={<Products />} />
          {/* Rutas de admin */}
          <Route path="/listClientes" element={<ListCliente />} />
          <Route path="/listCategorias" element={<ListCategoria />} />
          <Route path="/listProductos" element={<ListProducto />} />
          <Route path="/agregarItems" element={<AgregarItems />} />
          <Route path="/agregarCategorias" element={<AgregarCategorias />} />
          <Route path="/actualizarItems/:id" element={<ActualizarItems />} />
          <Route path="/actualizarCategorias/:id" element={<ActualizarCategorias />} />
        </Routes>
        <Footer />
      </div>
    </Router>
  );
}

export default App;


