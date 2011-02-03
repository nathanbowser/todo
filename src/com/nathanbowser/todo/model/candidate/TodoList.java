package com.nathanbowser.todo.model.candidate;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.nathanbowser.todo.model.user.User;

@Entity
@Table(name = "todo_lists")
public class TodoList {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Temporal(TemporalType.TIMESTAMP)
	private Date lastModified;

	@OneToOne(mappedBy = "todoList")
	private User user;

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinTable(name = "todo_tasks", joinColumns = @JoinColumn(name = "todo_list_id"), inverseJoinColumns = @JoinColumn(name = "task_id"))
	@OrderBy("dueDate asc")
	private final List<Task> tasks = new ArrayList<Task>();

	public TodoList() {
		// explicit
	}

	public Long getId() {
		return id;
	}

	public Date getLastModified() {
		return lastModified;
	}

	public final List<Task> getTasks() {
		return tasks;
	}

	public User getUser() {
		return user;
	}

	public void addTask(Task task) {
		this.tasks.add(task);
	}

}
