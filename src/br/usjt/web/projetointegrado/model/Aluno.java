package br.usjt.web.projetointegrado.model;

public class Aluno extends Usuario {
    public Turma turma;
    public Tema tema;
    public Grupo grupo;
    public Professor professor;
    
    private int idaluno;
    private String ra;
    
    public Aluno() {
        
    }

	public int getIdaluno() {
        return idaluno;
    }

    public void setIdaluno(int idaluno) {
        this.idaluno = idaluno;
    }
    
    public String getRa() {
        return ra;
    }
    
    public void setRa(String ra) {
        this.ra = ra;
    }
}
