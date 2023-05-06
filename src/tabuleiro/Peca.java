package tabuleiro;

public class Peca {
	
	// Implementação do encapsulamento
	protected Posicao posicao;
	private Tabuleiro tabuleiro;
	
	// Criando o construtor
	public Peca(Posicao posicao, Tabuleiro tabuleiro) {
		this.tabuleiro = tabuleiro;
		posicao = null;
	}
	
	// Criando apenas o get
	protected Tabuleiro getTabuleiro() {
		return tabuleiro;
	}
	
	
}
