<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Register</title>
</head>
<body>
	<form action="/web/register" method="post">
	    <c:if test = "${alert != null}">
			<h3 class = "alert alert danger">${alert}</h3>
		</c:if>
		<div class="container">
		  <h1>Register</h1>
		    <p>Please fill in this form to create an account.</p>
		  <hr>
			  <label for="username"><b>Username</b></label>
			  <input type="text" placeholder="Enter Username" name="username" id="username" required>
			  
			  <label for="fullname"><b>Fullname</b></label>
		      <input type="text" placeholder="Enter Full Name" name="fullname" id="fullname" required>	
			  
			  <label for="email"><b>Email</b></label>
		      <input type="text" placeholder="Enter Email" name="email" id="email" required>
			  
			  <label for="phone"><b>Phone</b></label>
		      <input type="text" placeholder="Enter Phone" name="phone" id="phone" required>
		  
			  <label for="psw"><b>Password</b></label>
			  <input type="password" placeholder="Enter Password" name="password" id="psw" required>
			
			  <label for="psw-repeat"><b>Repeat Password</b></label>
			  <input type="password" placeholder="Repeat Password" name="psw-repeat" id="psw-repeat" required>
		  <hr>
		
		  <button type="submit" class="registerbtn">Register</button>
	  	</div>
	
	  <div class="container signin">
	    <p>Already have an account? <a href= "${pageContext.request.contextPath }/login">Sign in</a>.</p>
	  </div>
	</form>
</body>
</html>