package com.sakura.balunga.game.factory;

import java.util.HashMap;

import com.sakura.balunga.game.model.Carta;
import com.sakura.balunga.game.model.Jogador;

/**
 * Aplica o padrão de projeto Factory para criar instâncias da classe
 * {@link Jogador}.
 * 
 * Esta classe abstrai a lógica de criação de jogadores principais (usuário humano) 
 * e jogadores comuns (controlados pelo sistema).
 *
 * @author Sakura
 * @version 1.0
 * @since 1.0.0
 */
public class JogadorFactory {
	
	/**
	 * Cria o jogador principal da partida.
	 *
	 * @param nome identificador textual do jogador
	 * 
	 * @return {@link Jogador} o objeto Jogador configurado como jogador principal
	 */
	public static Jogador criarJogadorPrincipal(String nome) {
		return new Jogador(nome, new HashMap<Integer, Carta>(), true);
	}
	
	/**
	 *  Cria jogadores controlados pelo sistema (automatizados).
	 *
	 * @param nome identificador textual do jogador
	 * 
	 * @return {@link Jogador} o objeto Jogador configurado como jogador automatizado
	 */
	public static Jogador criarJogadorComum(String nome) {
		return new Jogador(nome, new HashMap<Integer, Carta>(), false);
	}

}
