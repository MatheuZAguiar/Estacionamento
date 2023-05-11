package br.com.uniamerica.estacionamento.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.uniamerica.estacionamento.entity.Modelo;
import br.com.uniamerica.estacionamento.repository.ModeloRepository;

import java.util.List;

@Service
public class ModeloService {

    @Autowired
    private ModeloRepository modeloRepository;

    public Modelo criarModelo(Modelo modelo) {
        return modeloRepository.save(modelo);
    }

    public Modelo buscarModeloPorId(Long id) {
        return modeloRepository.findById(id).orElse(null);
    }

    public List<Modelo> buscarTodosModelos() {
        return modeloRepository.findAll();
    }

    public void atualizarModelo(Modelo modelo) {
        modeloRepository.save(modelo);
    }

    public void excluirModelo(Long id) {
        modeloRepository.deleteById(id);
    }
}
