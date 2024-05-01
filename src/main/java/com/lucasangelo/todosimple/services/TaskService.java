package com.lucasangelo.todosimple.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lucasangelo.todosimple.models.Task;
import com.lucasangelo.todosimple.models.User;
import com.lucasangelo.todosimple.repositories.TaskRepository;

@Service
public class TaskService {
	
	@Autowired
	private TaskRepository taskRepository;
	
	@Autowired
	private UserService userService;
	
	public Task findyId(Long id) {
		Optional<Task> task = this.taskRepository.findById(id);
		return task.orElseThrow(() -> new RuntimeException( 
				"Tarefa não encontrada! id:" + id +", Tipo: " + Task.class.getName()));
	}
	
	public List<Task> findByUserId(long userId){
		List<Task> tasks = this.taskRepository.findByuser_Id(userId);
		return tasks;
		
	}
	
	
	@Transactional
	public Task create(Task obj) {
		User user = this.userService.findById(obj.getUser().getId());
		obj.setId(null);
		obj.setUser(user);
		obj = this.taskRepository.save(obj);
		return obj;
	}
	
	@Transactional
	public Task update (Task obj) {
		Task newobj = findyId(obj.getId());
		newobj.setDescription(obj.getDescription());
		return this.taskRepository.save(newobj);
	}
	
	public void delete(Long id) {
		findyId(id);
		try {
			this.taskRepository.deleteById(id);
		} catch (Exception e) {
			throw new RuntimeException("Não é possivel excluir pois há entidade relacionada!");
		}
	}
	
	
	
	
	
	
	
	
	
	
}
