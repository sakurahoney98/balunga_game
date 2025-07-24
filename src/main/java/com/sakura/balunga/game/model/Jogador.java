package com.sakura.balunga.game.model;

import java.util.HashMap;
import java.util.Map;

/**
 * Representa um jogador da partida, contendo seu nome, a coleção de cartas 
 * em mãos, o controle de turno (vez de jogar) e o identificador da próxima 
 * carta a ser adicionada.
 * 
 * @author Sakura
 * @version 1.0
 * @since 1.0.0
 */
public class Jogador {
	
	private String nomeJogador;
	private Map<Integer, Carta> cartasNaMao;
	private boolean vezDeJogar;
	private int ultimoIDCarta = 0;
	
	/**
	 * Construtor do jogador.
	 * 
	 * @param nomeJogador indicador textual do jogador
	 * @param cartasNaMao conjunto de cartas do jogador
	 * @param vezDeJogar  indica se o jogador pode fazer uma jogada
	 */
	public Jogador(String nomeJogador, Map<Integer, Carta> cartasNaMao, boolean vezDeJogar) {
		super();
		this.nomeJogador = nomeJogador;
		this.cartasNaMao = cartasNaMao;
		this.vezDeJogar = vezDeJogar;
	}

	public String getNomeJogador() {
		return nomeJogador;
	}

	/**
	 * Retorna a coleção de cartas em forma de cópia, permitindo 
	 * visualização sem modificação da coleção original.
	 * 
	 * @return {@link HashMap} cópia da coleção de cartas do jogador
	 */
	public Map<Integer, Carta> getCartasNaMao() {
		return new HashMap<>(cartasNaMao);
	}
	
	/**
	 * Centraliza a inserção de objetos na coleção de
	 * cartas do jogador.
	 * 
	 * @param id inteiro identificador da carta
	 * @param carta objeto {@link Carta} da carta a ser inserida
	 */
	public void pegarCarta(int id, Carta carta) {
		cartasNaMao.put(id, carta);
	}
	
	/**
	 * Centraliza a remoção de objetos na coleção de
	 * cartas do jogador.
	 * 
	 * @param id inteiro identificador da carta
	 */
	public void descartar(int id) {
		cartasNaMao.remove(id);
	}

	/**
	 * Indica se é a vez do jogador jogar.
	 * 
	 * @return {@code true} se for a vez do jogador, {@code false} caso contrário
	 */
	public boolean isVezDeJogar() {
		return vezDeJogar;
	}

	/**
	 * Define se é a vez do jogador jogar.
	 * 
	 * @param vezDeJogar valor booleano que indica se é a vez do jogador
	 */
	public void setVezDeJogar(boolean vezDeJogar) {
		this.vezDeJogar = vezDeJogar;
	}

	/**
	 * Retorna o último ID usado ao adicionar uma carta.
	 * 
	 * @return {@code int} valor do último identificador usado
	 */
	public int getUltimoIDCarta() {
		return ultimoIDCarta;
	}

	/**
	 * Define o valor do último ID de carta.
	 * 
	 * @param ultimoIDCarta valor inteiro do último identificador utilizado
	 */
	public void setUltimoIDCarta(int ultimoIDCarta) {
		this.ultimoIDCarta = ultimoIDCarta;
	}

	/**
	 * Retorna uma representação textual do objeto jogador.
	 * 
	 * @return {@link String} contendo o nome, cartas na mão, estado de vez de jogar e o ID da última carta
	 */
	@Override
	public String toString() {
		return "Jogador [nomeJogador=" + nomeJogador + ", cartasNaMao=" + cartasNaMao.toString() + ", vezDeJogar=" + vezDeJogar
				+ ", proximoIdCarta=" + ultimoIDCarta + "]";
	}


	
	
	
	
	
	

}
