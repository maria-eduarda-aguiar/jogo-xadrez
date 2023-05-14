package tabuleiro;

import java.io.Serializable;

public class Posicao implements Serializable {
	

	private static final long serialVersionUID = 1L;
	// Implementação  do encapsulamento
	private int linha;
	private int coluna;
	
	// Criação do construtor
	public Posicao(int linha, int coluna) {
		this.linha = linha;
		this.coluna = coluna;
	}
	
	// Criação dos getters e setters
	public int getLinha() {
		return linha;
	}

	public void setLinha(int linha) {
		this.linha = linha;
	}

	public int getColuna() {
		return coluna;
	}

	public void setColuna(int coluna) {
		this.coluna = coluna;
	}
	
	// Permite atualizar os valores de uma posição
	public void setValores(int linha, int coluna) {
		this.linha = linha;
		this.coluna = coluna;
	}
	
	@Override
	public String toString() {
		return linha + ", " + coluna;
	}
	
}
