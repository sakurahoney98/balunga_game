package com.sakura.balunga.game.model;

import java.util.Stack;

import com.sakura.balunga.game.enumerator.TipoCartaEnum;

/**
 * Representa uma linha de cartas da mesa.
 * 
 * A linha é formada por uma {@link Carta} do tipo
 * {@link TipoCartaEnum#CARTA_COLORIDA} no centro e uma pilha {@link Stack} de
 * cartas do lado esquerdo e direito.
 * 
 * @author Sakura
 * @version 1.0
 * @since 1.0.0
 */
public class CartasMesa {

	private Carta cartaCoringa;
	private Stack<Carta> ladoEsquerdo;
	private Stack<Carta> ladoDireito;

	/**
	 * Construtor da linha de cartas na mesa.
	 * 
	 * @param cartaCoringa carta do centro da linha
	 * @param ladoEsquerdo pilha de cartas do lado esquerdo
	 * @param ladoDireito  pilha de cartas do lado direito
	 */
	public CartasMesa(Carta cartaCoringa, Stack<Carta> ladoEsquerdo, Stack<Carta> ladoDireito) {
		super();
		this.cartaCoringa = cartaCoringa;
		this.ladoEsquerdo = ladoEsquerdo;
		this.ladoDireito = ladoDireito;
	}

	/**
	 * Retorna a carta coringa central da linha.
	 * 
	 * @return {@link Carta} carta posicionada ao centro da linha
	 */
	public Carta getCartaCoringa() {
		return cartaCoringa;
	}

	/**
	 * Retorna a pilha de cartas do lado esquerdo da mesa como uma cópia,
	 * permitindo visualização sem modificação da pilha original.
	 * 
	 * @return {@link Stack} cópia da pilha de cartas do lado esquerdo
	 */
	public Stack<Carta> getLadoEsquerdo() {
		Stack<Carta> copiaLadoEsquerdo = new Stack<>();
		copiaLadoEsquerdo.addAll(this.ladoEsquerdo);
	    return copiaLadoEsquerdo;
	}
	
	/**
	 * Retorna a pilha de cartas do lado direito da mesa como uma cópia,
	 * permitindo visualização sem modificação da pilha original.
	 * 
	 * @return {@link Stack} cópia da pilha de cartas do lado direito
	 */
	public Stack<Carta> getLadoDireito() {
		Stack<Carta> copiaLadoDireito = new Stack<>();
		copiaLadoDireito.addAll(this.ladoDireito);
	    return copiaLadoDireito;
	}

	/**
	 * Centraliza a manipulação de cartas do lado direito, adicionando
	 * uma carta no topo da pilha.
	 * 
	 * @param carta carta a ser adicionada a pilha
	 */
	public void adicionarCartaLadoDireito(Carta carta) {
		this.ladoDireito.push(carta);

	}

	/**
	 * Centraliza a manipulação de cartas do lado esquerdo, adicionando
	 * uma carta no topo da pilha.
	 * 
	 * @param carta carta a ser adicionada a pilha
	 */
	public void adicionarCartaLadoEsquerdo(Carta carta) {
		this.ladoEsquerdo.push(carta);

	}

	/**
	 * Retorna a carta do topo da pilha do lado esquerdo, impedindo
	 * sua manipulação.
	 * 
	 * @return {@link Carta} carta do topo do lado esquerdo
	 */
	public Carta verCartaTopoEsquerda() {
		return ladoEsquerdo.peek();
	}

	/**
	 * Retorna a carta do topo da pilha do lado direito, impedindo
	 * sua manipulação.
	 * 
 	 * @return {@link Carta} carta do topo do lado direito
	 */
	public Carta verCartaTopoDireita() {
		return ladoDireito.peek();
	}

	@Override
	public String toString() {
		return "CartasMesa [cartaCoringa=" + cartaCoringa.toString() + ", ladoEsquerdo=" + ladoEsquerdo.toString()
				+ ", ladoDireito=" + ladoDireito.toString() + "]";
	}

}
