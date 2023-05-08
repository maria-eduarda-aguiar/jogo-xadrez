package xadrez;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import tabuleiro.Peca;
import tabuleiro.Posicao;
import tabuleiro.Tabuleiro;
import xadrez.pecas.Bispo;
import xadrez.pecas.Cavalo;
import xadrez.pecas.Dama;
import xadrez.pecas.Peao;
import xadrez.pecas.Rei;
import xadrez.pecas.Torre;

//Classe que irá conter as regras do jogo
public class PartidaXadrez {

	private int turno;
	private Cor jogadorAtual;
	private Tabuleiro tabuleiro;
	private boolean xeque;
	private boolean xequeMate;

	private List<Peca> pecasNoTabuleiro = new ArrayList<>();
	private List<Peca> pecasCapturadas = new ArrayList<>();

	// Construtor da partida de xadrez
	public PartidaXadrez() {
		tabuleiro = new Tabuleiro(8, 8);
		turno = 1;
		jogadorAtual = Cor.WHITE;
		configInicial();
	}

	// Criação dos getters
	public int getTurno() {
		return turno;
	}

	public Cor getJogadorAtual() {
		return jogadorAtual;
	}

	public boolean getXeque() {
		return xeque;
	}

	public boolean getXequeMate() {
		return xequeMate;
	}

	// Método para retornar uma matriz de peças de xadrez
	public PecaXadrez[][] getPecas() {
		PecaXadrez[][] matriz = new PecaXadrez[tabuleiro.getLinhas()][tabuleiro.getColunas()];
		for (int i = 0; i < tabuleiro.getLinhas(); i++) {
			for (int j = 0; j < tabuleiro.getColunas(); j++) {
				matriz[i][j] = (PecaXadrez) tabuleiro.peca(i, j);
			}
		}
		return matriz;
	}

	// Posições possíveis a partir de uma posição de origem
	public boolean[][] movimentosPossiveis(PosicaoXadrez posicaoOrigem) {
		Posicao posicao = posicaoOrigem.toPosicao();
		validarPosicaoOrigem(posicao);
		return tabuleiro.peca(posicao).movimentosPossiveis();
	}

	/*
	 * Método para executar uma jogada, validar a posição de origem e destino,
	 * verificar se o Rei se encontra em xeque ou xeque-mate
	 */
	public PecaXadrez executarJogadaXadrez(PosicaoXadrez posicaoOrigem, PosicaoXadrez posicaoDestino) {
		Posicao origem = posicaoOrigem.toPosicao();
		Posicao destino = posicaoDestino.toPosicao();
		validarPosicaoOrigem(origem);
		validarPosicaoDestino(origem, destino);
		Peca pecaCapturada = fazerJogada(origem, destino);

		if (verificarXeque(jogadorAtual)) {
			desfazerJogada(origem, destino, pecaCapturada);
			throw new ExcecaoXadrez("Você não pode se colocar em xeque.");
		}

		xeque = (verificarXeque(oponente(jogadorAtual))) ? true : false;

		if (verificarXequeMate(oponente(jogadorAtual))) {
			xequeMate = true;
		} else {
			proximoTurno();
		}

		return (PecaXadrez) pecaCapturada;
	}

	/*
	 * Método para fazer uma jogada após a validação, capturar uma peça caso ela
	 * exista, remover a peça capturada da lista de peças do tabuleiro e adicionar
	 * na lista de peças capturadas
	 */
	private Peca fazerJogada(Posicao origem, Posicao destino) {
		PecaXadrez p = (PecaXadrez) tabuleiro.removerPeca(origem);
		p.incrementarContadorMovimentos();
		Peca pecaCapturada = tabuleiro.removerPeca(destino);
		tabuleiro.colocarPeca(p, destino);

		if (pecaCapturada != null) {
			pecasNoTabuleiro.remove(pecaCapturada);
			pecasCapturadas.add(pecaCapturada);
		}

		return pecaCapturada;
	}

	// Método para desfazer uma jogada (caso o Rei estiver em xeque)
	private void desfazerJogada(Posicao origem, Posicao destino, Peca pecaCapturada) {
		PecaXadrez p = (PecaXadrez) tabuleiro.removerPeca(destino);
		p.decrementarContadorMovimentos();
		tabuleiro.colocarPeca(p, origem);

		if (pecaCapturada != null) {
			tabuleiro.colocarPeca(pecaCapturada, destino);
			pecasCapturadas.remove(pecaCapturada);
			pecasNoTabuleiro.add(pecaCapturada);
		}
	}

	// Método para validar a posição de origem
	private void validarPosicaoOrigem(Posicao posicao) {
		if (!tabuleiro.existePeca(posicao)) {
			throw new ExcecaoXadrez("Não há peça na posição de origem.");
		}
		if (jogadorAtual != ((PecaXadrez) tabuleiro.peca(posicao)).getCor()) {
			throw new ExcecaoXadrez("A peça escolhida não é sua.");
		}
		if (!tabuleiro.peca(posicao).existeMovimentoPossivel()) {
			throw new ExcecaoXadrez("Não existem movimentos possíveis para a peça escolhida.");
		}
	}

	// Método para validar a posição de destino
	private void validarPosicaoDestino(Posicao origem, Posicao destino) {
		if (!tabuleiro.peca(origem).movimentoPossivel(destino)) {
			throw new ExcecaoXadrez("A peça escolhida não pode se mover para a posição de destino.");
		}
	}

	// Método para implementar o sistema de turnos
	private void proximoTurno() {
		turno++;
		jogadorAtual = (jogadorAtual == Cor.WHITE) ? Cor.BLACK : Cor.WHITE;
	}
	
	// Método para identificar o oponente
	private Cor oponente(Cor cor) {
		return (cor == Cor.WHITE) ? Cor.BLACK : Cor.WHITE;
	}
	
	// Método para encontrar o Rei no tabuleiro
	private PecaXadrez rei(Cor cor) {
		List<Peca> lista = pecasNoTabuleiro.stream().filter(peca -> ((PecaXadrez) peca).getCor() == cor)
				.collect(Collectors.toList());
		for (Peca peca : lista) {
			if (peca instanceof Rei) {
				return (PecaXadrez) peca;
			}
		}
		throw new IllegalStateException("Não existe o rei da cor " + cor + "no tabuleiro");
	}
	
	/*
	 * Método para percorrer todas as peças adversárias e em cada peça adversária
	 * testar todos os movimentos possíveis dela para verificar se há movimento
	 * possível que deixe o Rei em xeque
	 */
	private boolean verificarXeque(Cor cor) {
		Posicao posicaoRei = rei(cor).getPosicaoXadrez().toPosicao();
		List<Peca> pecasOponente = pecasNoTabuleiro.stream()
				.filter(peca -> ((PecaXadrez) peca).getCor() == oponente(cor)).collect(Collectors.toList());
		for (Peca peca : pecasOponente) {
			boolean[][] matriz = peca.movimentosPossiveis();
			if (matriz[posicaoRei.getLinha()][posicaoRei.getColuna()]) {
				return true;
			}
		}
		return false;
	}
	
	// Método para verificar se o Rei está em xeque-mate
	private boolean verificarXequeMate(Cor cor) {
		if (!verificarXeque(cor)) {
			return false;
		}
		List<Peca> lista = pecasNoTabuleiro.stream().filter(peca -> ((PecaXadrez) peca).getCor() == cor)
				.collect(Collectors.toList());
		for (Peca peca : lista) {
			boolean[][] matriz = peca.movimentosPossiveis();
			for (int i = 0; i < tabuleiro.getLinhas(); i++) {
				for (int j = 0; j < tabuleiro.getColunas(); j++) {
					if (matriz[i][j]) {
						Posicao origem = ((PecaXadrez) peca).getPosicaoXadrez().toPosicao();
						Posicao destino = new Posicao(i, j);
						Peca pecaCapturada = fazerJogada(origem, destino);
						boolean verificaXeque = verificarXeque(cor);
						desfazerJogada(origem, destino, pecaCapturada);
						if (!verificaXeque) {
							return false;
						}
					}
				}
			}
		}
		return true;
	}

	/*
	 * Método para colocar uma nova peça já convertendo a posição de matriz padrão
	 * para o padrão do xadrez
	 */
	private void colocarNovaPeca(char coluna, int linha, PecaXadrez peca) {
		tabuleiro.colocarPeca(peca, new PosicaoXadrez(coluna, linha).toPosicao());
		pecasNoTabuleiro.add(peca);
	}

	/*
	 * Método responsável por iniciar a partida de xadrez colocando as peças no
	 * tabuleiro
	 */
	private void configInicial() {
		colocarNovaPeca('a', 1, new Torre(tabuleiro, Cor.WHITE));
		colocarNovaPeca('b', 1, new Cavalo(tabuleiro, Cor.WHITE));
		colocarNovaPeca('c', 1, new Bispo(tabuleiro, Cor.WHITE));
		colocarNovaPeca('d', 1, new Dama(tabuleiro, Cor.WHITE));
		colocarNovaPeca('e', 1, new Rei(tabuleiro, Cor.WHITE));
		colocarNovaPeca('f', 1, new Bispo(tabuleiro, Cor.WHITE));
		colocarNovaPeca('g', 1, new Cavalo(tabuleiro, Cor.WHITE));
		colocarNovaPeca('h', 1, new Torre(tabuleiro, Cor.WHITE));
		colocarNovaPeca('a', 2, new Peao(tabuleiro, Cor.WHITE));
		colocarNovaPeca('b', 2, new Peao(tabuleiro, Cor.WHITE));
		colocarNovaPeca('c', 2, new Peao(tabuleiro, Cor.WHITE));
		colocarNovaPeca('d', 2, new Peao(tabuleiro, Cor.WHITE));
		colocarNovaPeca('e', 2, new Peao(tabuleiro, Cor.WHITE));
		colocarNovaPeca('f', 2, new Peao(tabuleiro, Cor.WHITE));
		colocarNovaPeca('g', 2, new Peao(tabuleiro, Cor.WHITE));
		colocarNovaPeca('h', 2, new Peao(tabuleiro, Cor.WHITE));

		colocarNovaPeca('a', 8, new Torre(tabuleiro, Cor.BLACK));
		colocarNovaPeca('b', 8, new Cavalo(tabuleiro, Cor.BLACK));
		colocarNovaPeca('c', 8, new Bispo(tabuleiro, Cor.BLACK));
		colocarNovaPeca('d', 8, new Dama(tabuleiro, Cor.BLACK));
		colocarNovaPeca('e', 8, new Rei(tabuleiro, Cor.BLACK));
		colocarNovaPeca('f', 8, new Bispo(tabuleiro, Cor.BLACK));
		colocarNovaPeca('g', 8, new Cavalo(tabuleiro, Cor.BLACK));
		colocarNovaPeca('h', 8, new Torre(tabuleiro, Cor.BLACK));
		colocarNovaPeca('a', 7, new Peao(tabuleiro, Cor.BLACK));
		colocarNovaPeca('b', 7, new Peao(tabuleiro, Cor.BLACK));
		colocarNovaPeca('c', 7, new Peao(tabuleiro, Cor.BLACK));
		colocarNovaPeca('d', 7, new Peao(tabuleiro, Cor.BLACK));
		colocarNovaPeca('e', 7, new Peao(tabuleiro, Cor.BLACK));
		colocarNovaPeca('f', 7, new Peao(tabuleiro, Cor.BLACK));
		colocarNovaPeca('g', 7, new Peao(tabuleiro, Cor.BLACK));
		colocarNovaPeca('h', 7, new Peao(tabuleiro, Cor.BLACK));
	}
}
