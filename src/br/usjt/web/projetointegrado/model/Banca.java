/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.usjt.web.projetointegrado.model;

import java.sql.Date;

/**
 *
 * @author Fernando Bonatto
 */
public class Banca {
    
    private int id_banca, id_grupo, sala;
    private String dt_banca;
    
    public Banca() {
        
    }

    public int getId_banca() {
		return id_banca;
	}

	public void setId_banca(int id_banca) {
		this.id_banca = id_banca;
	}

	public int getId_grupo() {
		return id_grupo;
	}

	public void setId_grupo(int id_grupo) {
		this.id_grupo = id_grupo;
	}

    public String getDt_banca() {
		return dt_banca;
	}

	public void setDt_banca(String dt_banca) {
		this.dt_banca = dt_banca;
	}

	public int getSala() {
		return sala;
	}

	public void setSala(int sala) {
		this.sala = sala;
	}
}
