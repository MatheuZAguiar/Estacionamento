package br.com.uniamerica.estacionamento.service;

import br.com.uniamerica.estacionamento.entity.Condutor;
import br.com.uniamerica.estacionamento.repository.CondutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CondutorService {

    @Autowired
    private final CondutorRepository condutorRepository;

    public CondutorService(CondutorRepository condutorRepository) {
        this.condutorRepository = condutorRepository;
    }

    public List<Condutor> buscarTodosCondutores() {
        return condutorRepository.findAll();
    }

    public Condutor buscarCondutorPorId(Long id) {
        return condutorRepository.findById(id).orElse(null);
    }

    public Condutor criarCondutor(Condutor condutor) {
        return condutorRepository.save(condutor);
    }

    public Condutor atualizarCondutor(Long id, Condutor condutorAtualizado) {
        Optional<Condutor> condutorExistente = condutorRepository.findById(id);
        if (condutorExistente.isPresent()) {
            Condutor condutor = condutorExistente.get();
            condutor.setNomeCondutor(condutorAtualizado.getNomeCondutor());
            condutor.setCpf(condutorAtualizado.getCpf());
            condutor.setTelefone(condutorAtualizado.getTelefone());
            condutor.setTempoDesconto(condutorAtualizado.getTempoDesconto());
            condutor.setTempoPago(condutorAtualizado.getTempoPago());
            return condutorRepository.save(condutor);
        } else {
            return null;
        }
    }

    public boolean excluirCondutor(Long id) {
        if (condutorRepository.existsById(id)) {
            condutorRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    public List<Condutor> buscarCondutoresPorAtivo(boolean ativo) {
        return condutorRepository.findByAtivo(ativo);
    }
}
