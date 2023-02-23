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
        </style>
    </head>
    <body>
        <header>
            <nav class="navbar navbar-expand-md navbar-light bg-white p-3">
                <div>
                    <a href="https://www.linkedin.com/in/hemant-k-318155225/" class="navbar-brand">User Manager</a>
                </div>
            </nav>
        </header>
        <div>

            <div class="container mt-4">
                <h3 class="text-center">Active Users</h3>
                <hr>
            </div>
            <div class="table-responsive table-responsive-sm table-responsive-lg px-4">
                <table class="table table-sm table-hover table-striped table-borderless mt-3 text-light">
                    <caption>List of users</caption>
                    <thead>
                        <tr>
                            <th class="text-center">ID</th>
                            <th class="text-center">Name</th>
                            <th class="text-center">Phone</th>
                            <th class="text-center">Actions</th>
                        </tr>
                    </thead>
                    <tbody>

                        <c:forEach var="user" items="${listUser}">

                            <tr class="text-center">
                                <td><c:out value="${user.id}" /></td>
                                <td><c:out value="${user.name}" /></td>
                                <td><c:out value="${user.number}" /></td>
                                <td><a class="btn btn-light text-decoration-none" href="edit?id=<c:out value='${user.id}' />">Edit</a>
                                    <a class="text-decoration-none btn" href="delete?id=<c:out value='${user.id}' />">Delete</a></td>
                            </tr>
                        </c:forEach>

                    </tbody>

                </table>

            </div>
            <div class="px-4">
                    <a href="<%=request.getContextPath()%>/new" class="btn btn-light">Add User</a>
                </div>
        </div>
    </body>
</html>