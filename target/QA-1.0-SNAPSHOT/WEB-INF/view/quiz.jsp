<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>


<html>
<head> <title>Quiz App </title> </head>
<body>

<h2>List of quiz</h2>
<br>
    <ul>
        <c:forEach var="quiz" items="${quizList}">
          Title:  <li>${quiz.title}</li>
           NoOfQuestion: <li>${quiz.noOfQuestion}</li>
           Duration: <li>${quiz.duration}</li>
           Subject: <li>${quiz.subject}</li>
            Difficulty: <li>${quiz.difficulty}</li>
        </c:forEach>

    </ul>
<br>
</body>
</html>