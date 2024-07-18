import React, { useState, useEffect } from 'react';
import ProductoService from '../services/productoService';
import CategoriaService from '../services/categoriaService';
import fondoTitulo from '../assets/images/Products/productos_titulo.png';
import "../assets/css/catalogo.css";

const Productos = ({ carrito = [], agregarAlCarrito }) => {
    const [productos, setProductos] = useState([]);
    const [categorias, setCategorias] = useState([]);
    const [filtros, setFiltros] = useState({
        busqueda: '',
        categoria: '',
        precio: 500,
        ordenar: ''
    });
    const [mostrarModal, setMostrarModal] = useState(false);
    const [modalMessage, setModalMessage] = useState('');

    useEffect(() => {
        ProductoService.getAllProductos()
            .then(response => setProductos(response.data))
            .catch(error => console.error('Error al obtener productos:', error));

        CategoriaService.getAllCategorias()
            .then(response => setCategorias(response.data))
            .catch(error => console.error('Error al obtener categorías:', error));
    }, []);

    useEffect(() => {
        const fetchProductos = async () => {
            try {
                let productosFiltrados = [];

                if (filtros.busqueda) {
                    const response = await ProductoService.buscarProductos(filtros.busqueda);
                    productosFiltrados = response.data;
                } else {
                    const response = await ProductoService.getAllProductos();
                    productosFiltrados = response.data;
                }

                if (filtros.categoria) {
                    productosFiltrados = productosFiltrados.filter(producto => producto.categoriaNombre === filtros.categoria);
                }

                if (filtros.precio !== 500) {
                    productosFiltrados = productosFiltrados.filter(producto => producto.precio <= filtros.precio);
                }

                if (filtros.ordenar === '1') {
                    productosFiltrados.sort((a, b) => a.nombre.localeCompare(b.nombre));
                } else if (filtros.ordenar === '2') {
                    productosFiltrados.sort((a, b) => a.precio - b.precio);
                } else if (filtros.ordenar === '3') {
                    productosFiltrados.sort((a, b) => b.precio - a.precio);
                }

                setProductos(productosFiltrados);
            } catch (error) {
                console.error('Error al obtener productos:', error);
            }
        };

        fetchProductos();
    }, [filtros]);

    const handleFiltroChange = (e) => {
        const { name, value } = e.target;
        setFiltros({
            ...filtros,
            [name]: value
        });
    };

    const handleSubmit = (e) => {
        e.preventDefault();
    };

    const handleAgregarAlCarrito = (producto) => {
        if (carrito.some(item => item.id === producto.id)) {
            setModalMessage('El Producto ya está en el carrito');
        } else {
            agregarAlCarrito(producto);
            setModalMessage('Producto agregado al carrito');
        }
        setMostrarModal(true);
    };

    const handleOkClick = () => {
        setMostrarModal(false);
    };

    return (
        <div className="body-productos">
            <header>
                <div
                    className="titulo-catalogo"
                    style={{
                        backgroundImage: `url(${fondoTitulo})`,
                        backgroundSize: 'cover',
                        backgroundPosition: 'center',
                        color: 'white',
                        padding: '80px 0'
                    }}
                >
                    <h1>Productos Luciana! El lugar donde podrás encontrar lo que buscabas</h1>
                </div>
            </header>
            <main className="main-pag">
                <div className="contenedor-principal">
                    <div className="filtros-contenedor">
                        <form onSubmit={handleSubmit}>
                            <h3>Busqueda General:</h3>
                            <input
                                type="text"
                                className="filtros"
                                id="busqueda"
                                name="busqueda"
                                value={filtros.busqueda}
                                onChange={handleFiltroChange}
                            />
                            <h3>Filtros:</h3>
                            <div className="filtro-grupo">
                                <label htmlFor="categoria">Categoría</label>
                                <select
                                    id="categoria"
                                    name="categoria"
                                    className="filtros"
                                    value={filtros.categoria}
                                    onChange={handleFiltroChange}
                                >
                                    <option value="">Todos</option>
                                    {categorias.map(cat => (
                                        <option key={cat.id} value={cat.nombre}>{cat.nombre}</option>
                                    ))}
                                </select>
                            </div>
                            <div className="filtro-grupo">
                                <label htmlFor="precio">Precio</label>
                                <input
                                    type="range"
                                    id="precio"
                                    name="precio"
                                    min="5"
                                    max="500"
                                    step="1"
                                    className="filtros"
                                    value={filtros.precio}
                                    onChange={handleFiltroChange}
                                />
                                <div className="rango-precio">
                                    <span> min S/5</span> - <span>S/{filtros.precio} +</span>
                                </div>
                            </div>

                            <h3>Ordenar:</h3>
                            <select
                                name="ordenar"
                                id="ordenar"
                                className="filtros"
                                value={filtros.ordenar}
                                onChange={handleFiltroChange}
                            >
                                <option value="">Predeterminado</option>
                                <option value="1">Ordenar por nombre</option>
                                <option value="2">Ordenar por precio de menor a mayor</option>
                                <option value="3">Ordenar por precio de mayor a menor</option>
                            </select>
                        </form>
                    </div>

                    <div className="catalogo-contenedor" id="catalogo-contenedor">
                        {productos.map(producto => (
                            <div className="catalogo-item" key={producto.id}>
                                <img src={`data:image/png;base64,${producto.imagen}`} alt={producto.nombre} />
                                <h3>{producto.nombre}</h3>
                                <hr className="linea-separadora" />
                                <p>{producto.descripcion}</p>
                                {carrito.some(item => item.id === producto.id) ? (
                                    <p>Producto ya está en el carrito</p>
                                ) : (
                                    <button onClick={() => handleAgregarAlCarrito(producto)}>Agregar al Carro</button>
                                )}
                                <h4> Precio: S/{producto.precio}</h4>
                            </div>
                        ))}
                    </div>
                </div>
            </main>
            {mostrarModal && (
                <div className="modal">
                    <div className="modal-content">
                        <p>{modalMessage}</p>
                        <button className="btn btn-danger" onClick={handleOkClick}>OK</button>
                    </div>
                </div>
            )}
        </div>
    );
};

export default Productos;




