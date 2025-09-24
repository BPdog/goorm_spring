<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
<h2>Login</h2>
<form:form modelAttribute="user" method="post">
    <p>
        <label for="email">Email:</label>
        <form:input path="email" id="email"/>
    </p>
    <p>
        <label for="password">Password:</label>
        <form:password path="password" id="password"/>
    </p>
    <p>
        <button type="submit">Login</button>
    </p>
</form:form>
</body>
</html>
