<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<body>
<form:form  action="quiz/list">
Subject:<form:select path="subject">
    <form:option value="MATHS" name="maths"/>
    <form:option value="PHYSICS" name="Usa"/>
    <form:option value="BIOLOGY" name="Uk"/>
    <form:option value="CHEMISTRY" name="Uae"/>
    </form:select><br><br>
Difficulty:<form:select path="difficulty">
        <form:option value=" BEGINNER" name="maths"/>
        <form:option value= "INTERMEDIATE" name="Usa"/>
        <form:option value="ADVANCED" name="Uk"/>
        </form:select><br><br>
    <input type="submit" value="submit"/>
</form:form>
</body>
</html>
