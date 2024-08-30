package br.com.busca.livros.busca.livros.main;

import br.com.busca.livros.busca.livros.model.DadosLivros;
import br.com.busca.livros.busca.livros.service.ConsumoApi;
import br.com.busca.livros.busca.livros.service.ConverteDados;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Principal {

    public void exibeMenu() {
        Scanner scan = new Scanner(System.in);

        var consumoApi = new ConsumoApi();
        System.out.println("Digite o nome de um livro: ");
        var query = scan.nextLine();
        var json = consumoApi.obterDados(query);
        System.out.println("JSON recebido: " + json);

        // Convertendo o JSON e armazenando os livros
        ObjectMapper objectMapper = new ObjectMapper();
        List<DadosLivros> livros = new ArrayList<>();

        try {
            JsonNode rootNode = objectMapper.readTree(json);
            JsonNode itemsNode = rootNode.path("items");
            if (itemsNode.isArray()) {
                for (JsonNode itemNode : itemsNode) {
                    JsonNode volumeInfoNode = itemNode.path("volumeInfo");
                    ConverteDados conversor = new ConverteDados();
                    DadosLivros dados = conversor.obterDados(volumeInfoNode.toString(), DadosLivros.class);
                    livros.add(dados);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Filtrar livros válidos (sem campos nulos ou vazios)
        List<DadosLivros> livrosValidos = livros.stream()
                .filter(l -> l.getTitulo() != null && !l.getTitulo().isEmpty())
                .filter(l -> l.getAutores() != null && !l.getAutores().isEmpty())
                .filter(l -> l.getEditora() != null && !l.getEditora().isEmpty())
                .filter(l -> l.getAnoLancamento() != null && !l.getAnoLancamento().isEmpty())
                .filter(l -> l.getDescricao() != null && !l.getDescricao().isEmpty())
                .collect(Collectors.toList());

        // Exibir livros válidos
        livrosValidos.forEach(System.out::println);
    }
}
