import React from 'react';
import '../assets/css/principal.css';
import alimentos from "../assets/images/principal/alimentos.png";
import bebidas from "../assets/images/principal/bebidas.png";
import limpieza from "../assets/images/principal/limpieza.png";
import cosmeticos from "../assets/images/principal/cosmeticos.png";
import seccion3 from "../assets/images/principal/seccion3.png";
import seccion4 from "../assets/images/principal/seccion4.png";
import { Link } from 'react-router-dom';

const Index =()=>{
    return(
        <main id="main-content" className="contenido-principal">
        <section className="contenido_1">
            <p className="titulo-index">
                TU SUPERMERCADO DE CONFIANZA,
                CALIDAD Y VARIEDAD <span> a un click de distancia</span>
                
            </p>
        </section>
        <section className="contenido_2  separador">
            <div className="saludo">
                <h2 className="saludo">Puedes revisar nuestros productos por categorias </h2>
            </div>
            <div className="img-contenedor">
                <div id="al"><Link to="/products" style={{textDecoration: 'none', color: 'black'}}>
                        <img src={alimentos} alt="alimentos"/>
                        <p className="opcion">
                            Alimentos
                        </p>
                    </Link>
                </div>
                <div id="beb"><Link to="/products" style={{textDecoration: 'none', color: 'black'}}>
                        <img src={bebidas} alt="bebidas"/>
                        <p className="opcion">
                            Bebidas
                        </p>
                    </Link>
                </div>
                <div id="lim"> <Link to="/products" style={{textDecoration: 'none', color: 'black'}}>
                        <img src={limpieza} alt="limpieza"/>
                        <p className="opcion">
                            Limpieza
                        </p>
                    </Link>
                </div>
                <div id="cos"> <Link to="/products" style={{textDecoration: 'none', color: 'black'}}>
                    <img src={cosmeticos} alt="cosmeticos"/>
                    <p className="opcion">
                        Cuidado y Belleza
                    </p>
                </Link>
            </div>
            </div>
        </section>
        <section className="contenido_3">
            <div className="imagen-seccion3">
                <img src={seccion3} alt="Carrito de Supermercado"/>
            </div>
            <div className="texto-seccion3">
                <h1>MISIÓN</h1>
                <p>En Supermercado Luciana nos dedicamos a proporcionar a nuestros clientes una experiencia
                    de compra excepcional mediante la oferta de productos frescos, variados y de calidad 
                    superior a precios competitivos. Nos comprometemos a mantener un ambiente limpio, 
                    seguro y acogedor, donde el servicio al cliente es nuestra prioridad. Nuestro objetivo 
                    es satisfacer las necesidades diarias de nuestros clientes y contribuir positivamente al bienestar de la comunidad que servimos.</p>
            </div>
        </section>
        <section className="contenido_4">
            <div className="texto-seccion4">
                <h1>VISION</h1>
                <p>Nos vemos como el supermercado preferido de nuestra comunidad, reconocido por nuestra
                    excelencia en servicio, la frescura y variedad de nuestros productos, así como por 
                    nuestro compromiso con la sostenibilidad ambiental y el desarrollo local. Buscamos 
                    ser líderes en innovación dentro del sector, adaptándonos continuamente para superar 
                    las expectativas de nuestros clientes y ser un modelo de negocio responsable y ético 
                    en nuestro mercado</p>
            </div>
            <div className="imagen-seccion4">
                <img src={seccion4} alt="Carrito de Supermercado"/>
            </div>
        </section>

            
    </main>
    );
};

export default Index;