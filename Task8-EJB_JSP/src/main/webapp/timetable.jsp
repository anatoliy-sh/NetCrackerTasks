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
        <td>Week</td>
        <td>Probability</td>
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
        <td>
            <%=timetable.get(i).getWeek()%>
        </td>
        <td>
            <%=timetable.get(i).getProbability()%>
        </td>
    </tr>
    <%} %>
</table>

<h1>Add new local</h1>

<form action="/timetable/timetable" method="post">
    <input type="text" name="time" placeholder="time">
    <input type="text" name="para" placeholder="number">
    <input type="text" name="subject" placeholder="subject">
    <input type="text" name="week" placeholder="week">
    <input type="submit" name="add" value="Add">
</form>

</body>
</html>
