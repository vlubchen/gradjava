<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<jsp:include page="fragments/headTag.jsp"/>
<body>
<jsp:include page="fragments/bodyHeader.jsp"/>
<section>
    <h3><fmt:message key="dish.title"/></h3>
    <form method="get" action="dishes/filter">
        <dl>
            <dt><fmt:message key="dish.day"/>:</dt>
            <dd><input type="date" name="day" value="${param.day}"></dd>
        </dl>
        <button type="submit"><fmt:message key="dish.filter"/></button>
    </form>
    <hr>
    <a href="dishes/create"><fmt:message key="dish.add"/></a>
    <hr>
    <table border="1" cellpadding="8" cellspacing="0">
        <thead>
        <tr>
            <th><fmt:message key="dish.day"/></th>
            <th><fmt:message key="dish.restaurant"/></th>
            <th><fmt:message key="dish.name"/></th>
            <th><fmt:message key="dish.price"/></th>
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
                <td><a href="dishes/update?id=${dish.id}"><fmt:message key="common.update"/></a></td>
                <td><a href="dishes/delete?id=${dish.id}"><fmt:message key="common.delete"/></a></td>
            </tr>
        </c:forEach>
    </table>
</section>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>