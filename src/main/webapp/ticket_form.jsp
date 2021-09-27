<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE HTML>
<html>
<head>
    <meta charset="UTF-8" />
    <title>Ticket Form</title>


    <link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
    <link href="${contextPath}/resources/css/common.css" rel="stylesheet">
</head>


<body>

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

        </tr>
        <c:forEach items="${aviaTickets}" var="aviaTicket">
            <tr>

                <td>${aviaTicket.price}</td>
                <td>${aviaTicket.airline}</td>
                <td>${aviaTicket.flightNumber}</td>
                <td>${aviaTicket.departDate}</td>
                <td>${aviaTicket.returnDate}</td>
                <td>${aviaTicket.expiresAt}</td>
                <td>${aviaTicket.destination}</td>
                <td>${aviaTicket.origin}</td>
                <td><a class="btn btn-lg  btn-block"  href="saveTicket/${aviaTicket.id}"> Save</a></td>


            </tr>
        </c:forEach>
    </table>
</div>



</body>

</html>