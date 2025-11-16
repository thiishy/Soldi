package br.edu.fatecpg.soldi.service;

import br.edu.fatecpg.soldi.dto.response.GastoPorCategoriaDTO;
import br.edu.fatecpg.soldi.dto.response.TransacaoResumoDTO;
import br.edu.fatecpg.soldi.exception.ResourceNotFoundException;
import br.edu.fatecpg.soldi.model.Transacao;
import br.edu.fatecpg.soldi.repository.TransacaoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TransacaoService {

    private final TransacaoRepository transacaoRepository;

    /**
     * Retorna as últimas 5 transações de um usuário
     */
    public List<TransacaoResumoDTO> getTransacoesRecentes(UUID uuidUsuario) {
        List<Transacao> transacoes = transacaoRepository
                .findTop5ByUsuario_UuidExternoOrderByDataTransacaoDesc(uuidUsuario);

        return transacoes.stream()
                .map(this::converterParaResumoDTO)
                .collect(Collectors.toList());
    }

    /**
     * Calcula gastos agrupados por categoria (apenas DESPESAS)
     */
    public List<GastoPorCategoriaDTO> getGastosPorCategoria(UUID uuidUsuario) {
        List<Transacao> despesas = transacaoRepository
                .findByUsuarioUuidAndTipo(uuidUsuario, "DESPESA");

        if (despesas.isEmpty()) {
            return Collections.emptyList();
        }

        // Calcular total de despesas
        BigDecimal totalDespesas = despesas.stream()
                .map(Transacao::getValor)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        // Agrupar por categoria
        Map<String, List<Transacao>> porCategoria = despesas.stream()
                .collect(Collectors.groupingBy(Transacao::getCategoria));

        // Converter para DTO
        return porCategoria.entrySet().stream()
                .map(entry -> {
                    String categoria = entry.getKey();
                    List<Transacao> transacoes = entry.getValue();

                    BigDecimal totalCategoria = transacoes.stream()
                            .map(Transacao::getValor)
                            .reduce(BigDecimal.ZERO, BigDecimal::add);

                    Long quantidade = (long) transacoes.size();

                    Double percentual = totalCategoria
                            .divide(totalDespesas, 4, RoundingMode.HALF_UP)
                            .multiply(BigDecimal.valueOf(100))
                            .doubleValue();

                    return new GastoPorCategoriaDTO(categoria, totalCategoria, quantidade, percentual);
                })
                .sorted(Comparator.comparing(GastoPorCategoriaDTO::getTotal).reversed())
                .collect(Collectors.toList());
    }

    /**
     * Converte Transacao para TransacaoResumoDTO
     */
    private TransacaoResumoDTO converterParaResumoDTO(Transacao transacao) {
        return new TransacaoResumoDTO(
                transacao.getUuid_externo(),
                transacao.getTipo(),
                transacao.getValor(),
                transacao.getDescricao(),
                transacao.getCategoria(),
                transacao.getData_transacao()
        );
    }
}