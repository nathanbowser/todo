package com.nathanbowser.todo.model.task;

import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.nathanbowser.todo.model.user.User;
import com.nathanbowser.todo.model.user.UserService;

@Service("taskService")
@Transactional
public class TaskServiceImpl implements TaskService {

	@Resource(name = "userService")
	private UserService userService;

	@Resource(name = "todoListDao")
	private TodoListDao todoListDao;

	public void add(Task task) {
		getTodoList().addTask(task);
	}

	public void delete(Task task) {
		Assert.notNull(task);
		delete(task.getId());
	}

	public void delete(Long id) {
		// TODO Fix this so it's more elegant and uses a dao.
		TodoList todo = getTodoList();
		Iterator<Task> it = todo.getTasks().iterator();
		while (it.hasNext()) {
			Task task = it.next();
			if (task.getId().equals(id)) {
				it.remove();
			}
		}
	}

	private TodoList getTodoList() {
		User user = userService.getCurrentUser();
		return todoListDao.getTodoList(user);
	}

	public List<Task> getTasks() {
		return getTodoList().getTasks();
	}

}
