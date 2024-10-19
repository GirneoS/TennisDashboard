package com.ozhegov.tennis.controller;

import com.ozhegov.tennis.model.Match;
import com.ozhegov.tennis.service.MatchScoreCalculationService;
import com.ozhegov.tennis.service.OngoingMatchesService;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.UUID;

@WebServlet("/match-score")
public class MatchScoreController extends HttpServlet {
    private MatchScoreCalculationService calculationService;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletContext context = getServletContext();
        RequestDispatcher dispatcher = context.getRequestDispatcher("/current-match.jsp");

        dispatcher.forward(req,resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UUID uuid = UUID.fromString(req.getParameter("uuid"));
        long playerId = Long.parseLong(req.getParameter("playerId"));

        OngoingMatchesService ongoingMatches = new OngoingMatchesService();
        Match match = ongoingMatches.get(uuid);

        calculationService = new MatchScoreCalculationService(match, uuid);
        calculationService.addPoint(playerId);

        if(ongoingMatches.containsMatch(uuid)) {
            doGet(req, resp);
            return;
        }

        resp.sendRedirect("http://localhost:8081/Tennis_war_exploded/");
    }
}
