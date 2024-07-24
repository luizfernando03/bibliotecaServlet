package org.example.controller;

import org.example.dao.LivroDAO;
import org.example.model.Livro;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class LivroServlet extends HttpServlet {

    private LivroDAO livroDAO;

    @Override
    public void init() {
        try {
            livroDAO = new LivroDAO();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        listarLivros(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action.equals("inserir")) {
            inserirLivro(request, response);
        }
    }

    private void listarLivros(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            List<Livro> livros = livroDAO.listarTodos();
            request.setAttribute("livros", livros);
            request.getRequestDispatcher("livros.jsp").forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Erro ao listar livros");
        }
    }

    private void inserirLivro(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String isbn = request.getParameter("isbn");
        String nome = request.getParameter("nome");
        String categoria = request.getParameter("categoria");
        int quantidade = Integer.parseInt(request.getParameter("quantidade"));

        Livro livro = new Livro();
        livro.setIsbn(isbn);
        livro.setNome(nome);
        livro.setCategoria(categoria);
        livro.setQuantidade(quantidade);

        try {
            livroDAO.adicionarLivro(livro);
            response.sendRedirect("LivroServlet");
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Erro ao inserir livro");
        }
    }

}
