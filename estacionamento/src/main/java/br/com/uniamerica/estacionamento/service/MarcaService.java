package br.com.uniamerica.estacionamento.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.uniamerica.estacionamento.entity.Marca;
import br.com.uniamerica.estacionamento.repository.MarcaRepository;

import java.util.List;

@Service
public class MarcaService {

    @Autowired
    private MarcaRepository marcaRepository;

    public Marca criarMarca(Marca marca) {
        if (marcaRepository.existsByNomeMarca(marca.getNomeMarca())) {
            throw new IllegalArgumentException("Já existe uma marca com o nome informado.");
        }

        return marcaRepository.save(marca);
    }

    public Marca buscarMarcaPorId(Long id) {
        return marcaRepository.findById(id).orElse(null);
    }

    public List<Marca> buscarTodasMarcas() {
        return marcaRepository.findAll();
    }

    public void atualizarMarca(Marca marca) {
        Marca marcaExistente = marcaRepository.findByNomeMarca(marca.getNomeMarca());
        if (marcaExistente != null && !marcaExistente.getId().equals(marca.getId())) {
            throw new IllegalArgumentException("Já existe uma marca com o nome informado.");
        }

        marcaRepository.save(marca);
    }

    public void excluirMarca(Long id) {
        marcaRepository.deleteById(id);
    }

    public boolean existsByNomeMarca(String nomeMarca) {
        return marcaRepository.existsByNomeMarca(nomeMarca);
    }

    public Marca findByNomeMarca(String nomeMarca) {
        return marcaRepository.findByNomeMarca(nomeMarca);
    }


}
