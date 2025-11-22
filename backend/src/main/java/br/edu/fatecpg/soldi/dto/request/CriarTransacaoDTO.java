package br.edu.fatecpg.soldi.dto.request;

import br.edu.fatecpg.soldi.model.TipoTransacao;

import java.math.BigDecimal;
import java.util.UUID;

public record CriarTransacaoDTO(TipoTransacao tipo, BigDecimal valor, String descricao, String categoria) {}
