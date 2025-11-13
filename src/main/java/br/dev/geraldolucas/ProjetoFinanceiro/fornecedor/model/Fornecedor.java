package br.dev.geraldolucas.ProjetoFinanceiro.fornecedor.model;

import br.dev.geraldolucas.ProjetoFinanceiro.enums.UF;
import br.dev.geraldolucas.ProjetoFinanceiro.titulo.model.Titulo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@EntityListeners(AuditingEntityListener.class)
@Entity
@Table(name = "tb_fornecedores")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class Fornecedor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false, length = 100)
    private String nomeFantasia;

    @NotBlank
    @Column(nullable = false, length = 150)
    private String razaoSocial;

    @NotBlank
    @Column(unique = true, nullable = false, length = 14)
    @Pattern(regexp = "\\d{14}") //Utilizando Regex para validar se tem 14 digitos
    private String cnpj;

    @Email
    @Column(length = 100)
    private String email;

    @Column(length = 20)
    private String telefone;

    @Column(length = 9)
    @Size(min = 8, max = 9) // Limitando para aceitar com ou sem o - 00000-000 ou 00000000
    private String cep;

    @Column(length = 100)
    private String logradouro;

    @Column(length = 10)
    private String numero;

    @Column(length = 50)
    private String cidade;

    @Column(length = 50)
    private String bairro;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(length = 2, name = "uf")
    private UF uf;

    @Column(length = 100)
    private String complemento;

    //dataCriacao e dataAtualizacao para fins de auditoria
    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDateTime dataCriacao;

    @LastModifiedDate
    @Column(nullable = false)
    private LocalDateTime dataAtualizacao;

    //Opção para deixar o cadastro ativo ou não
    @Column(nullable = false)
    private boolean ativo = true;

    @OneToMany(mappedBy = "fornecedor", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Titulo> titulos = new ArrayList<>();
}
