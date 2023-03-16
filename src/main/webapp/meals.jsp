<%@ page import="ru.javawebinar.topjava.web.MealsServlet" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Meals</title>
</head>
<body>
<section>
    <div class="button-wrapper">
        <h2>Meals</h2>
        <button onclick="location.href='meals?action=save'" type="button">
            New meal
        </button>
    </div>
    <br/>
    <table border="1" cellpadding="8" cellspacing="0">
        <tr>
            <th>Date</th>
            <th>Description</th>
            <th>Calories</th>
            <th>Delete</th>
            <th>Update</th>
        </tr>
        <c:forEach items="${meals}" var="meal">
            <jsp:useBean id="meal" type="ru.javawebinar.topjava.model.MealTo"/>
            <c:choose>
                <c:when test="${meal.excess}">
                    <tr bgcolor="#a52a2a">
                </c:when>
                <c:otherwise>
                    <tr bgcolor="#7fff00">
                </c:otherwise>
            </c:choose>
            <td><a href="meals?id=${meal.id}&action=view">${MealsServlet.formatter.format(meal.dateTime)}
            </a></td>
            <td>${meal.description}</td>
            <td>${meal.calories}</td>
            <td><a href="meals?id=${meal.id}&action=delete">Delete</a></td>
            <td><a href="meals?id=${meal.id}&action=update">Update</a></td>
            </tr>
        </c:forEach>
    </table>
</section>
</body>
</html>
