package br.dev.geraldolucas.ProjetoFinanceiro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class ProjetoFinanceiroApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjetoFinanceiroApplication.class, args);
	}

}
