package br.usjt.web.projetointegrado.model;

public class Professor extends Usuario {
    public Grupo grupo;
    public Banca banca;
	
    private int idprofessor, administrador;
    private String matricula;
	
    public Professor() {

    }

    public int getIdprofessor() {
        return idprofessor;
    }

    public void setIdprofessor(int idprofessor) {
        this.idprofessor = idprofessor;
    }
        
    public int getAdministrador() {
        return administrador;
    }

    public void setAdministrador(int administrador) {
        this.administrador = administrador;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }
}
