/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.usjt.web.projetointegrado.model;

/**
 *
 * @author Fernando Bonatto
 */
public class Turma {
    public Tema tema;
	
    private int idTurma;
    private String sigla;
    private int semestreLetivo, anoLetivo, idTema;
    
    public Turma() {
        
    }

    public int getIdTurma() {
        return idTurma;
    }

    public void setIdTurma(int idTurma) {
        this.idTurma = idTurma;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }  
    
    public int getSemestreLetivo() {
        return semestreLetivo;
    }

    public void setSemestreLetivo(int semestreLetivo) {
        this.semestreLetivo = semestreLetivo;
    }

    public int getAnoLetivo() {
        return anoLetivo;
    }

    public void setAnoLetivo(int anoLetivo) {
        this.anoLetivo = anoLetivo;
    }

	public int getIdTema() {
		return idTema;
	}

	public void setIdTema(int idTema) {
		this.idTema = idTema;
	} 
}
