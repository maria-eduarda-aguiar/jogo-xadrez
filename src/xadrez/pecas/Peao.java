package xadrez.pecas;

import xadrez.PecaXadrez;
import xadrez.Cor;
import tabuleiro.Posicao;
import tabuleiro.Tabuleiro;

public class Peao extends PecaXadrez {

	public Peao(Tabuleiro tabuleiro, Cor cor) {
		super(tabuleiro, cor);
	}

	@Override
	public boolean[][] movimentosPossiveis() {
		boolean[][] matriz = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];

		Posicao p = new Posicao(0, 0);

		// Verifica uma posição acima do Peão branco
		if (getCor() == Cor.WHITE) {
			p.setValores(posicao.getLinha() - 1, posicao.getColuna());
			if (getTabuleiro().posicaoExiste(p) && !getTabuleiro().existePeca(p)) {
				matriz[p.getLinha()][p.getColuna()] = true;
			}
			/*
			 * Verifica se é o primeiro movimento do Peão branco, se a primeira e segunda
			 * posição acima está livre para poder subir duas posições
			 */
			p.setValores(posicao.getLinha() - 2, posicao.getColuna());
			Posicao p2 = new Posicao(posicao.getLinha() - 1, posicao.getColuna());
			if (getTabuleiro().posicaoExiste(p) && !getTabuleiro().existePeca(p) && getTabuleiro().posicaoExiste(p2)
					&& !getTabuleiro().existePeca(p2) && getContadorMovimentos() == 0) {
				matriz[p.getLinha()][p.getColuna()] = true;
			}
			/*
			 * Verifica se há peça adversária para capturar na diagonal esquerda do Peão
			 * branco
			 */
			p.setValores(posicao.getLinha() - 1, posicao.getColuna() - 1);
			if (getTabuleiro().posicaoExiste(p) && existePecaAdversaria(p)) {
				matriz[p.getLinha()][p.getColuna()] = true;
			}
			/*
			 * Verifica se há peça adversária para capturar na diagonal direita do Peão
			 * branco
			 */
			p.setValores(posicao.getLinha() - 1, posicao.getColuna() + 1);
			if (getTabuleiro().posicaoExiste(p) && existePecaAdversaria(p)) {
				matriz[p.getLinha()][p.getColuna()] = true;
			}
		} else {
			// Verifica uma posição abaixo do Peão preto
			p.setValores(posicao.getLinha() + 1, posicao.getColuna());
			if (getTabuleiro().posicaoExiste(p) && !getTabuleiro().existePeca(p)) {
				matriz[p.getLinha()][p.getColuna()] = true;
			}
			/*
			 * Verifica se é o primeiro movimento do Peão preto, se a primeira e segunda
			 * posição abaixo está livre para poder descer duas posições
			 */
			p.setValores(posicao.getLinha() + 2, posicao.getColuna());
			Posicao p2 = new Posicao(posicao.getLinha() + 1, posicao.getColuna());
			if (getTabuleiro().posicaoExiste(p) && !getTabuleiro().existePeca(p) && getTabuleiro().posicaoExiste(p2)
					&& !getTabuleiro().existePeca(p2) && getContadorMovimentos() == 0) {
				matriz[p.getLinha()][p.getColuna()] = true;
			}
			/*
			 * Verifica se há peça adversária para capturar na diagonal esquerda do Peão
			 * preto
			 */
			p.setValores(posicao.getLinha() + 1, posicao.getColuna() - 1);
			if (getTabuleiro().posicaoExiste(p) && existePecaAdversaria(p)) {
				matriz[p.getLinha()][p.getColuna()] = true;
			}
			/*
			 * Verifica se há peça adversária para capturar na diagonal direita do Peão
			 * preto
			 */
			p.setValores(posicao.getLinha() + 1, posicao.getColuna() + 1);
			if (getTabuleiro().posicaoExiste(p) && existePecaAdversaria(p)) {
				matriz[p.getLinha()][p.getColuna()] = true;
			}
		}

		return matriz;
	}
	
	@Override
	public String toString() {
		return "P";
	}

}
