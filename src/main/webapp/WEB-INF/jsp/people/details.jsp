<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <title>Task details</title>
        <link href="${pageContext.request.contextPath}/css/bootstrap.css" rel="stylesheet">
        <!--[if lt IE 9]>
          <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
        <![endif]-->
    </head>

    <body>

        <div class="container">
            <h2>User details</h2>
            <dl class="dl-horizontal">
                <dt>ID</dt>
                <dd>${it.id}</dd>
                <dt>First name</dt>
                <dd>${it.firstName}</dd>
                <dt>Last name</dt>
                <dd>${it.lastName}</dd>
            </dl>

            <p><a href="${pageContext.request.contextPath}/people/${it.id}/edit">Edit this user...</a></p>
        </div>

        <script src="${pageContext.request.contextPath}/js/jquery-1.8.0.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    </body>
</html>
