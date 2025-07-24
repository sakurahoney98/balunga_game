
/**
 * HTML da mesa de jogo, composta por 4 linhas com 3 cartas cada (direita, centro, esquerda).
 * 
 * Cada carta pode ser interativa (arrastar e soltar) ou coringa (central).
 * Utiliza classes para controle visual e identificadores únicos para facilitar a lógica do jogo.
 * 
 * IDs seguem o padrão:
 * - `direita-{n}`, `centro-{n}`, `esquerda-{n}` (n variando de 1 a 4)
 * 
 * Este conteúdo deve ser inserido dinamicamente no DOM antes da animação ou lógica de interação.
 * 
 * @type {string}
 */
const mesa_montada = `<div class="mesa__centro-cartas-tabela">
					<div class="mesa__centro-cartas-linha linha--primeira">
						<div class="mesa__carta-centro">
							<img src="./images/card-cinza-1.png" class="mesa__carta-centro-img" id="direita-1" ondragover="allowDrop(event)" ondrop="drop(event)">

						</div>
						<div class="mesa__carta-centro">
							<img src="./images/card-coringa-amarelo.png" class="mesa__carta-centro-img" id="centro-1" >

						</div>
						<div class="mesa__carta-centro">
							<img src="./images/card-cinza-3.png" class="mesa__carta-centro-img" id="esquerda-1" ondragover="allowDrop(event)" ondrop="drop(event)">

						</div>

					</div>
					<div class="mesa__centro-cartas-linha linha--segunda">
						<div class="mesa__carta-centro">
							<img src="./images/card-cinza-5.png" class="mesa__carta-centro-img" id="direita-2" ondragover="allowDrop(event)" ondrop="drop(event)">

						</div>
						<div class="mesa__carta-centro">
							<img src="./images/card-coringa-azul.png" class="mesa__carta-centro-img" id="centro-2">

						</div>
						<div class="mesa__carta-centro">
							<img src="./images/card-cinza-7.png" class="mesa__carta-centro-img" id="esquerda-2" ondragover="allowDrop(event)" ondrop="drop(event)">

						</div>
					</div>
					<div class="mesa__centro-cartas-linha linha--terceira">
						<div class="mesa__carta-centro">
							<img src="./images/card-cinza-12.png" class="mesa__carta-centro-img" id="direita-3" ondragover="allowDrop(event)" ondrop="drop(event)">

						</div>
						<div class="mesa__carta-centro">
							<img src="./images/card-coringa-vermelho.png" class="mesa__carta-centro-img" id="centro-3">

						</div>
						<div class="mesa__carta-centro">
							<img src="./images/card-cinza-14.png" class="mesa__carta-centro-img" id="esquerda-3" ondragover="allowDrop(event)" ondrop="drop(event)">

						</div>
					</div>
					<div class="mesa__centro-cartas-linha linha--quarta">
						<div class="mesa__carta-centro">
							<img src="./images/card-cinza-16.png" class="mesa__carta-centro-img" id="direita-4" ondragover="allowDrop(event)" ondrop="drop(event)">

						</div>
						<div class="mesa__carta-centro">
							<img src="./images/card-coringa-roxo.png" class="mesa__carta-centro-img" id="centro-4">

						</div>
						<div class="mesa__carta-centro">
							<img src="./images/card-cinza-18.png" class="mesa__carta-centro-img" id="esquerda-4" ondragover="allowDrop(event)" ondrop="drop(event)">

						</div>
					</div>


</div>`;


/**
 * Controla a animação das cartas sendo organizadas na tela.
 * 
 * @returns {void}
 */
function iniciarAnimacao() {
	const linhasCentro = document.querySelectorAll('.mesa__centro-cartas-linha');
	let delay = 0;
	if (linhasCentro) {
		linhasCentro.forEach((linha) => {
			const cartas = linha.querySelectorAll('.mesa__carta-centro');
			if (cartas) {
				cartas.forEach((carta) => {
					// Garante que as cartas apareceram na tela uma de cada vez
					carta.style.animationDelay = `${delay}s`;
					delay += 0.1;
				});
			} else {
				console.error('Não foi possível capturar o elemento ".mesa__carta-centro"');
			}


		});
	} else {
		console.error('Não foi possível capturar o elemento ".mesa__centro-cartas-linha"');
	}


}


