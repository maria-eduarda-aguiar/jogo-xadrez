package tabuleiro;

public abstract class Peca {
	
	// Implementação do encapsulamento
	protected Posicao posicao;
	private Tabuleiro tabuleiro;
	
	// Criando o construtor
	public Peca(Tabuleiro tabuleiro) {
		this.tabuleiro = tabuleiro;
		posicao = null;
	}
	
	// Criando apenas o get
	protected Tabuleiro getTabuleiro() {
		return tabuleiro;
	}
	
	// Método abstrato de movimentos possíveis
	public abstract boolean[][] movimentosPossiveis();
	
	// Método concreto que depende do abstrato para verificar se há movimento possível
	public boolean movimentoPossivel(Posicao posicao) {
		return movimentosPossiveis()[posicao.getLinha()][posicao.getColuna()];
	}
	
	// Método concreto que depende do abstrato para verificar se existe pelo menos um movimento possível (peça trancada)
	public boolean existeMovimentoPossivel() {
		boolean[][] matriz = movimentosPossiveis();
		for (int i = 0; i < matriz.length; i++) {
			for (int j = 0; j < matriz.length; j++) {
				if (matriz[i][j]) {
					return true;
				}
			}
		}
		return false;
	}
}
