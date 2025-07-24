
/**
 * @typedef {Object} Jogador
 * @property {string} nomeJogador - Nome do jogador
 * @property {Object<number, Carta>} cartasNaMao - Cartas do jogador indexadas por ID
 * @property {boolean} vezDeJogar - Indica se o jogador está na vez
 * @property {number} ultimoID - Último ID registrado para uma carta do jogador
 */

/**
 * @typedef {Object} Carta
 * @property {string} cor - Cor da carta
 * @property {string} urlImagem - URL da imagem da carta
 * @property {number} numero - Valor da carta
 * @property {number} tipoCarta - Tipo da carta: Coringa (1), Base (2) ou Colorida (3)
 */


/** @type {Jogador} Jogador da vez 
 * @global
 * */
let jogadorAtual;

/** @type {boolean} Indica se o jogador principal está temporariamente bloqueado para jogar 
 * @global
 * */
let bloquearJogadorPrincipal = false;

/** @type {Jogador[]} Lista dos jogadores  
 * @global
 * */
let todosJogadores;

/** @type {boolean} Indica se houve balunga na jogada */
let balunga = false;

/** @type {number} Indica a quantidade de cartas que foram descartadas durante o balunga */
let cartasBalunga = 0;


/**
 * @async
 * Controla a lógica da rodada atual, verificando se o jogo acabou.
 * Se não acabou, executa a próxima jogada.
 * 
 * @returns {Promise<void>}
 */

async function proximaRodada() {

	let jogoFinalizado = await isJogoFinalizado();
	if (!jogoFinalizado) {
		rodada();
	} else {
		alert('Fim do jogo!');
	}

}

/**
 * @async
 * Equivale a jogada de um jogador de máquina.
 * 
 * @returns {Promise<void>}
 */
async function rodada() {

	await new Promise(resolve => setTimeout(resolve, 2000));
	await jogadaMaquina();
	await checagem();
	let jogoFinalizado = await isJogoFinalizado();
	if (!jogoFinalizado) {
		await proximoJogador();
	}
	if (!jogadorAtual.nomeJogador.includes('principal')) {
		await proximaRodada();
	}

}

/**
 * @async
 * Verifica se o jogo foi finalizado.
 * 
 * @returns {Promise<boolean>} Verdadeiro se o jogo terminou.
 */
async function isJogoFinalizado() {
	const response = await fetch('/jogo/fim');
	const data = await response.json();
	return data;
}


/**
 * @async
 * Envia para o backend a jogada do jogador principal.
 * 
 * @param {number} carta - ID da carta jogada.
 * @param {string} posicao - Posição onde a carta foi jogada.
 * @returns {Promise<boolean>} Verdadeiro se a jogada foi registrada com sucesso.
 * 
 * @example
 * registrarJogada(2, "esquerda-4--verde");
 */
async function registrarJogada(carta, posicao) {

	await fetch('/registrar-jogada', {
		method: 'POST',
		headers: {
			'Content-Type': 'application/json'
		},
		body: JSON.stringify({ cartaID: carta, posicao: posicao })
	});
	await listaJogadores();
	await capturarJogadorAtual()
	let quantidadeCartas = Object.keys(jogadorAtual.cartasNaMao).length;
	const divPrincipal = document.querySelector('#' + jogadorAtual.nomeJogador).parentElement;
	const jogadorCartas = '#' + divPrincipal.querySelector('.mesa__posicao-jogador').id;
	posicionarCartasJogadoresHorizontal(jogadorCartas, quantidadeCartas);
	await checagem();
	let jogoFinalizado = await isJogoFinalizado();
	if (!jogoFinalizado) {
		await proximoJogador();
	}

	await proximaRodada();
	return true;



}

/**
 * @async
 * Captura a carta oriunda da jogada gerada pelo backend e exibe na tela.
 * 
 * @returns {Promise<boolean>} Verdadeiro se a máquina realizou a jogada.
 */
async function jogadaMaquina() {

	const response = await fetch('/jogador/maquina');
	const carta = await response.json();

	const divPrincipal = document.querySelector('#' + jogadorAtual.nomeJogador).parentElement;

	const corCarta = carta[0].cor;
	const lado = carta[1];
	const elemento = document.querySelector('[id*="' + lado + '"][id*="' + corCarta + '"]');

	// Criar imagem e aplicar estilo
	const imagemEfeito = document.createElement('img');
	imagemEfeito.src = './images/' + carta[0].urlImagem + '.png';
	imagemEfeito.classList.add('img--carta-horizontal', 'img--carta-jogada');
	imagemEfeito.style.position = 'absolute';

	// Calcular posição inicial (centro do jogador)
	const jogadorBox = divPrincipal.getBoundingClientRect();
	const elementoBox = elemento.getBoundingClientRect();

	imagemEfeito.style.top = jogadorBox.top + 'px';
	imagemEfeito.style.left = jogadorBox.left + 'px';

	document.body.appendChild(imagemEfeito); 

	// Forçar o reflow para garantir que a posição inicial foi "vista"
	void imagemEfeito.offsetWidth;

	// Aplicar destino com animação
	imagemEfeito.style.top = elementoBox.top + 'px';
	imagemEfeito.style.left = elementoBox.left + 'px';

	// Após animação, substituir imagem e remover efeito
	await new Promise(resolve => setTimeout(resolve, 600));
	elemento.src = './images/' + carta[0].urlImagem + '.png';
	imagemEfeito.remove();

	// Após a remoção do efeito reposiciona as cartas do box do jogador
	await capturarJogadorAtual();
	let quantidadeCartas = Object.keys(jogadorAtual.cartasNaMao).length;
	const jogadorCartas = '#' + divPrincipal.querySelector('.mesa__posicao-jogador').id;

	if (divPrincipal.className.includes('horizontal')) {
		posicionarCartasJogadoresHorizontal(jogadorCartas, quantidadeCartas);
	} else {
		posicionarCartasJogadoresVertical(jogadorCartas, quantidadeCartas);
	}
	

	return true;
}


/**
 * @async
 * Checa se a jogada gerou penalidade para o jogador e balunga de algum jogador.
 * 
 * @returns {Promise<void>}
 */
async function checagem() {

	await checarPenalidade();
	await checarBalunga();

}

/**
 * @async
 * Verifica se a jogada gerou penalidade e em caso positivo realiza o cava.
 * 
 * @returns {Promise<void>}
 */
async function checarPenalidade() {

	const response = await fetch('/checar-penalidade');
	const data = await response.json();
	if (data === true) {
		await faixaPenalidade();
		await cavarCartas();
	}

}

/**
 * @async
 * Verifica balunga de um jogador por vez chama o método de cavar cartas quando necessário.
 * 
 * @returns {Promise<void>}
 */
async function checarBalunga() {
	
	for (const jogador of todosJogadores) {
		await checarJogadorBalunga(jogador.nomeJogador);
	}

	if (balunga) {
		await explosaoBalunga();
		await cavarCartas();
		balunga = false;
		cartasBalunga = 0;
	}
}


/**
 * @async
 * Verifica se um jogador (com exceção do que realizou a jogada) fez balunga.
 * Caso o jogador tenha feito, exibe as cartas que foram descartadas.
 * 
 * @returns {Promise<boolean>} Verdadeiro se a verificação foi feita sem erros.
 */
async function checarJogadorBalunga(nomeJogador) {
	if (jogadorAtual.nomeJogador !== nomeJogador) {

		const response = await fetch('/checar-balunga', {
			method: 'POST',
			headers: {
				'Content-Type': 'application/json'
			},
			body: JSON.stringify({ jogadorID: nomeJogador })
		});

		const data = await response.json();

		if (data.length > 0) {

			// Faz a contagem de quantas cartas foram descartadas ao longo das checagens
			cartasBalunga = cartasBalunga + data.length;

			balunga = true;
			const divPrincipal = document.querySelector('#' + nomeJogador).parentElement;

			// Necessário para atualizar as cartas do jogador que fez balunga
			await listaJogadores();

			const jogadorCartas = '#' + divPrincipal.querySelector('.mesa__posicao-jogador').id;
			const jogadorLista = todosJogadores.find(item => item.nomeJogador === nomeJogador);

			const quantidadeCartas = Object.keys(jogadorLista.cartasNaMao).length;



			if (divPrincipal.className.includes('horizontal')) {
				posicionarCartasJogadoresHorizontal(jogadorCartas, quantidadeCartas);

			} else {
				posicionarCartasJogadoresVertical(jogadorCartas, quantidadeCartas);
			}

			//Exibe as cartas que foram descartadas na tela
			const jogadorBox = divPrincipal.getBoundingClientRect();
			let posicaoInicial = jogadorBox.left;
			data.forEach(carta => {
				const img = document.createElement('img');
				img.src = './images/' + carta.urlImagem + '.png';
				img.classList.add("mesa__carta-jogadores", "img--carta-horizontal");
				img.style.position = "absolute";
				img.style.bottom = jogadorBox.top;
				img.style.left = posicaoInicial + "px";
				posicaoInicial += 70;
				divPrincipal.appendChild(img);
				setTimeout(() => {
					img.remove();
				}, 2000)
			});

		} 
	}

	return true;

}


/**
 * @async
 * Retorna do back quantas e/ou quais foram as cartas cavadas pelo jogador atual.
 * 
 * @returns {Promise<void>}
 */
async function cavarCartas() {

	const response = await fetch('/cava');
	const cartas = await response.json();
	const divPrincipal = document.querySelector('#' + jogadorAtual.nomeJogador).parentElement;

	// Capturando o centro da tela para posicionar elemento
	let centroX = window.innerWidth / 2;
	const centroY = window.innerHeight / 2;

	const jogadorBox = divPrincipal.getBoundingClientRect();

	// Atualiza as cartas dos jogadores  
	await capturarJogadorAtual();
	await listaJogadores();

	// Exibe o efeito de cartasdo centro da tela para o jogador correspondente
	for (const [index, carta] of cartas.entries()) {
		const imagemEfeito = document.createElement('img');
		imagemEfeito.src = jogadorAtual.nomeJogador.includes('principal') ? './images/' + carta.urlImagem + '.png' : './images/fundo-carta.png';
		imagemEfeito.classList.add('img--carta-horizontal', 'img--carta-jogada');
		imagemEfeito.style.position = 'absolute';

		// Posicionando elemento no centro da tela
		imagemEfeito.style.top = centroY + 'px';
		imagemEfeito.style.left = centroX + 'px';
		centroX += 20;

		document.body.appendChild(imagemEfeito);

		// Forçar o reflow para garantir que a posição inicial foi "vista"
		void imagemEfeito.offsetWidth;


		// Enviando o elemento parao jogador correspondente
		imagemEfeito.style.top = (jogadorBox.top + 50) + 'px';
		imagemEfeito.style.left = jogadorBox.right + 'px';

		await new Promise(resolve => setTimeout(resolve, 600));

		imagemEfeito.remove();


		// Reposicionando as cartas do jogador, sendo exibidas as cartas cavadas no box uma de cada vez
		let quantidadeCartas = Object.keys(jogadorAtual.cartasNaMao).length - (cartas.length - index - 1);
		const jogadorCartas = '#' + divPrincipal.querySelector('.mesa__posicao-jogador').id;

		if (divPrincipal.className.includes('horizontal')) {
			posicionarCartasJogadoresHorizontal(jogadorCartas, quantidadeCartas);
		} else {
			posicionarCartasJogadoresVertical(jogadorCartas, quantidadeCartas);
		}

	}

}


/**
 * @async
 * Passa para o próximo jogador e atualiza o jogador atual.
 * 
 * @returns {Promise<boolean>} Verdadeiro se o proximo jogador foi selecionado.
 */
async function proximoJogador() {

	await fetch('/proximo-jogador');
	await capturarJogadorAtual();

	return true;

}

/**
 * @async
 * Modifica o valor do objeto jogador atual, insere um indicador visual do jogador que está na
 * na sua vez de jogar e bloqueia as ações do jogador principal quando necessário.
 * 
 * @returns {Promise<boolean>} Verdadeiro se foi possível recuperar o jogador atual do backend.
 */
async function capturarJogadorAtual() {

	const response = await fetch('/jogador/atual');

	const data = await response.json();

	document.querySelectorAll('.mesa__posicao-jogador-label').forEach(label => {
		if (label.classList.contains('jogador--ativo')) {
			label.classList.remove('jogador--ativo');
		}




	});
	jogadorAtual = data;
	document.querySelector('#' + data.nomeJogador).classList.add('jogador--ativo');
	if (!data.nomeJogador.includes('principal')) {
		bloquearJogadorPrincipal = true;
	} else {
		bloquearJogadorPrincipal = false;
	}



	return true;
}

/**
 * @async
 * Captura e atualiza a lista de jogadores.
 * 
 * @returns {Promise<Jogador[]>} Lista com os jogadores.
 */
async function listaJogadores() {
	const response = await fetch('/jogador/todos');
	const lista = await response.json();
	todosJogadores = lista;

	return lista;
}


/**
 * @async
 * Exibe uma faixa no meio da tela para indicar que a jogada foi penalizada.
 * 
 * @returns {Promise<boolean>} Verdadeiro se a faixa foi exibida e removida após 4 segundos.
 */
async function faixaPenalidade() {
	const faixa = document.createElement('div');
	faixa.classList.add('faixa');
	faixa.textContent = 'PENALIDADE: +1';
	faixa.style.backgroundColor = 'crimson';

	document.body.appendChild(faixa);

	await new Promise(resolve => setTimeout(resolve, 4000));

	faixa.remove();
	
	return true;

}

/**
 * @async
 * Exibe um indicativo visual de que houve balunga na rodada e a quantidade de cartas
 * que o jogador que realizou a jogada deve cavar.
 * 
 * @returns {Promise<boolean>} Verdadeiro se a animação foi exibida e depois removida corretamente.
 */
async function explosaoBalunga(){

	// Container utilizado para "guardar" os elementos visuais
	const container = document.createElement('div');
	container.classList.add('efeito-balunga');

	// Fundo para o texto
	const splash = document.createElement('div');
	splash.classList.add('splash');

	// Container para "guardar" o texto e a legenda
	const conteudo = document.createElement('div');
	conteudo.classList.add('conteudo-balunga');

	// Texto principal
	const texto = document.createElement('div');
	texto.classList.add('texto-balunga');
	texto.textContent = 'BALUNGA!';

	// Informativo do jogador a cavar e a quantidade
	const legenda = document.createElement('div');
	legenda.classList.add('legenda-balunga');
	const nomeJogador = document.querySelector('#' + jogadorAtual.nomeJogador).innerText;
	legenda.textContent = nomeJogador + " cava " + cartasBalunga + " carta(s).";

	conteudo.appendChild(texto);
	conteudo.appendChild(legenda);
	container.appendChild(splash);
	container.appendChild(conteudo);
	document.body.appendChild(container);

	await new Promise(resolve => setTimeout(resolve, 4000));

	//Removendo animação da tela
	container.classList.add('saida-suave');

	await new Promise(resolve => setTimeout(resolve, 600));

	container.remove();

	return true;
}