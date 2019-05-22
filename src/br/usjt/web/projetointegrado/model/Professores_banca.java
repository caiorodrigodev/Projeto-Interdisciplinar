package br.usjt.web.projetointegrado.model;

public class Professores_banca {

	private double avaliacao;
	private int id_banca, id_professor;
	
	public Professores_banca() {
		
	}

	public double getAvaliacao() {
		return avaliacao;
	}

	public void setAvaliacao(double avaliacao) {
		this.avaliacao = avaliacao;
	}

	public int getId_banca() {
		return id_banca;
	}

	public void setId_banca(int id_banca) {
		this.id_banca = id_banca;
	}

	public int getId_professor() {
		return id_professor;
	}

	public void setId_professor(int id_professor) {
		this.id_professor = id_professor;
	}
}
