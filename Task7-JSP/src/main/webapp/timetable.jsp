<%@ page import="java.util.List" %>
<%@ page import="db.Para" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Timetable</title>
</head>
<body>

<h1>Time table</h1>
<br>
<table border="2px">
    <tr>
        <td>Time</td>
        <td>Para</td>
        <td>Subject</td>
    </tr>
    <c:forEach var="para" items="${timetable}">
        <tr>
            <td>
                    ${para.getNum()}
            </td>
            <td>
                    ${para.getSubject()}
            </td>
            <td>
                    ${para.getTime()}
            </td>
        </tr>
    </c:forEach>

</table>

</body>
</html>
