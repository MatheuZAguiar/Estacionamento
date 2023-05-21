package br.com.uniamerica.estacionamento.controller;

import br.com.uniamerica.estacionamento.entity.Configuracao;
import br.com.uniamerica.estacionamento.repository.ConfiguracaoRepository;
import jakarta.validation.Valid;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/api/configuracao")
public class ConfiguracaoController {

    @Autowired
    ConfiguracaoRepository configuracaoRepository;

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id){
        return ResponseEntity.ok().body(this.configuracaoRepository.findById(id));
    }

    @GetMapping("/{ativo}")
    public ResponseEntity<?> findByAtivo(@PathVariable boolean ativo){
        List<Configuracao> configuracoes = this.configuracaoRepository.findByAtivo(ativo);

        if (configuracoes.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok().body(configuracoes);
    }
    @GetMapping
    public ResponseEntity<?> findAll() {
        List<Configuracao> configuracaos = this.configuracaoRepository.findAll();

        if (configuracaos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok().body(configuracaos);
    }


    @PostMapping
    public ResponseEntity<?> cadastrar(@RequestBody @Valid Configuracao configuracao) {
        this.configuracaoRepository.save(configuracao);
        return ResponseEntity.ok().body("Registro cadastrado com sucesso");
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizar(@PathVariable final @NotNull Long id, @RequestBody @Valid final Configuracao configuracao) {
        Optional<Configuracao> configuracaoOptional = configuracaoRepository.findById(id);
        if (configuracaoOptional.isPresent()) {
            Configuracao existingConfiguracao = configuracaoOptional.get();
            configuracaoRepository.save(existingConfiguracao);
            return ResponseEntity.ok().body("Registro atualizado com sucesso");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletar(@PathVariable final Long id) {
        Optional<Configuracao> configuracaoOptional = configuracaoRepository.findById(id);

        if (configuracaoOptional.isPresent()) {
            Configuracao configuracao = configuracaoOptional.get();
            configuracaoRepository.delete(configuracao);
            return ResponseEntity.ok().body("O registro da configuração foi deletado com sucesso");
        } else {
            return ResponseEntity.notFound().build();
        }
    }




}
