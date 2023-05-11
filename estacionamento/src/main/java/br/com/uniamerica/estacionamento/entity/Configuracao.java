package br.com.uniamerica.estacionamento.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalTime;

@Entity
@Table(name = "configuracoes", schema = "public")
public class Configuracao extends AbstractEntity{
    @Getter @Setter
    @Column(name = "valor_hora", nullable = false)
    private BigDecimal valorHora;
    @Getter @Setter
    @Column(name = "valor_minuto_multa", nullable = false)
    private BigDecimal valorMinutoMulta;
    @Getter @Setter
    @Column(name = "inicio_expediente", nullable = false)
    private LocalTime inicioExpediente;
    @Getter @Setter
    @Column(name = "fim_expediente", nullable = false)
    private LocalTime fimExpediente;
    @Getter @Setter
    @Column(name = "tempo_para_desconto", nullable = false)
    private LocalTime tempoParaDesconto;
    @Getter @Setter
    @Column(name = "tempo_desconto",nullable = false)
    private LocalTime tempoDeDesconto;
    @Getter @Setter
    @Column(name = "gerar_desconto")
    private boolean gerarDesconto;
    @Getter @Setter
    @Column(name = "vagas_moto", nullable = false)
    private int vagasMoto;
    @Getter @Setter
    @Column(name = "vagas_carro", nullable = false)
    private int vagasCarro;
    @Getter @Setter
    @Column(name = "vagas_van", nullable = false)
    private int vagasVan;

}
