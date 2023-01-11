<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<html lang="ru">
<head>
    <title>Dishes</title>
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<hr>
<h2>Dishes</h2>
<table border="1" cellpadding="8" cellspacing="0">
    <tr>
        <th>Date</th>
        <th>Restaurant</th>
        <th>Name</th>
        <th>Price</th>
    </tr>
    <c:forEach var="dish" items="${dishes}">
        <tr>
            <td>${dish.dateOfLunch}></td>
            <td>${dish.restaurant}></td>
            <td>${dish.nameOfDish}></td>
            <td>${dish.price}></td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
