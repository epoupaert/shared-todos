<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <title>Edit task</title>
        <link href="${pageContext.request.contextPath}/css/bootstrap.css" rel="stylesheet">
        <!--[if lt IE 9]>
          <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
        <![endif]-->
    </head>

    <body>

        <div class="container">
            <h1>Edit task (${it.id})</h1>

            <p>Created on: <fmt:formatDate value="${it.createdOn}" type="both" dateStyle="medium" timeStyle="short"/></p>

            <form method="POST">
                <label>Description</label>
                <input type="text" name="description" placeholder="Type something…" value="${it.description}">
                <div class="form-actions">
                    <button type="submit" name="action" value="save" class="btn btn-primary">Save</button>
                    <button type="submit" name="action" value="cancel" class="btn">Cancel</button>
                </div>
            </form>
                    
        </div>

        <script src="${pageContext.request.contextPath}/js/jquery-1.8.0.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    </body>
</html>
