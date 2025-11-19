import "../styles/global.css";

export function Button({className = "", ...props }) {
  return (
    <button
      className={`btn ${className}`}
      {...props}
    >
    </button>
  );
}
