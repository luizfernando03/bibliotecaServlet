package org.example.service;

import org.example.dao.UsuarioDAO;
import org.example.model.Usuario;

import java.sql.SQLException;

public class AuthService {

    private UsuarioDAO usuarioDAO;

    public AuthService() throws SQLException {
        this.usuarioDAO = new UsuarioDAO();
    }

    public boolean autenticar(String email, String senha) throws SQLException {
        Usuario usuario = usuarioDAO.buscarPorEmail(email);
        if (usuario != null && senha.equals(usuario.getSenha())) { // Adapte conforme necessário
            return true;
        }
        return false;
    }

    public Usuario buscarUsuarioPorEmail(String email) throws SQLException {
        return usuarioDAO.buscarPorEmail(email);
    }

}
