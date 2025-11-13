package br.dev.geraldolucas.ProjetoFinanceiro.titulo.model;

import br.dev.geraldolucas.ProjetoFinanceiro.enums.StatusTitulo;
import br.dev.geraldolucas.ProjetoFinanceiro.fornecedor.model.Fornecedor;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@EntityListeners(AuditingEntityListener.class)
@Entity
@Table(name = "tb_titulos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")

public class Titulo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @NotBlank
    @Column(nullable = false, length = 150)
    String descricao;

    @NotNull
    @DecimalMin(value = "0.01", message = "O valor deve ser positivo.")
    @Column(nullable = false, precision = 19, scale = 2)
    BigDecimal valor;

    @NotNull
    @Column(nullable = false)
    LocalDate dataVencimento;

    LocalDate dataPagamento;

    @CreatedDate
    @Column(nullable = false)
    LocalDateTime dataCriacao;

    @LastModifiedDate
    @Column(nullable = false)
    LocalDateTime dataAtualizacao;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    StatusTitulo status;

    @ManyToOne
    @JoinColumn(name = "fornecedor_id", nullable = false)
    private Fornecedor fornecedor;
}
