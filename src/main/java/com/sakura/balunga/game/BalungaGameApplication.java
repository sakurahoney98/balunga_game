package com.sakura.balunga.game;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;



@SpringBootApplication
/**
 * Ponto de entrada principal para a aplicação Balunga Game.
 * Esta classe é responsável por inicializar o aplicativo Spring Boot.
 * @author Sakura
 * @version 1.0
 */
public class BalungaGameApplication {
	
	/**
     * O método principal que inicia a aplicação Spring Boot.
     * @param args Argumentos de linha de comando passados para a aplicação.
     */
	public static void main(String[] args) {
		SpringApplication.run(BalungaGameApplication.class, args);
	}

}
