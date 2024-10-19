<%@ page import="java.util.UUID" %>
<%@ page import="com.ozhegov.tennis.model.Match" %>
<%@ page import="com.ozhegov.tennis.model.CurrentMatches" %>
<%@ page import="com.ozhegov.tennis.service.OngoingMatchesService" %><%--
  Created by IntelliJ IDEA.
  User: mak
  Date: 10.08.2024
  Time: 19:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="style.css">
</head>

<body>
    <%
        UUID uuid = UUID.fromString(request.getParameter("uuid"));

        OngoingMatchesService service = new OngoingMatchesService();
        Match match = service.get(uuid);
    %>
    <header>
        <a href="http://localhost:8081/Tennis_war_exploded/">New match</a>
        <a href="all-matches.jsp">Past matches</a>
    </header>
    <form action<%="=http://localhost:8081/Tennis_war_exploded/match-score?uuid="+uuid%> method="post">
        <table>
            <caption id="cap">Current match</caption>
            <thead>
                <tr>
                    <td class="table_head">Name</td>
                    <td class="table_head">Sets</td>
                    <td class="table_head">Games</td>
                    <td class="table_head">Points</td>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <td><%=match.getPlayer1().getName()%></td>
                    <td><%=match.getSets1()%></td>
                    <td><%=match.getGames1()%></td>
                    <td><%=match.getPoints1()%></td>
                    <td><button type="submit" name="playerId" value<%="="+match.getPlayer1().getId()%>>+</button></td>
                </tr>
                <tr>
                    <td><%=match.getPlayer2().getName()%></td>
                    <td><%=match.getSets2()%></td>
                    <td><%=match.getGames2()%></td>
                    <td><%=match.getPoints2()%></td>
                    <td><button type="submit" name="playerId" value<%="="+match.getPlayer2().getId()%>>+</button></td>
                </tr>
            </tbody>
        </table>
    </form>
</body>
</html>
