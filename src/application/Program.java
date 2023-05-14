package application;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import xadrez.ExcecaoXadrez;
import xadrez.PartidaXadrez;
import xadrez.PecaXadrez;
import xadrez.PosicaoXadrez;

public class Program {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		PartidaXadrez partidaXadrez = new PartidaXadrez();
		List<PecaXadrez> capturada = new ArrayList<>();

		while (!partidaXadrez.getXequeMate()) {
			try {
				UI.limparTela();
				UI.imprimirPartida(partidaXadrez, capturada);
				System.out.println();
				System.out.print("Digite a posição de origem: ");
				PosicaoXadrez origem = UI.lerPosicaoXadrez(sc);

				boolean[][] movimentosPossiveis = partidaXadrez.movimentosPossiveis(origem);
				UI.limparTela();
				UI.imprimirTabuleiro(partidaXadrez.getPecas(), movimentosPossiveis);
				System.out.println();
				System.out.print("Digite a posição de destino: ");
				PosicaoXadrez destino = UI.lerPosicaoXadrez(sc);

				PecaXadrez pecaCapturada = partidaXadrez.executarJogadaXadrez(origem, destino);

				if (pecaCapturada != null) {
					capturada.add(pecaCapturada);
				}

				if (partidaXadrez.getPromovido() != null) {
					System.out.print(
							"\nEscolha uma peça abaixo para promoção \n(D - Dama / B - Bispo / T - Torre / C - Cavalo: ");
					String tipoPeca = sc.nextLine().toUpperCase();
					while (!tipoPeca.equals("B") && !tipoPeca.equals("C") && !tipoPeca.equals("B")
							&& !tipoPeca.equals("T") && !tipoPeca.equals("D")) {
						System.out.print(
								"\nValor inválido! Escolha uma peça novamente para promoção\n(D - Dama / B - Bispo / T - Torre / C - Cavalo: ");
						tipoPeca = sc.nextLine().toUpperCase();
					}
					partidaXadrez.substituirPecaPromovida(tipoPeca);
				}
			} catch (ExcecaoXadrez e) {
				System.out.println(e.getMessage());
				sc.nextLine();
			} catch (InputMismatchException e) {
				System.out.println(e.getMessage());
				sc.nextLine();
			}
		}
		UI.limparTela();
		UI.imprimirPartida(partidaXadrez, capturada);
	}
}