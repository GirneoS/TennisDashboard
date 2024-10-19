package com.ozhegov.tennis.controller;

import java.io.*;
import java.util.UUID;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.ozhegov.tennis.model.Message;
import com.ozhegov.tennis.service.NewMatchService;
import jakarta.persistence.NoResultException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet("/")
public class NewMatchServlet extends HttpServlet {
    private NewMatchService newMatchservice;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.addHeader("Content-Type","text/html;charset=UTF-8");
        resp.setContentType("text/html");

        PrintWriter pw = resp.getWriter();

        try(BufferedReader reader = new BufferedReader(new FileReader("/Users/mak/IdeaProjects/Tennis/src/main/webapp/index.html"))) {
            String line;
            while((line=reader.readLine())!=null)
                pw.println(line);
        }
    }
    //Проверяет существование игроков в БД, создает экземпляр матча и редиректит на страницу начатого матча
    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        try{
            newMatchservice = new NewMatchService();
            UUID uuid = newMatchservice.startMatch(req.getParameter("player1"), req.getParameter("player2"));

            resp.sendRedirect("http://localhost:8081/Tennis_war_exploded/match-score?uuid="+uuid);

        }catch(NoResultException e) {
            resp.addHeader("Content-Type","application/json;charset=UTF-8");
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);

            Message messageError = new Message("Игрок не существует в БД");
            Gson gson = new GsonBuilder().setPrettyPrinting().create();

            resp.getWriter().write(gson.toJson(messageError));
        }

    }

    public void destroy() {
    }
}