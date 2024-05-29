import React from 'react';
import { BrowserRouter, Routes, Route } from 'react-router-dom';
import HeaderComponent from './Components/HeaderComponent';
import FooterComponent from './Components/FooterComponent';
import ListClientesComponent from './Components/ListClientesComponent';
import AddClienteComponent from './Components/AddClienteComponent';
import LoginComponent from './Components/LoginComponent';


function App() {
  return (
    <div>
      <BrowserRouter>
        <Routes>
          <Route path="/" element={<LoginComponent />} />
          <Route path="/clientes" element={<>
            <HeaderComponent />
            <ListClientesComponent />
            <FooterComponent />
          </>} />
          <Route path="/add-cliente" element={<>
            <HeaderComponent />
            <AddClienteComponent />
            <FooterComponent />
          </>} />
          <Route path="/edit-cliente/:id" element={<>
            <HeaderComponent />
            <AddClienteComponent />
            <FooterComponent />
          </>} />
        </Routes>
      </BrowserRouter>
    </div>
  );
}

export default App;


