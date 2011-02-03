package com.nathanbowser.todo.web.todo;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nathanbowser.todo.model.candidate.Task;
import com.nathanbowser.todo.model.candidate.TaskService;

@Controller
public class TodoListController {

	@Resource(name = "taskService")
	private TaskService taskService;

	@RequestMapping(value="/todo-list.json", method=RequestMethod.GET)
	@ResponseBody
	public List<Task> getList() {
		return taskService.getTasks();
	}

	@RequestMapping(value = "/add-task.html", method = RequestMethod.POST)
	@ResponseBody
	public Long add(@RequestParam("description") String description, @RequestParam("dueDate") Date dueDate) {
		Task task = new Task();
		task.setDescription(description);
		task.setDueDate(dueDate);
		taskService.add(task);
		return task.getId();
	}

	@RequestMapping(value = "/remove-task.html", method = RequestMethod.POST)
	@ResponseBody
	public String delete(@RequestParam("id") Long id) {
		taskService.delete(id);
		return null;
	}

}
