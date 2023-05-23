package br.com.uniamerica.estacionamento.controller;


import br.com.uniamerica.estacionamento.entity.Modelo;
import br.com.uniamerica.estacionamento.service.ModeloService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/modelo")
public class ModeloController {

    @Autowired
    private final ModeloService modeloService;
    @Autowired
    public ModeloController(ModeloService modeloService) {
        this.modeloService = modeloService;
    }
    @GetMapping("/{id}")
    public ResponseEntity<Modelo> findById(@PathVariable Long id) {
        return ResponseEntity.ok().body(this.modeloService.buscarModeloPorId(id));
    }
    @GetMapping
    public ResponseEntity<List<Modelo>> findAll() {
        List<Modelo> modelos = this.modeloService.buscarModelos();

        if (modelos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok().body(modelos);
    }
    @PostMapping
    public ResponseEntity<?> cadastrar(@RequestBody @Valid Modelo modelo) {
        this.modeloService.criarModelo(modelo.getNomeModelo(), modelo.getMarca(), modelo.isAtivo());
        return ResponseEntity.ok().body("Registro cadastrado com sucesso");
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> atualizarModelo(@PathVariable Long id, @RequestBody Modelo modeloAtualizado) {
        Modelo modelo = modeloService.atualizarModelo(id, modeloAtualizado);
        if (modelo != null) {
            return ResponseEntity.ok(modelo);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletar(@PathVariable Long id) {
        boolean sucesso = modeloService.excluirModelo(id);
        if (sucesso) {
            return ResponseEntity.ok("Registro excluído com sucesso");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
