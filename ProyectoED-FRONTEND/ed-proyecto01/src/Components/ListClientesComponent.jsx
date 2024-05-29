import React, { useEffect, useState } from 'react';
import ClienteService from '../Services/ClienteService';
import { Link } from 'react-router-dom';

export const ListClientesComponent = () => {
    const [clientes, setClientes] = useState([]);

    useEffect(() => {
        
        listarClientes()
    }, [])
    
    const listarClientes = () =>{
        ClienteService.getAllClientes().then(response =>{
            setClientes(response.data);
            console.log(response.data);
        }).catch(error =>{
            console.log(error);
        })
    }

    const deleteCliente = (clienteId) =>{
        ClienteService.deleteCliente(clienteId).then((response) =>{
            listarClientes();
        }).catch(error =>{
            console.log(error);
        })
    }

    return (
        <div className='container' style={{ marginTop: '50px' }}>
            <h2 className='text-center'>Lista De Clientes</h2>
            <Link to='/add-cliente' className='btn btn-primary mb-2'>Agregar Cliente</Link>  
            <table className='table table-bordered table-striped'>
                <thead>
                    <tr>
                        <th>Id</th>
                        <th>Nombre</th>
                        <th>Apellido</th>
                        <th>DNI</th>
                        <th>Email</th>
                        <th>Acciones</th>
                    </tr>
                </thead>
                <tbody>
                    {clientes.length > 0 ? (
                        clientes.map((cliente) => (
                            <tr key={cliente.id}>
                                <td>{cliente.id}</td>
                                <td>{cliente.nombre}</td>
                                <td>{cliente.apellido}</td>
                                <td>{cliente.dni}</td>
                                <td>{cliente.email}</td>
                                <td>
                                    <Link className='btn btn-info' to={`/edit-cliente/${cliente.id}`}>Actualizar Datos</Link>
                                    <button style={{marginLeft:"10PX"}} className='btn btn-danger' onClick={() => deleteCliente(cliente.id)}>Eliminar</button>
                                </td>
                            </tr>
                        ))
                    ) : (
                        <tr>
                            <td colSpan="5" className="text-center">No hay clientes disponibles</td>
                        </tr>
                    )}
                </tbody>
            </table>
        </div>
    );
};

export default ListClientesComponent;
