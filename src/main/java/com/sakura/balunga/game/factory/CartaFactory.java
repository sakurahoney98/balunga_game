package com.sakura.balunga.game.factory;

import com.sakura.balunga.game.enumerator.CorEnum;
import com.sakura.balunga.game.enumerator.TipoCartaEnum;
import com.sakura.balunga.game.model.Carta;

/**
*
* Aplica o padrão de projeto Factory para criar instâncias da classe
* {@link Carta} no projeto.
* 
*
* @author Sakura
* @version 1.0
* @since 1.0.0
*/
public class CartaFactory {
	
	/**
	 * Gera uma carta do tipo {@link TipoCartaEnum#CARTA_COLORIDA}.
	 *
	 * @param cor cor da carta
	 * @param numero valor da carta
	 * @param urlImagem caminho ou URL da imagem que representa visualmente a carta
	 * 
	 * @return {@link Carta} carta colorida gerada
	 */
	public static Carta criarCartaNormal (String cor, int numero, String urlImagem) {
		return new Carta(cor, numero, urlImagem, TipoCartaEnum.CARTA_COLORIDA.getID());
	}
	
	/**
	 * Gera uma carta do tipo {@link TipoCartaEnum#CARTA_CINZA}.
	 *
	 * @param numero valor da carta
	 * @param urlImagem caminho ou URL da imagem que representa visualmente a carta
	 * 
	 * @return {@link Carta} carta cinza gerada
	 */
	public static Carta criarCartaCinza (int numero, String urlImagem) {
		return new Carta(CorEnum.CINZA.getNomeCor(), numero, urlImagem, TipoCartaEnum.CARTA_CINZA.getID());
	}
	
	/**
	 * Gera uma carta do tipo {@link TipoCartaEnum#CORINGA}.
	 *
	 * @param cor cor da carta
	 * @param urlImagem caminho ou URL da imagem que representa visualmente a carta
	 * 
	 * @return {@link Carta} carta coringa gerada
	 */
	public static Carta criarCartaCoringa (String cor, String urlImagem) {
		return new Carta(cor, 0, urlImagem, TipoCartaEnum.CORINGA.getID());
	}

}
