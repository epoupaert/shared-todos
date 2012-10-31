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
            <h2>Edit task</h2>

            <dl class="dl-horizontal">
                <dt>ID</dt>
                <dd>${it.task.id}</dd>
                <dt>Created on</dt>
                <dd><fmt:formatDate value="${it.task.createdOn}" type="both" dateStyle="medium" timeStyle="short"/></dd>
                <dt>Created by</dt>
                <dd>${it.task.createdBy.displayName}</dd>
            </dl>

            <form method="POST" class="form-horizontal">
                <legend>Edit this task</legend>
                <div class="control-group">
                    <label class="control-label" for="description">Description</label>
                    <div class="controls">
                        <input type="text" id="description" name="description" placeholder="Type something…" value="${it.task.description}">
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label" for="owner">Owner</label>
                    <div class="controls">
                        <select id="owner" name="owner">
                            <option value="-1"
                                <c:if test="${empty it.task.owner}">selected="selected"</c:if>
                            >None...</option>
                            <c:forEach var="user" items="${it.people}">
                                <option value="${user.id}"
                                    <c:if test="${user.id eq it.task.owner.id}">selected="selected"</c:if>
                                >${user.displayName}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
                <div class="control-group">
                    <div class="controls">
                        <button type="submit" name="action" value="save" class="btn btn-primary">Save</button>
                        <button type="submit" name="action" value="cancel" class="btn">Cancel</button>
                    </div>
                </div>
            </form>                

        </div>

        <script src="${pageContext.request.contextPath}/js/jquery-1.8.0.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    </body>
</html>
