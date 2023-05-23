package br.com.uniamerica.estacionamento.service;

import br.com.uniamerica.estacionamento.entity.Veiculo;
import br.com.uniamerica.estacionamento.repository.VeiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VeiculoService {

    private final VeiculoRepository veiculoRepository;

    @Autowired
    public VeiculoService(VeiculoRepository veiculoRepository) {
        this.veiculoRepository = veiculoRepository;
    }

    public Veiculo buscarVeiculoPorId(Long id) {
        Optional<Veiculo> optionalVeiculo = veiculoRepository.findById(id);
        return optionalVeiculo.orElse(null);
    }

    public List<Veiculo> buscarVeiculosPorAtivo(boolean ativo) {
        return veiculoRepository.findByAtivo(ativo);
    }

    public List<Veiculo> buscarVeiculos() {
        return veiculoRepository.findAll();
    }

    public Veiculo criarVeiculo(Veiculo veiculo) {
        return veiculoRepository.save(veiculo);
    }

    public Veiculo atualizarVeiculo(Long id, Veiculo veiculoAtualizado) {
        Optional<Veiculo> optionalVeiculo = veiculoRepository.findById(id);
        if (optionalVeiculo.isPresent()) {
            Veiculo veiculo = optionalVeiculo.get();
            veiculo.setModelo(veiculoAtualizado.getModelo());
            veiculo.setPlaca(veiculoAtualizado.getPlaca());
            veiculo.setTipo(veiculoAtualizado.getTipo());
            veiculo.setAnoModelo(veiculoAtualizado.getAnoModelo());
            veiculo.setCor(veiculoAtualizado.getCor());
            veiculo.setMovimentacao(veiculoAtualizado.getMovimentacao());
            return veiculoRepository.save(veiculo);
        } else {
            return null;
        }
    }

    public boolean deletar(Long id) {
        Veiculo veiculo = buscarVeiculoPorId(id);
        if (veiculo != null) {
            veiculoRepository.delete(veiculo);
            return true;
        } else {
            return false;
        }
    }
    }

