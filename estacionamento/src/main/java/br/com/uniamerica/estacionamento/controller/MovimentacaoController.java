package br.com.uniamerica.estacionamento.controller;

import br.com.uniamerica.estacionamento.entity.Movimentacao;
import br.com.uniamerica.estacionamento.service.MovimentacaoService;
import jakarta.validation.Valid;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/api/movimentacao")
public class MovimentacaoController {

    @Autowired
    private final MovimentacaoService movimentacaoService;

    @Autowired
    public MovimentacaoController(MovimentacaoService movimentacaoService) {
        this.movimentacaoService = movimentacaoService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id){
        Optional<Movimentacao> optionalMovimentacao = movimentacaoService.buscarMovimentacaoPorId(id);
        if (optionalMovimentacao.isPresent()) {
            return ResponseEntity.ok().body(optionalMovimentacao.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/ativo/{ativo}")
    public ResponseEntity<?> findByAtivo(@PathVariable boolean ativo){
        List<Movimentacao> movimentacaos = movimentacaoService.buscarMovimentacoesPorAtivo(ativo);
        if (movimentacaos.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok().body(movimentacaos);
        }
    }

    @GetMapping
    public ResponseEntity<?> findAll() {
        List<Movimentacao> movimentacaos = movimentacaoService.buscarTodasMovimentacoes();
        if (movimentacaos.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok().body(movimentacaos);
        }
    }

    @PostMapping
    public ResponseEntity<?> cadastrar(@RequestBody @Valid Movimentacao movimentacao) {
        Movimentacao novaMovimentacao = movimentacaoService.cadastrarMovimentacao(movimentacao);
        return ResponseEntity.ok().body("Registro cadastrado com sucesso");
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizar(@PathVariable final @NotNull Long id, @RequestBody @Valid Movimentacao movimentacao) {
        Movimentacao movimentacaoAtualizada = movimentacaoService.atualizarMovimentacao(id, movimentacao);
        if (movimentacaoAtualizada != null) {
            return ResponseEntity.ok().body("Registro atualizado com sucesso");
        } else {
            return ResponseEntity.badRequest().body("Id não foi encontrado");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletar(@PathVariable Long id) {
        boolean sucesso = movimentacaoService.excluirMovimentacao(id);
        if (sucesso) {
            return ResponseEntity.ok("Registro excluído com sucesso");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
