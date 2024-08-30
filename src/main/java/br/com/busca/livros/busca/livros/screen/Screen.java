package br.com.busca.livros.busca.livros.screen;

import br.com.busca.livros.busca.livros.model.DadosLivros;
import br.com.busca.livros.busca.livros.service.ConsumoApi;
import br.com.busca.livros.busca.livros.service.ConverteDados;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Screen extends JFrame implements ActionListener {
    private JButton searchButton;
    private JTextField inputField;
    private JTextArea resultsArea;

    public Screen() {
        // Configuração básica da janela
        this.setTitle("Busca de Livros");
        this.setSize(800, 500);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setLayout(null);

        // Campo de texto para entrada do nome do livro
        inputField = new JTextField();
        inputField.setBounds(50, 50, 300, 30);
        this.add(inputField);

        // Botão de busca
        searchButton = new JButton("Pesquisar");
        searchButton.setBounds(370, 50, 150, 30);
        searchButton.setFont(new Font("Arial", Font.BOLD, 14));
        searchButton.setForeground(Color.WHITE);
        searchButton.setBackground(new Color(10, 10, 10));
        searchButton.addActionListener(this);
        this.add(searchButton);

        // Área de texto para exibir resultados
        resultsArea = new JTextArea();
        resultsArea.setBounds(50, 100, 700, 350);
        resultsArea.setFont(new Font("Arial", Font.PLAIN, 14));
        resultsArea.setEditable(false);
        this.add(resultsArea);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == searchButton) {
            String query = inputField.getText();
            if (!query.trim().isEmpty()) {
                buscarLivros(query);
            } else {
                JOptionPane.showMessageDialog(this, "Por favor, digite o nome de um livro.", "Aviso", JOptionPane.WARNING_MESSAGE);
            }
        }
    }

    private void buscarLivros(String query) {
        ConsumoApi consumoApi = new ConsumoApi();
        ConverteDados converteDados = new ConverteDados();
        String json = consumoApi.obterDados(query);

        ObjectMapper objectMapper = new ObjectMapper();
        List<DadosLivros> livros = new ArrayList<>();

        try {
            JsonNode rootNode = objectMapper.readTree(json);
            JsonNode itemsNode = rootNode.path("items");
            if (itemsNode.isArray()) {
                for (JsonNode itemNode : itemsNode) {
                    JsonNode volumeInfoNode = itemNode.path("volumeInfo");
                    DadosLivros dados = converteDados.obterDados(volumeInfoNode.toString(), DadosLivros.class);
                    livros.add(dados);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Filtrando livros válidos e atualizando a área de texto com os resultados
        List<DadosLivros> livrosValidos = livros.stream()
                .filter(l -> l.getTitulo() != null && !l.getTitulo().isEmpty())
                .filter(l -> l.getAutores() != null && !l.getAutores().isEmpty())
                .filter(l -> l.getEditora() != null && !l.getEditora().isEmpty())
                .filter(l -> l.getAnoLancamento() != null && !l.getAnoLancamento().isEmpty())
                .filter(l -> l.getDescricao() != null && !l.getDescricao().isEmpty())
                .collect(Collectors.toList());

        mostrarResultados(livrosValidos);
    }

    private void mostrarResultados(List<DadosLivros> livros) {
        if (livros.isEmpty()) {
            resultsArea.setText("Nenhum livro encontrado ou todos os resultados estão incompletos.");
        } else {
            StringBuilder resultados = new StringBuilder();
            for (DadosLivros livro : livros) {
                resultados.append(livro.toString()).append("\n\n");
            }
            resultsArea.setText(resultados.toString());
        }
    }
}
