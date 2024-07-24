package org.example.controller;

import org.example.service.AuthService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

public class LoginServlet {

    private AuthService authService;

    {
        try {
            authService = new AuthService();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String senha = request.getParameter("senha");

        try {
            if (authService.autenticar(email, senha)) {
                HttpSession session = request.getSession();
                session.setAttribute("usuario", authService.buscarUsuarioPorEmail(email));
                response.sendRedirect("livros"); // Redirecionar para a página de livros
            } else {
                response.sendRedirect("login.jsp?error=1"); // Redirecionar para a página de login com erro
            }
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Erro ao autenticar usuário");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
