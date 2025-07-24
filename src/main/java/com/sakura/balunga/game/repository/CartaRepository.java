package com.sakura.balunga.game.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.sakura.balunga.game.enumerator.CorEnum;
import com.sakura.balunga.game.enumerator.TipoCartaEnum;
import com.sakura.balunga.game.factory.CartaFactory;
import com.sakura.balunga.game.model.Carta;
import com.sakura.balunga.game.util.GeradorUtil;
import com.sakura.balunga.game.util.ValidadorUtil;


/**
 * Aplica o padrão de projeto Repository para fornecer listas imutáveis
 * de cartas que compõem o baralho do jogo.
 *
 * Esta classe é responsável por instanciar as três categorias de cartas:
 * {@link TipoCartaEnum#CARTA_CINZA}, {@link TipoCartaEnum#CORINGA} e
 * {@link TipoCartaEnum#CARTA_COLORIDA}.
 * 
 * @author Sakura
 * @version 1.0
 * @since 1.0.0
 */
@Repository
public class CartaRepository {

	private static final int MAIOR_VALOR_CARTA_BASE = 18;
	private static final int CARTAS_POR_COR = 18;
	private static final int[] VALORES_CARTA_BASE = new int[] { 1, 3, 5, 7, 12, 14, 16, 18 };
	private static final String[] LISTA_COR_CARTAS = new String[] { 
			CorEnum.LARANJA.getNomeCor(),
			CorEnum.PRETO.getNomeCor(), 
			CorEnum.ROXO.getNomeCor(), 
			CorEnum.VERDE.getNomeCor() };

	private final List<Carta> cartasBase = new ArrayList<>();
	private final List<Carta> cartasCoringa = new ArrayList<>();
	private final List<Carta> cartasJogo = new ArrayList<>();
	
	/**
     * Construtor do repositorio.
     * Faz a geração dos três tipos de cartas do jogo:
     * {@link TipoCartaEnum#CARTA_CINZA}
     * {@link TipoCartaEnum#CORINGA}
     * {@link TipoCartaEnum#CARTA_COLORIDA}
     */
	public CartaRepository() {
		gerarCartasBase();
		gerarCartasCoringa();
		gerarCartasJogo();
	}

	public List<Carta> getCartasBase() {
		return cartasBase;
	}

	public List<Carta> getCartasCoringa() {
		return cartasCoringa;
	}

	public List<Carta> getCartasJogo() {
		return cartasJogo;
	}

	/**
	 * Gera as cartas do tipo {@link TipoCartaEnum#CARTA_CINZA}
	 */
	private void gerarCartasBase() {
		for (int i = 1; i <= MAIOR_VALOR_CARTA_BASE; i++) {
			if (ValidadorUtil.isInArray(i, VALORES_CARTA_BASE)) {
				String idCarta = GeradorUtil.gerarIdCartaBase(i);
				Carta carta = CartaFactory.criarCartaCinza(i, idCarta);
				cartasBase.add(carta);
			}

		}
	}

	/**
	 * Gera as cartas do tipo {@link TipoCartaEnum#CORINGA}
	 */
	private void gerarCartasCoringa() {

		for (String corAtual : LISTA_COR_CARTAS) {
			String idCarta = GeradorUtil.gerarIdCartaCoringa(corAtual);
			Carta carta = CartaFactory.criarCartaCoringa(corAtual, idCarta);
			cartasCoringa.add(carta);
		}

	}

	/**
	 * Gera as cartas do tipo {@link TipoCartaEnum#CARTA_COLORIDA}
	 */
	private void gerarCartasJogo() {

		for (String corAtual : LISTA_COR_CARTAS) {
			for (int i = 1; i <= CARTAS_POR_COR; i++) {
				String idCarta = GeradorUtil.gerarIdCartaJogo(corAtual, i);
				Carta carta = CartaFactory.criarCartaNormal(corAtual, i, idCarta);
				cartasJogo.add(carta);

			}
		}

	}

}
