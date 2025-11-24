package br.edu.fatecpg.soldi.controller;

import br.edu.fatecpg.soldi.dto.response.ChatResponseDTO;
import br.edu.fatecpg.soldi.dto.response.GastoPorCategoriaDTO;
import br.edu.fatecpg.soldi.dto.response.SaldoResponseDTO;
import br.edu.fatecpg.soldi.dto.response.TransacaoResumoDTO;
import br.edu.fatecpg.soldi.service.ChatService;
import br.edu.fatecpg.soldi.service.TransacaoService;
import br.edu.fatecpg.soldi.service.UsuarioService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/usuarios")
@Tag(name = "Usuários", description = "Endpoints relacionados a todas as operações possíveis que o usuário pode realizar na sua própria conta")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;
    private final TransacaoService transacaoService;
    private final ChatService chatService;

    @GetMapping("/me/saldo")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<SaldoResponseDTO> getSaldo() {
        SaldoResponseDTO saldo = usuarioService.getSaldo();
        return ResponseEntity.ok(saldo);
    }

    @GetMapping("/me/transacoes/todas-transacoes")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<List<TransacaoResumoDTO>> listarTodas() {
        List<TransacaoResumoDTO> todasTransacoes = transacaoService.listarTodasTransacoes();
        return ResponseEntity.ok(todasTransacoes);
    }

    @GetMapping("/me/transacoes/ai-insight")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<ChatResponseDTO> getAiInsight() {
        ChatResponseDTO resposta = chatService.getTransactionInsight();
        return ResponseEntity.ok(resposta);
    }

    @GetMapping("/me/transacoes/recentes")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<List<TransacaoResumoDTO>> getTransacoesRecentes() {
        List<TransacaoResumoDTO> transacoes = transacaoService.getTransacoesRecentes();
        return ResponseEntity.ok(transacoes);
    }


    @GetMapping("/me/analytics/gastos-categoria")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<List<GastoPorCategoriaDTO>> getGastosPorCategoria() {
        List<GastoPorCategoriaDTO> gastos = transacaoService.getGastosPorCategoria();
        return ResponseEntity.ok(gastos);
    }
}
