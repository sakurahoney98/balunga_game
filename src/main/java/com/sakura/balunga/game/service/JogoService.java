package com.sakura.balunga.game.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ThreadLocalRandom;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.sakura.balunga.game.enumerator.TipoCartaEnum;
import com.sakura.balunga.game.factory.CartasMesaFactory;
import com.sakura.balunga.game.factory.JogadorFactory;
import com.sakura.balunga.game.model.Carta;
import com.sakura.balunga.game.model.CartasMesa;
import com.sakura.balunga.game.model.Jogador;
import com.sakura.balunga.game.model.Partida;
import com.sakura.balunga.game.repository.CartaRepository;
import com.sakura.balunga.game.util.BaralhoUtil;
import com.sakura.balunga.game.util.ExtratorUtil;
import com.sakura.balunga.game.util.GeradorUtil;
import com.sakura.balunga.game.util.ValidadorUtil;

/**
 *
 * Aplica o padrão de projeto Service para encapsular a regra de negócio.
 * 
 * Service responsável por orquestrar as funções inerentes ao jogo.
 *
 * @author Sakura
 * @version 1.0
 * @since 1.0.0
 */
@Service
public class JogoService {

	private Partida partida;
	private int cartasPorJogador = 8;
	private int quantidadeJogadores = 1;
	private CartaRepository cartaRepository;
	private String corModificada;
	private int cavarCartas = 0;
	private static final Logger log = LoggerFactory.getLogger(JogoService.class);

	/**
	 * Registra a quantidade de jogadores presente na partida.
	 *
	 * @param jogadores quantidade de jogadores da partida
	 */
	public void setQuantidadeJogadores(int jogadores) {
		this.quantidadeJogadores = jogadores;
		log.info("🎮 Quantidade de jogadores definida: {}", jogadores);
	}

	/**
	 * Recupera o jogador da vez.
	 * 
	 * @return {@link Jogador} jogador atual da rodada
	 */
	public Jogador getJogadorAtual() {
		return partida.getJogadorAtual();
	}

	/**
	 * Recupera a organização da mesa na partida.
	 * 
	 * @return {@link Map} lista de {@link CartasMesa} representando as fileiras da
	 *         mesa
	 */
	public Map<String, CartasMesa> getMesa() {

		return partida.getMesa();
	}

	/**
	 * Recupera a partida atual.
	 *
	 * @return objeto {@link Partida} com as informações necessárias para orquestrar
	 *         o andamento do jogo
	 */
	public Partida getPartida() {
		if (ValidadorUtil.isNull(partida)) {
			iniciarDadosPartida();
		}
		return partida;
	}

	/**
	 * Recupera a lista de joagdores da partida. 
	 * Se a lista de jogadores estiver vazia, é feita a inserção de jogadores.
	 * 
	 * @return {@link List} lista de jogadores {@link Jogador} da partida ou {code
	 *         null} se não houver uma instância de {@link Partida} válida
	 */
	public List<Jogador> getJogadores() {
		if (ValidadorUtil.isNotNull(partida)) {
			if (partida.getJogadores().isEmpty()) {
				inserirJogadores();
			}

			return partida.getJogadores();
		} else {
			log.error("❌ Não foi possível recuperar a partida atual.");
		}

		return null;

	}

	/**
	 * Inicializa a partida
	 */
	public void iniciarDadosPartida() {
		log.info("🔁 Inicializando nova partida...");
		cartaRepository = new CartaRepository();
		partida = new Partida(cartaRepository.getCartasBase(), cartaRepository.getCartasCoringa(),
				cartaRepository.getCartasJogo());
		log.info("✅ Partida inicializada com sucesso.");

	}

	/**
	 * Insere jogadores na partida. 
	 * Gera uma lista de jogadores com as informações de cada um. Se não houver 
	 * uma instância de {@link Partida} válida, nenhuma ação é realizada.
	 */
	public void inserirJogadores() {
		if (ValidadorUtil.isNotNull(partida)) {
			for (int i = 1; i <= quantidadeJogadores; i++) {
				Jogador jogador;
				if (i == 1) {
					jogador = JogadorFactory.criarJogadorPrincipal("jogador-principal-label");
				} else {
					jogador = JogadorFactory.criarJogadorComum("jogador-" + i);
				}

				partida.adicionarJogador(jogador);
			}
		} else {
			log.error("❌ Não foi possível recuperar a partida atual.");
		}

	}

	/**
	 * Embaralha as cartas. 
	 * Captura a coleção de cartas e modifica a ordem dos elementos. Se não houver 
	 * uma instância de {@link Partida} válida, nenhuma ação é realizada.
	 */
	public void embaralharCartas() {
		if (ValidadorUtil.isNotNull(partida)) {
			BaralhoUtil.embaralhar(partida.getCartasBase());
			BaralhoUtil.embaralhar(partida.getCartasJogo());
		} else {
			log.error("❌ Não foi possível recuperar a partida atual.");
		}

	}

	/**
	 * Organiza as cartas no centro da mesa. 
	 * Gera um {@link Map} de {@link CartasMesa} simulando a organização das cartas 
	 * no centro da mesa. Se não houver uma instância de {@link Partida} válida, 
	 * nenhuma ação é realizada
	 */
	public void organizarMesa() {

		if (ValidadorUtil.isNotNull(partida)) {
			Map<String, CartasMesa> mesa = new HashMap<String, CartasMesa>();
			int indiceCartaAtual = 0;
			for (Carta cartaCoringaAtual : partida.getCartasCoringa()) {
				CartasMesa filaMesa = CartasMesaFactory.criarLinha(cartaCoringaAtual);

				filaMesa.adicionarCartaLadoEsquerdo(partida.getCartasBase().get(indiceCartaAtual));
				indiceCartaAtual++;

				filaMesa.adicionarCartaLadoDireito(partida.getCartasBase().get(indiceCartaAtual));
				indiceCartaAtual++;

				mesa.put(cartaCoringaAtual.getCor().toUpperCase(), filaMesa);
			}

			partida.setMesa(mesa);
		} else {
			log.error("❌ Não foi possível recuperar a partida atual.");
		}

	}

	/**
	 * Distribui cartas para os jogadores. 
	 * Captura a coleção de cartas {@link TipoCartaEnum#CARTA_COLORIDA} e insere na coleção 
	 * de cartas, {@link Map} de {@link Carta}, de cada jogador {@link Jogador}. Posteriormente 
	 * remove a carta dada ao jogador da coleção de cartas do jogo. Se não houver uma instância 
	 * de {@link Partida} válida, nenhuma ação é realizada.
	 */
	public void distribuirCartas() {

		if (ValidadorUtil.isNotNull(partida)) {
			List<Jogador> listaJogadores = partida.getJogadores();
			List<Carta> listaCartasJogo = partida.getCartasJogo();
			int quantidadeCartasDistribuidas = 0;
			int pontoDeParada = cartasPorJogador * listaJogadores.size();

			while (quantidadeCartasDistribuidas < pontoDeParada) {
				for (Jogador jogadorAtual : listaJogadores) {
					int cartaID = jogadorAtual.getUltimoIDCarta() + 1;
					jogadorAtual.pegarCarta(cartaID, listaCartasJogo.get(0));
					jogadorAtual.setUltimoIDCarta(cartaID);
					listaCartasJogo.remove(0);
					quantidadeCartasDistribuidas++;
				}
			}
		} else {
			log.error("❌ Não foi possível recuperar a partida atual.");
		}

	}

	/**
	 * Registra uma jogada. 
	 * Atualiza as informações da mesa, {@link Map} de {@link CartasMesa}, mediante 
	 * a informação da posição que o jogador atual {@link Jogador}, seja ele principal 
	 * ou automatizado, posicionou uma carta {@link Carta} e remove a carta da coleção 
	 * do jogador .Se não houver uma instância de {@link Partida} válida ou não for 
	 * possível localizar a carta ou não for possível identificar a posição da carta, 
	 * nenhuma ação é realizada.
	 * 
	 * @param cartaID inteiro que identifica a carta do jogador
	 * @param posicao informação de cor e lado em que a carta foi posicionada
	 */
	public void registrarJogada(int cartaID, String posicao) {

		if (ValidadorUtil.isNotNull(partida)) {
			Carta carta = partida.getJogadorAtual().getCartasNaMao().get(cartaID);

			if (ValidadorUtil.isNotNull(carta)) {

				if (ExtratorUtil.parametroValido(posicao)) {
					String cor = ExtratorUtil.extrairCor(posicao);
					String lado = ExtratorUtil.extrairLado(posicao);

					CartasMesa linha = partida.getMesa().get(cor.toUpperCase());

					if (lado.equalsIgnoreCase("esquerda")) {
						linha.adicionarCartaLadoEsquerdo(carta);

					} else {
						linha.adicionarCartaLadoDireito(carta);

					}

					corModificada = carta.getCor();

					partida.getJogadorAtual().descartar(cartaID);
				} else {
					log.error("❌  Não foi possível extrair as informações dos parâmetros passados.");
					log.warn("⚠️ ID da carta: " + cartaID);
					log.warn("⚠️ Posição da carta: " + posicao);
				}

			} else {
				log.error("❌ Não foi possível capturar a carta pelo ID.");
				log.warn("⚠️ ID da carta: " + cartaID);
				log.warn("⚠️ Posição da carta: " + posicao);
			}
		} else {
			log.error("❌ Não foi possível recuperar a partida atual.");
		}

	}

	/**
	 * Muda o jogador da vez. 
	 * Verifica quem é o jogador atual {@link Jogador}, remove a vez e atribui ao próximo 
	 * jogador da fila que não tiver saído da partida. Se não houver uma instância de 
	 * {@link Partida} válida ou se não for possível recuperar o jogador atual, nenhuma ação 
	 * é realizada.
	 */
	public void proximoJogador() {

		if (ValidadorUtil.isNotNull(partida)) {
			Jogador jogadorAtual = partida.getJogadorAtual();

			if (ValidadorUtil.isNotNull(jogadorAtual)) {
				List<Jogador> jogadores = partida.getJogadores();
				int posicaoAtual = jogadores.indexOf(jogadorAtual);
				int totalJogadores = jogadores.size();

				jogadorAtual.setVezDeJogar(false);

				for (int i = 1; i <= totalJogadores; i++) {
					int proximaPosicao = (posicaoAtual + i) % totalJogadores;
					Jogador proximo = jogadores.get(proximaPosicao);

					if (!isJogadorFinalizado(proximo)) {
						proximo.setVezDeJogar(true);
						break;
					}
				}

			} else {
				log.error("❌ Não foi possível capturar o jogador atual.");
			}

		} else {
			log.error("❌ Não foi possível recuperar a partida atual.");
		}

	}

	/**
	 * Insere cartas para um jogador. 
	 * Insere na coleção de cartas, {@link Map} de {@link Carta}, do jogador 
	 * atual {@link Jogador} a quantidade de cartas referenciadas em {@code cavarCartas}. 
	 * Posteriormente remove a(s) carta(s) dada(s) ao jogador da coleção de cartas do jogo 
	 * e zera a quantidade de cartas a serem cavadas. Se não houver uma instância de 
	 * {@link Partida} e jogador atual válidas ou não seja possível recuperar a lista de 
	 * cartas do jogo, nenhuma ação é realizada.
	 * 
	 * @return {@link List} lista de {@link Carta} cavadas
	 */
	public List<Carta> cava() {
		List<Carta> cavadas = new ArrayList<Carta>();

		if (ValidadorUtil.isNotNull(partida)) {
			Jogador jogador = partida.getJogadorAtual();
			List<Carta> listaCartasJogo = partida.getCartasJogo();
			if (ValidadorUtil.isNotNull(jogador) && ValidadorUtil.isNotNull(listaCartasJogo)) {
				int i = 0;
				for (; i < cavarCartas; i++) {
					if (!listaCartasJogo.isEmpty()) {
						int cartaID = jogador.getUltimoIDCarta() + 1;
						jogador.pegarCarta(cartaID, listaCartasJogo.get(0));
						cavadas.add(listaCartasJogo.get(0));
						listaCartasJogo.remove(0);
						jogador.setUltimoIDCarta(cartaID);
					} else {
						break;
					}
				}

			} else {
				log.error("❌ Não foi possível capturar jogador atual ou lista de cartas do jogo.");
				log.warn("⚠️ Jogador atual is null: " + ValidadorUtil.isNull(jogador));
				log.warn("⚠️ Cartas da partida is null: " + ValidadorUtil.isNull(listaCartasJogo));
			}

			cavarCartas = 0;

		} else {
			log.error("❌ Não foi possível recuperar a partida atual.");
		}

		return cavadas;

	}

	/**
	 * Gera uma jogada para um jogador automatizado. 
	 * Se não houver uma instância de {@link Partida} válida ou não for possível recuperar 
	 * o jogador atual {@link Jogador}, nenhuma ação é realizada.
	 * 
	 * @return {@link List} lista contendo a carta {@link Carta} selecionada e
	 *         string com a posição da jogada
	 */
	public List<Object> jogadaComputador() {

		if (ValidadorUtil.isNotNull(partida)) {
			Jogador jogadorAtual = partida.getJogadorAtual();

			if (ValidadorUtil.isNotNull(jogadorAtual)) {
				Map<String, CartasMesa> mesa = partida.getMesa();
				Map<Integer, Carta> cartasJogador = new HashMap<Integer, Carta>();

				/*
				 * Criando uma cópia da coleção de cartas do jogador para fazer a manipulação
				 * sem afetar a coleção original
				 */
				cartasJogador.putAll(jogadorAtual.getCartasNaMao());

				while (cartasJogador.size() > 0) {
					List<Integer> listaChaves = new ArrayList<>(cartasJogador.keySet());

					// Capturando uma carta aleatória
					int posicaoChave = ThreadLocalRandom.current().nextInt(0, listaChaves.size());
					int chave = listaChaves.get(posicaoChave);
					Carta cartaSelecionada = cartasJogador.get(chave);

					// Recuperando os dados da linha em que a carta pode ser inserida
					CartasMesa linha = mesa.get(cartaSelecionada.getCor().toUpperCase());

					// Verificando se inserir no lado direito gera penalidade
					if (cartaSelecionada.getNumero() <= linha.verCartaTopoEsquerda().getNumero()) {
						linha.adicionarCartaLadoDireito(cartaSelecionada);
						String posicao = GeradorUtil.gerarPosicaoDireita(cartaSelecionada);
						registrarJogada(chave, posicao);
						return Arrays.asList(cartaSelecionada, "direita");

						// Verificando se inserir no lado esquerdo gera penalidade
					} else if (cartaSelecionada.getNumero() >= linha.verCartaTopoDireita().getNumero()) {
						linha.adicionarCartaLadoEsquerdo(cartaSelecionada);
						String posicao = GeradorUtil.gerarPosicaoEsquerda(cartaSelecionada);
						registrarJogada(chave, posicao);
						return Arrays.asList(cartaSelecionada, "esquerda");

					} else {
						/*
						 * Caso a carta gere penalidade em ambos os lados, ela deve ser removida das
						 * opções e deve ser sorteada uma nova carta. No caso da carta ser a única opção
						 * disponível, ela deve ser jogada na direita
						 */

						if (cartasJogador.size() == 1) {
							linha.adicionarCartaLadoDireito(cartaSelecionada);
							String posicao = GeradorUtil.gerarPosicaoDireita(cartaSelecionada);
							registrarJogada(chave, posicao);
							return Arrays.asList(cartaSelecionada, "direita");
						}

						cartasJogador.remove(chave);

					}
				}

			} else {
				log.error("❌ Não foi possível capturar o jogador atual.");
			}
		} else {
			log.error("❌ Não foi possível recuperar a partida atual.");
		}

		return null;

	}

	/**
	 * Verifica se um jogador especifico fez Balunga. 
	 * Atráves do ID do jogador {@link Jogador} o sistema verifica se a jogada da rodada 
	 * permite que o jogador em questão descarte uma ou mais cartas. Podendo haver o 
	 * descarte, a(s) carta(s) é(são) removida(s) da coleção do jogador e a váriavel
	 * {@code cavarCartas} é alimentada com seu valor anterior + a quantidade de cartas 
	 * descartadas.
	 * 
	 * @param jogadorID identificador do jogador na lista de jogadores
	 * @return {@link List} lista de cartas descartadas
	 */
	public List<Carta> descarteBalunga(String jogadorID) {
		List<Carta> cartas = new ArrayList<Carta>();
		if (ValidadorUtil.isNotNull(partida)) {
			Jogador jogador = null;

			for (Jogador j : partida.getJogadores()) {
				if (j.getNomeJogador().equals(jogadorID)) {
					jogador = j;
					break;
				}
			}

			if (ValidadorUtil.isNotNull(jogador)) {
				CartasMesa linhaMesa = partida.getMesa().get(corModificada.toUpperCase());
				int valorInicial = linhaMesa.verCartaTopoDireita().getNumero();
				int valorLimite = linhaMesa.verCartaTopoEsquerda().getNumero();
				ArrayList<Integer> cartasDescarte = new ArrayList<Integer>();

				if (!jogador.equals(partida.getJogadorAtual())) {
					for (Entry<Integer, Carta> carta : jogador.getCartasNaMao().entrySet()) {
						if (carta.getValue().getCor().equals(corModificada)) {
							int valorCarta = carta.getValue().getNumero();
							if (valorCarta > valorInicial && valorCarta <= valorLimite) {
								cartasDescarte.add(carta.getKey());
								cartas.add(carta.getValue());
							}
						}

					}

				}

				if (!cartasDescarte.isEmpty()) {
					for (int chave : cartasDescarte) {
						jogador.descartar(chave);
						cavarCartas++;
					}

				}

			} else {

				log.error("❌ Não foi possível localizar o jogador: " + jogadorID);
			}
		} else {
			log.error("❌ Não foi possível recuperar a partida atual.");
		}

		return cartas;

	}

	/**
	 * Verifica se um jogador já venceu o jogo.
	 * 
	 * @param jogador {@link Jogador} a ser verificado
	 * @return {@code true} se o jogador não possui cartas na sua coleção, caso
	 *         contrário, {@code false}
	 */
	private boolean isJogadorFinalizado(Jogador jogador) {

		return jogador.getCartasNaMao().isEmpty();
	}

	/**
	 * Verifica se a partida já pode ser encerrada.
	 * 
	 * @return {@code true} se o jogador principal não tiver cartas na coleção ou se
	 *         apenas um jogador ainda tiver cartas ou não houver uma instância de
	 *         {@link Partida} válida, caso contrário, {code false}
	 */
	public boolean isPartidaEncerrada() {
		int jogadoresConcluidos = 0;
		if (ValidadorUtil.isNotNull(partida)) {
			List<Jogador> jogadores = partida.getJogadores();
			for (Jogador jogador : jogadores) {
				if (jogador.getCartasNaMao().isEmpty()) {
					jogadoresConcluidos++;
				}
			}

			if (jogadoresConcluidos == (jogadores.size() - 1) || jogadores.get(0).getCartasNaMao().isEmpty()) {
				return true;
			}

			return false;
		}

		log.error("❌ Não foi possível recuperar a partida atual.");
		return true;

	}

	/**
	 * Verifica se a jogada feita pelo jogador resultou em penalidade. 
	 * Faz um comparativo entre os valores das cartas do lado direito na fileira em que
	 * sofreu a ação a fim de verificar se a jogada feita pelo jogador atual {@link Jogador} 
	 * não criou um intervalo inexistente.
	 * 
	 * @return {@code true} se deve ser aplicada a penalidade ou {@code false} se
	 *         nenhuma penalidade foi identificada ou se não há uma instância válida
	 *         de {@link Partida}
	 */
	public boolean penalidade() {
		if (ValidadorUtil.isNotNull(partida)) {
			CartasMesa linhaMesa = partida.getMesa().get(corModificada.toUpperCase());
			int valorInicial = linhaMesa.verCartaTopoDireita().getNumero();
			int valorLimite = linhaMesa.verCartaTopoEsquerda().getNumero();

			if (valorInicial > valorLimite) {
				cavarCartas++;
				return true;
			}
			return false;
		} else {
			log.error("❌ Não foi possível recuperar a partida atual.");
		}

		return false;

	}

}
