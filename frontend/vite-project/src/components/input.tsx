import * as React from "react"
import "../styles/global.css";

export function Input({ className="", type, ...props }: React.ComponentProps<"input">) {
  return (
    <input
      type={type}
      className={`input-base ${className}`}
      {...props}
    />
  );
}

