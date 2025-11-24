import { Chart as ChartJS } from "chart.js/auto";
import { Bar, Doughnut } from "react-chartjs-2";
import type { GastoPorCategoria } from "../../types/api.types";
import "./Chart.css";

interface ChartProps {
  dados?: GastoPorCategoria[];
}

export const Chart = ({ dados = [] }: ChartProps) => {
  
  if (dados.length === 0) {
    return (
      <div className="chartContainer">
        <p style={{ textAlign: 'center', padding: '20px' }}>
          Nenhum dado disponível para exibir os gráficos.
        </p>
      </div>
    );
  }

  const backgroundColors = [
    "rgba(53, 162, 235, 0.5)",
    "rgba(59, 235, 53, 0.5)",
    "rgba(235, 208, 53, 0.5)",
    "rgba(235, 156, 53, 0.5)",
    "rgba(235, 53, 190, 0.5)",
    "rgba(255, 99, 132, 0.5)",
  ];

  const borderColors = [
    "rgba(53, 162, 235, 1)",
    "rgba(59, 235, 53, 1)",
    "rgba(235, 208, 53, 1)",
    "rgba(235, 156, 53, 1)",
    "rgba(235, 53, 190, 1)",
    "rgba(255, 99, 132, 1)",
  ];

  return (
    <div className="chartContainer">
      <div className="dataCard bar customerCard">
        <h3 style={{ textAlign: 'center', marginBottom: '10px' }}>
          Gastos por Categoria
        </h3>
        <Bar 
          data={{
            labels: dados.map((data) => data.categoria),
            datasets: [
              {
                label: "Valor Gasto (R$)",
                data: dados.map((data) => data.total),
                backgroundColor: backgroundColors.slice(0, dados.length),
                borderColor: borderColors.slice(0, dados.length),
                borderWidth: 1,
                borderRadius: 5,
              },
            ],
          }}
          options={{
            responsive: true,
            plugins: {
              legend: {
                display: true,
                position: 'top',
              },
              tooltip: {
                callbacks: {
                  label: function(context: any) {
                    return `R$ ${context.parsed.y.toFixed(2)}`;
                  }
                }
              }
            },
            scales: {
              y: {
                beginAtZero: true,
                ticks: {
                  callback: function(value: any) {
                    return 'R$ ' + value;
                  }
                }
              }
            }
          }}
        />
      </div>
      
      <div className="dataCard donut customerCard">
        <h3 style={{ textAlign: 'center', marginBottom: '10px' }}>
          Distribuição de Gastos
        </h3>
        <Doughnut
          data={{
            labels: dados.map((data) => data.categoria),
            datasets: [
              {
                label: "Percentual",
                data: dados.map((data) => data.percentual),
                backgroundColor: backgroundColors.slice(0, dados.length),
                borderColor: borderColors.slice(0, dados.length),
                borderWidth: 2,
              },
            ]
          }}
          options={{
            responsive: true,
            plugins: {
              legend: {
                display: true,
                position: 'right',
              },
              tooltip: {
                callbacks: {
                  label: function(context: any) {
                    const categoria = context.label || '';
                    const percentual = context.parsed;
                    const item = dados.find(d => d.categoria === categoria);
                    const valor = item ? item.total : 0;
                    return [
                      `${categoria}`,
                      `${percentual.toFixed(1)}%`,
                      `R$ ${valor.toFixed(2)}`
                    ];
                  }
                }
              }
            }
          }}
        />
      </div>
    </div>
  );
};