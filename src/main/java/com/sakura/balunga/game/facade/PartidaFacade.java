package com.sakura.balunga.game.facade;

import org.springframework.stereotype.Component;

import com.sakura.balunga.game.service.JogoService;

/**
 *
 * Aplica o padrão de projeto Facade para encapsular a complexidade
 * da inicialização em uma única chamada.
 * 
 * Facade responsável por orquestrar as operações iniciais de uma partida de jogo.
 * Essa classe centraliza a chamada de múltiplos métodos do serviço {@link JogoService}
 * garantindo que a partida seja corretamente configurada antes do início.
 *
 * @author Sakura
 * @version 1.0
 * @since 1.0.0
 */
@Component
public class PartidaFacade {

	/**
	 * Inicializa a partida chamando os métodos do serviço na ordem correta.
	 *
	 * @param jogoService o serviço responsável pelas regras do jogo
	 */
	public void iniciarPartida(JogoService jogoService) {
		jogoService.iniciarDadosPartida();
		jogoService.inserirJogadores();
		jogoService.embaralharCartas();
		jogoService.organizarMesa();
		jogoService.distribuirCartas();

	}

}
