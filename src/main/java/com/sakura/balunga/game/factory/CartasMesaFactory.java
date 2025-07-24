package com.sakura.balunga.game.factory;

import java.util.Stack;

import com.sakura.balunga.game.enumerator.TipoCartaEnum;
import com.sakura.balunga.game.model.Carta;
import com.sakura.balunga.game.model.CartasMesa;

/**
 * Aplica o padrão de projeto Factory para criar instâncias da classe
 * {@link CartasMesa}, usados na composição da mesa do jogo.
 *
 * Esta classe encapsula a lógica de criação de uma linha da mesa contendo uma carta coringa central
 * e pilhas (vazias) de cartas cinza em ambos os lados.
 * 
 * @author Sakura
 * @version 1.0
 * @since 1.0.0
 */
public class CartasMesaFactory {
	
	/**
	 * Gera uma linha da mesa com estrutura:
	 * [CARTA_CINZA] ← {@link TipoCartaEnum#CORINGA} → [CARTA_CINZA].
	 * 
	 * Cada lado da carta coringa é representado por uma pilha (Stack) de cartas cinzas.
	 *
	 * @param cartaCoringa carta central da mesa
	 * 
	 * @return {@link CartasMesa} fileira inicializada
	 */
	public static CartasMesa criarLinha(Carta cartaCoringa) {
		
		return new CartasMesa(cartaCoringa, new Stack<Carta>(), new Stack<Carta>());
		
	}

}
