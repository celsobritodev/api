package br.com.dicasdeumdev.api.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {
	
	// Define o bean do ModelMapper para ser usado na aplicação
	// Assim, podemos injetar o ModelMapper em qualquer classe que precise dele
	// Exemplo: @Autowired private ModelMapper modelMapper;
	// O ModelMapper é uma biblioteca que facilita o mapeamento entre objetos, como DTOs e entidades
	// Isso ajuda a reduzir o código boilerplate e melhora a manutenção do código
	// Documentação: https://modelmapper.org/
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

}
