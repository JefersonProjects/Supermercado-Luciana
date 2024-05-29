
import './App.css'
import AddClienteComponent from './Components/AddClienteComponent';
import FooterComponent from './Components/FooterComponent';
import HeaderComponet from './Components/HeaderComponent';
import ListClientesComponent from './Components/ListClientesComponent';
import {BrowserRouter,Route,Routes} from 'react-router-dom';


function App() {

  return(
    <div>
      <BrowserRouter>
        <HeaderComponet/>
        <div className='container'>
          <Routes>
            <Route exact path='/' element = {<ListClientesComponent/>}></Route>
            <Route path='/clientes' element = {<ListClientesComponent/>}></Route>
            <Route path='/add-cliente' element = {<AddClienteComponent/>}></Route>
            <Route path='/edit-cliente/:id' element = {<AddClienteComponent/>}></Route>
          </Routes>
        </div>
        <FooterComponent/>
      </BrowserRouter>
    </div>
  );
}
export default App
