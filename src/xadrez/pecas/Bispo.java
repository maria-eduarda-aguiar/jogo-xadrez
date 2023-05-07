package xadrez.pecas;

import tabuleiro.Posicao;
import tabuleiro.Tabuleiro;
import xadrez.PecaXadrez;
import xadrez.Cor;

public class Bispo extends PecaXadrez {

	// Criação do construtor para repassar a chamada para a superclasse
	public Bispo(Tabuleiro tabuleiro, Cor cor) {
		super(tabuleiro, cor);
	}

	@Override
	public String toString() {
		return "B";
	}

	@Override
	public boolean[][] movimentosPossiveis() {
		boolean[][] matriz = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];

		Posicao p = new Posicao(0, 0);

		// Verificar o noroeste do Bispo
		p.setValores(posicao.getLinha() - 1, posicao.getColuna() - 1);
		while (getTabuleiro().posicaoExiste(p) && !getTabuleiro().existePeca(p)) {
			matriz[p.getLinha()][p.getColuna()] = true;
			p.setValores(p.getLinha() -1, p.getColuna() - 1);
		}
		if (getTabuleiro().posicaoExiste(p) && existePecaAdversaria(p)) {
			matriz[p.getLinha()][p.getColuna()] = true;
		}

		// Verificar o nordeste do Bispo
		p.setValores(posicao.getLinha() - 1, posicao.getColuna() + 1);
		while (getTabuleiro().posicaoExiste(p) && !getTabuleiro().existePeca(p)) {
			matriz[p.getLinha()][p.getColuna()] = true;
			p.setValores(p.getLinha() -1, p.getColuna() + 1);
		}
		if (getTabuleiro().posicaoExiste(p) && existePecaAdversaria(p)) {
			matriz[p.getLinha()][p.getColuna()] = true;
		}

		// Verificar o sudeste do Bispo
		p.setValores(posicao.getLinha() + 1, posicao.getColuna() + 1);
		while (getTabuleiro().posicaoExiste(p) && !getTabuleiro().existePeca(p)) {
			matriz[p.getLinha()][p.getColuna()] = true;
			p.setValores(p.getLinha() + 1, p.getColuna() + 1);
		}
		if (getTabuleiro().posicaoExiste(p) && existePecaAdversaria(p)) {
			matriz[p.getLinha()][p.getColuna()] = true;
		}

		// Verificar o sudoeste do Bispo
		p.setValores(posicao.getLinha() + 1, posicao.getColuna() - 1);
		while (getTabuleiro().posicaoExiste(p) && !getTabuleiro().existePeca(p)) {
			matriz[p.getLinha()][p.getColuna()] = true;
			p.setValores(p.getLinha() + 1, p.getColuna() - 1);
		}
		if (getTabuleiro().posicaoExiste(p) && existePecaAdversaria(p)) {
			matriz[p.getLinha()][p.getColuna()] = true;
		}

		return matriz;
	}
}
