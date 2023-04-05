<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>
<jsp:include page="fragments/headTag.jsp"/>
<body>
<script type="text/javascript" src="resources/js/gradjava.common.js" defer></script>
<script type="text/javascript" src="resources/js/gradjava.dishes.js" defer></script>
<jsp:include page="fragments/bodyHeader.jsp"/>
<div class="jumbotron pt-4">
    <div class="container">
        <h3 class="text-center"><spring:message code="dish.title"/></h3>
        <div class="card border-dark">
            <div class="card-body pb-0">
                <form id="filter">
                    <div class="row">
                        <div class="col-3">
                            <label for="filterDay"><spring:message code="dish.day"/></label>
                            <input class="form-control" type="date" name="day" id="filterDay">
                        </div>
                    </div>
                </form>
            </div>
            <div class="card-footer text-right">
                <button class="btn btn-primary" onclick="ctx.updateTable()">
                    <span class="fa fa-filter"></span>
                    <spring:message code="dish.filter"/>
                </button>
            </div>
        </div>
        <br/>
        <button class="btn btn-primary" onclick="add()">
            <span class="fa fa-plus"></span>
            <spring:message code="common.add"/>
        </button>

        <table class="table table-striped" id="datatable">
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
                    <td><a><span class="fa fa-pencil"></span></a></td>
                    <td><a onclick="deleteRow(${dish.id})"><span class="fa fa-remove"></span></a></td>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>
<div class="modal fade" tabindex="-1" id="editRow">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title" id="modalTitle"><spring:message code="dish.add"/></h4>
                <button type="button" class="close" data-dismiss="modal" onclick="closeNoty()">&times;</button>
            </div>
            <div class="modal-body">
                <form id="detailsForm">
                    <input type="hidden" id="id" name="id">

                    <div class="form-group">
                        <label for="day" class="col-form-label"><spring:message code="dish.day"/></label>
                        <input type="date" class="form-control" id="day" name="day"
                               placeholder="<spring:message code="dish.day"/>">
                    </div>

                    <div class="form-group">
                        <label for="restaurant" class="col-form-label"><spring:message
                                code="dish.restaurant"/></label>
                        <input type="text" class="form-control" id="restaurant" name="restaurant"
                               placeholder="<spring:message code="dish.restaurant"/>">
                    </div>

                    <div class="form-group">
                        <label for="name" class="col-form-label"><spring:message
                                code="dish.name"/></label>
                        <input type="text" class="form-control" id="name" name="name"
                               placeholder="<spring:message code="dish.name"/>">
                    </div>

                    <div class="form-group">
                        <label for="price" class="col-form-label"><spring:message code="dish.price"/></label>
                        <input type="number" class="form-control" id="price" name="price" placeholder="1000">
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal" onclick="closeNoty()">
                    <span class="fa fa-close"></span>
                    <spring:message code="common.cancel"/>
                </button>
                <button type="button" class="btn btn-primary" onclick="save()">
                    <span class="fa fa-check"></span>
                    <spring:message code="common.save"/>
                </button>
            </div>
        </div>
    </div>
</div>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>