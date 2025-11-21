import React from "react";
import "./Table.css";

type Transaction = {
  id: string;
  type: "entrada" | "saida";
  description: string;
  value: number;
  date: string;
};

const transactions: Transaction[] = [
  { id: "TR001", type: "entrada", description: "Salário Mensal", value: 4500, date: "01/11/2025" },
  { id: "TR002", type: "saida", description: "Supermercado", value: -320, date: "03/11/2025" },
  { id: "TR003", type: "saida", description: "Conta de Luz", value: -140, date: "04/11/2025" },
  { id: "TR004", type: "entrada", description: "Freelancer Website", value: 800, date: "06/11/2025" },
  { id: "TR005", type: "saida", description: "Academia", value: -90, date: "07/11/2025" },
  { id: "TR006", type: "entrada", description: "Venda de Produto", value: 350, date: "09/11/2025" },
];

export const Table: React.FC = () => {
  const total = transactions.reduce((acc, t) => acc + t.value, 0);

  const formatCurrency = (value: number) =>
    value.toLocaleString("pt-BR", {
      style: "currency",
      currency: "BRL",
    });

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
            <th>Data</th>
            <th className="text-right">Valor</th>
          </tr>
        </thead>

    <tbody>
      {transactions.map((item) => (
        <tr key={item.id}>
          <td className="bold" data-label="ID">{item.id}</td>
          <td 
            data-label="Tipo" 
            style={{ color: item.type === "entrada" ? "green" : "red", fontWeight: 600 }}
          >
            {item.type === "entrada" ? "Entrada" : "Saída"}
          </td>
          <td data-label="Descrição">{item.description}</td> 
          <td data-label="Data">{item.date}</td> 
          <td 
            className="text-right" 
            data-label="Valor" 
            style={{ color: item.value >= 0 ? "green" : "red" }}
          >
            {formatCurrency(item.value)}
          </td>
        </tr>
      ))}
    </tbody>
        <tfoot>
          <tr>
            <td colSpan={4}>Total Geral</td>
            <td className="text-right" style={{ color: total >= 0 ? "green" : "red" }}>
              {formatCurrency(total)}
            </td>
          </tr>
        </tfoot>
      </table>
    </div>
  );
};
