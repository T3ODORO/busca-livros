package br.com.busca.livros.busca.livros;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BuscaLivrosApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(BuscaLivrosApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Principal principal = new Principal();
		principal.exibeMenu();
	}
}



