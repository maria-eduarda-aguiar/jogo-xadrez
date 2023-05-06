package tabuleiro;

public class Tabuleiro {
	
	// Implementação do encapsulamento
	private int linhas;
	private int colunas;
	private Peca[][] pecas;
	
	// Criação do construtor
	public Tabuleiro(int linhas, int colunas) {
		this.linhas = linhas;
		this.colunas = colunas;
		pecas = new Peca[linhas][colunas];
	}
	
	// Criação dos getters e setters
	public int getLinhas() {
		return linhas;
	}

	public void setLinhas(int linhas) {
		this.linhas = linhas;
	}

	public int getColunas() {
		return colunas;
	}

	public void setColunas(int colunas) {
		this.colunas = colunas;
	}
	
}
