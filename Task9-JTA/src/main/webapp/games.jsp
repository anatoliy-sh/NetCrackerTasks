<%@ page import="entity.Game" %>
<%@ page import="entity.GamePlatform" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Games</title>
</head>
<body>

<h1>Games</h1>
<br>
<table border="2px">
    <tr>
        <td>id</td>
        <td>GamePlatform</td>
    </tr>
    <c:forEach var="gamePlatform" items="${gamePlatforms}">
        <tr>
            <td>
                    ${gamePlatform.getId()}
            </td>
            <td>
                    ${gamePlatform.getName()}
            </td>

        </tr>
    </c:forEach>


</table>
<table border="2px">
    <tr>
        <td>id</td>
        <td>Game</td>
        <td>GamePlatform id</td>
    </tr>
    <c:forEach var="game" items="${games}">
        <tr>

            <td>
                    ${game.getId()}
            </td>
            <td>
                    ${game.getName()}
            </td>
            <td>
                    ${game.getGamePlatform().getId()}
            </td>
        </tr>
    </c:forEach>

</table>


<form action="/Task9-Hibernate/game" method="post">
    <input type="text" name="gamePlatformId" id="gamePlatformId" placeholder="Platform ID"><br>
    <input type="text" name="gamePlatformName" id="gamePlatformName" placeholder="Platform Name"><br>
    <input type="submit" name="insertGamePlatform" value="insert"><br>
    <input type="submit" name="updateGamePlatform" value="update"><br>
    <br>
    <br>
    <input type="text" name="gameDelPlatformId" id="gameDelPlatformId" placeholder="Delete platform ID"><br>
    <input type="submit" name="deleteGamePlatform" value="delete game platform"><br>
    <br>
    <hr>

    <br>
    <input type="text" name="gameId" id="gameId" placeholder="Game ID"><br>
    <input type="text" name="gameName" id="gameName" placeholder="Game Name"><br>
    <input type="text" name="gamePlatformIdG" id="gamePlatformIdG" placeholder="Game platform id"><br>
    <input type="submit" name="insertGame" value="insert"><br>
    <input type="submit" name="updateGame" value="update"><br>
    <br>
    <br>
    <input type="text" name="gameDelId" id="gameDelId" placeholder="Delete game ID"><br>
    <input type="submit" name="deleteGame" value="delete game"><br>
    <br>
    <br>
    <%
        Game game = (Game) request.getAttribute("game");
        GamePlatform gamePlatform = (GamePlatform) request.getAttribute("gamePlatform");
        if (game != null) {%>
    Game
    <b><%=game.getId()%>
    </b>
    <b><%=game.getName()%>
    </b>
    <b><%=game.getGamePlatform().getId()%>
    </b>
    <%}%>
    <% if (gamePlatform != null) {%>
    Game platform
    <b><%= gamePlatform.getId() %>
    </b>
    <b><%= gamePlatform.getName() %>
    </b>

    <%}%>
    <br>
    <input type="text" name="search" id="search" placeholder="Search"><br>
    <input type="submit" name="getGameByName" value="GameByName"><br>
    <input type="submit" name="getGameById" value="GameById"><br>
    <input type="submit" name="getGamePlatformByName" value="GamePlatformByName"><br>
    <input type="submit" name="getGamePlatformById" value="GamePlatformById"><br>
</form>


</body>
</html>
