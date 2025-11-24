import api from '../config/api';
import type { LoginRequest, LoginResponse, RegistrarRequest, RegistrarResponse } from '../types/api.types';

class AuthService {
  // Fazer login
  async login(credentials: LoginRequest): Promise<LoginResponse> {
    const response = await api.post<LoginResponse>('/auth/login', credentials);
    
    // Salvar token no localStorage
    if (response.data.token) {
      localStorage.setItem('token', response.data.token);
    }
    
    return response.data;
  }

  // Fazer registro
  async registrar(data: RegistrarRequest): Promise<RegistrarResponse> {
    const response = await api.post<RegistrarResponse>('/auth/registrar', data);
    return response.data;
  }

  // Fazer logout
  logout(): void {
    localStorage.removeItem('token');
  }

  // Verificar se está autenticado
  isAuthenticated(): boolean {
    return !!localStorage.getItem('token');
  }
}

export default new AuthService();