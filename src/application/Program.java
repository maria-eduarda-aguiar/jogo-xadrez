package application;

import java.util.Scanner;
import xadrez.PartidaXadrez;
import xadrez.PecaXadrez;
import xadrez.PosicaoXadrez;

public class Program {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		PartidaXadrez partidaXadrez = new PartidaXadrez();

		while (true) {
			UI.imprimirTabuleiro(partidaXadrez.getPecas());
			System.out.println();
			System.out.print("Digite a posição de origem: ");
			PosicaoXadrez origem = UI.lerPosicaoXadrez(sc);
			
			System.out.println();
			System.out.print("Digite a posição de destino: ");
			PosicaoXadrez destino = UI.lerPosicaoXadrez(sc);
			
			PecaXadrez pecaCapturada = partidaXadrez.executarJogadaXadrez(origem, destino);
		}

	}

}
