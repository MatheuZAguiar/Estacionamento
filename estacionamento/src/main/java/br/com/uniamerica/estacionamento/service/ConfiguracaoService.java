package br.com.uniamerica.estacionamento.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.uniamerica.estacionamento.entity.Configuracao;
import br.com.uniamerica.estacionamento.repository.ConfiguracaoRepository;

import java.util.List;

@Service
public class ConfiguracaoService {

    @Autowired
    private ConfiguracaoRepository configuracaoRepository;

    public Configuracao criarConfiguracao(Configuracao configuracao) {
        return configuracaoRepository.save(configuracao);
    }

    public Configuracao buscarConfiguracaoPorId(Long id) {
        return configuracaoRepository.findById(id).orElse(null);
    }

    public List<Configuracao> buscarTodasConfiguracoes() {
        return configuracaoRepository.findAll();
    }

    public void atualizarConfiguracao(Configuracao configuracao) {
        configuracaoRepository.save(configuracao);
    }

    public void excluirConfiguracao(Long id) {
        configuracaoRepository.deleteById(id);
    }
}
