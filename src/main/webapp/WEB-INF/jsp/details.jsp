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
            <h2>Task details</h2>
            
            <dl class="dl-horizontal">
                <dt>Description</dt>
                <dd>${it.description}</dd>
                <dt>Owner</dt>
                <dd>
                    <c:choose>
                        <c:when test="${not empty it.owner}">
                            ${it.owner.displayName}
                        </c:when>
                        <c:otherwise>
                            None...
                        </c:otherwise>
                    </c:choose>
                </dd>
            </dl>
            <dl class="dl-horizontal">
                <dt>ID</dt>
                <dd>${it.id}</dd>
                <dt>Created on</dt>
                <dd><fmt:formatDate value="${it.createdOn}" type="both" dateStyle="medium" timeStyle="short"/></dd>
                <dt>Created by</dt>
                <dd>${it.createdBy.displayName}</dd>
            </dl>

            <p><a href="${pageContext.request.contextPath}/todo/${it.id}/edit">Edit this task...</a></p>

            <form method="POST" class="form-inline">
                <legend>Actions on this task</legend>
                <c:choose>
                    <c:when test="${it.status == 'created'}">
                        <button type="submit" name="action" value="complete" class="btn btn-primary">Complete</button>
                        <button type="submit" name="action" value="cancel" class="btn btn-danger">Cancel</button>
                    </c:when>
                    <c:otherwise>
                        <button type="submit" name="action" value="reopen" class="btn btn-danger">Reopen</button>                            
                    </c:otherwise>
                </c:choose>
            </form>
                    
        </div>

        <script src="${pageContext.request.contextPath}/js/jquery-1.8.0.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    </body>
</html>
