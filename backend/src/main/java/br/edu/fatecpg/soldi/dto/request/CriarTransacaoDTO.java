package br.edu.fatecpg.soldi.dto.request;

import java.math.BigDecimal;
import java.util.UUID;

public record CriarTransacaoDTO(String tipo, BigDecimal valor, String descricao, String categoria, UUID uuidUsuario) {}
