package com.lucasangelo.todosimple.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lucasangelo.todosimple.models.Task;
import com.lucasangelo.todosimple.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	
}
