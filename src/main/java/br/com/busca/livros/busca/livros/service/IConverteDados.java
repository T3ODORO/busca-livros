package br.com.busca.livros.busca.livros.service;

public interface IConverteDados {
    <T> T obterDados(String json, Class<T> classe);

}
