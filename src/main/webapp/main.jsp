<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE HTML>
<html>
<head>
    <meta charset="UTF-8" />
    <title>Main</title>
    <form action="/logout" method="post">
        <security:authorize access="isAuthenticated()">
            <security:authentication property="principal.username" />
        </security:authorize>
        <input type="hidden" name="_csrf" value="${_csrf.token}" />
        <button class="btn btn-primary" type="submit">Sign Out</button>
    </form>


    <form>
        <p><select size="1" name="currency" style="height:30px;font-size:14pt;width:100px" onchange="location.href=this.value">
            <option disabled>Choose currency</option>
            <option selected disabled >${current}</option>
            <option value="/exchange/RUB">RUB</option>
            <option value="/exchange/BYN">BYN</option>
            <option value="/exchange/EUR" >EUR</option>
            <option value="/exchange/USD">USD</option>
        </select></p>

    </form>

    <link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
    <link href="${contextPath}/resources/css/common.css" rel="stylesheet">
</head>


<body>

<div>

    <%--@elvariable id="ticketForm" type=""--%>
    <form:form action="/addTicketForm" method="post" modelAttribute="ticketForm">
        <!--<form:label path="id">id: </form:label> <form:input type="" path="id"/>-->
        <form:label path="departDate">Depart Date: </form:label> <form:input type="date" path="departDate"/>
        <form:label path="returnDate">Return Date: </form:label> <form:input type="date" path="returnDate"/>
        <form:label path="origin">Origin: </form:label> <form:input value="MOW" type="text" path="origin"/>
        <form:label path="destination">Destination: </form:label> <form:input value="London" type="text" path="destination"/>
        <input type="submit" value="submit"/>
    </form:form>

</div>

<div class="container">
    <h3>My tickets</h3>
</div>
<div class="container">
<table border="1" class="table">
    <tr>
        <th>Price</th>
        <th>Airline</th>
        <th>Flight number</th>
        <th>Departure</th>
        <th>Return</th>
        <th>Expires</th>
        <th>Destination</th>
        <th>Origin</th>
        <th></th>
        <th></th>
    </tr>
    <c:forEach items="${aviaTickets}" var="aviaTicket">
    <tr>
        <td>
<c:choose>
    <c:when test="${k != null}">
        <fmt:formatNumber value="${aviaTicket.price*k}" maxFractionDigits="2"/>
    </c:when>
    <c:otherwise>
        ${aviaTicket.price}
    </c:otherwise>
</c:choose>
        </td>
        <td>${aviaTicket.airline}</td>
        <td>${aviaTicket.flightNumber}</td>
        <td>${aviaTicket.departDate}</td>
        <td>${aviaTicket.returnDate}</td>
        <td>${aviaTicket.expiresAt}</td>
        <td>${aviaTicket.destination}</td>
        <td>${aviaTicket.origin}</td>
            <td><a class="btn btn-lg  btn-block"  href="deleteTicket/${aviaTicket.id}"> Delete</a></td>
            <td><a class="btn btn-lg  btn-block"  href="share/${aviaTicket.id}"> Share</a></td>

    </tr>
    </c:forEach>
</table>
</div>



</body>

</html>