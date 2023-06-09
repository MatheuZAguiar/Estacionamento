package br.com.uniamerica.estacionamento.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;
import java.sql.Time;
import java.time.LocalDateTime;
import java.time.LocalTime;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "movimentacoes", schema = "public")
public class Movimentacao extends AbstractEntity{
    @Getter @Setter
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "condutores", nullable = false)
    @NotNull(message = "O condutor é obrigatório")
    private Condutor condutor;

    @Getter @Setter
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "veiculos", nullable = false)
    @NotNull(message = "O veículo é obrigatório")
    private Veiculo veiculo;

    @Getter @Setter
    @Column(name = "data_entrada", nullable = false)
    @NotNull(message = "A data de entrada é obrigatória")
    private LocalDateTime dataEntrada;

    @Getter @Setter
    @Column(name = "data_saida")
    private LocalDateTime dataSaida;

    @Getter @Setter
    @Column(name = "tempo")
    private LocalTime tempo;

    @Getter @Setter
    @Column(name = "tempo_desconto")
    private Time tempoDesconto;

    @Getter @Setter
    @Column(name = "tempo_multa")
    private LocalTime tempoMulta;

    @Getter @Setter
    @Column(name = "valor_desconto")
    private BigDecimal valorDesconto;

    @Getter @Setter
    @Column(name = "valor_hora", nullable = false)
    @NotNull(message = "O valor da hora é obrigatório")
    private BigDecimal valorHora;

    @Getter @Setter
    @Column(name = "valor_total")
    private BigDecimal valorTotal;

    @Getter @Setter
    @Column(name = "valor_multa")
    private BigDecimal valorMulta;

    @Getter @Setter
    @Column(name = "valor_hora_multa")
    private BigDecimal valorHoraMulta;

}