package jogadaSocket;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ServerThread implements Runnable {
    private Server server;
    private ObjectInputStream in;
    private ObjectOutputStream out;

    public ServerThread(Socket socket, Server server) throws IOException {
        this.server = server;
        this.in = new ObjectInputStream(socket.getInputStream());
        this.out = new ObjectOutputStream(socket.getOutputStream());
    } // fim do construtor(Socket, Server)

    @Override
    public void run() {
        try {
            while (true) {
                JogadaPacket jogadaPacket = (JogadaPacket) this.in.readObject();
                
                if (jogadaPacket.getPartidaXadrez().getXequeMate()) {
                    break;
                }
                this.server.broadcast(jogadaPacket);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    } // fim do método run()

    public void send(JogadaPacket jogadaPacket) throws IOException {
        this.out.writeObject(jogadaPacket);
    } // fim do método send(MessagePacket)

} // fim da classe ServerThread