package br.usjt.web.projetointegrado.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import br.usjt.web.projetointegrado.dao.TurmaDAO;
import br.usjt.web.projetointegrado.model.Aluno;
import br.usjt.web.projetointegrado.model.Turma;

public class TurmaService {
    
    TurmaDAO turmaDAO;
    
    public TurmaService() {
    	turmaDAO = new TurmaDAO();
    }
    
    // [] MÉTODOS FEITOS PARA PRÓPRIA TURMA []
    
    // [BEGIN] --
    public String cadastrarTurma(String sigla, int semestre_letivo,
    		int ano_letivo, int idtema) {
    	String retorno = "";
    	boolean siglaMenor = false;
    	
    	if(sigla.length() > 10) {
    		siglaMenor = true;
    		retorno += "Sigla passou o limite de 10"
    				+ " caracteres!";
    	}
    	
    	boolean semestreInvalido = false;
    	
    	if(semestre_letivo > 2) {
    		semestreInvalido = true;
    		retorno += "O ano é composto por apenas 2 semestres!";
    	}
    	
    	boolean anoInvalido = false;
    	SimpleDateFormat formatDate = new SimpleDateFormat("dd/MM/yyyy");
    	Date date = new Date();
    	String data = formatDate.format(date);
    	int ano = Integer.parseInt(data.substring(6, 10));
    	
    	if(ano > ano_letivo) {
    		anoInvalido = true;
    		retorno += "Uma nova turma não pode ser cadastrada antes do ano atual!";
    	}
    	
    	if(siglaMenor == false && semestreInvalido == false && anoInvalido == false) {
	    	Turma turma = new Turma();
	    	turma.setSigla(sigla);
	    	turma.setSemestreLetivo(semestre_letivo);
	    	turma.setAnoLetivo(ano_letivo);
	    	turma.setIdTema(idtema);
	    	retorno = turmaDAO.cadastrarTurma(turma);
	    	return retorno;
    	} else {
    		return retorno;
    	}
    }
    // -- [END]
    
    // [BEGIN] --
    public String deletarTurma(int idturma) {
    	String retorno = "";
    	
    	retorno = turmaDAO.deletarTurma(idturma);
		return retorno;
    }
    // -- [END]
    
    // [BEGIN] --
    public Turma consultarTurma(int idturma) {
    	return turmaDAO.consultarTurma(idturma);
    }
    public ArrayList<Aluno> alunosDaTurma(int idturma) {
    	return turmaDAO.alunosDaTurma(idturma);
    }
    // -- [END]
    
    // [BEGIN] --
    public String alterarTema(int idtema, int idturma) {
    	String retorno = "";
    	
    	retorno = turmaDAO.alterarTurma(idtema, idturma);
    	return retorno;
    }
    // -- [END]
    
    // [] MÉTODO UTILIZADO POR CADASTRAR ALUNO []
    
    // [BEGIN] --
    public ArrayList<Integer> vagaDisponivel() {
    	return turmaDAO.vagaDisponivel();
    }
    // -- [END]
}
