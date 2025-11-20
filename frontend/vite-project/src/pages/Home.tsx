import "../styles/Home.css";
import { Card } from "../components/Card/Card";
import { Nav } from "../components/Sidebar/Sidebar"; 
import { Table } from "../components/Table/Table";
import Grafico  from "../components/Grafico/Grafico"; //default nao tem {}

function App() {
  return (
    <div className="App">
      <Nav/> 
      <main style={{ padding: '20px' }}>

        <div className="cards">
          <Card 
            title="Saldo Total"
            description="..."
          />
          <Card 
            title="Receita (Mês)"
            description="..."
          />
          <Card 
            title="Despesas (Mês)"
            description="..."
          />
          <Card 
            title="Investimentos"
            description="..."
          />
        </div>

        <div style={{ marginTop: "30px" }}>
          <Grafico />
        </div>

        <div style={{ marginTop: "30px" }}>
          <Table />
        </div>

      </main>
    </div>
  );
}

export default App;
