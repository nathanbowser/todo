package com.nathanbowser.todo.model.task;

import java.util.List;

public interface TaskService {

	void add(Task task);

	void delete(Task task);

	void delete(Long id);

	List<Task> getTasks();

}
