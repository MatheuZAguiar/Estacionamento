package br.com.uniamerica.estacionamento.service;

import br.com.uniamerica.estacionamento.entity.Configuracao;
import br.com.uniamerica.estacionamento.repository.ConfiguracaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConfiguracaoService {

    @Autowired
    private ConfiguracaoRepository configuracaoRepository;

    public ConfiguracaoService(ConfiguracaoRepository configuracaoRepository) {
        this.configuracaoRepository = configuracaoRepository;
    }
    public Configuracao buscarConfiguracao() {
        return configuracaoRepository.findById(1L).orElse(null);
    }
    public List<Configuracao> buscarTodasConfiguracoes() {
        return configuracaoRepository.findAll();
    }

    public Configuracao atualizarConfiguracao(Configuracao configuracaoAtualizada) {
        Configuracao configuracaoExistente = configuracaoRepository.findById(1L).orElse(null);
        if (configuracaoExistente == null) {
            return null;
        } else {
            configuracaoExistente.setValorHora(configuracaoAtualizada.getValorHora());
            configuracaoExistente.setValorMinutoMulta(configuracaoAtualizada.getValorMinutoMulta());
            configuracaoExistente.setInicioExpediente(configuracaoAtualizada.getInicioExpediente());
            configuracaoExistente.setFimExpediente(configuracaoAtualizada.getFimExpediente());
            configuracaoExistente.setTempoParaDesconto(configuracaoAtualizada.getTempoParaDesconto());
            configuracaoExistente.setTempoDeDesconto(configuracaoAtualizada.getTempoDeDesconto());
            configuracaoExistente.setGerarDesconto(configuracaoAtualizada.isGerarDesconto());
            configuracaoExistente.setVagasMoto(configuracaoAtualizada.getVagasMoto());
            configuracaoExistente.setVagasCarro(configuracaoAtualizada.getVagasCarro());
            configuracaoExistente.setVagasVan(configuracaoAtualizada.getVagasVan());
            return configuracaoRepository.save(configuracaoExistente);
        }
    }

    public void deletarConfiguracao(Configuracao configuracao) {
        configuracaoRepository.delete(configuracao);
    }
}
