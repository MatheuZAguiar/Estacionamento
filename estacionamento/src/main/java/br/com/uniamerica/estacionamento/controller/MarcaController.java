package br.com.uniamerica.estacionamento.controller;

import br.com.uniamerica.estacionamento.entity.*;
import br.com.uniamerica.estacionamento.repository.MarcaRepository;
import br.com.uniamerica.estacionamento.repository.ModeloRepository;
import br.com.uniamerica.estacionamento.repository.VeiculoRepository;
import jakarta.validation.Valid;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/api/marca")
public class MarcaController {

    @Autowired
    MarcaRepository marcaRepository;
    @Autowired
    ModeloRepository modeloRepository;
    @Autowired
    VeiculoRepository veiculoRepository;

    @GetMapping("/{id}")
    public ResponseEntity<Marca> findById(@PathVariable Long id){
        Optional<Marca> marcaOptional = marcaRepository.findById(id);
        if (marcaOptional.isPresent()) {
            return ResponseEntity.ok().body(marcaOptional.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{ativo}")
    public ResponseEntity<?> findByAtivo(@PathVariable boolean ativo){
        List<Marca> marcas = this.marcaRepository.findByAtivo(ativo);

        if (marcas.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok().body(marcas);
    }

    @GetMapping
    public ResponseEntity<?> findAll() {
        List<Marca> marcas = this.marcaRepository.findAll();

        if (marcas.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok().body(marcas);
    }

    @PostMapping
    public ResponseEntity<?> cadastrar(@RequestBody @Valid Marca marca) {
        this.marcaRepository.save(marca);
        return ResponseEntity.ok().body("Registro cadastrado com sucesso");
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizar(@PathVariable final @NotNull Long id, @RequestBody @Valid final Marca marca) {
        Optional<Marca> marcaOptional = marcaRepository.findById(id);
        if (marcaOptional.isPresent()) {
            Marca existingMarca = marcaOptional.get();
            existingMarca.setNomeMarca(marca.getNomeMarca()); // Atualizar outros campos, se necessário
            marcaRepository.save(existingMarca);
            return ResponseEntity.ok().body("Registro atualizado com sucesso");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletar(@PathVariable Long id) {
        Optional<Marca> optionalMarca = marcaRepository.findById(id);
        Optional<Modelo> optionalModelo = modeloRepository.findById(id);
        Optional<Veiculo> optionalVeiculo = veiculoRepository.findById(id);

        if (optionalMarca.isPresent() && optionalModelo.isPresent() && optionalVeiculo.isPresent()) {
            Veiculo veiculo = optionalVeiculo.get();
            Marca marca = optionalMarca.get();
            Modelo modelo = optionalModelo.get();
            Marca marca1 = veiculo.getModelo().getMarca();
            Movimentacao movimentacao = veiculo.getMovimentacao();

            if (movimentacao.isAtivo() && marca != (marca1)) {
                marcaRepository.delete(marca);
                return ResponseEntity.ok("O registro da marca foi deletado com sucesso");
            } else {
                marca.setAtivo(false);
                marcaRepository.save(marca);
                return ResponseEntity.ok("A marca estava vinculada a uma ou mais movimentações e foi desativada com sucesso");
            }
        } else

        {
            return ResponseEntity.notFound().build();
        }
    }
}
