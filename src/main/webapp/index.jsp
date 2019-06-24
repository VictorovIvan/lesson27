<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Students</title>
    <meta charset="UTF-8"/>
</head>
<body>
<h3>Please enter name and password(birthday)</h3>
<form method="post" action="${pageContext.request.contextPath}/authorization">
    <input type="text" name="name" placeholder="name"><br/>
    <input type="text" name="birthday" placeholder="birthday"><br/>
    <input type="submit"/>
</form>
</body>
</html>
