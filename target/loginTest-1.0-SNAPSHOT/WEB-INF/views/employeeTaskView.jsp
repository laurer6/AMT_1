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

<h3>Reserver un trajet</h3>

<p style="color: red;">${errorMessage12}</p>

Choisissez un station de depart, d'arrivée, ainsi qu'un véhicule </br>
Les emplacements disponible sont automatiquement reservé </br>
Seul les vehicules disponible sont selectionnables </br>
Ils seront ammenés sur un emplacement à la station de départ </br></br>


<form method="post" action="${pageContext.request.contextPath}/employeeTask">
   Choisir la station de départ:&nbsp;
    <select name="stationDepart">
        <c:forEach items="${stations}" var="st">
            <option value="${st.id}">  ${st.adresse}</option>
        </c:forEach>
    </select>
    <br/><br/>
    Choisir la station d'arrivé:&nbsp;
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

</body>
</html>