package br.com.uniamerica.estacionamento.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "veiculos", schema = "public")
public class Veiculo extends AbstractEntity{

    @Getter @Setter
    @NotBlank(message = "A placa é obrigatória")
    @Size(max = 10, message = "A placa deve ter no máximo 10 caracteres")
    @Column(name = "placa", nullable = false, unique = true, length = 10)
    private String placa;

    @Getter @Setter
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "modelo", unique = true, nullable = false)
    @NotNull(message = "O modelo é obrigatório")
    private Modelo modelo;

    @Getter @Setter
    @NotNull(message = "A cor é obrigatória")
    @Column(name = "cor", nullable = false)
    private Cor cor;

    @Getter @Setter
    @NotNull(message = "O tipo é obrigatório")
    @Column(name = "tipo", nullable = false)
    private Tipo tipo;

    @Getter @Setter
    @NotNull(message = "O ano do modelo é obrigatório")
    @Column(name = "ano_modelo", nullable = false, length = 5)
    private int anoModelo;

    @Getter @Setter
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "movimentacao_veiculo")
    private Movimentacao movimentacao;

}