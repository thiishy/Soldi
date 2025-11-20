import { StrictMode } from 'react'
import { createRoot } from 'react-dom/client'
import './styles/global.css'
import Login from './Login.tsx'
import Register from './Register.tsx'


createRoot(document.getElementById('root')!).render(
  <StrictMode>
    <Register/>
  </StrictMode>,
)
