package br.com.dicasdeumdev.api.services.impl;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.dicasdeumdev.api.domain.User;
import br.com.dicasdeumdev.api.domain.dto.UserDTO;
import br.com.dicasdeumdev.api.repositories.UserRepository;

@SpringBootTest
public class UserServiceImplTest {
	
	private static final Integer ID = 1;
	private static final String PASSWORD = "123";
	private static final String EMAIL = "helder@gmail.com";
	private static final String NAME = "Helder";

	@InjectMocks // cria uma instância real da classe e injeta os mocks criados com @Mock
	private UserServiceImpl service;
	
	@Mock // cria uma instancia de mentira da classe
	private UserRepository repository;
	
	@Mock // cria uma instancia de mentira da classe
	private ModelMapper mapper;
	
	private User user;
	private UserDTO userDTO;
	private Optional<User> optionalUser;
	
	
	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this); // vai iniciar os mocks anotados com @Mock e @InjectMocks
		startUser();
		
	}
	
	
	@Test
	void whenFindByIdThenReturnAnUserInstance() {
		// Quando o método findById do repositório for chamado com qualquer inteiro, retorne o optionalUser
		Mockito.when(repository.findById(Mockito.anyInt())).thenReturn(optionalUser); //
		
		User user = service.findById(ID);
		
		Assertions.assertNotNull(user); // verifica se o objeto não é nulo
		// verifica se objeto retornado é da classe User
		Assertions.assertEquals(User.class, user.getClass());
		Assertions.assertEquals(ID, user.getId()); // verifica se o id do objeto é igual ao id esperado
		Assertions.assertEquals(NAME, user.getName()); // verifica se o nome do objeto é igual ao nome esperado
		Assertions.assertEquals(EMAIL, user.getEmail()); // verifica se o email do objeto é igual ao email esperado
		
	}
	
	private void startUser() {
		user = new User(ID, NAME, EMAIL, PASSWORD);
		userDTO = new UserDTO(ID, NAME, EMAIL, PASSWORD);
		optionalUser = Optional.of(new User(ID, NAME, EMAIL, PASSWORD));
	}

}
