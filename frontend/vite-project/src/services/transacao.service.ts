import api from '../config/api';
import type { 
  CriarTransacaoRequest, 
  AtualizarTransacaoRequest, 
  TransacaoResumo 
} from '../types/api.types';

class TransacaoService {
  /**
   * Criar nova transação
   */
  async criar(data: CriarTransacaoRequest): Promise<TransacaoResumo> {
    const response = await api.post<TransacaoResumo>('/transacoes', data);
    return response.data;
  }

  /**
   * Buscar transação por UUID
   */
  async buscarPorUuid(uuid: string): Promise<TransacaoResumo> {
    const response = await api.get<TransacaoResumo>(`/transacoes/${uuid}`);
    return response.data;
  }

  /**
   * Atualizar transação existente
   */
  async atualizar(uuid: string, data: AtualizarTransacaoRequest): Promise<TransacaoResumo> {
    const response = await api.put<TransacaoResumo>(`/transacoes/${uuid}`, data);
    return response.data;
  }

  /**
   * Deletar transação
   */
  async deletar(uuid: string): Promise<void> {
    await api.delete(`/transacoes/${uuid}`);
  }

  /**
   * Listar todas as transações do usuário
   */
  async listarTodas(): Promise<TransacaoResumo[]> {
    const response = await api.get<TransacaoResumo[]>('/usuarios/me/transacoes/todas-transacoes');
    return response.data;
  }

  /**
   * Listar transações recentes
   */
  async listarRecentes(): Promise<TransacaoResumo[]> {
    const response = await api.get<TransacaoResumo[]>('/usuarios/me/transacoes/recentes');
    return response.data;
  }
}

export default new TransacaoService();