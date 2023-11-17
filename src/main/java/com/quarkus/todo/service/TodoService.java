package com.quarkus.todo.service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.quarkus.todo.dao.TodoDAO;
import com.quarkus.todo.dto.TodoDTO;
import com.quarkus.todo.model.Todo;
import com.quarkus.todo.parser.TodoParser;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.ws.rs.NotFoundException;
import org.eclipse.microprofile.opentracing.Traced;

@RequestScoped
@Traced
public class TodoService {

	@Inject
	TodoDAO todoDAO;

	@Transactional(rollbackOn = Exception.class)
	public void insert(@Valid TodoDTO todoDto) {
		Todo todo = TodoParser.get().toEntity(todoDto);
		Long id = todoDAO.insert(todo);
	}

	public List<TodoDTO> list() {
		return TodoParser.get().toDTOList(todoDAO.list());
	}


	public void delete(Long id) {
		if (todoDAO.searchById(id) == null) {
			throw new NotFoundException();
		}
		todoDAO.delete(id);
	}

	private Todo searchById(Long id) {
		Todo todo = todoDAO.searchById(id);
		if (todo == null) {
			throw new NotFoundException();
		}
		return todo;
	}

	public void updateStatus(Long id, String status) {
		Todo todo = searchById(id);
		todo.setStatus(status);
		todoDAO.update(todo);

	}

}
