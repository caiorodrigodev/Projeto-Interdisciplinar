/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.usjt.web.projetointegrado.service;

import br.usjt.web.projetointegrado.dao.AtividadeDAO;
import br.usjt.web.projetointegrado.model.Atividade;

public class AtividadeService {
 
    AtividadeDAO atvDAO;
    
    public AtividadeService() {
        atvDAO = new AtividadeDAO();
    }
    
    public String cadastrarAtividade(String descricao, String dt_inicio,
    		String dt_fim, int idaluno) {
    	String retorno = "";
    	boolean problema = false;
    	
    	if(descricao.equals("")) {
    		descricao = null;
    	} else if(descricao.length() > 100) {
    		retorno += "A descrição digitada ultrapassou o limite de caracteres!";
    		problema = true;
    	}
    	
    	if(dt_inicio.equals("")) {
    		dt_inicio = null;
    	} else if(dt_inicio.length() > 10) {
    		retorno += "A data de início digitada ultrapassou o limite de caracteres!";
    		problema = true;
    	}
    	
    	if(dt_fim.equals("")) {
    		dt_fim = null;
    	} else if(dt_fim.length() > 10) {
    		retorno += "A data de fim digitada ultrapassou o limite de caracteres!";
    		problema = true;
    	}
    	
    	if(problema == false) {
	    	Atividade atv = new Atividade();
	    	atv.setDescricao(descricao);
	    	atv.setDt_inicio(dt_inicio);
	    	atv.setDt_fim(dt_fim);
	    	retorno = atvDAO.cadastrarAtividade(atv, idaluno);
	    	return retorno;
    	} else {
    		return retorno;
    	}
    }
}
