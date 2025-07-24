package com.sakura.balunga.game.util;

/**
 * Fornece funções auxiliares para extrair informações de texto. 
 * 
 * A string posicao é esperada no formato onde lado é a primeira parte 
 * antes do primeiro hífen, o valor é a parte entre o primeiro hífen e o 
 * separador {@code --}, e cor é a parte após {@code --}. Por exemplo: 
 * {@code esquerda-7--vermelho}.
 * 
 * @author Sakura
 * @version 1.0
 * @since 1.0.0
 */
public class ExtratorUtil {

	/**
	 * Extrai o lado da string posicao. 
	 * Se a string fornecida for {@code null} ou não possuir o
	 * simbolo separador {@code --}, nenhuma operação será realizada.
	 *
	 * @param posicao texto com as informações de lado e cor
	 * @return {@link String} lado em que a carta foi posicionada ou 
	 * 			{@code null} se não for possível fazer a extração
	 * 
	 */
	public static String extrairLado(String posicao) {
		if (ValidadorUtil.isNull(posicao)|| !posicao.contains("--"))
			return null;
		String[] partes = posicao.split("--");
		String[] ladoSplit = partes[0].split("-");
		return ladoSplit.length > 0 ? ladoSplit[0] : null;
	}

	/**
	 * Extrai a cor. 
	 * Se a string fornecida for {@code null} ou não possuir o simbolo
	 * separador {@code --}, nenhuma operação será realizada. 
	 *
	 * @param posicao texto com as informações de lado e cor
	 * @return {@link String} cor da carta ou {@code null} se não for 
	 * 			possível fazer a extração
	 * 
	 */
	public static String extrairCor(String posicao) {
		if (ValidadorUtil.isNull(posicao) || !posicao.contains("--"))
			return null;
		String[] partes = posicao.split("--");
		return partes.length > 1 ? partes[1] : null;
	}

	/**
	 * Valida se é possível fazer a extração do lado e da cor da string.
	 *
	 * @param posicao texto com as informações de lado e cor
	 * @return {@code true} se for possível extrair o lado e a posição da string
	 *         passada, caso contrário, {@code false}
	 * 
	 */
	public static boolean parametroValido(String posicao) {
		return  ValidadorUtil.isNotNull(extrairLado(posicao)) && ValidadorUtil.isNotNull(extrairCor(posicao));
	}

}
