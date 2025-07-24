package com.sakura.balunga.game.util;



/**
 * Aplica o padrão DTO para compartilhar informações entre
 * a view e o backend.
 * Faz a manipulação de variáveis que são recebidas do frontend
 * e enviadas para o backend.
 * 
 * @author Sakura
 * @version 1.0
 * @since 1.0.0
 */
public class ParametroRequest {

    private int jogadores;
    private int cartaID;
    private String posicao;    
    private String jogadorID;
    
    
    /**
     * Retorna a quantidade de jogadores informada.
     * 
     * @return quantidade de jogadores
     */
    public int getJogadores() {
        return jogadores;
    }

    /**
     * Define a quantidade de jogadores.
     * 
     * @param jogadores quantidade de jogadores
     */
    public void setJogadores(int jogadores) {
        this.jogadores = jogadores;
    }

    /**
     * Retorna o identificador da carta selecionada.
     * 
     * @return ID da carta
     */
    public int getCartaID() {
        return cartaID;
    }

    /**
     * Define o identificador da carta.
     * 
     * @param cartaID ID da carta
     */
    public void setCartaID(int cartaID) {
        this.cartaID = cartaID;
    }

    /**
     * Retorna a posição escolhida para jogar a carta.
     * 
     * @return {@link String} representando lado e cor da jogada
     */
    public String getPosicao() {
        return posicao;
    }

    /**
     * Define a posição em que a carta será jogada.
     * 
     * @param posicao texto representando lado e cor da jogada
     */
    public void setPosicao(String posicao) {
        this.posicao = posicao;
    }

    /**
     * Retorna o identificador do jogador que está realizando a jogada.
     * 
     * @return {@link String} ID do jogador
     */
    public String getJogadorID() {
        return jogadorID;
    }

    /**
     * Define o identificador do jogador que está jogando.
     * 
     * @param jogadorID ID do jogador
     */
    public void setJogadorID(String jogadorID) {
        this.jogadorID = jogadorID;
    }
	
}