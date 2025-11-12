package br.dev.geraldolucas.ProjetoFinanceiro.titulo.repository;

import br.dev.geraldolucas.ProjetoFinanceiro.titulo.model.Titulo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TituloRepository extends JpaRepository<Titulo, Long> {
}
