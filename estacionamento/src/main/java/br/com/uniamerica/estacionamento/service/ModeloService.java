package br.com.uniamerica.estacionamento.service;

import br.com.uniamerica.estacionamento.entity.Marca;
import br.com.uniamerica.estacionamento.entity.Modelo;
import br.com.uniamerica.estacionamento.repository.ModeloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ModeloService {

    @Autowired
    private ModeloRepository modeloRepository;

    @Autowired
    private MarcaService marcaService;


    public List<Modelo> buscarModelos() {
        return modeloRepository.findAll();
    }

    public Modelo buscarModeloPorId(Long id) {
        return modeloRepository.findById(id).orElse(null);
    }

    public Modelo criarModelo(String nomeModelo, Marca marca, boolean ativo) {
        Modelo modelo = new Modelo();
        modelo.setNomeModelo(nomeModelo);
        modelo.setMarca(marca);
        modelo.setAtivo(ativo);
        return modeloRepository.save(modelo);
    }
    public Modelo atualizarModelo(Long id, Modelo modeloAtualizado) {
        Optional<Modelo> optionalModelo = modeloRepository.findById(id);
        if (optionalModelo.isPresent()) {
            Modelo modelo = optionalModelo.get();
            modelo.setNomeModelo(modeloAtualizado.getNomeModelo());
            modelo.setMarca(modeloAtualizado.getMarca());
            modelo.setAtivo(modeloAtualizado.isAtivo());
            modeloRepository.save(modelo);
            return modelo;
        }
        return null;
    }
    public boolean excluirModelo(Long id) {
        Modelo modeloExistente = modeloRepository.findById(id).orElse(null);
        if (modeloExistente == null) {
            return false;
        } else {
            modeloRepository.delete(modeloExistente);
            return true;
        }
    }
}