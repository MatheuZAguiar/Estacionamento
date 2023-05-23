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
        Optional<Veiculo> veiculoOpt = veiculoRepository.findById(id);
        return veiculoOpt.orElse(null);
    }

    public List<Veiculo> buscarVeiculos() {
        return veiculoRepository.findAll();
    }

    public List<Veiculo> buscarVeiculosPorAtivo(boolean ativo) {
        return veiculoRepository.findByAtivo(ativo);
    }

    public Veiculo criarVeiculo(Veiculo veiculo) {
        return veiculoRepository.save(veiculo);
    }

    public Veiculo atualizarVeiculo(Long id, Veiculo veiculoAtualizado) {
        Optional<Veiculo> veiculoOpt = veiculoRepository.findById(id);

        if (veiculoOpt.isPresent() && id.equals(veiculoAtualizado.getId())) {
            Veiculo veiculoExistente = veiculoOpt.get();
            veiculoExistente.setPlaca(veiculoAtualizado.getPlaca());
            veiculoExistente.setModelo(veiculoAtualizado.getModelo());
            veiculoExistente.setCor(veiculoAtualizado.getCor());
            veiculoExistente.setTipo(veiculoAtualizado.getTipo());
            veiculoExistente.setAnoModelo(veiculoAtualizado.getAnoModelo());
            return veiculoRepository.save(veiculoExistente);
        }

        return null;
    }

    public boolean excluirVeiculo(Long id) {
        Optional<Veiculo> optionalVeiculo = veiculoRepository.findById(id);

        if (optionalVeiculo.isPresent()) {
            Veiculo veiculo = optionalVeiculo.get();
            if (veiculo.getMovimentacao() != null && veiculo.getMovimentacao().isAtivo()) {
                veiculoRepository.delete(veiculo);
            } else {
                veiculo.setAtivo(false);
                veiculoRepository.save(veiculo);
            }
            return true;
        }

        return false;
    }
}
