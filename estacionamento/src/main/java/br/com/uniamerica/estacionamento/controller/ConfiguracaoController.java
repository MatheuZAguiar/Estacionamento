package br.com.uniamerica.estacionamento.controller;

import br.com.uniamerica.estacionamento.entity.Configuracao;
import br.com.uniamerica.estacionamento.service.ConfiguracaoService;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/configuracao")
public class ConfiguracaoController {

    private final ConfiguracaoService configuracaoService;

    @Autowired
    public ConfiguracaoController(ConfiguracaoService configuracaoService) {
        this.configuracaoService = configuracaoService;
    }

    @GetMapping
    public ResponseEntity<List<Configuracao>> findAll() {
        List<Configuracao> configuracoes = configuracaoService.buscarTodasConfiguracoes();
        if (configuracoes.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(configuracoes);
    }

    @PostMapping
    public ResponseEntity<String> cadastrar(@RequestBody Configuracao configuracao) {
        configuracaoService.atualizarConfiguracao(configuracao);
        return ResponseEntity.ok("Registro cadastrado com sucesso");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> atualizar(@PathVariable final @NotNull Long id, @RequestBody final Configuracao configuracao) {
        Configuracao configuracaoExistente = configuracaoService.buscarConfiguracao();
        if (configuracaoExistente != null && id.equals(configuracaoExistente.getId())) {
            configuracaoService.atualizarConfiguracao(configuracao);
            return ResponseEntity.ok("Registro atualizado com sucesso");
        } else {
            return ResponseEntity.badRequest().body("ID não encontrado");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable final @NotNull Long id) {
        Configuracao configuracao = configuracaoService.buscarConfiguracao();
        if (configuracao != null && id.equals(configuracao.getId())) {
            configuracaoService.deletarConfiguracao(configuracao);
            return ResponseEntity.ok("Registro deletado com sucesso");
        } else {
            return ResponseEntity.badRequest().body("ID não encontrado");
        }
    }
}
