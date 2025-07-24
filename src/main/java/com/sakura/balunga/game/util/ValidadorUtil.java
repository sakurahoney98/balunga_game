package com.sakura.balunga.game.util;

/**
 * Fornece funções auxiliares para validar informações.
 * 
 * @author Sakura
 * @version 1.0
 * @since 1.0.0
 */
public class ValidadorUtil {
	
    /**
     * Verifica se um objeto é nulo.
	 *
	 * @param object objeto a ser avaliado
	 * @return {@code true} se o elemento for null, caso contrário, {@code false}
	 *                      
	 */
	public static boolean isNull(Object object) {
		return object == null;
	}
	
	  /**
     * Verifica se um objeto não é nulo.
	 *
	 * @param object objeto a ser avaliado
	 * @return {@code false} se o elemento for null, caso contrário, {@code true}
	 *                      
	 */
	public static boolean isNotNull(Object object) {
		return object != null;
	}
	
    /**
     * Verifica se um array possui um registro especifico.
     * Faz a validação a fim de conferir se um número está presente
     * em um vetor de inteiros.
	 *
	 * @param valor inteiro a ser procurado no array
	 * @param array vetor de inteiros
	 * @return {@code true} se o elemento estiver no array, caso contrário, {@code false}
	 *                      
	 */
	public static boolean isInArray(int valor, int[] array) {
		for (int itemAtual : array) {
			if (itemAtual == valor) {
				return true;
			}
		}

		return false;

	}

}
