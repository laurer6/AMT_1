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
    <title>Employee Task</title>
</head>
<body>

<jsp:include page="_menu.jsp"></jsp:include>

<h3>Employee Task</h3>

<p style="color: red;">${errorMessage12}</p>

Hello, This is a protected page! <br>


<form method="post" action="${pageContext.request.contextPath}/employeeTask">
   Choisir la station de départ:&nbsp;
    <select name="stationDepart">
        <c:forEach items="${stations}" var="st">
            <option value="${st.id}">  ${st.adresse}</option>
        </c:forEach>
    </select>
    <br/><br/>
    <select name="stationArrive">
        <c:forEach items="${stations}" var="st">
            <option value="${st.id}">  ${st.adresse}</option>
        </c:forEach>
    </select>
    <br/><br/>
    <select name="voitureChoisit">
        <c:forEach items="${vehiculeLibre}" var="veh">
            <option value="${veh.id}"> ${veh.id} : ${veh.categorie} </option>
        </c:forEach>
    </select>
    <br/><br/>
    <input type="submit" value="Submit" />
</form>

<%--
<form method="post" action="${pageContext.request.contextPath}/employeeTask">
    Select a Category:&nbsp;
    <select name="emplacementArrive">
        <c:forEach items="${emplacementsLibres}" var="emp">
            <option value="${emp.id}"> ${emp.adresse} : ${emp.emplacement_id} </option>
        </c:forEach>
    </select>
    <br/><br/>
    <input type="submit" value="Submit" />
</form>
--%>



<h2>Liste des stations</h2>
<c:forEach items="${emplacementsLibres}" var="emp" >
    <li>${emp.id} : ${emp.adresse} : ${emp.emplacement_id} : occupe ?${emp.occupe}: reserve ?${emp.reserve}</li>
</c:forEach>

</body>
</html>