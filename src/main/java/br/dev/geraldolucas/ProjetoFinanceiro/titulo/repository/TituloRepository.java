package br.dev.geraldolucas.ProjetoFinanceiro.titulo.repository;

import br.dev.geraldolucas.ProjetoFinanceiro.enums.StatusTitulo;
import br.dev.geraldolucas.ProjetoFinanceiro.titulo.model.Titulo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TituloRepository extends JpaRepository<Titulo, Long> {
    //Listar titulos por status
    List<Titulo> findByStatus(StatusTitulo status);

    //Listar titulos por fornecedor
    List<Titulo> findByFornecedorId(Long fornecedorId);

    //Listar titulos de um fornecedor por status
    List<Titulo> findByFornecedorIdAndStatus(Long fornecedorId, StatusTitulo status);
}
