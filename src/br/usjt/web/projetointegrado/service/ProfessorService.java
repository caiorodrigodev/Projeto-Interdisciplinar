package br.usjt.web.projetointegrado.service;

import br.usjt.web.projetointegrado.dao.ProfessorDAO;
import br.usjt.web.projetointegrado.model.Professor;

public class ProfessorService {
    
    ProfessorDAO profDAO;
    
    public ProfessorService() {
    	profDAO = new ProfessorDAO();
    }
    
    public String cadastrarProfessor(String nome, String sobrenome, String email,
    		String fone, String sexo, String dt_nascimento, String senha,
    		String confirmarSenha, int administrador, String matricula) {
    	String retorno = "";
    	
    	if(!nome.equals("") && !sobrenome.equals("") && !email.equals("")
    			&& !fone.equals("") && !sexo.equals("") && !dt_nascimento.equals("")
    			&& !senha.equals("") && !confirmarSenha.equals("")) {
    		boolean problema = false;
			
			String nomeMaiusculo = nome.substring(0, 1).toUpperCase();
			String sobrenomeMaiusculo = sobrenome.substring(0, 1).toUpperCase();
			String nomeCompleto = (nomeMaiusculo + nome.substring(1)) + " "
					+ (sobrenomeMaiusculo + sobrenome.substring(1));
			if(nomeCompleto.length() > 50) {
				retorno += "O nome digitado ultrapassou o limite de caracteres!";
				problema = true;
			}
			if(email.length() > 30) {
				retorno += "O e-mail digitado ultrapassou o limite de caracteres!";
				problema = true;
			}
			
			if(fone.length() > 15) {
				retorno += "O número digitado ultrapassou o limite de caracteres!";
				problema = true;
			}
			
			if(dt_nascimento.length() > 10) {
				retorno += "Data de nascimento digitada ultrapassou o limite de caracteres!";
				problema = true;
			}
			if(senha.length() > 50 || confirmarSenha.length() > 50) {
				retorno += "A senha digitada ultrapassou o limite de caracteres!";
				problema = true;
			}
			if(!senha.equals(confirmarSenha)) {
				retorno += "Senhas digitadas são diferentes!";
				problema = true;
			} 
    		
			if(problema == false) {
		    	Professor professor = new Professor();
				professor.setNome(nomeCompleto);
				professor.setEmail(email);
				professor.setFone(fone);
				professor.setSexo(sexo);
				professor.setDt_nascimento(dt_nascimento);
				professor.setSenha(senha);
				professor.setAdministrador(administrador);
				professor.setMatricula(matricula);
				retorno = profDAO.cadastrarProfessor(professor);
				return retorno;
			} else {
				return retorno;
			}
    	} else {
    		retorno = "Preencha todos os campos!";
    		return retorno;
    	}	
    }
    
    public String deletarProfessor(int idprofessor) {
    	String retorno = "";
    	
    	retorno = profDAO.deletarProfessor(idprofessor);
    	return retorno;
    }
    
    public Professor consultarProfessor(String matricula) {
    	return profDAO.consultarProfessor(matricula);
    }
}
