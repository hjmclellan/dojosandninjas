<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>    
    
<!DOCTYPE html>
<html>

	<head>
		<meta charset="ISO-8859-1">
		<title>New Dojo</title>
	</head>

<body>
	<div class="container">
		<h1>New Dojo!</h1>
		<form:form method="POST" action="/dojos/new" modelAttribute="dojo">
			<p>
				<form:label path="name">Name:</form:label>
				<form:errors path="name"/>
				<form:input path="name"/>
			</p>
			
			<div>
				<input type="submit" value="Create"/>			
			</div>
		</form:form>
	</div>
</body>
</html>