<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <title>Task</title>
        <link href="${pageContext.request.contextPath}/css/bootstrap.css" rel="stylesheet">
        <!--[if lt IE 9]>
          <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
        <![endif]-->
    </head>

    <body>

        <div class="container">
            <h1>Task (${it.id})</h1>

            <form method="POST">
                <label>Task</label>
                <input type="text" name="description" placeholder="Type something…" value="${it.description}">
                <label class="checkbox">
                    <input type="checkbox" name="done" value="true" <c:if test="${it.status == 'closed'}">checked="checked"</c:if>>
                    This task is already finished!
                </label>
                <button type="submit" class="btn">Save</button>
            </form>
                    
        </div>

        <script src="${pageContext.request.contextPath}/js/jquery-1.8.0.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    </body>
</html>
