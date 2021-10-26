<%--
  Created by IntelliJ IDEA.
  User: laure
  Date: 22.10.2021
  Time: 09:28
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>



<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Station</title>
</head>


<jsp:include page="_menu.jsp"></jsp:include>

<h3>Station</h3>

Affichage des stations



</head>
<body>
<table border="1" cellpadding="5" cellspacing="5">
    <tr>
        <th>Emp ID</th>
        <th>Emp Addr</th>
        <th>Emp occupe</th>
        <th>Emp reserve</th>
    </tr>

    <c:forEach var="emp" items="${listeEmpView}">
        <tr>
            <td>${emp.id}</td>
            <td>${emp.adresse}</td>
            <td>${emp.occupe}</td>
            <td>${emp.reserve}</td>
        </tr>
    </c:forEach>
</table>

<%--For displaying Previous link except for the 1st page --%>
<c:if test="${currentPage != 1}">
    <td><a href="stationEtEmplacment?page=${currentPage - 1}">Previous</a></td>
</c:if>

<%--For displaying Page numbers.
The when condition does not display a link for the current page--%>
<table border="1" cellpadding="5" cellspacing="5">
    <tr>
        <c:forEach begin="1" end="${noOfPages}" var="i">
            <c:choose>
                <c:when test="${currentPage eq i}">
                    <td>${i}</td>
                </c:when>
                <c:otherwise>
                    <td><a href="stationEtEmplacment?page=${i}">${i}</a></td>
                </c:otherwise>
            </c:choose>
        </c:forEach>
    </tr>
</table>

<%--For displaying Next link --%>
<c:if test="${currentPage lt noOfPages}">
    <td><a href="stationEtEmplacment?page=${currentPage + 1}">Next</a></td>
</c:if>

<%--
<h2>Liste des stations</h2>
<c:forEach items="${stations}" var="station" >
    <li>${station.id} : ${station.adresse}</li>
</c:forEach>

<h2>Liste des emplacements</h2>
<c:forEach items="${emplacements}" var="emplacement" >
    <li>${emplacement.id} : ${emplacement.station_id}</li>
</c:forEach>

<h2>Liste des vehicules</h2>
<c:forEach items="${vehicules}" var="vehicule" >
    <li>${vehicule.id} : ${vehicule.matricule} : ${vehicule.emplacement_id} : ${vehicule.station_id} : ${vehicule.categorie}</li>
</c:forEach>
--%>

<h2>Liste des stations</h2>
<c:forEach items="${emplacementsLibres}" var="emp" >
    <li>${emp.id} : ${emp.adresse} : ${emp.emplacement_id} : occupe ?${emp.occupe}: reserve ?${emp.reserve}</li>
</c:forEach>

<h2>Liste des stations 2</h2>
<c:forEach var= "liste" items="${listes}"  >
    <li>${liste}</li>
</c:forEach>

</body>

</html>
