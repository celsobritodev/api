package br.com.dicasdeumdev.api.resources;

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
	
	
	
	
	private void startUser() {
		user = new User(ID, NAME, EMAIL, PASSWORD);
		userDTO = new UserDTO(ID, NAME, EMAIL, PASSWORD);

	}

	
	

}
