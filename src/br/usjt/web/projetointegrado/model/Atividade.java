package br.usjt.web.projetointegrado.model;

import java.sql.Date;

public class Atividade {

    private int idatividade, idtema;
    private String descricao, dt_inicio, dt_fim;

    public Atividade() {

    }

    public int getIdatividade() {
		return idatividade;
	}

	public void setIdatividade(int idatividade) {
		this.idatividade = idatividade;
	}

	public int getIdtema() {
		return idtema;
	}

	public void setIdtema(int idtema) {
		this.idtema = idtema;
	}

	public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

	public String getDt_inicio() {
		return dt_inicio;
	}

	public void setDt_inicio(String dt_inicio) {
		this.dt_inicio = dt_inicio;
	}

	public String getDt_fim() {
		return dt_fim;
	}

	public void setDt_fim(String dt_fim) {
		this.dt_fim = dt_fim;
	}
}
