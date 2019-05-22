package br.usjt.web.projetointegrado.model;

import java.util.ArrayList;

public class Turma_aluno {

	private ArrayList<Aluno> alunos = new ArrayList<Aluno>();
	private ArrayList<Turma> turmas = new ArrayList<Turma>();
	private ArrayList<Grupo> grupos = new ArrayList<Grupo>();
	
	public Turma_aluno() {
		
	}

	public ArrayList<Aluno> getAlunos() {
		return alunos;
	}

	public void setAlunos(ArrayList<Aluno> alunos) {
		this.alunos = alunos;
	}

	public ArrayList<Turma> getTurmas() {
		return turmas;
	}

	public void setTurmas(ArrayList<Turma> turmas) {
		this.turmas = turmas;
	}

	public ArrayList<Grupo> getGrupos() {
		return grupos;
	}

	public void setGrupos(ArrayList<Grupo> grupos) {
		this.grupos = grupos;
	}
	
}
