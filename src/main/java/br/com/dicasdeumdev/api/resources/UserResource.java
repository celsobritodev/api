package br.com.dicasdeumdev.api.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.dicasdeumdev.api.domain.User;
import br.com.dicasdeumdev.api.services.UserService;

@RestController
@RequestMapping("/user")
public class UserResource {
	
	@Autowired
	private UserService service;
	

	@GetMapping("/{id}")
	public ResponseEntity<User> findById(@PathVariable Integer id){
	    User user = service.findById(id);
		return ResponseEntity.ok().body(user);
	}
	
	
	

}
