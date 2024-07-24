package org.example.service;

import org.example.dao.LivroDAO;
import org.example.model.Livro;

import java.sql.SQLException;
import java.util.List;

public class LivroService {

    private final LivroDAO livroDAO;

    public LivroService() throws SQLException {
        livroDAO = new LivroDAO();
    }

    public void adicionarLivro(Livro livro) throws SQLException {
        livroDAO.adicionarLivro(livro);
    }

    public void atualizarLivro(Livro livro) throws SQLException {
        livroDAO.atualizarLivro(livro);
    }

    public void removerLivro(String isbn) throws SQLException {
        livroDAO.removerLivro(isbn);
    }

    public Livro buscarLivroPorIsbn(String isbn) throws SQLException {
        return livroDAO.buscarLivroPorIsbn(isbn);
    }

    public List<Livro> listarTodosLivros() throws SQLException {
        return livroDAO.listarTodosLivros();
    }

}
