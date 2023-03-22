<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html lang="ru">
<head>
    <title>Dishes</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
<h3><a href="index.jsp">Home</a></h3>
<hr>
<form method="get" action="dishes">
    <input type="hidden" name="action" value="filter">
    <label>Choose date:</label>
    <input type="date" name="day" value="${param.day}">
    <button type="submit">Filter</button>
</form>
<hr/>
<a href="dishes?action=create">Add Dish</a>
<br><br>
<table border="1" cellpadding="8" cellspacing="0">
    <thead>
    <tr>
        <th>Date</th>
        <th>Restaurant</th>
        <th>Name</th>
        <th>Price</th>
        <th></th>
        <th></th>
    </tr>
    </thead>
    <c:forEach items="${requestScope.dishes}" var="dish">
        <jsp:useBean id="dish" type="ru.vlubchen.gradjava.to.DishTo"/>
        <tr>
            <td>${dish.day}</td>
            <td>${dish.restaurant}</td>
            <td>${dish.name}</td>
            <td>${dish.price}</td>
            <td><a href="dishes?action=update&id=${dish.id}">Update</a></td>
            <td><a href="dishes?action=delete&id=${dish.id}">Delete</a></td>
        </tr>
    </c:forEach>
</table>
</body>
</html>