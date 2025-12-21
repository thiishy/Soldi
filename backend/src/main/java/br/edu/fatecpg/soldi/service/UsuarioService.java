package br.edu.fatecpg.soldi.service;

import br.edu.fatecpg.soldi.dto.response.SaldoResponseDTO;
import br.edu.fatecpg.soldi.exception.ResourceNotFoundException;
import br.edu.fatecpg.soldi.model.TipoTransacao;
import br.edu.fatecpg.soldi.model.Transacao;
import br.edu.fatecpg.soldi.repository.TransacaoRepository;
import br.edu.fatecpg.soldi.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final TransacaoRepository transacaoRepository;


    public SaldoResponseDTO getSaldo(UUID uuidUsuario) {
        if(!usuarioRepository.existsByUuidExterno(uuidUsuario)) throw new ResourceNotFoundException("Usuário não encontrado.");

        // Buscar todas as transações do usuário
        List<Transacao> transacoes = transacaoRepository.findAllByUsuarioUuid(uuidUsuario);

        // Calcular receitas
        BigDecimal totalReceitas = transacoes.stream()
                .filter(t -> t.getTipo() == TipoTransacao.RECEITA)
                .map(Transacao::getValor)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        // Calcular despesas
        BigDecimal totalDespesas = transacoes.stream()
                .filter(t -> t.getTipo() == TipoTransacao.DESPESA)
                .map(Transacao::getValor)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        // Saldo = Receitas - Despesas
        BigDecimal saldoTotal = totalReceitas.subtract(totalDespesas);

        return new SaldoResponseDTO(saldoTotal, totalReceitas, totalDespesas);
    }
}