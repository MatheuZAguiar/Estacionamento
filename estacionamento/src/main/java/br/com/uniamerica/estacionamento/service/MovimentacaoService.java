package br.com.uniamerica.estacionamento.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.uniamerica.estacionamento.entity.Movimentacao;
import br.com.uniamerica.estacionamento.repository.MovimentacaoRepository;

import java.util.List;

@Service
public class MovimentacaoService {

    @Autowired
    private MovimentacaoRepository movimentacaoRepository;

    public Movimentacao criarMovimentacao(Movimentacao movimentacao) {
        return movimentacaoRepository.save(movimentacao);
    }

    public Movimentacao buscarMovimentacaoPorId(Long id) {
        return movimentacaoRepository.findById(id).orElse(null);
    }

    public List<Movimentacao> buscarTodasMovimentacoes() {
        return movimentacaoRepository.findAll();
    }

    public void atualizarMovimentacao(Movimentacao movimentacao) {
        movimentacaoRepository.save(movimentacao);
    }

    public void excluirMovimentacao(Long id) {
        movimentacaoRepository.deleteById(id);
    }
}
