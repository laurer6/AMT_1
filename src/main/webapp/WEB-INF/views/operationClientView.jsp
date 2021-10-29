<%--
  Created by IntelliJ IDEA.
  User: laure
  Date: 29.10.2021
  Time: 12:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Manager Task</title>
</head>

<body>

<jsp:include page="_menu.jsp"></jsp:include>

<p style="color: red;">${errorMessage12}</p>

<h3>Manager Task 2</h3>

Operation sur les clients

<form method="post" action="${pageContext.request.contextPath}/operationClient">
    choisir un utilisateur
    <select name="clientNum">
        <c:forEach items="${listUser}" var="listUser">
            <option value="${listUser.id}">  ${listUser.userName}</option>
        </c:forEach>
    </select>
    <br/><br/>
    <input type="submit" value="Submit" />
</form>

</html>