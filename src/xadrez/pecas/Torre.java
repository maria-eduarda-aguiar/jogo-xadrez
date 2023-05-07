package xadrez.pecas;

import tabuleiro.Tabuleiro;
import xadrez.PecaXadrez;
import xadrez.Cor;

public class Torre extends PecaXadrez{
	
	// Criação do construtor para repassar a chamada para a superclasse
	public Torre(Tabuleiro tabuleiro, Cor cor) {
		super(tabuleiro, cor);
	}
	
	@Override
	public String toString() {
		return "T";
	}
	
	@Override
	public boolean[][] movimentosPossiveis() {
		boolean[][] matriz = new boolean [getTabuleiro().getLinhas()][getTabuleiro().getColunas()];
		return matriz;
	}

}
