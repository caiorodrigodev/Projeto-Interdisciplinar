package br.usjt.web.projetointegrado.service;

import java.util.ArrayList;

import br.usjt.web.projetointegrado.dao.TemaDAO;
import br.usjt.web.projetointegrado.model.Tema;

public class TemaService {
    
    TemaDAO temaDAO;
    
    public TemaService() {      
    	temaDAO = new TemaDAO();
    }
    
    // [] MÉTODOS FEITOS PARA O PRÓPRIO TEMA []
    
    // [BEGIN] --
    public String cadastrarTema(String titulo, String introducao,
    		String requisitos) {
    	String retorno = "";
    	
    	if(!titulo.equals("") && !introducao.equals("") 
    			&& !requisitos.equals("")) {
	    	boolean tituloMenor = false;
	        if(titulo.length() > 100) {
	        	tituloMenor = true;
	        	retorno += "Título ultrapassou o limite"
						+ " de 100 caracteres!";
	        }
	        
	        boolean introducaoMenor = false;
	        if(introducao.length() > 250) {
	        	introducaoMenor = true;
	        	retorno += "Introdução ultrapassou o limite"
	    				+ " de 250 caracteres!";
	        }
	        
	        boolean requisitosMenor = false;
	        if(requisitos.length() > 1000) {
	        	requisitosMenor = true;
	        	retorno += "Requisito ultrapassou o limite"
	        			+ " de 1000 caracteres!";
	        }
	        
	        if(tituloMenor == false && introducaoMenor == false && requisitosMenor == false) {
		        Tema tema = new Tema();
				tema.setTitulo(titulo);
				tema.setIntroducao(introducao);
				tema.setRequisitos(requisitos);
				retorno = temaDAO.cadastrarTema(tema);
				return retorno;
	        } else {
	        	return retorno;
	        }
    	} else {
    		retorno = "Preencha todos os campos!";
    		return retorno;
    	}
    }
    // -- [END]
    
    // [BEGIN] --
    public String deletarTema(int idtema) {
    	String retorno = "";

    	retorno = temaDAO.deletarTema(idtema);
    	return retorno;
    }
    // -- [END]
    
    // [BEGIN] --
    public Tema consultarTema(int idtema) {
    	return temaDAO.consultarTema(idtema);
    }
    // -- [END]
    
    // [BEGIN] --
    public String alterarTema(String requisitos, int idtema) {
    	String retorno = "";

    	if(!requisitos.equals("")) {
			if(requisitos.length() <= 1000) {
				retorno = temaDAO.alterarTema(requisitos, idtema);
				return retorno;
			} else {
				retorno = "Requisitos ultrapassou o limite"
						+ " de 1000 caracteres!";
				return retorno;
			}
    	} else {
    		retorno = "Preencha todos os campos!";
    		return retorno;
    	}
    }
    // -- [END]
    
    // [BEGIN] --
    public ArrayList<Tema> listarTemas() {
    	return temaDAO.listarTemas();
    }
    // -- [END]
    
    public String tituloTema(int idaluno) {
    	return temaDAO.tituloTema(idaluno);
    }
}
