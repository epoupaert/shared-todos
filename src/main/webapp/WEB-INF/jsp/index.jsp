<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <title>All tasks</title>
        <meta name="description" content="">
        <meta name="author" content="">

        <link href="${pageContext.request.contextPath}/css/bootstrap.css" rel="stylesheet">
        <!--[if lt IE 9]>
          <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
        <![endif]-->
    </head>

    <body>

        <div class="container">
            <h1>All tasks</h1>

            <c:choose>
                <c:when test="${not empty it}">
                    <ul>
                        <c:forEach var="task" items="${it}">
                            <li>${task.description} (${task.status}) [<a href="${pageContext.request.contextPath}/todo/${task.id}">details</a>]</li>
                        </c:forEach>
                    </ul>
                </c:when>
                <c:otherwise>
                    <p>No task yet!</p>
                </c:otherwise>
            </c:choose>
            
            <p><a href="${pageContext.request.contextPath}/todo/top">Show the top tasks...</a></p>

            <form method="POST" action="${pageContext.request.contextPath}/todo">
                <label>New task</label>
                <input type="text" name="description" placeholder="Type something…"><br>
                <div class="form-actions">
                    <button type="submit" class="btn btn-primary">Create</button>
                </div>
            </form>
                        </div>                    

        <script src="${pageContext.request.contextPath}/js/jquery-1.8.0.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    </body>
</html>
