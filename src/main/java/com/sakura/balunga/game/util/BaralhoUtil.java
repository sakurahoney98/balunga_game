package com.sakura.balunga.game.util;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Fornece funções auxiliares para manipulação do baralho.
 * Inclui funcionalidades como embaralhamento.
 * 
 * @author Sakura
 * @version 1.0
 * @since 1.0.0
 */
public class BaralhoUtil {

    /**
     * Embaralha as cartas do jogo.
	 * Se a lista fornecida for {@code null}, nenhuma operação será realizada. Esta 
	 * operação modifica a ordem dos elementos da lista in-place.
	 *
	 * @param listaDeCartas uma {@link List} de cartas a ser embaralhada.
	 *                      
	 */
	public static void embaralhar(List<?> listaDeCartas) {
		if (ValidadorUtil.isNotNull(listaDeCartas)) {
		    Collections.shuffle(listaDeCartas, ThreadLocalRandom.current());
		}
	}

}
