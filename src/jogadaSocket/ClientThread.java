package jogadaSocket;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import application.UI;
import xadrez.PartidaXadrez;
import xadrez.PecaXadrez;

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

				if (this.jogadaPacket == null) {
					PartidaXadrez partidaXadrez = new PartidaXadrez();
					List<PecaXadrez> capturadas = new ArrayList<>();

					this.jogadaPacket = new JogadaPacket(partidaXadrez, capturadas);
				} else {
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