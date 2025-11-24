import React, { useState } from 'react';
import styles from './Sidebar.module.css';

export const Nav: React.FC = () => {
const [isOpen, setIsOpen] = useState(false); // isOpen indica se ta aberta ou fechada e setIsOpen atualiza

    const toggleSidebar = () => { //toggleSideBar handler que altera entre aberto e fechado
    setIsOpen(!isOpen);
  };

return (
    <>
     
  <button 
     className={styles.hamburger} 
     onClick={toggleSidebar} 
     aria-label="Abrir menu">
     <img src="src/assets/hamburgericon.png" alt="Ícone do menu hambúrguer" />
  </button>

     <nav className={`${styles.nav} ${isOpen ? styles.open : ''}`}>
      <div className={styles.content}>
        {/* <h1 className={styles.title}>Soldi</h1> */}
        <br></br>
        <ul className={styles.links}>
          <li>
            <a href="#saldo-total" className={styles.link}> 
              <img src="src/assets/saldo.png" alt="Ícone do Saldo"/>
              Saldo</a>
          </li>
          <li>
            <a href="#transacao" className={styles.link}>
               <img src="src/assets/transacoes.png" alt="Ícone das Transações"/>
               Transações</a>
          </li>
          <li>
            <a href="#grafico" className={styles.link}>
               <img src="src/assets/grafico.png" alt="Ícone do Gráfico"/>
              Gráfico</a>
          </li>
          <li>
            <a href="#" className={styles.link}>
               <img src="src/assets/login.png" alt="Ícone de Login"/>
              Login</a>
          </li>
          <li>
            <a href="#" className={styles.link}>
               <img src="src/assets/logout.png" alt="Ícone de Logout"/>
              Logout</a>
          </li>
        </ul>
      </div>
    </nav>
    </>
  );
};