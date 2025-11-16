package br.edu.fatecpg.soldi.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TransacaoResumoDTO {
    private UUID uuid;
    private String tipo;
    private BigDecimal valor;
    private String descricao;
    private String categoria;
    private LocalDateTime dataTransacao;
}