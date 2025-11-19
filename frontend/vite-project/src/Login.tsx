import "./styles/global.css";
import './App.css';
import {
  Card,
  CardContent,
  CardDescription,
  CardHeader,
  CardTitle,
} from "./components/cardLogin.tsx"
import { Input } from './components/input.tsx'
import { Label } from './components/label.tsx'
import { Button } from './components/button.tsx'

function Login() {
  return (
    <>
     <main className="container">
          <div className="login-box">
            <section className="login-form">
              <Card>
                <CardHeader >
                  <CardTitle className="text-2xl font-bold tracking tighter">
                    Entre com sua conta
                  </CardTitle>
                  <CardDescription>
                    Utilize seu e-mail e senha
                  </CardDescription>
                </CardHeader>
                <CardContent>
                  <div>
                    <Label className= "label-base" htmlFor = "email">E-mail</Label>
                    <Input className="input-base" id="email" placeholder="user@email.com" type="email"/>
                  </div>
                  <div>
                    <br />
                    <Label className= "label-base" htmlFor = "password">Senha</Label>
                    <Input className="" id="password" placeholder="user123" type="password"/>
                  </div>
                  <br />
                    <a href="#" className="link">
                      Cadastre-se
                    </a>
                  <Button className="btn-login"type="submit"> Entrar </Button>
                </CardContent>
              </Card>
            </section>
          </div>
        </main>
    </>
  )
}

export default Login
