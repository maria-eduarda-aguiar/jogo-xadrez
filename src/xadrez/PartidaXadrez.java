package xadrez;

import tabuleiro.Peca;
import tabuleiro.Posicao;
import tabuleiro.Tabuleiro;
import xadrez.pecas.Rei;
import xadrez.pecas.Torre;

public class PartidaXadrez {
	
	// Classe que irá conter as regras do jogo
	private Tabuleiro tabuleiro;
	
	// Construtor da partida de xadrez 
	public PartidaXadrez() {
		tabuleiro = new Tabuleiro(8, 8);
		configInicial();
	}
	
	// Método para retornar uma matriz de peças de xadrez
	public PecaXadrez[][] getPecas(){
		PecaXadrez[][] matriz = new PecaXadrez[tabuleiro.getLinhas()][tabuleiro.getColunas()];
		for (int i = 0; i < tabuleiro.getLinhas(); i++) {
			for (int j = 0; j < tabuleiro.getColunas(); j++) {
				matriz[i][j] = (PecaXadrez) tabuleiro.peca(i, j);
			}
		}
		return matriz;
	}
	
	// Método para executar uma jogada e validar
	public PecaXadrez executarJogadaXadrez(PosicaoXadrez posicaoOrigem, PosicaoXadrez posicaoDestino) {
		Posicao origem = posicaoOrigem.toPosicao();	
		Posicao destino = posicaoDestino.toPosicao();
		validarPosicaoOrigem(origem);
		Peca pecaCapturada = fazerJogada(origem, destino);
		return (PecaXadrez)pecaCapturada;
	}
	
	// Método para fazer uma jogada após a validação e captura a peça caso ela exista
	private Peca fazerJogada(Posicao origem, Posicao destino) {
		Peca p = tabuleiro.removerPeca(origem);
		Peca pecaCapturada = tabuleiro.removerPeca(destino);
		tabuleiro.colocarPeca(p, destino);
		return pecaCapturada;
	}
	
	// Método para validar a posição de origem
	private void validarPosicaoOrigem(Posicao posicao) {
		if (!tabuleiro.existePeca(posicao)) {
			throw new ExcecaoXadrez("Não há peça na posição de origem.");
		}
	}
	
	// Método para colocar uma nova peça já convertendo a posição de matriz padrão para o padrão do xadrez
	private void colocarNovaPeca(char coluna, int linha, PecaXadrez peca) {
		tabuleiro.colocarPeca(peca, new PosicaoXadrez(coluna, linha).toPosicao());
	}
	
	// Método responsável por iniciar a partida de xadrez colocando as peças no tabuleiro
	private void configInicial() {
		colocarNovaPeca('c', 1, new Torre(tabuleiro, Cor.WHITE));
		colocarNovaPeca('c', 2, new Torre(tabuleiro, Cor.WHITE));
		colocarNovaPeca('d', 2, new Torre(tabuleiro, Cor.WHITE));
		colocarNovaPeca('e', 2, new Torre(tabuleiro, Cor.WHITE));
		colocarNovaPeca('e', 1, new Torre(tabuleiro, Cor.WHITE));
		colocarNovaPeca('d', 1, new Rei(tabuleiro, Cor.WHITE));

		colocarNovaPeca('c', 7, new Torre(tabuleiro, Cor.BLACK));
		colocarNovaPeca('c', 8, new Torre(tabuleiro, Cor.BLACK));
		colocarNovaPeca('d', 7, new Torre(tabuleiro, Cor.BLACK));
		colocarNovaPeca('e', 7, new Torre(tabuleiro, Cor.BLACK));
		colocarNovaPeca('e', 8, new Torre(tabuleiro, Cor.BLACK));
		colocarNovaPeca('d', 8, new Rei(tabuleiro, Cor.BLACK));
	}
}
