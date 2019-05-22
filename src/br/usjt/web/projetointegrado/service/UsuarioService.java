package br.usjt.web.projetointegrado.service;

import java.util.ArrayList;

import br.usjt.web.projetointegrado.dao.UsuarioDAO;
import br.usjt.web.projetointegrado.model.Usuario;

public class UsuarioService {

	UsuarioDAO userDAO;
	
	public UsuarioService() {
		userDAO = new UsuarioDAO();
	}
	
	public Usuario login(String ra, String senha) {
		return userDAO.login(ra, senha);
	}
	
    public String alterarSenha(String senhaAtual, String senha,
    		String confirmarSenha, int idusuario) {
    	String retorno = "";
    	
    	if(senha.length() <= 50 && confirmarSenha.length() <=50) {
	    	if(!senha.equals("") && !confirmarSenha.equals("")) {
		    	if(senha.equals(confirmarSenha)) {
		    		if(!senha.equals(senhaAtual)) {
		    			userDAO.alterarSenha(senha, idusuario);
		    			retorno = "Senha alterada com sucesso!";
		    			return retorno;
		    		} else {
		    			retorno = "A nova senha não pode ser igual a atual!";
		    			return retorno;
		    		}
		    	} else {
		    		retorno = "Senha digitadas são diferentes!";
		    		return retorno;
		    	}
	    	} else {
	    		retorno = "Preencha todos os campos!";
	    		return retorno;
	    	}
    	} else {
    		retorno = "A senha ultrapassou o limite de caracteres!";
    		return retorno;
    	}
    }
    
    public ArrayList<String> alterarDados(String emailAtual, String email,
    		String foneAtual, String fone, int idusuario) {
    	ArrayList<String> alteracoes = new ArrayList<String>();

		if(!email.equals(emailAtual)) {
    		userDAO.alterarEmail(email, idusuario);
    		alteracoes.add("email");
    	}
    	if(!fone.equals(foneAtual)) {
    		userDAO.alterarFone(fone, idusuario);
    		alteracoes.add("fone");
    	}
    	return alteracoes;
    }
}
