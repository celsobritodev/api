package br.com.dicasdeumdev.api.services.impl;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.dicasdeumdev.api.domain.User;
import br.com.dicasdeumdev.api.domain.dto.UserDTO;
import br.com.dicasdeumdev.api.repositories.UserRepository;
import br.com.dicasdeumdev.api.services.UserService;
import br.com.dicasdeumdev.api.services.exceptions.DataIntegratyViolationException;
import br.com.dicasdeumdev.api.services.exceptions.ObjectNotFoundException;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository repository;
	
	@Autowired
	private ModelMapper mapper;
	
	@Override
	public User findById(Integer id) {
		Optional<User> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado!"));
	}
	
	
	// Método para buscar todos os usuários
    public List<User> findAll() {
		return repository.findAll();
	}


	@Override
	public User create(UserDTO userDTO) {
		findByEmail(userDTO);
		return repository.save(mapper.map(userDTO,User.class));
	}
	
	
	private void findByEmail(UserDTO userDTO) {
		Optional<User> obj = repository.findByEmail(userDTO.getEmail());
		if (obj.isPresent()) {
			throw new DataIntegratyViolationException("Email já cadastrado no sistema!");
		}
	}
	

}
