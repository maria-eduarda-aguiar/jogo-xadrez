package xadrez.pecas;

import xadrez.PecaXadrez;
import xadrez.Cor;
import xadrez.PartidaXadrez;

import java.io.Serializable;

import tabuleiro.Posicao;
import tabuleiro.Tabuleiro;

public class Peao extends PecaXadrez  implements Serializable {


	private static final long serialVersionUID = 1L;
	private PartidaXadrez partidaXadrez;

	public Peao(Tabuleiro tabuleiro, Cor cor, PartidaXadrez partidaXadrez) {
		super(tabuleiro, cor);
		this.partidaXadrez = partidaXadrez;
	}

	@Override
	public String toString() {
		return "P";
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

			// Jogada especial En Passant Peão branco
			if (posicao.getLinha() == 3) {
				Posicao esquerda = new Posicao(posicao.getLinha(), posicao.getColuna() - 1);
				if (getTabuleiro().posicaoExiste(esquerda) && existePecaAdversaria(esquerda)
						&& getTabuleiro().peca(esquerda) == partidaXadrez.getEnPassantVulneravel()) {
					matriz[esquerda.getLinha() - 1][esquerda.getColuna()] = true;
				}
				Posicao direita = new Posicao(posicao.getLinha(), posicao.getColuna() + 1);
				if (getTabuleiro().posicaoExiste(direita) && existePecaAdversaria(direita)
						&& getTabuleiro().peca(direita) == partidaXadrez.getEnPassantVulneravel()) {
					matriz[direita.getLinha() - 1][direita.getColuna()] = true;
				}

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
			
			// Jogada especial En Passant Peão preto
			if (posicao.getLinha() == 4) {
				Posicao esquerda = new Posicao(posicao.getLinha(), posicao.getColuna() - 1);
				if (getTabuleiro().posicaoExiste(esquerda) && existePecaAdversaria(esquerda)
						&& getTabuleiro().peca(esquerda) == partidaXadrez.getEnPassantVulneravel()) {
					matriz[esquerda.getLinha() + 1][esquerda.getColuna()] = true;
				}
				Posicao direita = new Posicao(posicao.getLinha(), posicao.getColuna() + 1);
				if (getTabuleiro().posicaoExiste(direita) && existePecaAdversaria(direita)
						&& getTabuleiro().peca(direita) == partidaXadrez.getEnPassantVulneravel()) {
					matriz[direita.getLinha() + 1][direita.getColuna()] = true;
				}

			}
		}

		return matriz;
	}
}
