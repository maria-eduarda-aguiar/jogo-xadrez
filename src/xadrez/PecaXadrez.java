package xadrez;

import java.io.Serializable;

import tabuleiro.Peca;
import tabuleiro.Posicao;
import tabuleiro.Tabuleiro;

public abstract class PecaXadrez extends Peca implements Serializable{
	

	private static final long serialVersionUID = 1L;
	private Cor cor;
	private int contadorMovimentos;

	public PecaXadrez(Tabuleiro tabuleiro, Cor cor) {
		super(tabuleiro);
		this.cor = cor;
	}
	
	// Criação dos getters
	public Cor getCor() {
		return cor;
	}
	
	public PecaXadrez getRef() {
		return this;
	}
	
	public int getContadorMovimentos() {
		return contadorMovimentos;
	}
	
	public PosicaoXadrez getPosicaoXadrez() {
		return PosicaoXadrez.fromPosicao(posicao);
	}
	
	// Método para incrementar na contagem de movimentos
	public void incrementarContadorMovimentos() {
		contadorMovimentos++;
	}
	
	// Método para decrementar na contagem de movimentos
	public void decrementarContadorMovimentos() {
		contadorMovimentos--;
	}
	
	// Método para verificar se existe uma peça adversária
	protected boolean existePecaAdversaria(Posicao posicao) {
		PecaXadrez p = (PecaXadrez)getTabuleiro().peca(posicao);
		return p != null && p.getCor() != cor;
	}
}
