package com.sakura.balunga.game.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sakura.balunga.game.enumerator.TipoCartaEnum;

/**
 * Representa uma partida do jogo, contendo as informações de lista de 
 * jogadores, coleção de cartas ({@link TipoCartaEnum#CARTA_COLORIDA},
 * {@link TipoCartaEnum#CARTA_CINZA}, {@link TipoCartaEnum#CORINGA})
 * e organização das cartas do centro da mesa.
 * 
 * @author Sakura
 * @version 1.0
 * @since 1.0.0
 */
public class Partida {
	
	 private List<Jogador> jogadores;
	    private List<Carta> cartasBase;
	    private List<Carta> cartasCoringa;
	    private List<Carta> cartasJogo;
	    private Map<String, CartasMesa> mesa;
	
	
	    /**
		 * Construtor da partida.
		 * 
		 * @param cartasBase coleção de cartas {@link TipoCartaEnum#CARTA_CINZA}
		 * @param cartasCoringa coleção de cartas {@link TipoCartaEnum#CORINGA}
		 * @param cartasJogo coleção de cartas {@link TipoCartaEnum#CARTA_COLORIDA}
		 */
	    public Partida(List<Carta> cartasBase, List<Carta> cartasCoringa, List<Carta> cartasJogo) {
	        this.jogadores = new ArrayList<>();
	        this.cartasBase = cartasBase;
	        this.cartasCoringa = cartasCoringa;
	        this.cartasJogo = cartasJogo;
	        this.mesa = new HashMap<String, CartasMesa>();
	     
	    }

		/**
		 * Retorna a lista de jogadores em forma de cópia, permitindo 
		 * visualização sem modificação da lista original.
		 * 
		 * @return {@link List} lista de jogadores da partida
		 */
	    public List<Jogador> getJogadores() {
	        return new ArrayList<>(jogadores);
	    }

	    /**
	     * Adiciona um novo jogador à lista da partida.
	     *
	     * @param jogador jogador a ser adicionado
	     */
	    public void adicionarJogador(Jogador jogador) {
	        this.jogadores.add(jogador);
	    }

	    /**
	     * Retorna a coleção de cartas cinzas da partida.
	     * 
	     * @return {@link List} com as cartas base
	     */
	    public List<Carta> getCartasBase() {
	        return cartasBase;
	    }

	    /**
	     * Retorna a coleção de cartas coringas da partida.
	     * 
	     * @return {@link List} com as cartas coringa
	     */
	    public List<Carta> getCartasCoringa() {
	        return cartasCoringa;
	    }

	    /**
	     * Retorna a coleção de cartas coloridas da partida.
	     * 
	     * @return {@link List} com as cartas do jogo
	     */
		public List<Carta> getCartasJogo() {
			return cartasJogo;
		}


		/**
		 * Retorna a coleção de cartas em forma de cópia, permitindo 
		 * visualização sem modificação da coleção original.
		 * 
		 * @return {@link HashMap} coleção de cartas centrais da mesa
		 */
		public Map<String, CartasMesa> getMesa() {
			return new HashMap<>(mesa);
		}

	    /**
	     * Define a coleção de cartas centrais da mesa.
	     * 
	     * @param mesa mapa contendo as linhas da mesa
	     */
		public void setMesa(Map<String, CartasMesa> mesa) {
			this.mesa = mesa;
		}

		/**
		 * Retorna o jogador da vez.
		 * Percorre a lista de jogadores e retorna aquele que o
		 * {@link Jogador#isVezDeJogar()} retorna true.
		 * 
		 * @return {@link Jogador} coleção de cartas centrais da mesa, ou {@code null} se nenhum estiver definido
		 */
		public Jogador getJogadorAtual() {
			for(Jogador jogador: jogadores) {
				if(jogador.isVezDeJogar()) {
					return jogador;
				}
			}
			return null;
		}

	    /**
	     * Retorna uma representação textual do objeto {@code Partida}.
	     * 
	     * @return {@link String} contendo a lista de jogadores, cartas e organização da mesa
	     */
		@Override
		public String toString() {
			return "Partida [jogadores=" + jogadores.toString() + ", cartasBase=" + cartasBase.toString() + ", cartasCoringa=" + cartasCoringa.toString()
					+ ", cartasJogo=" + cartasJogo.toString() + ", mesa=" + mesa.toString() + "]";
		}

		
		
		
		
		
		
	    
	    

}
