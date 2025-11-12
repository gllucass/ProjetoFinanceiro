package br.dev.geraldolucas.ProjetoFinanceiro.fornecedor.repository;

import br.dev.geraldolucas.ProjetoFinanceiro.fornecedor.model.Fornecedor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FornecedorRepository extends JpaRepository<Fornecedor, Long> {
}
