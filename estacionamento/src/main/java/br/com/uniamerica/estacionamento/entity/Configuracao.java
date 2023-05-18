package br.com.uniamerica.estacionamento.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalTime;

@Entity
@Table(name = "configuracoes", schema = "public")
public class Configuracao extends AbstractEntity {

    @Getter @Setter
    @NotNull(message = "O valor da hora não pode ser nulo")
    @DecimalMin(value = "0.0", inclusive = false, message = "O valor da hora deve ser maior que 0")
    @Column(name = "valor_hora", nullable = false)
    private BigDecimal valorHora;

    @Getter @Setter
    @NotNull(message = "O valor do minuto de multa não pode ser nulo")
    @DecimalMin(value = "0.0", inclusive = false, message = "O valor do minuto de multa deve ser maior que 0")
    @Column(name = "valor_minuto_multa", nullable = false)
    private BigDecimal valorMinutoMulta;

    @Getter @Setter
    @NotNull(message = "O horário de início de expediente não pode ser nulo")
    @Column(name = "inicio_expediente", nullable = false)
    private LocalTime inicioExpediente;

    @Getter @Setter
    @NotNull(message = "O horário de fim de expediente não pode ser nulo")
    @Column(name = "fim_expediente", nullable = false)
    private LocalTime fimExpediente;

    @Getter @Setter
    @NotNull(message = "O tempo para desconto não pode ser nulo")
    @Column(name = "tempo_para_desconto", nullable = false)
    private LocalTime tempoParaDesconto;

    @Getter @Setter
    @NotNull(message = "O tempo de desconto não pode ser nulo")
    @Column(name = "tempo_desconto", nullable = false)
    private LocalTime tempoDeDesconto;

    @Getter @Setter
    private boolean gerarDesconto;

    @Getter @Setter
    @Min(value = 0, message = "O número de vagas para moto não pode ser menor que 0")
    @Column(name = "vagas_moto", nullable = false)
    private int vagasMoto;

    @Getter @Setter
    @Min(value = 0, message = "O número de vagas para carro não pode ser menor que 0")
    @Column(name = "vagas_carro", nullable = false)
    private int vagasCarro;

    @Getter @Setter
    @Min(value = 0, message = "O número de vagas para van não pode ser menor que 0")
    @Column(name = "vagas_van", nullable = false)
    private int vagasVan;
}
