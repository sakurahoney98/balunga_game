package com.sakura.balunga.game.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sakura.balunga.game.facade.PartidaFacade;
import com.sakura.balunga.game.model.Carta;
import com.sakura.balunga.game.model.CartasMesa;
import com.sakura.balunga.game.model.Jogador;
import com.sakura.balunga.game.service.JogoService;
import com.sakura.balunga.game.util.ParametroRequest;

/**
 * Classe responsável por mapear as rotas do site. 
 * Gerencia as requisições web para diferentes páginas relacionadas ao jogo.
 * 
 * @author Sakura
 * @version 1.0
 * @since 1.0.0
 */
@org.springframework.stereotype.Controller
public class JogoController {

	/**
	 * Serviço responsável pela lógica de negócio do jogo. Utilizado para interagir
	 * com operações relacionadas aos dados do jogo.
	 */
	@Autowired
	private JogoService jogoService;

	/**
	 * Fachada para orquestrar operações inerentes a partida.
	 */
	@Autowired
	private PartidaFacade partidaFacade;

	/**
	 * Exibe a página inicial da aplicação. Este método mapeia a URL raiz ("/") e
	 * retorna o nome da view "index".
	 *
	 * @return Página HTML "index".
	 */
	@GetMapping("/")
	public String index() {
		return "index";
	}

	/**
	 * Exibe a página do jogo.
	 *
	 * @return Página HTML "mesa-jogo".
	 */
	@GetMapping("/jogo")
	public String jogo() {
		partidaFacade.iniciarPartida(jogoService);

		return "mesa-jogo";
	}

	/**
	 * Modifica o jogador da vez.
	 *
	 * @return {@code 204 No Content} se a operação for bem-sucedida
	 */
	@GetMapping("/proximo-jogador")
	@ResponseBody
	public ResponseEntity<Void> proximoJogador() {

		jogoService.proximoJogador();

		return ResponseEntity.noContent().build();
	}

	/**
	 * Verifica penalidade para o jogador da vez. Esse método faz chamada para a
	 * camada de Service afim de verificar se alguma penalidade foi computada para o
	 * jogador.
	 *
	 * @return {@code true} se houve penalidade; caso contrário, {@code false}
	 */
	@GetMapping("/checar-penalidade")
	@ResponseBody
	public ResponseEntity<Boolean> checarPenalidade() {
		return ResponseEntity.ok(jogoService.penalidade());
	}

	/**
	 * Captura a arrumação da mesa. 
	 * Esse método faz uma chamada para o Service afim
	 * de capturar o Map com a distribuição das cartas base e coringa na mesa
	 *
	 * @return objeto {@link ResponseEntity} lista com a arrumação das cartas da mesa
	 */
	@GetMapping("/partida/mesa")
	@ResponseBody
	public ResponseEntity<Map<String, CartasMesa>> getMesa() {
		return ResponseEntity.ok(jogoService.getPartida().getMesa());
	}

	/**
	 * Aciona a ação cavar. 
	 * Esse método manda o comando para o Service indicando que algum jogador deve 
	 * adicionar cartas ao seu baralho e captura as cartas que foram adicionadas.
	 *
	 * @return objeto {@link ResponseEntity} lista de cartas cavadas
	 */
	@GetMapping("/cava")
	@ResponseBody
	public ResponseEntity<List<Carta>> getCava() {
		return ResponseEntity.ok(jogoService.cava());
	}

	/**
	 * Captura a lista de jogadores da partida.
	 *
	 * @return objeto {@link ResponseEntity} lista com os jogadores da partidada
	 */
	@GetMapping("/jogador/todos")
	@ResponseBody
	public ResponseEntity<List<Jogador>> getJogadores() {
		return ResponseEntity.ok(jogoService.getJogadores());
	}

	/**
	 * Captura o jogador da vez.
	 *
	 * @return objeto {@link ResponseEntity} jogador que possui a vez de jogar.
	 */
	@GetMapping("/jogador/atual")
	@ResponseBody
	public ResponseEntity<Jogador> getJogadorAtual() {

		return ResponseEntity.ok(jogoService.getJogadorAtual());
	}

	/**
	 * Gera uma jogada para a máquina. 
	 * Aciona o Service para gerar uma jogada para a máquina e captura a carta 
	 * e posição escolhidos para exibir na tela.
	 *
	 * @return objeto {@link ResponseEntity} lista contendo a carta jogada e posição
	 *         escolhida
	 */
	@GetMapping("/jogador/maquina")
	@ResponseBody
	public ResponseEntity<List<Object>> getJogadaComputador() {

		return ResponseEntity.ok(jogoService.jogadaComputador());
	}

	/**
	 * Verifica se o jogo chegou ao fim.
	 *
	 * @return {@code true} se o jogo estiver finalizado; caso contrário, {@code false}
	 */
	@GetMapping("/jogo/fim")
	@ResponseBody
	public ResponseEntity<Boolean> isJogoFinalizado() {

		return ResponseEntity.ok(jogoService.isPartidaEncerrada());
	}

	/**
	 * Altera a quantidade de jogadores. 
	 * Registra a informação de quantos jogadores foram selecionados para a partida.
	 *
	 * @return {@code 204 No Content} se a operação for bem-sucedida
	 */
	@PostMapping("/jogadores")
	@ResponseBody
	public ResponseEntity<Void> jogadoresSelecionados(@RequestBody ParametroRequest request) {

		jogoService.setQuantidadeJogadores(request.getJogadores());

		return ResponseEntity.noContent().build();
	}

	/**
	 * Registra a jogada. 
	 * Registra a carta e a posição escolhidas pelo jogador principal na rodada atual.
	 *
	 * @param request Objeto contendo o identificador da carta e a posição em que a
	 *                carta foi jogada.
	 * @return {@code 204 No Content} se a operação for bem-sucedida
	 */
	@PostMapping("/registrar-jogada")
	@ResponseBody
	public ResponseEntity<Void> registrarJogada(@RequestBody ParametroRequest request) {

		jogoService.registrarJogada(request.getCartaID(), request.getPosicao());

		return ResponseEntity.noContent().build();
	}

	/**
	 * Verifica se um jogador fez Balunga. 
	 * Checa se um jogador fez Balunga pelo identificador. Posteriormente retorna a lista 
	 * de cartas do jogador que foram descartadas.
	 *
	 * @param request Objeto contendo o identificador do jogador.
	 * @return objeto {@link ResponseEntity} lista das cartas descartadas.
	 */
	@PostMapping("/checar-balunga")
	@ResponseBody
	public ResponseEntity<List<Carta>> checarBalunga(@RequestBody ParametroRequest request) {
		return ResponseEntity.ok(jogoService.descarteBalunga(request.getJogadorID()));
	}

}
