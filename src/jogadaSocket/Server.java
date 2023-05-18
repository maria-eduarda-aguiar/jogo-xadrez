package jogadaSocket;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import xadrez.PartidaXadrez;
import xadrez.PecaXadrez;

public class Server {
	private final int PORT = 5000;
	private List<ServerThread> threads = new ArrayList<>();
	private JogadaPacket jogadaPacket;
	private int players = 0;

	public static void main(String[] args) {
		new Server().start();
	} // fim do método main(String[])

	private void start() {
		System.out.println("Iniciando o servidor...");
		try (ServerSocket serverSocket = new ServerSocket(PORT)) {
			System.out.println("Servidor iniciado");
			listen(serverSocket);
		} catch (Exception e) {
			e.printStackTrace();
		}
	} // fim do método start()

	private void listen(ServerSocket serverSocket) throws IOException, ClassNotFoundException {

		PartidaXadrez partidaXadrez = new PartidaXadrez();
		List<PecaXadrez> capturadas = new ArrayList<>();

		jogadaPacket = new JogadaPacket(partidaXadrez, capturadas);

		while (players < 2) {
			System.out.println("Aguardando conexão...");
			Socket socket = serverSocket.accept();
			System.out.println("Conexão aceita");

			ServerThread serverThread = new ServerThread(socket, this);
			threads.add(serverThread);
			new Thread(serverThread).start();
			players++;

		}

	} // fim do método listen(ServerSocket)

	public void broadcast(JogadaPacket jogadaPacket) throws IOException {
		for (ServerThread thread : threads) {
			thread.send(jogadaPacket);
		}
	} // fim do método broadcast(JogadaPacket)

} // fim da classe Server
