const modal_regras = `

	<div class="painel-regras">

		<div class="painel-regras__modal">
			<div class="painel-regras__modal-cabecalho">
				<button class="painel-regras__modal-cabecalho-btn btn--fechar" onclick="fechar()"></button>

			</div>
			<div class="painel-regras__modal-titulo">
				📜 INSTRUÇÕES
			</div>
			<div class="painel-regras__modal-texto">
				<div class="painel-regras__modal-texto-item texto--objetivo">
					<div class="painel-regras__modal-texto-item-titulo texto--objetivo-titulo">
						Objetivo

					</div>
					<div class="painel-regras__modal-texto-item-conteudo texto--objetivo-conteudo">
						Ser o primeiro jogador a se livrar de todas as cartas da mão ou ter a menor pontuação ao final do jogo.
					</div>

				</div>
				<div class="painel-regras__modal-texto-item painel-regras__modal-texto-como-jogar">
					<div class="painel-regras__modal-texto-item-titulo texto--como-jogar-titulo">
						Como jogar
					</div>
					<div class="painel-regras__modal-texto-item-conteudo texto--como-jogar-conteudo">
						O jogo ocorre em turnos no sentido horário.

						No seu turno, você deve jogar uma carta colorida ao lado esquerdo ou direito da carta coringa da mesma cor.

						A carta jogada deve ser colocada em uma das extremidades, sobrepondo a carta cinza ou a carta mais recente daquele lado.

						Sempre que houver duas cartas numéricas à esquerda e à direita da carta coringa de uma cor, um intervalo numérico é criado.

						Se você jogar uma carta que forme um intervalo, qualquer jogador (com exceção de você) que tiver cartas dentro desse intervalo e da mesma cor pode gritar "Cabanga!" e descartar todas de uma vez.

						O intervalo é exclusivo à esquerda e inclusivo à direita.

						Exemplo: Se há um 6 à esquerda e você joga um 12 à direita, você pode jogar cartas de número 7, 8, 9, 10, 11 ou 12.

						Quem jogou a carta que criou o intervalo compra a mesma quantidade de cartas que o(s) outro(s) jogador(es) descartou(aram).
					</div>

				</div>
				<div class="painel-regras__modal-texto-item painel-regras__modal-texto-penalidade">
					<div class="painel-regras__modal-texto-item-titulo texto--penalidade-titulo">
						Penalidade
					</div>
					<div class="painel-regras__modal-texto-item-conteudo texto--penalidade-conteudo">
						Se você jogar uma carta sem formar um intervalo, você deve comprar 1 carta do monte.
					</div>

				</div>
				<div class="painel-regras__modal-texto-item painel-regras__modal-texto-vencedor">
					<div class="painel-regras__modal-texto-item-titulo texto--vencedor-titulo">
						Vencedor
					</div>
					<div class="painel-regras__modal-texto-item-conteudo texto--vencedor-conteudo">
						O jogo termina quando um jogador descartar todas as suas cartas, ou o monte de compra se esgotar.

						Caso o jogo termine com cartas nas mãos dos jogadores, some os valores de todas as cartas restantes. Vence quem tiver a menor pontuação.
					</div>
				</div>

			</div>
		</div>


	</div>

`

function fechar(){
	const div = document.querySelector(".painel-regras");
	
	if(div){
		div.classList.add("efeito--fade-out");
	setTimeout(() => {
		div.remove();
	}, 500)
	}else{
		console.error('Não foi possível capturar o elemento ".painel-regras" ');
	}
	
}