package br.edu.fatecpg.soldi.dto.response;

import java.math.BigDecimal;

public record GastoPorCategoriaDTO(String categoria, BigDecimal total, Long quantidadeTransacoes, Double percentual) {}