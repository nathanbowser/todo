package com.nathanbowser.todo.model.user;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

@Repository("userDao")
class UserDao {

	@PersistenceContext
	private EntityManager entityManager;

	void save(User user) {
		this.entityManager.persist(user);
	}

	User findByEmail(String email) {
		try {
			return (User) this.entityManager.createQuery("select u from User u where u.email = :email") //
											.setParameter("email", email).getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

}
