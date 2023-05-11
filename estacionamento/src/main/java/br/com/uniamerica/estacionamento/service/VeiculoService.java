package br.com.uniamerica.estacionamento.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.uniamerica.estacionamento.entity.Veiculo;
import br.com.uniamerica.estacionamento.repository.VeiculoRepository;

import java.util.List;

@Service
public class VeiculoService {

    @Autowired
    private VeiculoRepository veiculoRepository;

    public Veiculo criarVeiculo(Veiculo veiculo) {
        return veiculoRepository.save(veiculo);
    }

    public Veiculo buscarVeiculoPorId(Long id) {
        return veiculoRepository.findById(id).orElse(null);
    }

    public List<Veiculo> buscarTodosVeiculos() {
        return veiculoRepository.findAll();
    }

    public void atualizarVeiculo(Veiculo veiculo) {
        veiculoRepository.save(veiculo);
    }

    public void excluirVeiculo(Long id) {
        veiculoRepository.deleteById(id);
    }
}
