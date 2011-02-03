package com.nathanbowser.todo.model.candidate;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.nathanbowser.todo.model.user.User;

@Repository("todoListDao")
class TodoListDao {

	@PersistenceContext
	private EntityManager entityManager;

	TodoList getTodoList(User user) {
		return (TodoList) entityManager.createQuery("select t from TodoList t where t.user.id = :u").setParameter("u", user.getId()).getSingleResult();
	}

}
