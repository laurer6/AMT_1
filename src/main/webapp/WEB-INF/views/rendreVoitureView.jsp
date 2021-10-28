<%--
  Created by IntelliJ IDEA.
  User: laure
  Date: 27.10.2021
  Time: 22:06
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


<form method="POST" action="${pageContext.request.contextPath}/rendreVoiture">

    <table border="0">
        <tr>
            <td>kilometre</td>
            <td><input type="text" name="kilometre" value= "${kilometre}" /> </td>
        </tr>
        <tr>
            <td>duree</td>
            <td><input type="text" name="duree" value= "${duree}" /> </td>
        </tr>
        <tr>
            <td colspan ="2">
                <input type="submit" value= "Submit" />
                <a href="${pageContext.request.contextPath}/">Cancel</a>
            </td>
        </tr>
    </table>
</form>

</br>
<c:if test="${errors != null}">
    Erreurs:
    <ul>
        <c:forEach items="${errors}" var="error">
            <li>${error}</li>
        </c:forEach>
    </ul>
</c:if>

<%--
<h2>Ajoute detail trajet</h2>

<form action="ajoute" method="post">
    <div id="kilometre">
        <label for="kilometre">kilometre</label>
        <input type="text" name="km" value="${kilometre}">
    </div>
    <div id="duree">
        <label for="duree">duree</label>
        <input type="text" name="jour" value="${duree}"/>
    </div>
    <button type="submit" name="Submit">Ajoute</button>
</form>
--%>

</body>
</html>
