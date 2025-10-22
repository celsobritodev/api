package br.com.dicasdeumdev.api.resources;

import java.util.ArrayList;
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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import br.com.dicasdeumdev.api.domain.User;
import br.com.dicasdeumdev.api.domain.dto.UserDTO;
import br.com.dicasdeumdev.api.services.impl.UserServiceImpl;

@SpringBootTest
public class UserResourceTest {
	
	
	private static final String EMAIL_JÁ_CADASTRADO_NO_SISTEMA = "Email já cadastrado no sistema!";
	private static final int INDEX = 0;
	private static final String OBJETO_NAO_ENCONTRADO = "Objeto não encontrado!";
	private static final Integer ID = 1;
	private static final String PASSWORD = "123";
	private static final String EMAIL = "helder@gmail.com";
	private static final String NAME = "Helder";
	
	private User user;
	private UserDTO userDTO;
	
	// Injeta a classe real UserResource e injeta os mocks criados com @Mock
	@InjectMocks
	private UserResource userResource;
	
	// Cria uma instancia de mentira da classe
	@Mock
	private UserServiceImpl userService;
	
	// Cria uma instancia de mentira da classe
	@Mock
	private ModelMapper modelMapper;
	
	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		startUser();
	}
	
	@Test
	void whenFindByIdThenReturnSucess() {
		Mockito.when(userService.findById(Mockito.anyInt())).thenReturn(user);
		Mockito.when(modelMapper.map(Mockito.any(), Mockito.any())).thenReturn(userDTO);
		
		ResponseEntity<UserDTO> response = userResource.findById(ID);
		
		Assertions.assertNotNull(response);
		Assertions.assertNotNull(response.getBody());
		Assertions.assertEquals(ResponseEntity.class, response.getClass());
		Assertions.assertEquals(UserDTO.class, response.getBody().getClass());
		
		
		
		Assertions.assertEquals(ID, response.getBody().getId());
		Assertions.assertEquals(NAME, response.getBody().getName());
		Assertions.assertEquals(EMAIL, response.getBody().getEmail());
		Assertions.assertEquals(PASSWORD, response.getBody().getPassword());
	}
	
	
	
	@Test
	void whenFindAllThenReturnAListOfUserDTO() {
		
		// Simula o comportamento do método findAll do serviço para retornar uma lista com o usuário criado
		Mockito.when(userService.findAll()).thenReturn(List.of(user));
				
		Mockito.when(modelMapper.map(Mockito.any(), Mockito.any())).thenReturn(userDTO);
		
		ResponseEntity<List<UserDTO>> response = userResource.findAll();
		
		Assertions.assertNotNull(response);
		Assertions.assertNotNull(response.getBody());
		Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
		Assertions.assertEquals(ResponseEntity.class, response.getClass());
		Assertions.assertEquals(ArrayList.class, response.getBody().getClass());
		
		// Verifica se a lista retornada tem o tamanho esperado
		Assertions.assertEquals(1, response.getBody().size());
		// Verifica se o primeiro elemento da lista é do tipo UserDTO
		Assertions.assertEquals(UserDTO.class, response.getBody().get(INDEX).getClass());
		
		
		Assertions.assertEquals(ID, response.getBody().get(INDEX).getId());
		Assertions.assertEquals(NAME, response.getBody().get(INDEX).getName());
		Assertions.assertEquals(EMAIL, response.getBody().get(INDEX).getEmail());
		Assertions.assertEquals(PASSWORD, response.getBody().get(INDEX).getPassword());
	}
	
	
	@Test
	void whenCreateThenReturnCreated() {
		Mockito.when(userService.create(Mockito.any())).thenReturn(user);
		
		ResponseEntity<UserDTO> response = userResource.create(userDTO);
		
		Assertions.assertNotNull(response);
		Assertions.assertEquals(ResponseEntity.class, response.getClass());
		Assertions.assertEquals(HttpStatus.CREATED, response.getStatusCode());
		Assertions.assertNotNull(response.getHeaders().get("Location"));
	}
	
	
	
	private void startUser() {
		user = new User(ID, NAME, EMAIL, PASSWORD);
		userDTO = new UserDTO(ID, NAME, EMAIL, PASSWORD);

	}

	
	

}
