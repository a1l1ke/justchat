package com.example.justchat;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/chat")
public class ChatServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/chat.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Post - Redirect - Get
        // form으로 받을 것임
        String model = req.getParameter("model");
        String message = req.getParameter("message");
        HttpSession session = req.getSession();
        List<String> chat = null;
        Object chatObj = session.getAttribute("chat");
        if (!(chatObj instanceof ArrayList)) {
            chat = new ArrayList<>();
        } else {
            chat = (ArrayList<String>) session.getAttribute("chat");
        }
        System.out.println("chat = " + chat);
        chat.add("나 : %s".formatted(message));
        chat.add("%s : %s".formatted(model, message));
        session.setAttribute("chat", chat); // -> el -> jstl(for-each)
        resp.sendRedirect("chat"); // P -> R/G
        // Form 처리를 Post하고 나서 다시 Post 경로로 포워드를 하면
        // 나중에 그 링크를 새로고침하거나 하면 재제출이 됨 (PRG)
    }
}