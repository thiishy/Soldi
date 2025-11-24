import { useState, type FormEvent } from 'react';
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
import Logo from './assets/logoSoldi.png'; 
import authService from './services/auth.service';

function Register() {
  const [nome, setNome] = useState('');
  const [email, setEmail] = useState('');
  const [senha, setSenha] = useState('');
  const [erro, setErro] = useState('');
  const [carregando, setCarregando] = useState(false);
  const [sucesso, setSucesso] = useState(false);

  const handleRegister = async (e: FormEvent) => {
    e.preventDefault();
    setErro('');
    setCarregando(true);

    try {
      await authService.registrar({ nome, email, senha });
      setSucesso(true);
      alert('Cadastro realizado com sucesso! Faça login agora.');
      
      // Redirecionar para login após 2 segundos
      setTimeout(() => {
        window.location.href = '/login';
      }, 2000);
      
    } catch (error: any) {
      const mensagemErro = error.response?.data?.message || 'Erro ao cadastrar usuário';
      setErro(mensagemErro);
    } finally {
      setCarregando(false);
    }
  };

  return (
    <>
      <main className="container">
        <div className="login-box">
          <section className="login-form">
            <img src={Logo} alt="Imagem de login" className="logo-image" />              
            <Card>
              <CardHeader>
                <CardTitle className="text-2xl font-bold tracking tighter">
                  Cadastre-se
                </CardTitle>
              </CardHeader>
              <CardContent>
                <form onSubmit={handleRegister}>
                  <div>
                    <Label className="label-base" htmlFor="nome">Nome</Label>
                    <Input 
                      className="input-base" 
                      id="nome" 
                      placeholder="Rebeca" 
                      type="text" 
                      value={nome}
                      onChange={(e) => setNome(e.target.value)}
                      required
                    />
                  </div>
                  <br />
                  <div>
                    <Label className="label-base" htmlFor="email">E-mail</Label>
                    <Input 
                      className="input-base" 
                      id="email" 
                      placeholder="user@email.com" 
                      type="email"
                      value={email}
                      onChange={(e) => setEmail(e.target.value)}
                      required
                    />
                  </div>
                  <div>
                    <br />
                    <Label className="label-base" htmlFor="senha">Senha</Label>
                    <Input 
                      className="" 
                      id="senha" 
                      placeholder="user123" 
                      type="password"
                      value={senha}
                      onChange={(e) => setSenha(e.target.value)}
                      required
                    />
                  </div>

                  {erro && (
                    <p style={{ color: 'red', marginTop: '10px', fontSize: '14px' }}>
                      {erro}
                    </p>
                  )}

                  {sucesso && (
                    <p style={{ color: 'green', marginTop: '10px', fontSize: '14px' }}>
                      Cadastro realizado! Redirecionando...
                    </p>
                  )}

                  <Button 
                    className="btn-login" 
                    type="submit"
                    disabled={carregando}
                  >
                    {carregando ? 'Cadastrando...' : 'Cadastrar'}
                  </Button>
                </form>
              </CardContent>
            </Card>
          </section>
        </div>
      </main>
    </>
  )
}

export default Register