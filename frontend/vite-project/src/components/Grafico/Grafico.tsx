import "./Grafico.css";
import { XAxis, YAxis, CartesianGrid, Tooltip, ResponsiveContainer, Area, AreaChart } from 'recharts';
import type { GastoPorCategoria } from "../../types/api.types";

interface GraficoProps {
  dados?: GastoPorCategoria[];
}

export default function FluxoCaixaChart({ dados = [] }: GraficoProps) {
  
  const chartData = dados.map((item) => ({
    name: item.categoria,
    saida: item.total,
    entrada: 0,
  }));

  if (chartData.length === 0) {
    return (
      <div className="fluxo-container" style={{ 
        display: 'flex', 
        justifyContent: 'center', 
        alignItems: 'center',
        minHeight: '300px'
      }}>
        <p>Nenhum dado disponível para exibir o gráfico.</p>
      </div>
    );
  }

  return (
    <div className="fluxo-container">
      <h2>Gastos por Categoria</h2>
      <ResponsiveContainer>
        <AreaChart data={chartData}> 
          <defs>
            <linearGradient id="colorEntrada" x1="0" y1="0" x2="0" y2="1">
              <stop offset="5%" stopColor="#00b96b" stopOpacity={0.3}/>
              <stop offset="95%" stopColor="#00b96b" stopOpacity={0}/>
            </linearGradient>

            <linearGradient id="colorSaida" x1="0" y1="0" x2="0" y2="1">
              <stop offset="5%" stopColor="#ff4d4f" stopOpacity={0.3}/>
              <stop offset="95%" stopColor="#ff4d4f" stopOpacity={0}/>
            </linearGradient>
          </defs>

          <CartesianGrid strokeDasharray="3 3" />
          <XAxis dataKey="name" />
          <YAxis width={70} tickFormatter={(value: number) => `R$ ${value}`} />
          <Tooltip />

          <Area 
            type="monotone" 
            dataKey="saida" 
            stroke="#ff4d4f" 
            fill="url(#colorSaida)" 
          />

          <Area 
            type="monotone" 
            dataKey="entrada" 
            stroke="#00b96b" 
            fill="url(#colorEntrada)" 
          />
        </AreaChart>
      </ResponsiveContainer>
    </div>
  );
}