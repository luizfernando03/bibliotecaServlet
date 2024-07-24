package org.example.model;

public class Livro {

    private String isbn;
    private String nome;
    private String categoria;
    private int quantidade;

    public Livro(String isbn, String nome, String categoria, int quantidade) {
        this.isbn = isbn;
        this.nome = nome;
        this.categoria = categoria;
        this.quantidade = quantidade;
    }

    public Livro() { }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    @Override
    public String toString() {
        return "Livro{" +
                "isbn =' " + isbn + '\'' +
                ", nome = '" + nome + '\'' +
                ", categoria = '" + categoria + '\'' +
                ", quantidade = " + quantidade +
          '}';
    }
}
