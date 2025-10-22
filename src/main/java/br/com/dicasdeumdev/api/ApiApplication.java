package br.com.dicasdeumdev.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

//https://youtu.be/lvswjr0T4KM?list=PLA8Qj9w4RGkWgyYa485pgf-VAoJgL4rW1
// Aula 03 Classe de dominio - [Testes com JUnit5 Mockito e Java]


// Aula 20 Teste UserResource findById - [Testes com JUnit5 Mockito e Java]
// https://youtu.be/zA1FlWk1k8w?list=PLA8Qj9w4RGkWgyYa485pgf-VAoJgL4rW1


// Para poder usar o application-local.properties tive que fazer o seguinte:
// Run Configurations -> Edit Configurations -> VM options: -Dspring.profiles.active=local

@SpringBootApplication
public class ApiApplication {

	public static void main(String[] args) {
		 ConfigurableApplicationContext context =SpringApplication.run(ApiApplication.class, args);
		 System.out.println("=== PERFIS ATIVOS NA MAIN: " +
		            String.join(", ", context.getEnvironment().getActiveProfiles()) + " ===");
		
		
	}

}
