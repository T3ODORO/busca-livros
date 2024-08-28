package br.com.busca.livros.busca.livros.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DadosLivros {
    @JsonAlias("title")
    private String titulo;

    @JsonAlias("authors")
    private List<String> autores;

    @JsonAlias("publisher")
    private String editora;

    @JsonAlias("publishedDate")
    private String anoLancamento;

    @JsonAlias("pageCount")
    private int numPaginas;

    @JsonAlias({"type", "identifier"})
    private String isbn;

    @JsonAlias("description")
    private String descricao;

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public List<String> getAutores() {
        return autores;
    }

    public void setAutores(List<String> autores) {
        this.autores = autores;
    }

    public String getEditora() {
        return editora;
    }

    public void setEditora(String editora) {
        this.editora = editora;
    }

    public String getAnoLancamento() {
        return anoLancamento;
    }

    public void setAnoLancamento(String anoLancamento) {
        this.anoLancamento = anoLancamento;
    }

    public int getNumPaginas() {
        return numPaginas;
    }

    public void setNumPaginas(int numPaginas) {
        this.numPaginas = numPaginas;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public String toString() {
        return "DadosLivros{" +
                "titulo='" + titulo + '\'' +
                ", autores=" + autores +
                ", editora='" + editora + '\'' +
                ", anoLancamento='" + anoLancamento + '\'' +
                ", numPaginas=" + numPaginas +
                ", isbn='" + isbn + '\'' +
                ", descricao='" + descricao + '\'' +
                '}';
    }
}
