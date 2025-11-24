import { useState } from "react";
import ChatModal from "../Modal/Modal";
import "./Button.css";

export default function Button() {
  const [open, setOpen] = useState(false);

  return (
    <div className="button-modal">
      <button
        className="chat-floating-button"
        onClick={() => setOpen(true)}
      >
        <img src="src/assets/chat.png" alt="chat" />
      </button>

      {open && <ChatModal onClose={() => setOpen(false)} />}
    </div>
  );
}
