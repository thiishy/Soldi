import { Routes, Route, Navigate } from 'react-router-dom';
import Login from '../vite-project/src/Login';
import Register from '../vite-project/src/Register';
import Home from './src/pages/Home';
import Transacoes from './src/pages/Transacoes/Transacoes';
import LandingPage from './src/pages/LandingPage/LandingPage';

function App() {
  return (
    <Routes>
      <Route path="/" element={<LandingPage />} />
      <Route path="/login" element={<Login />} />
      <Route path="/register" element={<Register />} />
      <Route path="/home" element={<Home />} />
      <Route path="/transacoes" element={<Transacoes />} />
    </Routes>
  );
}

export default App;