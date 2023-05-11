package br.com.uniamerica.estacionamento.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.uniamerica.estacionamento.entity.Condutor;
import br.com.uniamerica.estacionamento.repository.CondutorRepository;

import java.util.List;

@Service
public class CondutorService {

    @Autowired
    private CondutorRepository condutorRepository;

    public Condutor criarCondutor(Condutor condutor) {
        if (condutorRepository.existsByCpf(condutor.getCpf())) {
            throw new IllegalArgumentException("Já existe um condutor com o CPF informado.");
        }

        if (condutorRepository.existsByTelefone(condutor.getTelefone())) {
            throw new IllegalArgumentException("Já existe um condutor com o telefone informado.");
        }

        return condutorRepository.save(condutor);
    }

    public Condutor buscarCondutorPorId(Long id) {
        return condutorRepository.findById(id).orElse(null);
    }

    public List<Condutor> buscarTodosCondutores() {
        return condutorRepository.findAll();
    }

    public void atualizarCondutor(Condutor condutor) {
        Condutor condutorExistente = condutorRepository.findByCpf(condutor.getCpf());
        if (condutorExistente != null && !condutorExistente.getId().equals(condutor.getId())) {
            throw new IllegalArgumentException("Já existe um condutor com o CPF informado.");
        }

        condutorExistente = condutorRepository.findByTelefone(condutor.getTelefone());
        if (condutorExistente != null && !condutorExistente.getId().equals(condutor.getId())) {
            throw new IllegalArgumentException("Já existe um condutor com o telefone informado.");
        }

        condutorRepository.save(condutor);
    }

    public void excluirCondutor(Long id) {
        condutorRepository.deleteById(id);
    }
}
