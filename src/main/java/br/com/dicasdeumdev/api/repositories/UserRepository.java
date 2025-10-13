package br.com.dicasdeumdev.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.dicasdeumdev.api.domain.User;

public interface UserRepository extends JpaRepository<User, Integer> {

}
