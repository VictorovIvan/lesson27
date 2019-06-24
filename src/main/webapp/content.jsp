<%@ page import="ru.inno.stc14.entity.Person" %>
<%@ page import="ru.inno.stc14.servlet.PersonServlet" %>
<%@ page import="ru.inno.stc14.servlet.AuthorizationServlet" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Content</title>
</head>
<body>
<% String name = (String) request.getAttribute("authorization");
    if (name == null) {
        name = "";
    } else {
        name = "Hello " + name;
    }%>
<h1><%=name%>
</h1>
<ul>
    <li><a href="${pageContext.request.contextPath}/person/list">List students</a></li>
    <li><a href="${pageContext.request.contextPath}/person">New student</a></li>
</ul>
</body>
</html>