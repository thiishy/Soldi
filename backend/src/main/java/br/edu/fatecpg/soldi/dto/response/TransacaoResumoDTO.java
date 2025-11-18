package br.edu.fatecpg.soldi.dto.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record TransacaoResumoDTO(UUID uuid, String tipo, BigDecimal valor, String descricao, String categoria, LocalDateTime dataTransacao) {}