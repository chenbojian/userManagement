<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: chenbojian
  Date: 15-6-26
  Time: 上午11:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add Employee</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/lib/css/bootstrap.min.css"/>

</head>
<body>
<div class="container">
    <h1>Add Employee</h1>
    <form:form method="post" commandName="employee" role="form">
        <div class="form-group">
            <label for="userId">User Id:</label>
            <form:input class="form-control" path="userId"></form:input>
        </div>
        <div class="form-group">
            <label for="job">Job:</label>
            <form:input class="form-control" path="job"></form:input>
        </div>
        <div class="form-group">
            <input class="btn btn-primary" type="submit" value="新增"/>
            <a href="${pageContext.request.contextPath}/employee/">返回</a>
        </div>
    </form:form>
</div>
</body>
</html>
