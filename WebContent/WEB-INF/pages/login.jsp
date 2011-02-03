<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
	<head>
		<title>Todo App - Login</title>
		<link rel="stylesheet" href="style/blueprint/screen.css" type="text/css" media="screen, projection"> 
		<link rel="stylesheet" href="style/blueprint/print.css" type="text/css" media="print"> 
		<link rel="stylesheet" href="style/main.css" type="text/css" media="screen">	
		<!--[if lt IE 8]><link rel="stylesheet" href="css/blueprint/ie.css" type="text/css" media="screen, projection"><![endif]--> 
	</head>
	<body>
		<div class="container">
			<form method="post" action="j_spring_security_check">
				<div class="prepend-6 span-10 last" id="login">
					<fieldset>
						<legend>Login</legend>

						<c:if test="${param.status eq 'LOGOUT'}">
							<div class="success">
								Successfully logged out.
							</div>
						</c:if>
						<c:if test="${not empty SPRING_SECURITY_LAST_EXCEPTION.message}">
							<div class="error">
								${SPRING_SECURITY_LAST_EXCEPTION.message}
							</div>
						</c:if>

						<dl>
							<dt><label for="j_username">Email</label></dt>
							<dd><input type="text" name="j_username" id="j_username" size="50" class="larger" /></dd>
							<dt><label for="j_password">Password</label></dt>
							<dd><input type="password" name="j_password" id="j_password" size="50" class="larger" /></dd>
							<dt></dt>
							<dd><input type="submit" name="login" value="Login" /></dd>
							<dt class="clear"></dt>
							<dd>
								<a href="signup.html">Signup</a>
							</dd>
						</dl>
					</fieldset>
				</div>
			</form>
		</div> 
	</body>
</html>
