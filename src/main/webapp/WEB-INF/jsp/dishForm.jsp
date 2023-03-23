<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>
<jsp:include page="fragments/headTag.jsp"/>
<body>
<jsp:include page="fragments/bodyHeader.jsp"/>

<section>
    <jsp:useBean id="dish" type="ru.vlubchen.gradjava.model.Dish" scope="request"/>
    <h3><spring:message code="${dish.isNew() ? 'dish.add' : 'dish.edit'}"/></h3>
    <hr>
    <form method="post" action="dishes">
        <input type="hidden" name="id" value="${dish.id}">
        <dl>
            <dt><spring:message code="dish.day"/></dt>
            <dd><input type="date" value="${dish.day}" name="day" required></dd>
        </dl>
        <dl>
            <dt><spring:message code="dish.restaurant"/></dt>
            <dd><input type="text" value="${dish.restaurant}" name="restaurant" required></dd>
        </dl>
        <dl>
            <dt><spring:message code="dish.name"/></dt>
            <dd><input type="text" value="${dish.name}" size=40 name="name" required></dd>
        </dl>
        <dl>
            <dt><spring:message code="dish.price"/></dt>
            <dd><input type="int" value="${dish.price}" name="price" required></dd>
        </dl>
        <button type="submit"><spring:message code="common.save"/></button>
        <button onclick="window.history.back()" type="button"><spring:message code="common.cancel"/></button>
    </form>
</section>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>