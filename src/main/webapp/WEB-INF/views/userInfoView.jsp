<%--
  Created by IntelliJ IDEA.
  User: laure
  Date: 20.10.2021
  Time: 09:55
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>User Info</title>
</head>
<body>

<jsp:include page="_menu.jsp"></jsp:include>

<h3>Hello: ${loginedUser.userName}</h3>

User Name: <b>${loginedUser.userName}</b>
<br />

</body>
</html>