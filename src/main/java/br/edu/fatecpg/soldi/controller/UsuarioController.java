package br.edu.fatecpg.soldi.controller;

import br.edu.fatecpg.soldi.dto.response.GastoPorCategoriaDTO;
import br.edu.fatecpg.soldi.dto.response.SaldoResponseDTO;
import br.edu.fatecpg.soldi.dto.response.TransacaoResumoDTO;
import br.edu.fatecpg.soldi.service.TransacaoService;
import br.edu.fatecpg.soldi.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;
    private final TransacaoService transacaoService;


    @GetMapping("/{uuid}/saldo")
    public ResponseEntity<SaldoResponseDTO> getSaldo(@PathVariable("uuid") UUID uuidUsuario) {
        SaldoResponseDTO saldo = usuarioService.getSaldo(uuidUsuario);
        return ResponseEntity.ok(saldo);
    }

    /**
     * GET /api/v1/usuarios/{uuid}/transacoes/recentes
     * Retorna as últimas 5 transações do usuário
     */
    @GetMapping("/{uuid}/transacoes/recentes")
    public ResponseEntity<List<TransacaoResumoDTO>> getTransacoesRecentes(
            @PathVariable("uuid") UUID uuidUsuario) {
        List<TransacaoResumoDTO> transacoes = transacaoService.getTransacoesRecentes(uuidUsuario);
        return ResponseEntity.ok(transacoes);
    }

    /**
     * GET /api/v1/usuarios/{uuid}/analytics/gastos-categoria
     * Retorna gastos agrupados por categoria para o gráfico de pizza
     */
    @GetMapping("/{uuid}/analytics/gastos-categoria")
    public ResponseEntity<List<GastoPorCategoriaDTO>> getGastosPorCategoria(
            @PathVariable("uuid") UUID uuidUsuario) {
        List<GastoPorCategoriaDTO> gastos = transacaoService.getGastosPorCategoria(uuidUsuario);
        return ResponseEntity.ok(gastos);
    }
}