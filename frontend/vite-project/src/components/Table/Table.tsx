import React from "react";
import "./Table.css";
import type { TransacaoResumo } from "../../types/api.types";

interface TableProps {
  transacoes?: TransacaoResumo[];
}

export const Table: React.FC<TableProps> = ({ transacoes = [] }) => {
  
  const formatCurrency = (value: number) =>
    value.toLocaleString("pt-BR", {
      style: "currency",
      currency: "BRL",
    });

  const formatDate = (dateString: string) => {
    const date = new Date(dateString);
    return date.toLocaleDateString("pt-BR");
  };

  // Calcular total (receitas - despesas)
  const total = transacoes.reduce((acc, t) => {
    return acc + (t.tipo === 'RECEITA' ? t.valor : -t.valor);
  }, 0);

  if (transacoes.length === 0) {
    return (
      <div className="table-container">
        <h2 className="title">Transações Recentes</h2>
        <p style={{ textAlign: 'center', padding: '20px' }}>
          Nenhuma transação encontrada.
        </p>
      </div>
    );
  }

  return (
    <div className="table-container">
      <h2 className="title">Transações Recentes</h2>

      <table className="invoice-table">
        <caption>Lista das suas últimas movimentações financeiras.</caption>

        <thead>
          <tr>
            <th>ID</th>
            <th>Tipo</th>
            <th>Descrição</th>
            <th>Categoria</th>
            <th>Data</th>
            <th className="text-right">Valor</th>
          </tr>
        </thead>

        <tbody>
          {transacoes.map((item) => (
            <tr key={item.uuidTransacao}>
              <td className="bold" data-label="ID">
                {item.uuidTransacao.substring(0, 8)}...
              </td>
              <td 
                data-label="Tipo" 
                style={{ 
                  color: item.tipo === "RECEITA" ? "green" : "red", 
                  fontWeight: 600 
                }}
              >
                {item.tipo === "RECEITA" ? "Receita" : "Despesa"}
              </td>
              <td data-label="Descrição">{item.descricao}</td>
              <td data-label="Categoria">{item.categoria}</td>
              <td data-label="Data">{formatDate(item.dataTransacao)}</td>
              <td 
                className="text-right" 
                data-label="Valor" 
                style={{ 
                  color: item.tipo === "RECEITA" ? "green" : "red" 
                }}
              >
                {formatCurrency(item.valor)}
              </td>
            </tr>
          ))}
        </tbody>

        <tfoot>
          <tr>
            <td colSpan={5}>Saldo das Transações</td>
            <td 
              className="text-right" 
              style={{ color: total >= 0 ? "green" : "red" }}
            >
              {formatCurrency(total)}
            </td>
          </tr>
        </tfoot>
      </table>
    </div>
  );
};