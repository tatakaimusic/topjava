<%@ page import="ru.javawebinar.topjava.model.Meal" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:useBean id="meal" type="ru.javawebinar.topjava.model.Meal" scope="request"/>
    <title>Title</title>
</head>
<body>
<section>
    <h2>Edit meal</h2>
    <br/>
    <form method="post" action="meals" enctype="application/x-www-form-urlencoded">
        <input type="hidden" name="uuid" value="${meal.uuid}">
        <dl>
            <dt>Date:</dt>
            <dd><input type="text" name="date" size="30" value="<%=Meal.formatter.format(meal.getDateTime())%>"
                       placeholder="yyyy-MM-dd HH:mm:ss" required></dd>
        </dl>
        <dl>
            <dt>Description</dt>
            <dd><input type="text" name="description" size="30" value="${meal.description}"></dd>
        </dl>
        <dl>
            <dt>Calories</dt>
            <dd><input type="text" name="calories" size="30" value="${meal.calories}"></dd>
        </dl>
        <button type="submit">Save</button>
        <button type="reset" onclick="window.history.back()">Cancel</button>
    </form>
</section>

</body>
</html>
