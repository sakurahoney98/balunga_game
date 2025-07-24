package com.sakura.balunga.game.util;

import com.sakura.balunga.game.enumerator.CorEnum;
import com.sakura.balunga.game.enumerator.TipoCartaEnum;
import com.sakura.balunga.game.model.Carta;

/**
 * Fornece funções para gerar textos auxiliares para as cartas. 
 * Gera um identificador textual  único baseado na semântica 
 * do tipo de carta e a posição em que uma carta foi jogada.
 * 
 * @author Sakura
 * @version 1.0
 * @since 1.0.0
 */
public class GeradorUtil {
	
	private static final String PREFIXO_CARTA_CORINGA = "coringa";
	private static final String PREFIXO_CARTA_JOGO = "card";
	
	/**
	 * Gera id para cartas {@link TipoCartaEnum#CARTA_CINZA}. 
	 *
	 * @param numero inteiro que representa o valor da carta
	 * @return {@link String} id da carta
	 * 
	 */
	public static String gerarIdCartaBase(int numero) {
		return String.format("%s-%d", CorEnum.CINZA.getNomeCor(), numero);
	}
	
	/**
	 * Gera id para cartas {@link TipoCartaEnum#CORINGA}. 
	 *
	 * @param cor texto que representa a cor da carta
	 * @return {@link String} id da carta
	 * 
	 */
	public static String gerarIdCartaCoringa(String cor) {
		return String.format("%s-%s", PREFIXO_CARTA_CORINGA, cor);
	}
	
	/**
	 * Gera id para cartas {@link TipoCartaEnum#CARTA_COLORIDA}. 
	 *
	 * @param cor texto que representa a cor da carta
	 * @param numero inteiro que representa o valor da carta
	 * @return {@link String} id da carta
	 * 
	 */
	public static String gerarIdCartaJogo(String cor, int numero) {
		return String.format("%s-%s-%d", PREFIXO_CARTA_JOGO, cor, numero);
	}
	
	public static String gerarPosicaoDireita(Carta carta) {
		if(ValidadorUtil.isNotNull(carta)) {
			return "direita-0--" + carta.getCor();
		}
		return null;
	}
	
	public static String gerarPosicaoEsquerda(Carta carta) {
		if(ValidadorUtil.isNotNull(carta)) {
			return "esquerda-0--" + carta.getCor();
		}
		return null;
	}

}
