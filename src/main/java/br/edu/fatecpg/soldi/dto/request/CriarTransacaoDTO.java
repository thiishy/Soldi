package br.edu.fatecpg.soldi.dto.request;

import java.math.BigDecimal;

public record CriarTransacaoDTO(String tipo, BigDecimal valor, String descricao, String categoria) {}
