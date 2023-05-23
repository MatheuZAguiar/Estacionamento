package br.com.uniamerica.estacionamento.controller;

import br.com.uniamerica.estacionamento.entity.Condutor;
import br.com.uniamerica.estacionamento.service.CondutorService;
import jakarta.validation.Valid;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/condutor")
public class CondutorController {

    @Autowired
    private final CondutorService condutorService;

    public CondutorController(CondutorService condutorService) {
        this.condutorService = condutorService;
    }

    @GetMapping("/ativo/{ativo}")
    public ResponseEntity<?> findByAtivo(@PathVariable boolean ativo) {
        List<Condutor> condutores = condutorService.buscarCondutoresPorAtivo(ativo);

        if (condutores.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok().body(condutores);
    }

    @GetMapping
    public ResponseEntity<?> findAll() {
        List<Condutor> condutores = condutorService.buscarTodosCondutores();

        if (condutores.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok().body(condutores);
    }

    @PostMapping
    public ResponseEntity<?> cadastrar(@RequestBody @Valid Condutor condutor) {
        Condutor novoCondutor = condutorService.criarCondutor(condutor);
        return ResponseEntity.ok().body("Registro cadastrado com sucesso");
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizar(@PathVariable final @NotNull Long id, @RequestBody @Valid Condutor condutor) {
        Condutor condutorAtualizado = condutorService.atualizarCondutor(id, condutor);

        if (condutorAtualizado != null) {
            return ResponseEntity.ok().body("Registro atualizado com sucesso");
        } else {
            return ResponseEntity.badRequest().body("ID não encontrado");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletar(@PathVariable final Long id) {
        boolean removido = condutorService.excluirCondutor(id);

        if (removido) {
            return ResponseEntity.ok().body("O registro do condutor foi deletado com sucesso");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
