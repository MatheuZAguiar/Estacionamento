package br.com.uniamerica.estacionamento.controller;

import br.com.uniamerica.estacionamento.entity.Marca;
import br.com.uniamerica.estacionamento.service.MarcaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/marca")
public class MarcaController {

    private final MarcaService marcaService;

    @Autowired
    public MarcaController(MarcaService marcaService) {
        this.marcaService = marcaService;
    }

    @GetMapping
    public ResponseEntity<List<Marca>> buscarMarcas() {
        List<Marca> marcas = marcaService.buscarMarcas();
        return ResponseEntity.ok(marcas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Marca> buscarMarcaPorId(@PathVariable Long id) {
        Marca marca = marcaService.buscarMarcaPorId(id);
        if (marca != null) {
            return ResponseEntity.ok(marca);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Marca> criarMarca(@RequestBody Marca marca) {
        Marca novaMarca = marcaService.criarMarca(marca);
        return ResponseEntity.status(HttpStatus.CREATED).body(novaMarca);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Marca> atualizarMarca(@PathVariable Long id, @RequestBody Marca marcaAtualizada) {
        Marca marca = marcaService.atualizarMarca(id, marcaAtualizada);
        if (marca != null) {
            return ResponseEntity.ok(marca);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> excluirMarca(@PathVariable Long id) {
        boolean sucesso = marcaService.excluirMarca(id);
        if (sucesso) {
            return ResponseEntity.ok("Marca excluída com sucesso");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
