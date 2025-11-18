package br.edu.fatecpg.soldi.controller;

import br.edu.fatecpg.soldi.dto.request.AtualizarTransacaoDTO;
import br.edu.fatecpg.soldi.dto.request.CriarTransacaoDTO;
import br.edu.fatecpg.soldi.dto.response.TransacaoResumoDTO;
import br.edu.fatecpg.soldi.service.TransacaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/transacoes")
@RequiredArgsConstructor
public class TransacaoController {

    private final TransacaoService transacaoService;

    @GetMapping
    public ResponseEntity<List<TransacaoResumoDTO>> listarTodas() {
        List<TransacaoResumoDTO> todasTransacoes = transacaoService.listarTodasTransacoes();
        return ResponseEntity.ok(todasTransacoes);
    }

    @PostMapping
    public ResponseEntity<TransacaoResumoDTO> criar(@RequestBody CriarTransacaoDTO criarTransacaoDto) {
        TransacaoResumoDTO novaTransacao = transacaoService.criarTransacao(criarTransacaoDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(novaTransacao);
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<TransacaoResumoDTO> buscarPorUuid(@PathVariable("uuid") UUID uuidTransacao) {
        TransacaoResumoDTO transacaoEncontrada = transacaoService.buscarTransacao(uuidTransacao);
        return ResponseEntity.ok().body(transacaoEncontrada);
    }

    @PutMapping("/{uuid}")
    public ResponseEntity<TransacaoResumoDTO> atualizar(
            @PathVariable("uuid") UUID uuidTransacao,
            @RequestBody AtualizarTransacaoDTO transacaoAtualizada) {

        TransacaoResumoDTO transacaoAtt = transacaoService.atualizarTransacao(uuidTransacao, transacaoAtualizada);
        return ResponseEntity.ok().body(transacaoAtt);
    }

    @DeleteMapping("/{uuid}")
    public ResponseEntity<Void> deletar(@PathVariable("uuid") UUID uuidTransacao) {
        transacaoService.deletarTransacao(uuidTransacao);
        return ResponseEntity.noContent().build();
    }
}