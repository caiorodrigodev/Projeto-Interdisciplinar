package br.usjt.web.projetointegrado.model;

import java.sql.Date;

public class Avaliacao {
    
    private int id_entrega;
    private double nota;
    private Date dt_avaliacao;
    private String comentarios;
    
    public Avaliacao() {
        
    }

    public int getId_entrega() {
        return id_entrega;
    }

    public void setId_entrega(int id_entrega) {
        this.id_entrega = id_entrega;
    }

    public double getNota() {
        return nota;
    }

    public void setNota(double nota) {
        this.nota = nota;
    }

    public Date getDt_avaliacao() {
        return dt_avaliacao;
    }

    public void setDt_avaliacao(Date dt_avaliacao) {
        this.dt_avaliacao = dt_avaliacao;
    }

    public String getComentarios() {
        return comentarios;
    }

    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }
}
