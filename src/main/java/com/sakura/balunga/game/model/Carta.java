package com.sakura.balunga.game.model;

/**
 * Representa uma carta do jogo contendo informações sobre cor,
 * número, imagem e tipo.
 * 
 * @author Sakura
 * @version 1.0
 * @since 1.0.0
 */
public class Carta {
	
	private String cor;
	private int numero;
	private String urlImagem;
	private int tipoCarta;

	/**
     * Construtor da carta.
     * 
     * @param cor cor da carta
     * @param numero valor da carta.
     * @param urlImagem caminho ou URL da imagem que representa visualmente a carta
     * @param tipoCarta  tipo da carta
     */
	public Carta(String cor, int numero, String urlImagem, int tipoCarta) {
		super();
		this.cor = cor;
		this.numero = numero;
		this.urlImagem = urlImagem;
		this.tipoCarta = tipoCarta;
	}
	/**
	 * Recupera a cor da carta.
	 * 
	 * @return {@link String} cor associada à carta
	 */
	public String getCor() {
		return cor;
	}

	/**
	 * Recupera o valor numérico da carta.
	 * 
	 * @return {@code int} valor da carta
	 */
	public int getNumero() {
		return numero;
	}

	/**
	 * Recupera o caminho da imagem da carta.
	 * 
	 * @return {@link String} URL ou path da imagem associada à carta
	 */
	public String getUrlImagem() {
		return urlImagem;
	}

	/**
	 * Recupera o tipo da carta.
	 * 
	 * @return {@code int} código do tipo de carta 
	 */
	public int getTipoCarta() {
		return tipoCarta;
	}

	/**
	 * Retorna a representação textual da carta.
	 * Esta saída é útil para debug ou logs e exibe as informações principais da carta.
	 * 
	 * @return {@link String} descrição detalhada da carta
	 */
	@Override
	public String toString() {
		return "Carta [cor=" + cor + ", numero=" + numero + ", urlImagem=" + urlImagem + ", tipoCarta=" + tipoCarta
				+ "]";
	}

}
