package jogadaSocket;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import application.UI;
import xadrez.Cor;
import xadrez.ExcecaoXadrez;
import xadrez.PartidaXadrez;
import xadrez.PecaXadrez;
import xadrez.PosicaoXadrez;

public class Client {
	private final String HOST = "localhost";
	private final int PORT = 4000;
	// private final String HOST = "localhost";
	// private final int PORT = 4000;
	// private final String EXIT_MSG = "exit";
	private ObjectOutputStream streamOut;
	private Scanner in;
	private Cor cor;
	private Thread thread;

	private Socket socket;

	public Client() throws UnknownHostException, IOException, ClassNotFoundException {
		this.in = new Scanner(System.in);
		this.cor = askCor();
		this.start();
	} // fim do construtor()

	public Cor getCor() {
		return this.cor;
	}

	public static void main(String[] args) throws UnknownHostException, IOException, ClassNotFoundException {
		new Client();
	} // fim do método main(String[])

	private void start() throws UnknownHostException, IOException, ClassNotFoundException {
		connect();
		run();
		disconnect();

	} // fim do método start()

	private void connect() {
		System.out.println("Conectando ao servidor...");
		try {
			this.socket = new Socket(HOST, PORT);
			System.out.println("Cliente conectado");

			this.streamOut = new ObjectOutputStream(socket.getOutputStream());
		
			
            ClientThread clientThread = new ClientThread(socket);
            this.thread = new Thread(clientThread);
            this.thread.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	} // fim do método connect()

	private void run() {
		try {

	
			Scanner sc = new Scanner(System.in);


			PartidaXadrez partidaXadrez = new PartidaXadrez();
			List<PecaXadrez> capturadas = new ArrayList<>();
			
			
			do {
				try {

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
						capturadas.add(pecaCapturada);
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
					JogadaPacket newJogadaPocket = new JogadaPacket(partidaXadrez, capturadas);

					streamOut.writeObject(newJogadaPocket);

				} catch (ExcecaoXadrez e) {
					System.out.println(e.getMessage());
					sc.nextLine();
				} catch (InputMismatchException e) {
					System.out.println(e.getMessage());
					sc.nextLine();
				}
				
			}

			while (!partidaXadrez.getXequeMate());

		
			

		} catch (Exception e) {
			e.printStackTrace();
		}
	} // fim do método run()

	private void disconnect() {
		try {
			this.thread.interrupt();
			this.socket.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	} // fim do método disconnect()

	private Cor askCor() {
		boolean valid = true;
		int option;
		Cor cor = Cor.WHITE;

		do {
			System.out.println("Informe sua cor: ");
			System.out.println("1 - BRANCA");
			System.out.println("2 - PRETA");
			option = Integer.parseInt(in.nextLine());
			if (option == 1) {
				cor = Cor.WHITE;
			} else if (option == 2) {
				cor = Cor.BLACK;
			}

			valid = (option == 1 || option == 2);
			if (!valid) {
				System.out.println("Opção inválida!");
			}
		} while (!valid);

		return cor;
	} // fim do método askName()

} // fim da classe Client