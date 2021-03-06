<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<?xml version="1.0" encoding="utf-8" ?>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>List of Coachs</title>
    <meta name="description" content="">
    <meta name="viewport" content="width=device-width">
    <link rel="stylesheet" href="../lib/css/bootstrap.min.css"/>
</head>
<body>
<div class="navbar navbar-default navbar-static-top" role="navigation">
    <div class="container">
        <div class="navbar-header">
            <a class="navbar-brand" href="#">管理界面</a>
        </div>
        <div class="navbar-collapse collapse">
            <ul class="nav navbar-nav">
                <li class="active"><a href="#">Coach管理</a></li>
            </ul>
        </div>
    </div>
</div>

<div class="container">
    <div class="form-group input-group ">
        <a href="${pageContext.request.contextPath}/coach/add" class="btn btn-primary ">添加Coach</a>
        <%--<button type="button" class="btn btn-default btn " onclick="delete_all_selected_users()">批量删除</button>--%>
    </div>
    <div class="form-group input-group ">
        <input type="search" class="form-control"/>
        <span class="input-group-btn ">
            <button class="btn btn-default" type="button">Search</button>
        </span>
    </div>
</div>
<div class="container">
    <small>${message}</small>

    <table class="table table-bordered" id="user-table">
        <thead>
        <tr>
            <th><input type="checkbox" onchange="select_all_or_select_none(this)"/></th>
            <th>ID</th>
            <th>Name</th>
            <%--<th>Name</th>--%>
            <th>CustomerNames</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="coach" items="${coachs}">
            <tr class="user_line">
                <td><input type="checkbox"/></td>
                <td>${coach.id}</td>
                <td>${coach.user.name}</td>
                <%--<td>${coach.user.name}</td>--%>
                <td>
                    <c:forEach var="customer" items="${coach.privateCustomers}">
                        ${customer.name}+++
                    </c:forEach>
                </td>
                <td>
                    <a class="btn btn-default" href="${pageContext.request.contextPath}/coach/${coach.id}/edit">修改</a>
                    <a class="btn btn-default"
                       href="${pageContext.request.contextPath}/coach/${coach.id}/delete">删除</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
<%--<c:forEach var="customer" items="${coachs[3].privateCustomers}">--%>
<%--<p>${customer.name}</p>--%>
<%--</c:forEach>--%>
<script src="../lib/js/jquery-1.11.1.min.js"></script>
<script src="../lib/js/bootstrap.min.js"></script>
<script src="../lib/js/underscore.min.js"></script>
<script src="../js/jquery_webmvc/select_all_users.js"></script>


</body>
</html>