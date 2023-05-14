package jogadaSocket;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import xadrez.PartidaXadrez;
import xadrez.PecaXadrez;

public class JogadaPacket implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private PartidaXadrez partidaXadrez;
	private List<PecaXadrez> capturadas = new ArrayList<>();
	
	
	
	public JogadaPacket(PartidaXadrez partidaXadrez,List<PecaXadrez> capturadas) {
		this.partidaXadrez = partidaXadrez;
		this.capturadas = capturadas;
	}
	
	public PartidaXadrez getPartidaXadrez() {
		return partidaXadrez;
	}
	
	public List<PecaXadrez> getCapturadas(){
		return this.capturadas;
	}
	
	@Override
	public String toString() {
		return "OK";
	}

}
