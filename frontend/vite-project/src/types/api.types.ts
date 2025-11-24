// Enums
export const TipoTransacao = {
  RECEITA: 'RECEITA',
  DESPESA: 'DESPESA'
} as const;

export type TipoTransacao = typeof TipoTransacao[keyof typeof TipoTransacao];

// Request DTOs
export interface LoginRequest {
  email: string;
  senha: string;
}

export interface RegistrarRequest {
  nome: string;
  email: string;
  senha: string;
}

export interface CriarTransacaoRequest {
  tipo: TipoTransacao;
  valor: number;
  descricao: string;
  categoria: string;
}

export interface AtualizarTransacaoRequest {
  tipo?: TipoTransacao;
  valor?: number;
  descricao?: string;
  categoria?: string;
}

// Response DTOs
export interface LoginResponse {
  token: string;
}

export interface RegistrarResponse {
  nome: string;
  email: string;
}

export interface TransacaoResumo {
  uuidTransacao: string;
  tipo: TipoTransacao;
  valor: number;
  descricao: string;
  categoria: string;
  dataTransacao: string;
}

export interface SaldoResponse {
  saldoTotal: number;
  totalReceitas: number;
  totalDespesas: number;
}

export interface GastoPorCategoria {
  categoria: string;
  total: number;
  quantidadeTransacoes: number;
  percentual: number;
}

export interface ChatResponse {
  resposta: string;
  dataResposta: string;
}