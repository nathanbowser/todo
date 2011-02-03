package com.nathanbowser.todo.model.user;

import static java.util.Arrays.asList;

import java.util.Collection;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.UserDetails;

import com.nathanbowser.todo.model.candidate.TodoList;

@SuppressWarnings("serial")
@Entity
@Table(name = "users")
public class User implements UserDetails {

	private static final String USER_ROLE = "ROLE_USER";

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(length = 50, unique = true)
	private String email;

	private String password;

	@Column(name = "name", length = 100)
	private String name;

	@Temporal(TemporalType.DATE)
	private Date createDate;

	@Temporal(TemporalType.TIMESTAMP)
	private Date lastLogin;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "todo_list")
	private TodoList todoList;

	User() {
		// explicit
	}

	public Long getId() {
		return id;
	}

	public String getEmail() {
		return email;
	}

	void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	void setPassword(String password) {
		this.password = password;
	}

	public Date getCreateDate() {
		return createDate;
	}

	void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getLastLogin() {
		return lastLogin;
	}

	void setLastLogin(Date lastLogin) {
		this.lastLogin = lastLogin;
	}

	void setTodoList(TodoList todoList) {
		this.todoList = todoList;
	}

	public TodoList getTodoList() {
		return todoList;
	}

	public String getName() {
		return name;
	}

	void setName(String name) {
		this.name = name;
	}

	public Collection<GrantedAuthority> getAuthorities() {
		return asList((GrantedAuthority) new GrantedAuthorityImpl(USER_ROLE));
	}

	public String getUsername() {
		return getEmail();
	}

	public boolean isAccountNonExpired() {
		return true;
	}

	public boolean isAccountNonLocked() {
		return true;
	}

	public boolean isCredentialsNonExpired() {
		return true;
	}

	public boolean isEnabled() {
		return true;
	}
}
