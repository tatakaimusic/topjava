<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:useBean id="meal" type="ru.javawebinar.topjava.model.Meal" scope="request"/>
    <jsp:useBean id="FORMATTER" type="java.time.format.DateTimeFormatter" scope="request"/>
    <title>Title</title>
</head>
<body>
<section>
    <h2><c:out value="${meal.id != null ? 'Edit meal' : 'Add meal'}"/></h2>
    <br/>
    <form method="post" action="meals" enctype="application/x-www-form-urlencoded">
        <input type="hidden" name="id" value="${meal.id}">
        <dl>
            <dt>Date:</dt>
            <dd><input type="datetime-local" name="date" size="30"
                       value="${FORMATTER.format(meal.dateTime)}"
                       placeholder="yyyy-MM-dd HH:mm" required></dd>

        </dl>
        <dl>
            <dt>Description</dt>
            <dd><input type="text" name="description" size="30" value="${meal.description}"></dd>
        </dl>
        <dl>
            <dt>Calories</dt>
            <dd><input type="number" name="calories" size="30" min="1" value="${meal.calories}"
                       pattern="^[ 0-9]+$"
                       placeholder="любое число"></dd>
        </dl>
        <button type="submit">Save</button>
        <button type="reset" onclick="window.history.back()">Cancel</button>
    </form>
</section>

</body>
</html>
