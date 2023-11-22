<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-BmbxuPwQa2lc/FVzBcNJ7UAyJxM6wuqIj61tLrc4wSX0szH/Ev+nYRRuWlolflfl" crossorigin="anonymous">
		<title>Auth page</title>
	</head>
	<body>
	
		<div class="container">
			<h1 class="mt-5">Welcom !</h1>
			<p>Join our grawing community</p>
		
			<div class="row">
				<div class="col-6 card p-3">
					<h2>Register</h2>
					<form:form action="/register" method="post" modelAttribute="user">
						<div class="row mb-2">
							<form:label class="col-sm-3 col-form-label" path="userName">User Name :</form:label>
							<div class="col-sm-9">
			                	<form:input class="form-control" path="userName"/>
			                </div>
			                <form:errors class="col-12" path="userName"/>
						</div>
						
						<div class="row mb-2">
							<form:label class="col-sm-3 col-form-label" path="email">Email :</form:label>
			                <div class="col-sm-9">
			                	<form:input class="form-control" type="email" path="email"/>
			                </div>
			                <form:errors path="email"/>
						</div>
						
						<div>
							<form:label path="password">Password :</form:label>
			                <form:errors path="password"/>
			                <form:input type="password" path="password"/>
						</div>
						
						<div>
							<form:label path="confirm">Confirm PW :</form:label>
			                <form:errors path="confirm"/>
			                <form:input type="password" path="confirm"/>
						</div>
						
						<div>
							<input type="submit" value="Submit" />
						</div>
					</form:form>
				</div>
				
				<div class="col-6 card p-3 ml-5">
					<h2>Log in</h2>
					<form:form action="/login" method="post" modelAttribute="login">
						<div>
							<form:label path="email">Email :</form:label>
			                <form:errors path="email"/>
			                <form:input type="email" path="email"/>
						</div>

						<div>
							<form:label path="password">Password :</form:label>
			                <form:errors path="password"/>
			                <form:input type="password" path="password"/>
						</div>
						
						<div>
							<input type="submit" value="Submit" />
						</div>
					</form:form>
				</div>
			</div>
		</div>
	</body>
</html>