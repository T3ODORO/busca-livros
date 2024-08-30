package br.com.busca.livros.busca.livros.screen;

import javax.swing.SwingUtilities;

public class Teste {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Screen().setVisible(true);
        });
    }
}
