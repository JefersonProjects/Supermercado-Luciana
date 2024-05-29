import React, { useEffect, useState } from 'react'
import ClienteService from '../Services/ClienteService';
import { useNavigate,Link, useParams } from 'react-router-dom';


export const AddClienteComponent = () => {

  const [nombre,setNombre] = useState('');
  const [apellido,setApellido] = useState('');
  const [dni,setDni] = useState('');
  const [telefono,setTelefono] = useState('');
  const [email,setEmail] = useState('');
  const [password,setPassword] = useState('');
  const nagivate = useNavigate();
  const {id} = useParams();

  const saveOrUpdatCliente= (e) =>{
    e.preventDefault();

    const cliente = {nombre,apellido,dni,telefono,email,password};

    if(id){
        ClienteService.updateCliente(id,cliente).then((response) => {
            console.log(response.data);
            nagivate('/clientes');
            }).catch((error) => {
                console.log(error)
            })
            
        }
        else{
            ClienteService.createCliente(cliente).then((response) => {
                console.log(response.data);
                nagivate('/clientes');
                }).catch(error => {
                    console.log(error)
                }) 
            }
        

        }

        useEffect(() => {
            ClienteService.getClienteById(id).then((response) => {
                setNombre(response.data.nombre);
                setApellido(response.data.apellido);
                setDni(response.data.dni);
                setTelefono(response.data.telefono);
                setEmail(response.data.password);
                setPassword(response.data.password);
            }).catch(error =>{
                console.log(error);
            })
        },[])

        const title = () =>{
            if (id) {
                return <h2 className='text-center'>Actualizar Cliente</h2>
            }else{
                return <h2 className='text-center'>Agregar Cliente</h2>
            }
        }

  return (
    <div>
        <div className='container' style={{ marginTop: '50px' }}>
            <div className='row'>
                <div className='card col-md-6 offset-md-3 offset-md-3'>
                    <h2 className='text-center'>
                        {
                            title()
                        }
                    </h2>
                    <div className='card-body'>
                        <form>
                            <div className='form-group mb-2'>
                                <label className="form-label">Nombre</label>
                                <input 
                                type="text"
                                placeholder='Digite su nombre' 
                                name='nombre'
                                className='form-control'
                                value={nombre}
                                onChange={ (e) => setNombre(e.target.value)}/>
                            </div>
                            <div className='form-group mb-2'>
                                <label className="form-label">Apellido</label>
                                <input 
                                type="text"
                                placeholder='Digite su apellido' 
                                name='apellido'
                                className='form-control'
                                value={apellido}
                                onChange={ (e) => setApellido(e.target.value)}/>
                            </div>
                            <div className='form-group mb-2'>
                                <label className="form-label">dni</label>
                                <input 
                                type="text"
                                placeholder='Digite su nombre' 
                                name='dni'
                                className='form-control'
                                value={dni}
                                onChange={ (e) => setDni(e.target.value)}/>
                            </div>
                            <div className='form-group mb-2'>
                                <label className="form-label">Telefono</label>
                                <input 
                                type="text"
                                placeholder='Digite su nombre' 
                                name='telefono'
                                className='form-control'
                                value={telefono}
                                onChange={ (e) => setTelefono(e.target.value)}/>
                            </div>
                            <div className='form-group mb-2'>
                                <label className="form-label">Email</label>
                                <input 
                                type="text"
                                placeholder='Digite su nombre' 
                                name='email'
                                className='form-control'
                                value={email}
                                onChange={ (e) => setEmail(e.target.value)}/>
                            </div>
                            <div className='form-group mb-2'>
                                <label className="form-label">Password</label>
                                <input 
                                type="password"
                                placeholder='Digite su nombre' 
                                name='password'
                                className='form-control'
                                value={password}
                                onChange={ (e) => setPassword(e.target.value)}/>
                            </div>
                            <button className='btn btn-success' onClick={(e) => saveOrUpdatCliente(e)}> Guardar </button>
                            &nbsp; &nbsp;
                            <Link to='/clientes' className='btn btn-danger'>Cancelar</Link>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
  )
}

export default AddClienteComponent;