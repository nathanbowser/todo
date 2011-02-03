<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Todo App - Signup</title>
		<link rel="stylesheet" href="style/blueprint/screen.css" type="text/css" media="screen, projection"> 
		<link rel="stylesheet" href="style/blueprint/print.css" type="text/css" media="print"> 
		<link rel="stylesheet" href="style/main.css" type="text/css" media="screen">	
		<!--[if lt IE 8]><link rel="stylesheet" href="css/blueprint/ie.css" type="text/css" media="screen, projection"><![endif]--> 
	</head>
	<body>
		<div class="container"> 

			<div id="content" class="span-22 prepend-1 append-1 last"> 

				<form:form modelAttribute="formbean">
					<fieldset id="signup">
						<legend>Signup</legend>
	
						<dl>
							<dt><form:label path="name">Name</form:label></dt>
							<dd>
								<form:input path="name" size="45" cssClass="larger" />
								<form:errors path="name" cssClass="signup-error" />
							</dd>
							<dt><form:label path="email">Email</form:label></dt>
							<dd>
								<form:input path="email" size="45" cssClass="larger" />
								<form:errors path="email" cssClass="signup-error" />
							</dd>
							<dt><form:label path="password1">Password</form:label></dt>
							<dd>
								<form:password path="password1" size="20" cssClass="larger" />
								<form:errors path="password1" cssClass="signup-error" />
							</dd>
							<dt><form:label path="password2">Confirm Password</form:label></dt>
							<dd>
								<form:password path="password2" size="20" cssClass="larger" />
								<form:errors path="password2" cssClass="signup-error" />
							</dd>
							<dt></dt>
							<dd>
								<input type="submit" name="signup" value="Signup" />
								<input type="submit" name="cancel" value="Cancel" />
							</dd>
						</dl>
					</fieldset>
				</form:form>
			</div>
		</div>
	</body>
</html>
