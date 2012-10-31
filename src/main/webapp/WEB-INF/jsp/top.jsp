<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <title>Top tasks</title>
        <meta name="description" content="">
        <meta name="author" content="">

        <link href="${pageContext.request.contextPath}/css/bootstrap.css" rel="stylesheet">
        <style type="text/css">
            body {
                padding-top: 40px;
                padding-bottom: 40px;
            }
        </style>
        <!--[if lt IE 9]>
          <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
        <![endif]-->
    </head>

    <body>

        <div class="navbar navbar-fixed-top">
            <div class="navbar-inner">
                <div class="container">
                    <a class="brand" href="#">Shared Todos</a>
                    <ul class="nav nav-pills">
                        <li><a href="${pageContext.request.contextPath}/todo">All tasks</a></li>
                        <li class="active"><a href="${pageContext.request.contextPath}/todo/top">Top tasks</a></li>
                        <li><a href="${pageContext.request.contextPath}/people">Users</a></li>
                    </ul>
                    <p class="navbar-text pull-right">Welcome ${it.user.firstName}!</p>
                </div>
            </div>
        </div>

        <div class="container">
            <h2>Top tasks</h2>

            <c:choose>
                <c:when test="${not empty it.tasks}">
                    <ul>
                        <c:forEach var="task" items="${it.tasks}">
                            <li>${task.description} (${task.status}) [<a href="${pageContext.request.contextPath}/todo/${task.id}/edit">edit</a>]</li>
                        </c:forEach>
                    </ul>
                </c:when>
                <c:otherwise>
                    <p>No active task!</p>
                </c:otherwise>
            </c:choose>
                        
            <form method="POST" action="${pageContext.request.contextPath}/todo" class="form-inline">
                <legend>New task</legend>
                <input type="text" name="description" class="input-xxlarge" placeholder="Task description...">
                <button type="submit" class="btn btn-primary">Create</button>
            </form>
        </div>                    

        <script src="${pageContext.request.contextPath}/js/jquery-1.8.0.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    </body>
</html>
