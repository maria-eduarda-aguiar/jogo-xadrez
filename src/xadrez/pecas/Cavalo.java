package xadrez.pecas;

import java.io.Serializable;

import tabuleiro.Posicao;
import tabuleiro.Tabuleiro;
import xadrez.Cor;
import xadrez.PecaXadrez;

public class Cavalo extends PecaXadrez  implements Serializable{
	

	private static final long serialVersionUID = 1L;

	// Criação do construtor para repassar a chamada para a superclasse
	public Cavalo(Tabuleiro tabuleiro, Cor cor) {
		super(tabuleiro, cor);
	}
	
	@Override
	public String toString() {
		return "C";
	}
	
	// Método para saber se o Cavalo pode se mover para uma determinada posição
	private boolean podeMover(Posicao posicao) {
		PecaXadrez p = (PecaXadrez)getTabuleiro().peca(posicao);
		return p == null || p.getCor() != getCor();		
	}

	@Override
	public boolean[][] movimentosPossiveis() {
		boolean[][] matriz = new boolean [getTabuleiro().getLinhas()][getTabuleiro().getColunas()];
		
		Posicao p = new Posicao(0, 0);
		
		// Verificar acima do Cavalo
		p.setValores(posicao.getLinha() - 1, posicao.getColuna() - 2);
		if (getTabuleiro().posicaoExiste(p) && podeMover(p)) {
			matriz[p.getLinha()][p.getColuna()] = true;
		}
		
		// Verificar abaixo do Cavalo
		p.setValores(posicao.getLinha() - 2, posicao.getColuna() - 1);
		if (getTabuleiro().posicaoExiste(p) && podeMover(p)) {
			matriz[p.getLinha()][p.getColuna()] = true;
		}
		
		// Verificar à esquerda do Cavalo
		p.setValores(posicao.getLinha() - 2, posicao.getColuna() + 1);
		if (getTabuleiro().posicaoExiste(p) && podeMover(p)) {
			matriz[p.getLinha()][p.getColuna()] = true;
		}
		
		// Verificar à direita do Cavalo
		p.setValores(posicao.getLinha() - 1, posicao.getColuna() + 2);
		if (getTabuleiro().posicaoExiste(p) && podeMover(p)) {
			matriz[p.getLinha()][p.getColuna()] = true;
		}
		
		// Verificar a diagonal noroeste do Cavalo
		p.setValores(posicao.getLinha() + 1, posicao.getColuna() + 2);
		if (getTabuleiro().posicaoExiste(p) && podeMover(p)) {
			matriz[p.getLinha()][p.getColuna()] = true;
		}
		
		// Verificar a diagonal nordeste do Cavalo 
		p.setValores(posicao.getLinha() + 2, posicao.getColuna() + 1);
		if (getTabuleiro().posicaoExiste(p) && podeMover(p)) {
			matriz[p.getLinha()][p.getColuna()] = true;
		}
		
		// Verificar a diagonal sudoeste do Cavalo
		p.setValores(posicao.getLinha() + 2, posicao.getColuna() - 1);
		if (getTabuleiro().posicaoExiste(p) && podeMover(p)) {
			matriz[p.getLinha()][p.getColuna()] = true;
		}
		
		// Verificar a diagonal sudeste do Cavalo
		p.setValores(posicao.getLinha() + 1, posicao.getColuna() - 2);
		if (getTabuleiro().posicaoExiste(p) && podeMover(p)) {
			matriz[p.getLinha()][p.getColuna()] = true;
		}
		
		return matriz;
	}

}
