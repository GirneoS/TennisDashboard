package com.ozhegov.tennis.controller;

import java.io.*;

import com.ozhegov.tennis.dao.MatchDAO;
import com.ozhegov.tennis.dto.MatchDTO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.Servlet;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet("/new-match/")
public class NewMatchServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        MatchDAO dao = new MatchDAO();
        System.out.println(dao.getAll());

        resp.setContentType("text/html");

        PrintWriter pw = resp.getWriter();

        try(BufferedReader reader = new BufferedReader(new FileReader("/Users/mak/IdeaProjects/Tennis/src/main/webapp/index.html"))) {
            String line;
            while((line=reader.readLine())!=null)
                pw.println(line);
        }
    }
    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        resp.setContentType("text/html");
        System.out.println("AaaaaAAAAA");
        System.out.println(req.getParameterMap().entrySet());

        PrintWriter writer = resp.getWriter();
        writer.println("<html>");
        writer.println("<h1>Hi</h1>");
        writer.println("</html>");
        ServletContext context = req.getServletContext();
        RequestDispatcher rd = context.getRequestDispatcher("/current-match/");
        rd.forward(req,resp);

    }

    public void destroy() {
    }
}