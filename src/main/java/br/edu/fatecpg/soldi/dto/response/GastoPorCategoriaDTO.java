package br.edu.fatecpg.soldi.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GastoPorCategoriaDTO {
    private String categoria;
    private BigDecimal total;
    private Long quantidadeTransacoes;
    private Double percentual; // Percentual em relação ao total de despesas
}