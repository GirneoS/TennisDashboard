<%@ page import="com.ozhegov.tennis.dao.MatchDAO" %>
<%@ page import="com.ozhegov.tennis.model.Match" %>
<%@ page import="java.util.List" %>
<%@ page import="java.io.Writer" %>
<%@ page import="java.io.PrintWriter" %>
<%@ page import="com.ozhegov.tennis.model.MatchDTO" %><%--
  Created by IntelliJ IDEA.
  User: mak
  Date: 21.08.2024
  Time: 23:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>all-matches</title>
    <link rel="stylesheet" href="style.css">
</head>
<body>
    <%
        MatchDAO matchDAO = new MatchDAO();
        List<MatchDTO> list = matchDAO.getAll();
        if(!request.getParameterMap().isEmpty()){
            String name = request.getParameter("playerName");
            if(name!=null){
                list = matchDAO.getByPlayerName(name);
            }
        }
    %>
    <header>
        <a href="http://localhost:8081/Tennis_war_exploded/">New match</a>
        <a href="all-matches.jsp">Past matches</a>
    </header>
    <form method="get" action="http://localhost:8081/Tennis_war_exploded/all-matches.jsp">
        <label for="playerName">Поиск по имени игрока</label>
        <input type="text" name="playerName" id="playerName">
        <input type="submit" value="Поиск">
    </form>
    <table>
        <thead>
            <tr>
                <th>Player1</th>
                <th>Player2</th>
                <th>Winner</th>
            </tr>
        </thead>
        <tbody>
            <%for(MatchDTO dto: list){%>
                <%="<tr>"+
                    "<td>"+dto.getPlayer1().getName()+"</td>"+
                    "<td>"+dto.getPlayer2().getName()+"</td>"+
                    "<td>"+dto.getWinner().getName()+"</td>"+
                "</tr>"%>
            <%}%>
        </tbody>

    </table>
</body>
</html>
