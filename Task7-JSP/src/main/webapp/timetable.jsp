<%@ page import="java.util.List" %>
<%@ page import="db.Para" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
    <%
        List<Para> timetable = (List<Para>) request.getAttribute("timetable");
        for (int i = 0; i < timetable.size(); i++) {

    %>
    <tr>
        <td>
            <%=timetable.get(i).getTime()%>
        </td>
        <td>
            <%=timetable.get(i).getNum()%>
        </td>
        <td>
            <%=timetable.get(i).getSubject()%>
        </td>
    </tr>
    <%} %>
</table>

</body>
</html>
