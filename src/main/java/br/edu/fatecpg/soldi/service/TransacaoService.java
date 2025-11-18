package br.edu.fatecpg.soldi.service;

import br.edu.fatecpg.soldi.dto.request.AtualizarTransacaoDTO;
import br.edu.fatecpg.soldi.dto.request.CriarTransacaoDTO;
import br.edu.fatecpg.soldi.dto.response.GastoPorCategoriaDTO;
import br.edu.fatecpg.soldi.dto.response.TransacaoResumoDTO;
import br.edu.fatecpg.soldi.exception.ResourceNotFoundException;
import br.edu.fatecpg.soldi.model.Transacao;
import br.edu.fatecpg.soldi.model.Usuario;
import br.edu.fatecpg.soldi.repository.TransacaoRepository;
import br.edu.fatecpg.soldi.repository.UsuarioRepository;
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
    private final UsuarioRepository usuarioRepository;

    /**
     * Retorna as últimas 5 transações de um usuário
     */
    public List<TransacaoResumoDTO> getTransacoesRecentes(UUID uuidUsuario) {
        List<Transacao> transacoes = transacaoRepository
                .buscarUltimasCinco(uuidUsuario);

        return transacoes.stream()
                .map(this::converterParaResumoDTO)
                .collect(Collectors.toList());
    }


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
                .sorted(Comparator.comparing(GastoPorCategoriaDTO::total).reversed())
                .collect(Collectors.toList());
    }


    private TransacaoResumoDTO converterParaResumoDTO(Transacao transacao) {
        return new TransacaoResumoDTO(
                transacao.getUuidExterno(),
                transacao.getTipo(),
                transacao.getValor(),
                transacao.getDescricao(),
                transacao.getCategoria(),
                transacao.getDataTransacao()
        );
    }

    public List<TransacaoResumoDTO> listarTodasTransacoes() {
        return transacaoRepository.findAll()
                .stream().map(t -> new TransacaoResumoDTO(t.getUuidExterno(), t.getTipo(), t.getValor(), t.getDescricao(), t.getCategoria(), t.getDataTransacao()))
                .toList();
    }

    public TransacaoResumoDTO criarTransacao(CriarTransacaoDTO criarTransacaoDto) {
        Usuario usuario = usuarioRepository.findByUuidExterno(criarTransacaoDto.uuidUsuario())
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado."));

        Transacao transacao = new Transacao();
        transacao.setTipo(criarTransacaoDto.tipo());
        transacao.setValor(criarTransacaoDto.valor());
        transacao.setDescricao(criarTransacaoDto.descricao());
        transacao.setCategoria(criarTransacaoDto.categoria());
        transacao.setUsuario(usuario);

        transacaoRepository.save(transacao);

        return converterParaResumoDTO(transacao);
    }

    public TransacaoResumoDTO buscarTransacao(UUID uuidTransacao) {
        Transacao transacao = transacaoRepository.findByUuidExterno(uuidTransacao)
                .orElseThrow(() -> new ResourceNotFoundException("Transação não encontrada."));

        return converterParaResumoDTO(transacao);
    }

    public TransacaoResumoDTO atualizarTransacao(UUID uuidTransacao, AtualizarTransacaoDTO transacaoAtualizada) {
        Transacao transacao = transacaoRepository.findByUuidExterno(uuidTransacao)
                .orElseThrow(() -> new ResourceNotFoundException("Transação não encontrada."));

        transacao.setTipo(transacaoAtualizada.tipo());
        transacao.setValor(transacaoAtualizada.valor());
        transacao.setDescricao(transacaoAtualizada.descricao());
        transacao.setCategoria(transacaoAtualizada.categoria());

        transacaoRepository.save(transacao);
        return converterParaResumoDTO(transacao);
    }

    public void deletarTransacao(UUID uuidTransacao) {
        Transacao transacao = transacaoRepository.findByUuidExterno(uuidTransacao)
                .orElseThrow(() -> new ResourceNotFoundException("Transação não encontrada."));

        transacaoRepository.delete(transacao);
    }

}