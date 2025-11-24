import { useState, useEffect } from 'react';

// Hook para gerenciar o token JWT

export const useToken = () => {
  const [token, setToken] = useState<string | null>(null);

  // Carregar token do localStorage quando o componente montar
  useEffect(() => {
    const storedToken = localStorage.getItem('token');
    if (storedToken) {
      setToken(storedToken);
    }
  }, []);

  // Salvar token
  const saveToken = (newToken: string) => {
    localStorage.setItem('token', newToken);
    setToken(newToken);
  };

  // Remover token
  const removeToken = () => {
    localStorage.removeItem('token');
    setToken(null);
  };

  // Obter token
  const getToken = () => {
    return token || localStorage.getItem('token');
  };

  return {
    token,
    saveToken,
    removeToken,
    getToken,
  };
};