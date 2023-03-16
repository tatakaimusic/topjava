<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.time.LocalDateTime" %>
<%@ page import="ru.javawebinar.topjava.web.MealsServlet" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:useBean id="meal" type="ru.javawebinar.topjava.model.Meal" scope="request"/>
    <jsp:useBean id="exist" type="java.lang.Boolean" scope="request"/>
    <title>Title</title>
</head>
<body>
<section>
    <c:choose>
        <c:when test="${exist}">
            <h2>Edit meal</h2>
        </c:when>
        <c:otherwise>
            <h2>Add meal</h2>
        </c:otherwise>
    </c:choose>
    <br/>
    <form method="post" action="meals" enctype="application/x-www-form-urlencoded">
        <input type="hidden" name="id" value="${meal.id}">
        <dl>
            <dt>Date:</dt>
            <c:choose>
                <c:when test="${exist}">
                    <dd><input type="datetime-local" name="date" size="30"
                               value="<%=MealsServlet.formatter.format(meal.getDateTime())%>"
                               placeholder="yyyy-MM-dd HH:mm" required></dd>
                </c:when>
                <c:otherwise>
                    <dd><input type="datetime-local" name="date" size="30"
                               value="<%=MealsServlet.formatter.format(LocalDateTime.now())%>"
                               placeholder="yyyy-MM-dd HH:mm" required></dd>
                </c:otherwise>
            </c:choose>
        </dl>
        <dl>
            <dt>Description</dt>
            <dd><input type="text" name="description" size="30" value="${meal.description}"></dd>
        </dl>
        <dl>
            <dt>Calories</dt>
            <dd><input type="text" name="calories" size="30" value="${meal.calories}" pattern="^[ 0-9]+$"
                       placeholder="любое число"></dd>
        </dl>
        <button type="submit">Save</button>
        <button type="reset" onclick="window.history.back()">Cancel</button>
    </form>
</section>

</body>
</html>
