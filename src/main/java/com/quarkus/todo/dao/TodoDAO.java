package com.quarkus.todo.dao;

import java.util.ArrayList;
import java.util.List;

import com.quarkus.todo.model.Todo;
import jakarta.enterprise.context.RequestScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.eclipse.microprofile.opentracing.Traced;

@RequestScoped
@Traced
public class TodoDAO {

	@PersistenceContext
	EntityManager em;

	@Transactional
	public Long insert(Todo todo) {
		todo.persistAndFlush();
		return todo.getId();
	}

	@Transactional
	public void update(Todo todo) {
		String sqlName = "UPDATE_TODO";
		insertAndUpdate(sqlName, todo);
	}

	@Transactional
	public void insertAndUpdate(String sqlNme, Todo todo) {
		Query query = em.createNamedQuery(sqlNme);

		query.setParameter("id", todo.getId());
		query.setParameter("name", todo.getName());
		query.setParameter("dateCreation", todo.getDateCreation());
		query.setParameter("status", todo.getStatus());
		query.executeUpdate();

	}

	public List<Todo> list() {
		String listTodo = "LIST_TODO";
		List<Todo> returnList;
		TypedQuery<Todo> query = em.createNamedQuery(listTodo, Todo.class);

		try {
			returnList = query.getResultList();
		} catch (NoResultException e) {
			returnList = new ArrayList();
		}

		return returnList;
	}

	@Transactional
	public void delete(Long id) {
		String nomeSql = "DELETE_TODO";
		Query query = em.createNamedQuery(nomeSql);
//		
//		query.setParameter("id", id);
//		
//		query.executeUpdate();
		Todo.deleteById(id);
	}

	public Todo searchById(Long id) {
		String sqlName = "SEARCH_BY_ID";
		Todo todo;
		TypedQuery<Todo> query = em.createNamedQuery(sqlName, Todo.class);
		query.setParameter("id", id);
		try {
			todo = query.getSingleResult();
		} catch (NoResultException e) {
			todo = null;
		}
		return todo;
	}

}
