
/**
 * @typedef {Object} Box
 * @property {boolean} topo - Box do topo da tela
 * @property {boolean} esquerda - Box da esquerda da tela
 * @property {boolean} direita - Box da direita da tela
 */


/** 
 * Controla quais boxes de jogadores estão ativos.
 * @type {Box}
 * @global
 */
let boxAtivo = {
	topo: false,
	esquerda: false,
	direita: false
}



/** @type {URLSearchParams} Captura  o parâmetro da URL*/
const params = new URLSearchParams(window.location.search);

/**
 * Quantidade de jogadores recebida via parâmetro da URL.
 * Valor padrão é 4 caso não seja possível converter.
 * @type {number}
 */
const numeroJogadores = parseInt(params.get('jogadores'), 10) || 4;


/**
 * Monta os boxes de acordo a quantidade de jogadores e inicia a animação
 * das cartas da mesa.
 * 
 * @returns {void}
 */
function iniciarPreparacao(){
	switch(numeroJogadores){
	case 2:
		montarBox2Jogadores();
		break;
	case 3:
		montarBox3Jogadores();
		break;

	case 4:
		montarBox4Jogadores();
		break;
	}

}


/**
 * Monta o box para dois jogadores.
 * 
 * @returns {void}
 */
function montarBox2Jogadores(){
	
	boxAtivo.topo = true;
	configurarJogador("#jogador-topo-label", "Jogador 2", "jogador-2");
}


/**
 * Monta o box para três jogadores.
 * 
 * @returns {void}
 */
function montarBox3Jogadores(){
	
	boxAtivo.esquerda = true;
	boxAtivo.direita = true;

	configurarJogador("#jogador-esquerda-label", "Jogador 2", "jogador-2");
	configurarJogador("#jogador-direita-label", "Jogador 3", "jogador-3");

}


/**
 * Monta o box para quatro jogadores.
 * 
 * @returns {void}
 */
function montarBox4Jogadores(){
	
	boxAtivo.topo = true;
	boxAtivo.esquerda = true;
	boxAtivo.direita = true;

	configurarJogador("#jogador-esquerda-label", "Jogador 2", "jogador-2");
	configurarJogador("#jogador-topo-label", "Jogador 3", "jogador-3");
	configurarJogador("#jogador-direita-label", "Jogador 4", "jogador-4");

}


/**
 * Atualiza o nome e o id de um jogador.
 *
 * @param {string} seletor - Seletor CSS do elemento.
 * @param {string} nome - Nome do jogador.
 * @param {string} novoId - Novo ID para o elemento.
 * @returns {void}
 * 
 * @example
 * configurarJogador("#jogador-esquerda-label", "Jogador 2", "jogador-2");
 */
function configurarJogador(seletor, nome, novoId) {
	const elemento = document.querySelector(seletor);
	if (elemento) {
		elemento.innerHTML = nome;
		elemento.id = novoId;
	} else {
		console.error(`Não foi possível capturar o elemento "${seletor}"`);
	}
}






