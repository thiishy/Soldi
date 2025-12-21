package br.edu.fatecpg.soldi.controller;

import br.edu.fatecpg.soldi.dto.request.AtualizarTransacaoDTO;
import br.edu.fatecpg.soldi.dto.request.CriarTransacaoDTO;
import br.edu.fatecpg.soldi.dto.response.TransacaoResumoDTO;
import br.edu.fatecpg.soldi.service.TransacaoService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/transacoes")
@Tag(name = "Transações", description = "Endpoints relacionados a todas as operações possíveis que o usuário pode realizar em transações (CRUD)")
@RequiredArgsConstructor
public class TransacaoController {

    private final TransacaoService transacaoService;

    @PostMapping
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<TransacaoResumoDTO> criar(@AuthenticationPrincipal UUID uuidUsuario, @RequestBody @Valid CriarTransacaoDTO criarTransacaoDto) {
        TransacaoResumoDTO novaTransacao = transacaoService.criarTransacao(uuidUsuario, criarTransacaoDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(novaTransacao);
    }

    @GetMapping("/{uuid}")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<TransacaoResumoDTO> buscarPorUuid(@AuthenticationPrincipal UUID uuidUsuario, @PathVariable("uuid") UUID uuidTransacao) {
        TransacaoResumoDTO transacaoEncontrada = transacaoService.buscarTransacao(uuidUsuario, uuidTransacao);
        return ResponseEntity.ok().body(transacaoEncontrada);
    }

    @PutMapping("/{uuid}")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<TransacaoResumoDTO> atualizar(
            @AuthenticationPrincipal UUID uuidUsuario,
            @PathVariable("uuid") UUID uuidTransacao,
            @RequestBody @Valid AtualizarTransacaoDTO transacaoAtualizada) {

        TransacaoResumoDTO transacaoAtt = transacaoService.atualizarTransacao(uuidUsuario, uuidTransacao, transacaoAtualizada);
        return ResponseEntity.ok().body(transacaoAtt);
    }

    @DeleteMapping("/{uuid}")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<Void> deletar(@AuthenticationPrincipal UUID uuidUsuario, @PathVariable("uuid") UUID uuidTransacao) {
        transacaoService.deletarTransacao(uuidUsuario, uuidTransacao);
        return ResponseEntity.noContent().build();
    }
}