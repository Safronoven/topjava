<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
    <title>Show All Meals</title>
</head>
<body>
<table border=1>
    <thead>
    <tr>
        <th>Id</th>
        <th>Description</th>
        <th>Calories</th>
        <th>Date</th>
        <th colspan=2>Action</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${meals}" var="meal">
        <tr>
            <td><c:out value="${meal.getId()}" /></td>
            <td><c:out value="${meal.getDescription()}" /></td>
            <td><c:out value="${meal.getCalories()}" /></td>
            <td><fmt:formatDate pattern="yyyy-MMM-dd" value="${meal.getDate()}" /></td>
            <td><a href="MealServlet?action=edit&userId=<c:out value="${meal.Id}"/>">Update</a></td>
            <td><a href="MealServlet?action=delete&userId=<c:out value="${meal.Id}"/>">Delete</a></td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<p><a href="meals.jsp">Add Meal</a></p>

</body>
</html>