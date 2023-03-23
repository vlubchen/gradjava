<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>
<jsp:include page="fragments/headTag.jsp"/>
<body>
<jsp:include page="fragments/bodyHeader.jsp"/>
<section>
    <h3><spring:message code="dish.title"/></h3>
    <form method="get" action="dishes/filter">
        <dl>
            <dt><spring:message code="dish.day"/>:</dt>
            <dd><input type="date" name="day" value="${param.day}"></dd>
        </dl>
        <button type="submit"><spring:message code="dish.filter"/></button>
    </form>
    <hr>
    <a href="dishes/create"><spring:message code="dish.add"/></a>
    <hr>
    <table border="1" cellpadding="8" cellspacing="0">
        <thead>
        <tr>
            <th><spring:message code="dish.day"/></th>
            <th><spring:message code="dish.restaurant"/></th>
            <th><spring:message code="dish.name"/></th>
            <th><spring:message code="dish.price"/></th>
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
                <td><a href="dishes/update?id=${dish.id}"><spring:message code="common.update"/></a></td>
                <td><a href="dishes/delete?id=${dish.id}"><spring:message code="common.delete"/></a></td>
            </tr>
        </c:forEach>
    </table>
</section>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>