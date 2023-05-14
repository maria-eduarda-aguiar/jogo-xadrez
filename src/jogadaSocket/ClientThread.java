package jogadaSocket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import application.UI;
import xadrez.ExcecaoXadrez;
import xadrez.PartidaXadrez;
import xadrez.PecaXadrez;
import xadrez.PosicaoXadrez;

public class ClientThread implements Runnable {
	private Socket socket;
	private JogadaPacket jogadaPacket;

	public ClientThread(Socket socket) throws IOException {
		this.socket = socket;
	} // fim do construtor(Socket)

	@Override
	public void run() {
		try {

			while (true) {
			
				if(this.jogadaPacket == null) {
					PartidaXadrez partidaXadrez = new PartidaXadrez();
					List<PecaXadrez> capturadas = new ArrayList<>();
					
					this.jogadaPacket = new JogadaPacket(partidaXadrez, capturadas);
				}else {
					ObjectInputStream inputReader = new ObjectInputStream(socket.getInputStream());
					this.jogadaPacket = (JogadaPacket) inputReader.readObject();
				}

				
	

				UI.limparTela();
				UI.imprimirPartida(jogadaPacket.getPartidaXadrez(), jogadaPacket.getCapturadas());
				

			

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	} // fim do método run()

} // fim da classe ClientThread