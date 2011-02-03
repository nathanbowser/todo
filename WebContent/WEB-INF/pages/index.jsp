<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Todo App</title>
		<link rel="stylesheet" href="style/blueprint/screen.css" type="text/css" media="screen, projection"> 
		<link rel="stylesheet" href="style/blueprint/print.css" type="text/css" media="print">
		<link rel="stylesheet" href="script/jquery-ui-1.8.9.custom/css/pepper-grinder/jquery-ui-1.8.9.custom.css" type="text/css" media="screen">
		<link rel="stylesheet" href="style/main.css" type="text/css" media="screen">
		<!--[if lt IE 8]><link rel="stylesheet" href="css/blueprint/ie.css" type="text/css" media="screen, projection"><![endif]--> 
		<style type="text/css">
		
			.ui-datepicker {
				font-size: 12px;
			}
		
		</style>
		<script type="text/javascript" src="script/jquery-1.5.min.js"></script>
		<script type="text/javascript" src="script/jquery-ui-1.8.9.custom/js/jquery-ui-1.8.9.custom.min.js"></script>
		<script type="text/javascript">

			$(function () {

				$("#due-date").datepicker({
					showOn: "both",
					buttonImage: "image/calendar.png",
					buttonImageOnly: true,
					dateFormat: "m/d/yy"
				});

				$.getJSON("todo-list.json", function (tasks) {
					$(tasks).each(function () {
						// Gross...
						var date = new Date(this.dueDate);
						var dueDate = (date.getMonth() + 1) + "/" + date.getDate() + "/" + date.getFullYear();
						populate(this.id, this.description, dueDate);
					});
				});

				function send(task, dueDate) {
					var params = {
						description : task,
						dueDate:	dueDate
					};
					$.post("add-task.html", params, function (id) {
						populate(id, task, dueDate);
					});
				}

				$("#add").click(function () {
					var task = $("#task").val();
					var dueDate = $("#due-date").val();
					if (task == "" || dueDate == "") {
						// TODO Only doing client side validation, which is scary!
						return;
					}
					send(task, dueDate); 
					$("#task, #due-date").val("");
				});

				$("input.removable").live("click", function () {
					var taskId = $(this).data("id");
					var row = $(this).parents("tr");
					$.post("remove-task.html", {id : taskId}, function () {
						row.remove();
					});
				});

				function populate(id, task, dueDate) {
					var input =  $("<input type='image' src='image/delete.png' title='Remove Task' class='removable' />").hide();
					input.data("id", id);
					var descCell = $("<td>").text(task);
					var dueDateCell = $("<td>").text(dueDate);
					var inputCell = $("<td>").append(input);
					var row = $("<tr>");
					row.append(descCell);
					row.append(dueDateCell);
					row.append(inputCell);

					row.hover(function () {
						input.show();
					}, function () {
						input.hide();
					});

					$("#todo-list").append(row);
				}
			});

		</script>
	</head>
	<body>
		<div class="container">
			<div class="span-24">
				<fieldset>
					<legend>Todo List</legend>
					
					<table id="todo-list">
						<col style="width: 85%;" />
						<col style="width: 13%;" />
						<col />

						<thead>
							<tr>
								<th>Description</th>
								<th>Due</th>
								<th>&nbsp;</th>
							</tr>
						</thead>
					</table>
	
					<div id="task-entry">
						<input id="task" type="text" class="span-18 larger" />
						<input id="due-date" type="text" class="date larger" size="10" />
						<input id="add" type="image" title="Add Task" src="image/add.png" />
					</div>
				</fieldset>
				
				<div id="footer">
					<span id="date-time">
						<jsp:useBean id="timestamp" class="java.util.Date" />
						<fmt:formatDate value="${timestamp}" pattern="MMM d, yyyy hh:mm:ss a z" />
					</span>
					<span id="log-message">
						You are logged in as <sec:authentication property="name" /> - <a href="j_spring_security_logout">Logout</a>
					</span>
				</div>
			</div>
		</div>
	</body>
</html>
