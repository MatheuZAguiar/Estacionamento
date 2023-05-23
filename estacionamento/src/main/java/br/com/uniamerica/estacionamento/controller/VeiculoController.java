package br.com.uniamerica.estacionamento.controller;

import br.com.uniamerica.estacionamento.entity.Veiculo;
import br.com.uniamerica.estacionamento.repository.VeiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/api/veiculo")
public class VeiculoController {

    private final VeiculoRepository veiculoRepository;

    @Autowired
    public VeiculoController(VeiculoRepository veiculoRepository) {
        this.veiculoRepository = veiculoRepository;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        Optional<Veiculo> veiculoOpt = veiculoRepository.findById(id);

        if (veiculoOpt.isPresent()) {
            return ResponseEntity.ok().body(veiculoOpt.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/ativo/{ativo}")
    public ResponseEntity<?> findByAtivo(@PathVariable boolean ativo) {
        List<Veiculo> veiculos = veiculoRepository.findByAtivo(ativo);

        if (veiculos.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok().body(veiculos);
        }
    }

    @GetMapping
    public ResponseEntity<?> findAll() {
        List<Veiculo> veiculos = veiculoRepository.findAll();

        if (veiculos.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok().body(veiculos);
        }
    }

    @PostMapping
    public ResponseEntity<?> cadastrar(@RequestBody Veiculo veiculo) {
        Veiculo novoVeiculo = veiculoRepository.save(veiculo);
        return ResponseEntity.ok().body(novoVeiculo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizar(@PathVariable Long id, @RequestBody Veiculo veiculoAtualizado) {
        Optional<Veiculo> veiculoOpt = veiculoRepository.findById(id);

        if (veiculoOpt.isPresent()) {
            Veiculo veiculoExistente = veiculoOpt.get();
            veiculoExistente.setPlaca(veiculoAtualizado.getPlaca());
            veiculoExistente.setModelo(veiculoAtualizado.getModelo());
            veiculoExistente.setCor(veiculoAtualizado.getCor());
            veiculoExistente.setTipo(veiculoAtualizado.getTipo());
            veiculoExistente.setAnoModelo(veiculoAtualizado.getAnoModelo());

            Veiculo veiculoAtualizadoResult = veiculoRepository.save(veiculoExistente);
            return ResponseEntity.ok().body(veiculoAtualizadoResult);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}/inativo")
    public ResponseEntity<?> definirInativo(@PathVariable Long id) {
        Optional<Veiculo> optionalVeiculo = veiculoRepository.findById(id);

        if (optionalVeiculo.isPresent()) {
            Veiculo veiculo = optionalVeiculo.get();
            veiculo.setAtivo(false);
            veiculoRepository.save(veiculo);
            return ResponseEntity.ok("O veículo foi definido como inativo com sucesso");
        } else {
            return ResponseEntity.notFound().build();
        }
    }



    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletar(@PathVariable Long id) {
        Optional<Veiculo> optionalVeiculo = veiculoRepository.findById(id);

        if (optionalVeiculo.isPresent()) {
            Veiculo veiculo = optionalVeiculo.get();

            if (veiculo.getMovimentacao() != null && veiculo.getMovimentacao().getCondutor().getMovimentacao().isAtivo()) {
                veiculoRepository.deleteById(id);
                return ResponseEntity.ok("O registro do veículo foi deletado com sucesso");
            } else {
                return ResponseEntity.ok("O veículo não pode ser excluído pois não possui movimentações ativas");
            }
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
