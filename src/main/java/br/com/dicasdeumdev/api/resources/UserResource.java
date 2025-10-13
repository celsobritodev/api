package br.com.dicasdeumdev.api.resources;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.dicasdeumdev.api.domain.User;

@RestController
@RequestMapping("/user")
public class UserResource {

	@GetMapping("/{id}")
	public ResponseEntity<User> findById(@PathVariable Integer id){
	    User user = new User(1,"Valdir","val@mail.com","123");
		return ResponseEntity.ok().body(user);
	}

}
