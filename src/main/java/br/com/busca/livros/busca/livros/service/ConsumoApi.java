package br.com.busca.livros.busca.livros.service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ConsumoApi {
    public String obterDados(String endereco){

        // Substitui espaços por "+"
        String query = endereco.replace(" ", "+");
        String consumo = "https://www.googleapis.com/books/v1/volumes?q=" + query + "&key=AIzaSyD4EVtWGO6ez3SFAUrPLjTO7wRMAuYLP6I";

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(consumo))
                .build();
        HttpResponse<String> response = null;
        try {
            response = client
                    .send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException e) {
            System.err.println("Erro de IO: " + e.getMessage());
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            System.err.println("Requisição interrompida: " + e.getMessage());
            throw new RuntimeException(e);
        }

        String json = response.body();
        return json;
    }
}



