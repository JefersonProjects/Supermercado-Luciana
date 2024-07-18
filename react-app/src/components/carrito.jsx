import React, { useEffect, useState } from 'react';
import ProductoService from '../services/productoService';
import '../assets/css/carrito.css';
import { useNavigate } from 'react-router-dom';

const Carrito = ({ carrito, setCarrito, isLoggedIn }) => {
    const [productosActuales, setProductosActuales] = useState([]);
    const [showModal, setShowModal] = useState(false);
    const [userData, setUserData] = useState(() => JSON.parse(localStorage.getItem('userData')));
    const [formData, setFormData] = useState({
        nombre: userData?.nombre || '',
        apellido: userData?.apellido || '',
        dni: userData?.dni || '',
        telefono: userData?.telefono || '',
        email: userData?.email || '',
        metodoEnvio: 'Retiro en tienda',
        direccion: '',
        idCliente: userData?.id || '' 
    });
    const navigate = useNavigate();

    useEffect(() => {
        const obtenerPreciosActuales = async () => {
            try {
                const response = await ProductoService.getAllProductos();
                setProductosActuales(response.data);
            } catch (error) {
                console.error('Error al obtener los productos:', error);
            }
        };

        obtenerPreciosActuales();
    }, []);

    useEffect(() => {
        const actualizarPreciosCarrito = () => {
            setCarrito(prevCarrito => {
                return prevCarrito.map(item => {
                    const productoActual = productosActuales.find(p => p.id === item.id);
                    return productoActual ? { ...item, precio: productoActual.precio } : item;
                });
            });
        };

        if (productosActuales.length > 0) {
            actualizarPreciosCarrito();
        }
    }, [productosActuales, setCarrito]);

    useEffect(() => {
        localStorage.setItem('carrito', JSON.stringify(carrito));
    }, [carrito]);

    const actualizarCantidad = (id, cantidad) => {
        if (cantidad < 1) return; // Evitar que la cantidad sea menor a 1
        setCarrito(carrito.map(item => item.id === id ? { ...item, stock: cantidad } : item));
    };

    const eliminarProducto = (id) => {
        setCarrito(carrito.filter(item => item.id !== id));
    };

    const total = carrito.reduce((acc, producto) => acc + producto.precio * producto.stock, 0).toFixed(2);

    const handleProceedToCheckout = () => {
        if (!isLoggedIn) {
            alert("Debe de iniciar sesión para comprar");
            navigate('/inicioSesion');
            return;
        }

        if (!formData.telefono || (formData.metodoEnvio === 'Delivery' && !formData.direccion)) {
            setShowModal(true);
            return;
        }

        navigate('/paypal-button', { state: { formData, carrito, total } });
    };

    const handleChange = (e) => {
        const { name, value } = e.target;
        setFormData(prevState => ({
            ...prevState,
            [name]: value
        }));
    };

    const closeModal = () => {
        setShowModal(false);
    };

    return (
        <div className="carrito-container">
            <div className="carrito-header">
                <a href="#" className="carrito-titulo">DETALLE DE LA COMPRA</a>
            </div>
            <h2 className="carrito-subtitulo">Listado de Productos Agregados a tu Carrito</h2>
            <table className="carrito-tabla">
                <thead>
                    <tr>
                        <th>Nombre</th>
                        <th>Descripción</th>
                        <th>Precio (S/.)</th>
                        <th>Cantidad</th>
                        <th>Monto</th>
                        <th>Acciones</th>
                    </tr>
                </thead>
                <tbody>
                    {carrito.length > 0 ? (
                        carrito.map(producto => (
                            <tr key={producto.id}>
                                <td>{producto.nombre}</td>
                                <td>{producto.descripcion}</td>
                                <td>{producto.precio}</td>
                                <td>
                                    <input
                                        type="number"
                                        value={producto.stock}
                                        min="1"
                                        onChange={(e) => actualizarCantidad(producto.id, parseInt(e.target.value, 10))}
                                    />
                                </td>
                                <td>{(producto.precio * producto.stock).toFixed(1)}</td>
                                <td>
                                    <button className="btn btn-danger" onClick={() => eliminarProducto(producto.id)}>Eliminar</button>
                                </td>
                            </tr>
                        ))
                    ) : (
                        <tr>
                            <td colSpan="6">No hay productos en el carrito.</td>
                        </tr>
                    )}
                </tbody>
            </table>
            <div className="carrito-total">
                <h2>Monto total: S/. {total}</h2>
            </div>
            {carrito.length > 0 && (
                <>
                    <div className="carrito-form">
                        <h2>Detalles del Cliente</h2>
                        <form>
                            <div className="form-column">
                            <div>
                                    <label>Id del Usuario:</label>
                                    <input type="text" name="nombre" value={formData.idCliente} readOnly />
                                </div>
                                <div>
                                    <label>Nombre:</label>
                                    <input type="text" name="nombre" value={formData.nombre} readOnly />
                                </div>
                                <div>
                                    <label>Apellido:</label>
                                    <input type="text" name="apellido" value={formData.apellido} readOnly />
                                </div>
                                <div>
                                    <label>DNI:</label>
                                    <input type="text" name="dni" value={formData.dni} readOnly />
                                </div>
                            </div>
                            <div className="form-column">
                                <div>
                                    <label>Teléfono:</label>
                                    <input type="text" name="telefono" value={formData.telefono} onChange={handleChange} />
                                </div>
                                <div>
                                    <label>Email:</label>
                                    <input type="text" name="email" value={formData.email} readOnly />
                                </div>
                                <div className="envio-container">
                                    <div>
                                        <label>Método de Envío:</label>
                                        <select name="metodoEnvio" value={formData.metodoEnvio} onChange={handleChange}>
                                            <option value="Retiro en tienda">Retiro en tienda</option>
                                            <option value="Delivery">Delivery</option>
                                        </select>
                                    </div>
                                    {formData.metodoEnvio === 'Delivery' && (
                                        <div>
                                            <label>Dirección:</label>
                                            <input type="text" name="direccion" value={formData.direccion} onChange={handleChange} />
                                        </div>
                                    )}
                                </div>
                            </div>
                        </form>
                    </div>
                    <div className="carrito-pagar">
                        <button className="btn" onClick={handleProceedToCheckout}>Procesar Compra</button>
                    </div>
                </>
            )}
            {showModal && (
                <div className="modal-carrito">
                    <div className="modal-content-carrito">
                        <span className="close" onClick={closeModal}>&times;</span>
                        <p>Por favor complete todos los campos requeridos.</p>
                    </div>
                </div>
            )}
        </div>
    );
};

export default Carrito;