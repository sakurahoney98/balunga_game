package com.sakura.balunga.game.enumerator;

/**
 * Enumeração que representa as cores possíveis que uma carta do jogo pode ter.
 * As cores são utilizadas para determinar as regras de jogada e organizar as linhas na mesa.
 * 
 * @author Sakura
 * @version 1.0
 * @since 1.0.0
 */
public enum CorEnum {

	LARANJA("laranja"), PRETO("preto"), ROXO("roxo"), VERDE("verde"), CINZA("cinza");

	private final String nomeCor;

	
	CorEnum(String nomeCor) {
		this.nomeCor = nomeCor;
	}

	/**
	 * Retorna o nome da cor em forma de string.
	 * 
	 * @return String representando o nome da cor
	 */
	public String getNomeCor() {
		return this.nomeCor;
	}

}
