package br.usjt.web.projetointegrado.service;

import br.usjt.web.projetointegrado.model.Aluno;
import br.usjt.web.projetointegrado.model.Inscricao;

import java.util.ArrayList;

import br.usjt.web.projetointegrado.dao.AlunoDAO;

public class AlunoService {

    AlunoDAO alunoDAO;

    public AlunoService() {
    	alunoDAO = new AlunoDAO();
    }

    public String cadastrarAluno(int idinscricao) {
    	String retorno = "";
    	int idturma = 0;
    	
    	TurmaService turmaService = new TurmaService();
    	ArrayList<Integer> quantidadeAlunos = turmaService.vagaDisponivel();
    	
    	if(quantidadeAlunos.size() > 0) {
    		for(int i = 1; i < quantidadeAlunos.size(); i += 2) {
				if(quantidadeAlunos.get(i) < 40) {
					idturma = quantidadeAlunos.get(i - 1);
					break;
				} else {
					retorno = "Todas as turmas estão cheias.";
				}
			}
    	} else {
    		retorno = "Não temos nenhuma turma no primeiro semestre!";
    		return retorno;
    	}
    	
    	if(!retorno.equals("Não temos nenhuma turma no primeiro semestre!")) {
    		InscricaoService inscricaoService = new InscricaoService();
    		Inscricao inscrito = inscricaoService.consultarInscricao(idinscricao);
    		Aluno aluno = new Aluno();
    		aluno.setNome(inscrito.getNome());
    		aluno.setEmail(inscrito.getEmail());
    		aluno.setFone(inscrito.getFone());
    		aluno.setSexo(inscrito.getSexo());
    		aluno.setDt_nascimento(inscrito.getDt_nascimento());
    		aluno.setSenha(inscrito.getSenha());
    		retorno = alunoDAO.cadastrarAluno(aluno, idturma);
    		if(retorno.equals("Aluno cadastrado com sucesso!")) {
    			inscricaoService.excluirInscricao(idinscricao);
    		}
    		return retorno;
    	} else {
    		return retorno;
    	}
    }
    
    public String deletarAluno(int idaluno) {
    	String retorno = "";
    	
    	retorno = alunoDAO.deletarAluno(idaluno);
    	return retorno;
    }
        
    public Aluno consultarAluno(String ra) {
        return alunoDAO.consultarAluno(ra);
    }
    
    public String alterarDadosAluno(String nomeAtual, String nome, String emailAtual,
    		String email, String telefoneAtual, String telefone, String dt_nascAtual,
    		String dt_nasc, String senhaAtual, String senha, String ra) {
    	String retorno = "";
     	
    	if(!nome.equals("") && !email.equals("") && !telefone.equals("")
    			&& !dt_nasc.equals("") && !senha.equals("")) {
    		retorno = alunoDAO.alterarDadosAluno(nome, emailAtual, email, telefoneAtual,
    				telefone, dt_nasc, senha, ra);
    		return retorno;
    	} else {
    		retorno = "Preencha todos os campos!";
    		return retorno;
    	}
    }
}
