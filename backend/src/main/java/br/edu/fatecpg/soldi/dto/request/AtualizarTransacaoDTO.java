package br.edu.fatecpg.soldi.dto.request;

import br.edu.fatecpg.soldi.model.TipoTransacao;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record AtualizarTransacaoDTO(@NotNull TipoTransacao tipo,
                                    @NotNull @Positive BigDecimal valor,
                                    String descricao,
                                    @NotBlank String categoria) {}