import { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import "../styles/Home.css";
import { Card } from "../components/Card/Card";
import { Nav } from "../components/Sidebar/Sidebar"; 
import { Table } from "../components/Table/Table";
import Grafico from "../components/Grafico/Grafico";
import { Chart } from "../components/Chart/Chart";
import Button from "../components/Button/Button"; 
import usuarioService from '../services/usuario.service';
import transacaoService from '../services/transacao.service';
import authService from '../services/auth.service';
import type { SaldoResponse, TransacaoResumo, GastoPorCategoria } from '../types/api.types';

function Home() {
  const navigate = useNavigate();
  
  // Estados para armazenar dados do backend
  const [saldo, setSaldo] = useState<SaldoResponse | null>(null);
  const [transacoes, setTransacoes] = useState<TransacaoResumo[]>([]);
  const [gastosPorCategoria, setGastosPorCategoria] = useState<GastoPorCategoria[]>([]);
  const [carregando, setCarregando] = useState(true);
  const [erro, setErro] = useState('');

  // Buscar dados do backend quando o componente montar
  useEffect(() => {
    const carregarDados = async () => {
      // Verificar se está autenticado
      if (!authService.isAuthenticated()) {
        navigate('/login');
        return;
      }

      try {
        setCarregando(true);
        
        // Buscar dados em paralelo
        const [saldoData, transacoesData, gastosData] = await Promise.all([
          usuarioService.getSaldo(),
          transacaoService.listarRecentes(),
          usuarioService.getGastosPorCategoria()
        ]);

        setSaldo(saldoData);
        setTransacoes(transacoesData);
        setGastosPorCategoria(gastosData);
      } catch (error: any) {
        console.error('Erro ao carregar dados:', error);
        setErro('Erro ao carregar dados. Tente novamente.');
        
        // Se erro 401, redirecionar para login
        if (error.response?.status === 401) {
          authService.logout();
          navigate('/login');
        }
      } finally {
        setCarregando(false);
      }
    };

    carregarDados();
  }, [navigate]);

  // Função para formatar valores em moeda
  const formatarMoeda = (valor: number) => {
    return new Intl.NumberFormat('pt-BR', {
      style: 'currency',
      currency: 'BRL'
    }).format(valor);
  };

  // Mostrar loading
  if (carregando) {
    return (
      <div className="App">
        <Nav />
        <main style={{ padding: '20px', textAlign: 'center' }}>
          <h2>Carregando dados...</h2>
        </main>
      </div>
    );
  }

  // Mostrar erro
  if (erro) {
    return (
      <div className="App">
        <Nav />
        <main style={{ padding: '20px', textAlign: 'center' }}>
          <h2 style={{ color: 'red' }}>{erro}</h2>
          <button onClick={() => window.location.reload()}>Tentar Novamente</button>
        </main>
      </div>
    );
  }

  return (
    <div className="App">
      <Nav /> 
      <main style={{ padding: '20px' }}>

        <div id="saldo-total" className="cards">
          <Card 
            title="Saldo Total"
            description={saldo ? formatarMoeda(saldo.saldoTotal) : 'R$ 0,00'}
          />
          <Card 
            title="Receita (Mês)"
            description={saldo ? formatarMoeda(saldo.totalReceitas) : 'R$ 0,00'}
          />
          <Card 
            title="Despesas (Mês)"
            description={saldo ? formatarMoeda(saldo.totalDespesas) : 'R$ 0,00'}
          />
          <Card 
            title="Investimentos"
            description="Em breve"
          />
        </div>

        <div id="grafico" style={{ marginTop: "30px" }}>
          <Grafico dados={gastosPorCategoria} />
        </div>

        <div id="grafico" style={{ marginTop: "30px" }}>
          <Chart dados={gastosPorCategoria} />
        </div>

        <div id="transacao" style={{ marginTop: "10px" }}>
          <Table transacoes={transacoes} />
        </div>

        <Button /> 

      </main>
    </div>
  );
}

export default Home;