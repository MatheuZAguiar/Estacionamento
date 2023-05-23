package br.com.uniamerica.estacionamento.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalTime;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "condutores", schema = "public")
public class Condutor extends AbstractEntity{
    @Getter @Setter
    @NotBlank(message = "O nome do condutor é obrigatório")
    @Size(max = 100, message = "O nome do condutor deve ter no máximo {max} caracteres")
    @Column(name = "nome", nullable = false, length = 100)
    private String nomeCondutor;

    @Getter @Setter
    @NotBlank(message = "O CPF é obrigatório")
    @NotNull(message = "O CPF não pode ser nulo")
    @Size(max = 15, message = "O CPF deve ter no máximo {max} caracteres")
    @Column(name = "cpf", nullable = false, unique = true, length = 15)
    private String cpf;

    @Getter @Setter
    @NotBlank(message = "O telefone é obrigatório")
    @Size(max = 17, message = "O telefone deve ter no máximo {max} caracteres")
    @Column(name = "telefone", nullable = false, length = 17)
    private String telefone;

    @Getter @Setter
    @Column(name = "tempo_desconto")
    private LocalTime tempoDesconto;

    @Getter @Setter
    @Column(name = "tempo_pago")
    private LocalTime tempoPago;

    @Getter @Setter
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "movimentacao_id")
    private Movimentacao movimentacao;
}