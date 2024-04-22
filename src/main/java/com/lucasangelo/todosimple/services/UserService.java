package com.lucasangelo.todosimple.services;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lucasangelo.todosimple.models.User;
import com.lucasangelo.todosimple.repositories.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;


	public User findById(Long id) {
		Optional<User> user = this.userRepository.findById(id);
		return user.orElseThrow(
				() -> new RuntimeException("Usuario não encontrado! Id:" + id + ", Tipos: " + User.class.getName()));
	}

	@Transactional
	public User create(User obj) {
		obj.setId(0);
		obj = this.userRepository.save(obj);
		return obj;
	}

	@Transactional
	public User update(User obj) {
		User newobj = findById(obj.getId());
		newobj.setPassword(obj.getPassword());
		return this.userRepository.save(newobj);
	}

	public void delete(Long id) {
		findById(id);
		try {
			this.userRepository.deleteById(id);
		} catch (Exception e) {
			throw new RuntimeException("Não é possivel excluir, Pois esta relacionado a uma entidade!");
		}
	}
}
