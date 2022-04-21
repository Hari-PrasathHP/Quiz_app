<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head> <title>Quiz App </title> </head>
<body>
<h2>List of question and Answer</h2>
<br>
    <ol>
        <c:forEach var="question" items="${questionList}">
            <li>${question.description}</li>
            <c:forEach var="answerChoice" items="${question.answerChoices}">
            <ul style="style-type:none">
            <li>${answerChoice.title}</li>
            </ul>
            </c:forEach>
            </c:forEach>
    </ol>
<br>
</body>
</html>

