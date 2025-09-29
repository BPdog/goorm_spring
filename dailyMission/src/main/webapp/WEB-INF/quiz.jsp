<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Quiz</title>
</head>
<body>
    <h1>Quiz</h1>
    <p>${question.question}</p>
    <form action="/quiz" method="post">
        <input type="text" name="answer" required>
        <button type="submit">Submit</button>
    </form>
</body>
</html>
