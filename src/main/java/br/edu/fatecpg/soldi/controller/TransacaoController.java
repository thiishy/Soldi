package br.edu.fatecpg.soldi.controller;

import br.edu.fatecpg.soldi.model.Transacao;
import br.edu.fatecpg.soldi.repository.TransacaoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/transacoes")
@RequiredArgsConstructor
public class TransacaoController {

    private final TransacaoRepository transacaoRepository;

    @GetMapping
    public ResponseEntity<List<Transacao>> listarTodas() {
        List<Transacao> transacoes = transacaoRepository.findAll();
        return ResponseEntity.ok(transacoes);
    }

    @PostMapping
    public ResponseEntity<Transacao> criar(@RequestBody Transacao transacao) {
        Transacao novaTrans = transacaoRepository.save(transacao);
        return ResponseEntity.status(HttpStatus.CREATED).body(novaTrans);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Transacao> buscarPorId(@PathVariable Long id) {
        return transacaoRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }


    @PutMapping("/{id}")
    public ResponseEntity<Transacao> atualizar(
            @PathVariable Long id,
            @RequestBody Transacao transacaoAtualizada) {

        return transacaoRepository.findById(id)
                .map(transacao -> {
                    transacao.setTipo(transacaoAtualizada.getTipo());
                    transacao.setValor(transacaoAtualizada.getValor());
                    transacao.setDescricao(transacaoAtualizada.getDescricao());
                    transacao.setCategoria(transacaoAtualizada.getCategoria());
                    Transacao salva = transacaoRepository.save(transacao);
                    return ResponseEntity.ok(salva);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        if (transacaoRepository.existsById(id)) {
            transacaoRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}