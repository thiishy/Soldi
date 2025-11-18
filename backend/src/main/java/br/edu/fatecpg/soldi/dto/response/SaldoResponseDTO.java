package br.edu.fatecpg.soldi.dto.response;

import java.math.BigDecimal;

public record SaldoResponseDTO(BigDecimal saldoTotal, BigDecimal totalReceitas, BigDecimal totalDespesas) {}