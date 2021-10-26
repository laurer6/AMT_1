<%--
  Created by IntelliJ IDEA.
  User: laure
  Date: 20.10.2021
  Time: 10:27
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

<h3>Manager Task</h3>

Hello, This is a protected page!

</body>

<body>


<h2>Liste utilisateurs</h2>
<c:forEach items="${utilisateurs}" var="utilisateur" >
    <li>${utilisateur.id} : ${utilisateur.password} : ${utilisateur.login}</li>
</c:forEach>

<h2>Liste des utilisateur 2.0</h2>
<c:forEach var= "liste" items="${listes}"  >
    <li>${liste}</li>
</c:forEach>





</body>
</html>
