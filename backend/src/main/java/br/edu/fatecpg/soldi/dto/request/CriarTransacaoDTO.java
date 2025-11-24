package br.edu.fatecpg.soldi.dto.request;

import br.edu.fatecpg.soldi.model.TipoTransacao;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record CriarTransacaoDTO(@NotNull(message = "O tipo é obrigatório. (RECEITA ou DESPESA)") TipoTransacao tipo,
                                @NotNull(message = "O valor é obrigatório.") @Positive(message = "O valor deve ser positivo.") BigDecimal valor,
                                String descricao,
                                @NotNull(message = "A categoria é obrigatória.") String categoria) {}
