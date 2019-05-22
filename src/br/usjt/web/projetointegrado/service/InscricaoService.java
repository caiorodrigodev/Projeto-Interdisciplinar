package br.usjt.web.projetointegrado.service;

import java.util.ArrayList;

import br.usjt.web.projetointegrado.dao.InscricaoDAO;
import br.usjt.web.projetointegrado.model.Inscricao;

public class InscricaoService {

	InscricaoDAO inscDAO;
	
	public InscricaoService() {
		this.inscDAO = new InscricaoDAO();
	}
	
	// {} MÉTODOS USADOS PELA PRÓPRIA INSCRIÇÃO {}
	
	public String cadastrarInscricao(String nome, String sobrenome, String email,
			String fone, String dt_nascimento, String senha, String confirmarSenha,
			String sexo) {
		String retorno = "";
		
		if(!nome.equals("") && !sobrenome.equals("") && !email.equals("")
				&& !fone.equals("") && !dt_nascimento.equals("") && !senha.equals("")
				&& !confirmarSenha.equals("") && sexo != null) {
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
				Inscricao insc = new Inscricao();
				insc.setNome(nomeCompleto);
				insc.setEmail(email);
				insc.setFone(fone);
				insc.setDt_nascimento(dt_nascimento);
				insc.setSenha(senha);
				insc.setSexo(sexo);
				retorno = inscDAO.cadastrarInscricao(insc);
				return retorno;
			} else {
				return retorno;
			}
		} else {
			retorno = "Preencha todos os campos!";
			return retorno;
		}
	}
	
	public ArrayList<Inscricao> listarInscricoes() {
		return inscDAO.listarInscricoes();
	}

	// {} MÉTODOS USADOS JUNTAMENTE COM O CADASTRO ALUNO {}
	
	public Inscricao consultarInscricao(int idinscricao) {
		return inscDAO.consultarInscricao(idinscricao);
	}
	
	public String excluirInscricao(int idinscricao) {
		return inscDAO.excluirInscricao(idinscricao);
	}
}
