<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>User Manager</title>

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">

<style>
    body{
        background: rgb(238,174,202);
        background: radial-gradient(circle, rgba(238,174,202,1) 0%, rgba(148,187,233,1) 100%);
    }
    
    .form-control{
        background: none;
    }
    
    
    .form-control:focus{
        background: none;
        outline: none;
        box-shadow: none;
        border-color: #000;
    }
    
    .card{
        background: none;
    }
</style>
</head>
<body>
<header>
		<nav class="navbar bg-white navbar-expand-md navbar-light bg-dark p-3">
			<div>
				<a href="https://www.xadmin.net" class="navbar-brand">User Manager</a>
			</div>
		</nav>
	</header>
	<br>
	<div class="container col-md-5 mt-4">
		<div class="card">
			<div class="card-body">
				<c:if test="${user != null}">
					<form action="update" method="post">
				</c:if>
				<c:if test="${user == null}">
					<form action="insert" method="post">
				</c:if>

				<caption>
                                    <h2>
                                        <c:if test="${user != null}">Edit User</c:if>
                                        <c:if test="${user == null}">Add User</c:if>
                                    </h2>
				</caption>

				<c:if test="${user != null}">
					<input type="hidden" name="id" value="<c:out value='${user.id}' />" />
				</c:if>

				<fieldset class="form-group">
                                        <input type="text"
						value="<c:out value='${user.name}' />" class="form-control mt-3"
						name="name" required="required" placeholder="username">
				</fieldset>

                                <fieldset class="form-group">
                                    <input type="text" value="<c:out value='${user.number}' />" class="form-control my-4" name="number" required="required" placeholder="phone">
				</fieldset>

				<button type="submit" class="btn btn-light px-3">Add</button>
				</form>
			</div>
		</div>
	</div>
</body>
</html>