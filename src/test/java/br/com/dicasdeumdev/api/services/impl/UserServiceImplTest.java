package br.com.dicasdeumdev.api.services.impl;

import java.util.List;
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
import br.com.dicasdeumdev.api.services.exceptions.DataIntegratyViolationException;
import br.com.dicasdeumdev.api.services.exceptions.ObjectNotFoundException;

@SpringBootTest
public class UserServiceImplTest {
	
	private static final String EMAIL_JÁ_CADASTRADO_NO_SISTEMA = "Email já cadastrado no sistema!";
	private static final int INDEX = 0;
	private static final String OBJETO_NÃO_ENCONTRADO = "Objeto não encontrado!";
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
	
	
	// Teste para o método findById
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
	
	// Teste para o método findById quando o usuário não é encontrado
	@Test
	void whenFindByIdThenReturnAnObjectNotFoundException() {
		Mockito.when(repository.findById(Mockito.anyInt()))
			.thenThrow(new ObjectNotFoundException(OBJETO_NÃO_ENCONTRADO));
		try {
			service.findById(ID);
		} catch (Exception ex) {
			Assertions.assertEquals(ObjectNotFoundException.class, ex.getClass());
			Assertions.assertEquals(OBJETO_NÃO_ENCONTRADO, ex.getMessage());
		
		}
		
	}
	
	// Teste para o método findAll
	@Test
	void whenFindAllThenReturnAnListOfUsers() {
		Mockito.when(repository.findAll()).thenReturn(List.of(user));
		
		List<User> users = service.findAll();
		
		Assertions.assertNotNull(users); // verifica se o objeto não é nulo
		Assertions.assertEquals(1, users.size()); // verifica se o tamanho da lista é igual a 1
		Assertions.assertEquals(User.class, users.get(INDEX).getClass());
		
		Assertions.assertEquals(ID, users.get(INDEX).getId());
		Assertions.assertEquals(NAME, users.get(INDEX).getName());
		Assertions.assertEquals(EMAIL, users.get(INDEX).getEmail());
		Assertions.assertEquals(PASSWORD, users.get(INDEX).getPassword());
		
	}
	
	
	@Test
	void whenCreateThenReturnSuccess() {
		Mockito.when(repository.save(Mockito.any())).thenReturn(user);
		
		User userResponse = service.create(userDTO);
		
		Assertions.assertNotNull(userResponse); // verifica se o objeto não é nulo
		Assertions.assertEquals(User.class, userResponse.getClass()); // verifica se o objeto retornado é da classe User
		Assertions.assertEquals(ID, userResponse.getId());
		Assertions.assertEquals(NAME, userResponse.getName());
		Assertions.assertEquals(EMAIL, userResponse.getEmail());
		Assertions.assertEquals(PASSWORD, userResponse.getPassword());
		
	}
	
	
	@Test
	void whenCreateThenReturnAnDataIntegrityViolationException() {
		// Quando o método findByEmail do repositório for chamado com qualquer string, retorne o optionalUser
		Mockito.when(repository.findByEmail(Mockito.anyString())).thenReturn(optionalUser);
		
		try {
			optionalUser.get().setId(2); // altera o id do usuário para simular que já existe outro usuário com o mesmo email
			service.create(userDTO);
		} catch (Exception ex) {
			Assertions.assertEquals(DataIntegratyViolationException.class, ex.getClass());
			Assertions.assertEquals(EMAIL_JÁ_CADASTRADO_NO_SISTEMA, ex.getMessage());
		}
		
		
		
	}
	
	
	
	@Test
	void whenUpdateThenReturnSuccess() {
		Mockito.when(repository.save(Mockito.any())).thenReturn(user);
		
		User userResponse = service.update(userDTO);
		
		Assertions.assertNotNull(userResponse); // verifica se o objeto não é nulo
		Assertions.assertEquals(User.class, userResponse.getClass()); // verifica se o objeto retornado é da classe User
		Assertions.assertEquals(ID, userResponse.getId());
		Assertions.assertEquals(NAME, userResponse.getName());
		Assertions.assertEquals(EMAIL, userResponse.getEmail());
		Assertions.assertEquals(PASSWORD, userResponse.getPassword());
		
	}
	
	
	@Test
	void whenUpdateThenReturnAnDataIntegrityViolationException() {
		// Quando o método findByEmail do repositório for chamado com qualquer string, retorne o optionalUser
		Mockito.when(repository.findByEmail(Mockito.anyString())).thenReturn(optionalUser);
		
		try {
			optionalUser.get().setId(2); // altera o id do usuário para simular que já existe outro usuário com o mesmo email
			service.create(userDTO);
		} catch (Exception ex) {
			Assertions.assertEquals(DataIntegratyViolationException.class, ex.getClass());
			Assertions.assertEquals(EMAIL_JÁ_CADASTRADO_NO_SISTEMA, ex.getMessage());
		}
		
		
		
	}
	
	
	
	
	
	private void startUser() {
		user = new User(ID, NAME, EMAIL, PASSWORD);
		userDTO = new UserDTO(ID, NAME, EMAIL, PASSWORD);
		optionalUser = Optional.of(new User(ID, NAME, EMAIL, PASSWORD));
	}

}
