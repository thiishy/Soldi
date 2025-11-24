import "./Modal.css";

interface ModalProps {
  onClose: () => void;
}

export default function Modal({ onClose }: ModalProps) {
  return (
    <div className="chatbox-container">
      <div className="chatbox">
        <div className="chatbox-header">
          <span>SoldIA • Assistente</span>
          <button className="close-btn" onClick={onClose}>×</button>
        </div>

        <div className="chatbox-body">
          <div className="message bot-message">
            <p className="message-text">
              Claro! Aqui está seu histórico de transações recentes:
              <br /><br />
              🔹 Compra mercado — R$ 82,00  
              🔹 Uber — R$ 14,30  
              🔹 Restaurante — R$ 45,00  
            </p>
          </div>

        </div>

      </div>
    </div>
  );
}
