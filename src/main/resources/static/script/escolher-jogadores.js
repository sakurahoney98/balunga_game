
/**
 * HTML do modal de seleção de jogadores, exibido antes do início do jogo.
 * 
 * Contém:
 * - Título explicativo
 * - Botões de seleção de quantidade de jogadores
 * - Botões de ação (Voltar e OK)
 * 
 * Deve ser inserido dinamicamente no DOM.
 * 
 * @type {string}
 */
const modal_escolher_jogadores = `
	<div class="painel-regras">

		<div class="painel-regras__modal">
			<div class="painel-regras__modal-titulo">
				Escolha a quantidade de jogadores:
			</div>
			<div class="painel-regras__modal-opcao">
				<button class="painel-regras__modal-opcao-btn btn--selecionado" onclick="selecionarOpcao(this)">2 jogadores</button> 
				<button class="painel-regras__modal-opcao-btn" onclick="selecionarOpcao(this)">3 jogadores</button>
				<button class="painel-regras__modal-opcao-btn" onclick="selecionarOpcao(this)">4 jogadores</button> 

			</div>
			<div class="painel-regras__modal-acao">
				<button class="painel-regras__modal-acao-btn btn--voltar" onclick="fechar()">
					<i class="fas fa-arrow-left"></i> Voltar
				</button>
				<button class="painel-regras__modal-acao-btn btn--ok" onclick="irParaJogo()">OK</button>

			</div>

		</div>


	</div>
`

/** @type {number} Quantidade de jogadores selecionado pelo usuário */
let jogadoresSelecionado = 2;

/**
 * Remove o modal da tela com efeito de fade out.
 * 
 * @returns {void}
 * @throws {Error} Se o modal não for encontrado.
 */
function fechar() {
	const div = document.querySelector(".painel-regras");
	if (div) {

		div.classList.add("efeito--fade-out");
		setTimeout(() => {
			div.remove();
		}, 500)

	} else {
		console.error('Não foi possível capturar o elemento ".painel-regras"');
	}

}

/**
 * Destaca a opção clicada e atualiza a variável `jogadoresSelecionado`.
 *  
 * @param {HTMLButtonElement} clique - elemento que sofreu a ação
 * @returns {void}
 * @throws {Error} Se os botões de opção não forem encontrados no DOM.
 * @throws {Error} Se o elemento clicado não for definido.
 */
function selecionarOpcao(clique) {
	const listaOpcoes = document.querySelectorAll('.painel-regras__modal-opcao-btn');
	if (listaOpcoes) {
		listaOpcoes.forEach(opcao => {
			if (opcao.classList.contains("btn--selecionado")) {
				opcao.classList.remove("btn--selecionado")
			}
		});
	} else {
		console.error('Não foi possível capturar o elemento ".painel-regras__modal-opcao-btn"');
	}

	if (clique) {
		clique.classList.add("btn--selecionado");
		const texto = clique.textContent.trim();
		jogadoresSelecionado = parseInt(texto);
	} else {
		console.error('Não foi possível capturar o elemento que fez a chamada');
	}

}

/**
 * Envia a quantidade de jogadores selecionada ao servidor e redireciona para a tela do jogo.
 * 
 * @returns {Promise<void>}
 * @throws {Error} Se não for possível redirecionar o usuário.
 */
function irParaJogo() {
	fetch('/jogadores', {
		method: 'POST',
		headers: {
			'Content-Type': 'application/json'
		},
		body: JSON.stringify({ jogadores: jogadoresSelecionado })
	}).then(response =>{
		window.location.href = `/jogo?jogadores=${jogadoresSelecionado}`;
	}).catch(error => {
		console.error("Erro ao redirecionar para o jogo:", error);
	});

	


}