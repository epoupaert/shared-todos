<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <title>All users</title>
        <meta name="description" content="">
        <meta name="author" content="">

        <link href="${pageContext.request.contextPath}/css/bootstrap.css" rel="stylesheet">
        <!--[if lt IE 9]>
          <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
        <![endif]-->
    </head>

    <body>

        <div class="container">
            <h2>All users</h2>

            <c:choose>
                <c:when test="${not empty it}">
                    <ul>
                        <c:forEach var="user" items="${it}">
                            <li>${user.displayName} (${user.id}, ${user.username}) [<a href="${pageContext.request.contextPath}/people/${user.id}">details</a>]</li>
                        </c:forEach>
                    </ul>
                </c:when>
                <c:otherwise>
                    <p>No user yet!</p>
                </c:otherwise>
            </c:choose>
            
            <form method="POST" action="${pageContext.request.contextPath}/people" class="form-inline">
                <legend>Create new user</legend>
                <input type="text" name="username" placeholder="User Name" class="input-medium">
                <input type="text" name="firstName" placeholder="First name...">
                <input type="text" name="lastName" placeholder="Last name...">
                <button type="submit" class="btn btn-primary">Create</button>
            </form>

        </div>                    

        <script src="${pageContext.request.contextPath}/js/jquery-1.8.0.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    </body>
</html>
