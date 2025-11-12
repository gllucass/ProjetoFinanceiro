package br.dev.geraldolucas.ProjetoFinanceiro.config;

import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        // Configura o mapper para copiar SÓ propriedades que NÃO são nulas.
        modelMapper.getConfiguration()
                .setPropertyCondition(Conditions.isNotNull());

        return modelMapper;
    }
}
