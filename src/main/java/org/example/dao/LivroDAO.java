package org.example.dao;

import org.example.model.Livro;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LivroDAO {

    private Connection connection;

    public LivroDAO() throws SQLException {
        connection = DatabaseConnection.getConnection();
    }

    public void adicionarLivro(Livro livro) throws SQLException {
        String sql = "INSERT INTO livros (isbn, nome, categoria, quantidade) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, livro.getIsbn());
            stmt.setString(2, livro.getNome());
            stmt.setString(3, livro.getCategoria());
            stmt.setInt(4, livro.getQuantidade());
            stmt.executeUpdate();
        }
    }

    public void atualizarLivro(Livro livro) throws SQLException {
        String sql = "UPDATE livros SET nome = ?, categoria = ?, quantidade = ? WHERE isbn = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, livro.getNome());
            stmt.setString(2, livro.getCategoria());
            stmt.setInt(3, livro.getQuantidade());
            stmt.setString(4, livro.getIsbn());
            stmt.executeUpdate();
        }
    }

    public Livro buscarLivroPorIsbn(String isbn) throws SQLException {
        String sql = "SELECT * FROM livros WHERE isbn = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, isbn);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Livro(
                            rs.getString("isbn"),
                            rs.getString("nome"),
                            rs.getString("categoria"),
                            rs.getInt("quantidade")
                    );
                }
            }
        }
        return null;
    }

    public List<Livro> listarTodos() throws SQLException {
        List<Livro> livros = new ArrayList<>();
        String sql = "SELECT * FROM livros";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Livro livro = new Livro();
                livro.setIsbn(rs.getString("isbn"));
                livro.setNome(rs.getString("nome"));
                livro.setCategoria(rs.getString("categoria"));
                livro.setQuantidade(rs.getInt("quantidade"));
                livros.add(livro);
            }
        }
        return livros;
    }

    public void removerLivro(String isbn) throws SQLException {
        String sql = "DELETE FROM livros WHERE isbn = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, isbn);
            stmt.executeUpdate();
        }
    }
}
