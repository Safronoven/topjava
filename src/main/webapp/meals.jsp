<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Meals</title>
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<h2>Meals</h2>
<table class="item-table">
    <tr>
        <th>Date</th>
        <th>Time</th>
        <th>Description</th>
        <th>Calories</th>
    </tr>
    <c:forEach var="item" items="${mealList}">
        <tr style="color: <c:out value="${item.exceed eq true ? 'red' : 'green'}" />">
            <td>${item.date}</td>
            <td>${item.time}</td>
            <td>${item.description}</td>
            <td>${item.calories}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
