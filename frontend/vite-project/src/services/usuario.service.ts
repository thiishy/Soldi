import api from '../config/api';
import type { 
  SaldoResponse, 
  GastoPorCategoria, 
  ChatResponse 
} from '../types/api.types';

class UsuarioService {
  /**
   * Obter saldo do usuário
   */
  async getSaldo(): Promise<SaldoResponse> {
    const response = await api.get<SaldoResponse>('/usuarios/me/saldo');
    return response.data;
  }

  /**
   * Obter gastos por categoria
   */
  async getGastosPorCategoria(): Promise<GastoPorCategoria[]> {
    const response = await api.get<GastoPorCategoria[]>('/usuarios/me/analytics/gastos-categoria');
    return response.data;
  }

  /**
   * Obter insights de IA sobre transações
   */
  async getAiInsight(): Promise<ChatResponse> {
    const response = await api.get<ChatResponse>('/usuarios/me/transacoes/ai-insight');
    return response.data;
  }
}

export default new UsuarioService();