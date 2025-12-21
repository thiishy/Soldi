package br.edu.fatecpg.soldi.controller;

import br.edu.fatecpg.soldi.dto.response.*;
import br.edu.fatecpg.soldi.service.ChatService;
import br.edu.fatecpg.soldi.service.TransacaoService;
import br.edu.fatecpg.soldi.service.UsuarioService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

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
    public ResponseEntity<SaldoResponseDTO> getSaldo(@AuthenticationPrincipal UUID uuidUsuario) {
        SaldoResponseDTO saldo = usuarioService.getSaldo(uuidUsuario);
        return ResponseEntity.ok(saldo);
    }

    @GetMapping("/me/transacoes/todas-transacoes")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<List<TransacaoResumoDTO>> listarTodas(@AuthenticationPrincipal UUID uuidUsuario) {
        List<TransacaoResumoDTO> todasTransacoes = transacaoService.listarTodasTransacoes(uuidUsuario);
        return ResponseEntity.ok(todasTransacoes);
    }

    @GetMapping("/me/transacoes/ai-insight")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<ChatResponseDTO> getAiInsight(@AuthenticationPrincipal UUID uuidUsuario) {
        ChatResponseDTO resposta = chatService.getTransactionInsight(uuidUsuario);
        return ResponseEntity.ok(resposta);
    }

    @GetMapping("/me/transacoes/recentes")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<List<TransacaoResumoDTO>> getTransacoesRecentes(@AuthenticationPrincipal UUID uuidUsuario) {
        List<TransacaoResumoDTO> transacoes = transacaoService.getTransacoesRecentes(uuidUsuario);
        return ResponseEntity.ok(transacoes);
    }


    @GetMapping("/me/analytics/gastos-categoria")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<List<GastoPorCategoriaDTO>> getGastosPorCategoria(@AuthenticationPrincipal UUID uuidUsuario) {
        List<GastoPorCategoriaDTO> gastos = transacaoService.getGastosPorCategoria(uuidUsuario);
        return ResponseEntity.ok(gastos);
    }

    @GetMapping("/me/analytics/gastos-mensais")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<List<GastoMensalDTO>> getGastosMensais(@AuthenticationPrincipal UUID uuidUsuario) {
        List<GastoMensalDTO> gastoMensal = transacaoService.getGastosMensais(uuidUsuario);
        return ResponseEntity.ok(gastoMensal);
    }
}
