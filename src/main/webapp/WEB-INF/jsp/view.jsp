<%@ page import="ru.javawebinar.topjava.model.Meal" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <jsp:useBean id="meal" type="ru.javawebinar.topjava.model.Meal" scope="request"/>
    <title>Meal ${meal.uuid}</title>
</head>
<body>
<section>
    <h2>Meal</h2>
    <br/>
    <p>Date: <%=Meal.formatter.format(meal.getDateTime())%>
    </p>
    <p>Description: ${meal.description}</p>
    <p>Calories: ${meal.calories}</p>
    <button onclick="location.href='meals'" type="button">
        Ok
    </button>
</section>
</body>
</html>
