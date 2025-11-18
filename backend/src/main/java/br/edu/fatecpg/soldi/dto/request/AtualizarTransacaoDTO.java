package br.edu.fatecpg.soldi.dto.request;

import java.math.BigDecimal;

public record AtualizarTransacaoDTO(String tipo, BigDecimal valor, String descricao, String categoria) {}