package br.com.uniamerica.estacionamento.controller;

import br.com.uniamerica.estacionamento.entity.Movimentacao;
import br.com.uniamerica.estacionamento.entity.Veiculo;
import br.com.uniamerica.estacionamento.service.VeiculoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/api/veiculo")
public class VeiculoController {

    private final VeiculoService veiculoService;

    @Autowired
    public VeiculoController(VeiculoService veiculoService) {
        this.veiculoService = veiculoService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Veiculo> findById(@PathVariable Long id) {
        Veiculo veiculo = veiculoService.buscarVeiculoPorId(id);
        if (veiculo != null) {
            return ResponseEntity.ok(veiculo);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/ativo/{ativo}")
    public ResponseEntity<List<Veiculo>> findByAtivo(@PathVariable boolean ativo) {
        List<Veiculo> veiculos = veiculoService.buscarVeiculosPorAtivo(ativo);
        if (veiculos.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(veiculos);
        }
    }

    @GetMapping
    public ResponseEntity<List<Veiculo>> findAll() {
        List<Veiculo> veiculos = veiculoService.buscarVeiculos();
        if (veiculos.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(veiculos);
        }
    }

    @PostMapping
    public ResponseEntity<Veiculo> cadastrar(@RequestBody Veiculo veiculo) {
        Veiculo novoVeiculo = veiculoService.criarVeiculo(veiculo);
        return ResponseEntity.ok(novoVeiculo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Veiculo> atualizar(@PathVariable Long id, @RequestBody Veiculo veiculoAtualizado) {
        Veiculo veiculo = veiculoService.atualizarVeiculo(id, veiculoAtualizado);
        if (veiculo != null) {
            return ResponseEntity.ok(veiculo);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletar(@PathVariable Long id) {
        Optional<Veiculo> optionalVeiculo = Optional.ofNullable(veiculoService.buscarVeiculoPorId(id));

        if (optionalVeiculo.isPresent()) {
            Veiculo veiculo = optionalVeiculo.get();
            Movimentacao movimentacao = veiculo.getMovimentacao();

            if (movimentacao != null && movimentacao.isAtivo()) {
                veiculoService.deletar(veiculo.getId());
                return ResponseEntity.ok("O registro do veículo foi deletado com sucesso");
            } else {
                veiculo.setAtivo(false);
                veiculoService.atualizarVeiculo(id, veiculo);
                return ResponseEntity.ok("O veículo estava vinculado a uma ou mais movimentações e foi desativado com sucesso");
            }
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
