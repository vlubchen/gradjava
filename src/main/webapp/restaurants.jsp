<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html lang="ru">
<head>
    <title>Restaurants</title>
</head>
<body>
<h3><a href="index.jsp">Home</a></h3>
<hr>
<h2>Restaurants</h2>
<table border="1" cellpadding="8" cellspacing="0">
    <tr>
        <th>Restaurants</th>
    </tr>
    <c:forEach items="${requestScope.restaurants}" var="restaurant">
        <jsp:useBean id="restaurant" type="ru.vlubchen.gradjava.model.Restaurant"/>
        <tr>
            <td>${restaurant.toString()}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>