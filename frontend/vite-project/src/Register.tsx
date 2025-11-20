import "./styles/global.css";
import './App.css';
import {
  Card,
  CardContent,
  CardHeader,
  CardTitle,
} from "./components/cardLogin.tsx"
import { Input } from './components/input.tsx'
import { Label } from './components/label.tsx'
import { Button } from './components/button.tsx'
import Logo  from './assets/logoSoldi.png'; 
import { useRef } from 'react';
import api from './services/api.tsx'; 

// import cors from 'cors';

function Register() {

// deixando pronto para conexão com a API
const inputName = useRef<HTMLInputElement>(null);
const inputEmail = useRef<HTMLInputElement>(null);
const inputPassword = useRef<HTMLInputElement>(null); 

async function createUsers(){
  try{
    await api.post('/usuarios',{
      name: inputName.current,
      email: inputEmail.current,
      password: inputPassword.current
    });
    alert("Cadastro realizado!");
  } catch(error){
    console.error("Erro ao cadastrar usuário", error);
  }
}

  return (
    <>
     <main className="container">
          <div className="login-box">
            <section className="login-form">
              <img src={Logo} alt="Imagem de login" className="logo-image" />              
              <Card>
                <CardHeader >
                  <CardTitle className="text-2xl font-bold tracking tighter">
                    Cadastre-se
                  </CardTitle>
                </CardHeader>
                <CardContent>
                  <div>
                    <Label className= "label-base" htmlFor = "text">Nome</Label>
                    <Input className="input-base" id="name" placeholder="Rebeca" type="text" ref={inputName}/>
                  </div>
                    <br />
                  <div>
                    <Label className= "label-base" htmlFor = "email">E-mail</Label>
                    <Input className="input-base" id="email" placeholder="user@email.com" type="email" ref={inputEmail}/>
                  </div>
                  <div>
                    <br />
                    <Label className= "label-base" htmlFor = "password">Senha</Label>
                    <Input className="" id="password" placeholder="user123" type="password" ref={inputPassword}/>
                  </div>
                  <Button className="btn-login"type="submit" onSubmit={createUsers}> Cadastrar </Button>
                </CardContent>
              </Card>
            </section>
          </div>
        </main>
    </>
  )
}

export default Register
