package br.com.dicasdeumdev.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.dicasdeumdev.api.domain.User;

//https://youtu.be/lvswjr0T4KM?list=PLA8Qj9w4RGkWgyYa485pgf-VAoJgL4rW1
// Aula 03 Classe de dominio - [Testes com JUnit5 Mockito e Java]

@SpringBootApplication
public class ApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiApplication.class, args);
		
		User user = new User(1,"valdir","email@email.com","123");
	}

}
