package br.edu.fatecpg.soldi.controller;

import br.edu.fatecpg.soldi.dto.response.ChatResponseDTO;
import br.edu.fatecpg.soldi.dto.response.GastoPorCategoriaDTO;
import br.edu.fatecpg.soldi.dto.response.SaldoResponseDTO;
import br.edu.fatecpg.soldi.dto.response.TransacaoResumoDTO;
import br.edu.fatecpg.soldi.service.ChatService;
import br.edu.fatecpg.soldi.service.TransacaoService;
import br.edu.fatecpg.soldi.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;
    private final TransacaoService transacaoService;
    private final ChatService chatService;

    @GetMapping("/me/saldo")
    public ResponseEntity<SaldoResponseDTO> getSaldo() {
        SaldoResponseDTO saldo = usuarioService.getSaldo();
        return ResponseEntity.ok(saldo);
    }

    @GetMapping("/me/transacoes/todas-transacoes")
    public ResponseEntity<List<TransacaoResumoDTO>> listarTodas() {
        List<TransacaoResumoDTO> todasTransacoes = transacaoService.listarTodasTransacoes();
        return ResponseEntity.ok(todasTransacoes);
    }

    @GetMapping("/me/transacoes/ai-insight")
    public ResponseEntity<ChatResponseDTO> getAiInsight() {
        ChatResponseDTO resposta = chatService.getTransactionInsight();
        return ResponseEntity.ok(resposta);
    }

    @GetMapping("/me/transacoes/recentes")
    public ResponseEntity<List<TransacaoResumoDTO>> getTransacoesRecentes() {
        List<TransacaoResumoDTO> transacoes = transacaoService.getTransacoesRecentes();
        return ResponseEntity.ok(transacoes);
    }


    @GetMapping("/me/analytics/gastos-categoria")
    public ResponseEntity<List<GastoPorCategoriaDTO>> getGastosPorCategoria() {
        List<GastoPorCategoriaDTO> gastos = transacaoService.getGastosPorCategoria();
        return ResponseEntity.ok(gastos);
    }
}
