package com.sakura.balunga.game.enumerator;

/**
 * Enumeração que representa os tipos de carta disponíveis no jogo.
 * 
 * {@link #CORINGA} - Carta especial que fica no centro na mesa.
 * {@link #CARTA_CINZA} - Carta base que fica do lado esquerdo e direito da carta coringa.
 * {@link #CARTA_COLORIDA} - Carta jogável associada a uma cor.
 * 
 * 
 * @author Sakura
 * @version 1.0
 * @since 1.0.0
 */
public enum TipoCartaEnum {
	CORINGA(1), CARTA_CINZA(2), CARTA_COLORIDA(3);

	private final int idTipoCarta;

	TipoCartaEnum(int idTipoCarta) {
		this.idTipoCarta = idTipoCarta;
	}

	/**
	 * Retorna o identificador numérico associado ao tipo da carta.
	 * 
	 * @return int representando o tipo da carta
	 */
	public int getID() {
		return idTipoCarta;
	}
}
