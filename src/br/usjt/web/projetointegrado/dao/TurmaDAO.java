package br.usjt.web.projetointegrado.dao;

import br.usjt.web.projetointegrado.model.Aluno;
import br.usjt.web.projetointegrado.model.Tema;
import br.usjt.web.projetointegrado.model.Turma;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class TurmaDAO {
    
    Connection conexao;
    
    public TurmaDAO() {       
        try {
            this.conexao = ConnectionFactory.conectar();
        } catch (SQLException connectionfail) {
            connectionfail.printStackTrace();
        }
    }
    
    // [] TUDO RELACIONADO A ADMINISTRADORES []
    
    // [BEGIN] --
    public String cadastrarTurma(Turma turma) {        
        String retorno = "";
        boolean siglaUso = false;
        
        String siglaUtilizado = "SELECT sigla FROM turma"
        		+ " WHERE sigla = ?";
        try(PreparedStatement preparedSiglaUtilizado =
        		this.conexao.prepareStatement(siglaUtilizado);) {
        	preparedSiglaUtilizado.setString(1, turma.getSigla());
        	ResultSet resultadoSiglaUtilizado = preparedSiglaUtilizado.executeQuery();
        	if(resultadoSiglaUtilizado.next()) {
        		siglaUso = true;
        		retorno = "Sigla já está em uso!";
        		return retorno;
        	}
        } catch(SQLException searchfailure) {
        	searchfailure.printStackTrace();
        }
        
        if(siglaUso == false) {
	        String cadastrarTurma = "INSERT INTO turma VALUES (null, ?, ?, ?, ?)";
	               
	        try (PreparedStatement preparedCadastrarTurma =
	                this.conexao.prepareStatement(cadastrarTurma);) {	            
	        	preparedCadastrarTurma.setString(1, turma.getSigla());
	        	preparedCadastrarTurma.setInt(2, turma.getSemestreLetivo());
	        	preparedCadastrarTurma.setInt(3, turma.getAnoLetivo());
	        	preparedCadastrarTurma.setInt(4, turma.getIdTema());
	        	preparedCadastrarTurma.execute();
	        	retorno = "Turma cadastrada com sucesso!";
	        	return retorno;
	        } catch (SQLException registerfail) {	            
	        	registerfail.printStackTrace();
	        }
        }
        return null;
    }
    // -- [END]
    
    // [BEGIN] --
    public String deletarTurma(int idturma) {
    	String retorno = "";
    	    	
    	String alterarTurmaAluno = "UPDATE turma_aluno"
    			+ " SET turma_idturma = null, grupo_idgrupo = null"
    			+ " WHERE turma_idturma = ?";
    	try(PreparedStatement preparedAlterarTurmaAluno =
    			this.conexao.prepareStatement(alterarTurmaAluno);) {
    		preparedAlterarTurmaAluno.setInt(1, idturma);
    		preparedAlterarTurmaAluno.execute();
    	} catch(SQLException deletefailure) {
    		deletefailure.printStackTrace();
    	}
    	
    	String deletarTurma = "DELETE FROM turma WHERE idturma= ?";
    	try(PreparedStatement preparedDeletarTurma =
    			this.conexao.prepareStatement(deletarTurma);) {
    		preparedDeletarTurma.setInt(1, idturma);
    		preparedDeletarTurma.execute();
    		retorno = "Turma deletada com sucesso!";
    		return retorno;
    	} catch(SQLException deletefailure) {
    		deletefailure.printStackTrace();
    	}
    	return null;
    }
    // -- [END]   
    
    // [BEGIN] --
    public Turma consultarTurma(int idturma) {
    	boolean consultaGeral = true;
    	
    	String consultarTurmaGeral = "SELECT idturma, sigla, semestre_letivo,"
    			+ " ano_letivo, titulo FROM turma"
    			+ " JOIN tema ON turma.tema_idtema = tema.idtema"
    			+ " WHERE idturma = ?";
    	try(PreparedStatement preparedConsultarTurmaGeral = 
    			this.conexao.prepareStatement(consultarTurmaGeral);) {
    		preparedConsultarTurmaGeral.setInt(1, idturma);
    		ResultSet resultadoConsultarTurmaGeral = preparedConsultarTurmaGeral.executeQuery();
    		if(resultadoConsultarTurmaGeral.next()) {
    			Turma turma = new Turma();
    			Tema tema = new Tema();
    			turma.setIdTurma(resultadoConsultarTurmaGeral.getInt("idturma"));
    			turma.setSigla(resultadoConsultarTurmaGeral.getString("sigla"));
    			turma.setSemestreLetivo(resultadoConsultarTurmaGeral.getInt("semestre_letivo"));
    			turma.setAnoLetivo(resultadoConsultarTurmaGeral.getInt("ano_letivo"));
    			tema.setTitulo(resultadoConsultarTurmaGeral.getString("titulo"));
    			turma.tema = tema;
    			return turma;
    		} else {
    			consultaGeral = false;
    		}
    	} catch(SQLException searchfailure) {
    		searchfailure.printStackTrace();
    	}
    	
    	if(consultaGeral == false) {
    		String consultaTurma = "SELECT * FROM turma"
    				+ " WHERE idturma = ?";
    		try(PreparedStatement preparedConsultaTurma =
    				this.conexao.prepareStatement(consultaTurma);) {
    			preparedConsultaTurma.setInt(1, idturma);
    			ResultSet resultadoConsultaTurma = preparedConsultaTurma.executeQuery();
    			if(resultadoConsultaTurma.next()) {
    				Turma turma = new Turma();
    				turma.setIdTurma(resultadoConsultaTurma.getInt("idturma"));
    				turma.setSigla(resultadoConsultaTurma.getString("sigla"));
    				turma.setSemestreLetivo(resultadoConsultaTurma.getInt("semestre_letivo"));
    				turma.setAnoLetivo(resultadoConsultaTurma.getInt("ano_letivo"));
    				turma.setIdTema(resultadoConsultaTurma.getInt("tema_idtema"));
    				return turma;
    			}
    		} catch(SQLException searchfailure) {
    			searchfailure.printStackTrace();
    		}
    	}
    	return null;
    }
    public ArrayList<Aluno> alunosDaTurma(int idturma) {
    	ArrayList<Aluno> alunosTurma = new ArrayList<Aluno>();
    	
    	String consultarAlunos = "SELECT idusuario, nome, email, fone,"
    			+ " sexo, dt_nascimento, ra FROM turma_aluno"
    			+ " JOIN aluno ON turma_aluno.aluno_idaluno = aluno.idaluno"
    			+ " JOIN usuario ON aluno.idaluno = usuario.idusuario"
    			+ " WHERE turma_idturma = ?";
    	try(PreparedStatement preparedConsultarAlunos =
    			this.conexao.prepareStatement(consultarAlunos);) {
    		preparedConsultarAlunos.setInt(1, idturma);
    		ResultSet resultadoConsultarAlunos = preparedConsultarAlunos.executeQuery();
    		while(resultadoConsultarAlunos.next()) {
    			Aluno aluno = new Aluno();
    			aluno.setIdaluno(resultadoConsultarAlunos.getInt("idusuario"));
    			aluno.setNome(resultadoConsultarAlunos.getString("nome"));
    			aluno.setEmail(resultadoConsultarAlunos.getString("email"));
    			aluno.setFone(resultadoConsultarAlunos.getString("fone"));
    			aluno.setSexo(resultadoConsultarAlunos.getString("sexo"));
    			aluno.setDt_nascimento(resultadoConsultarAlunos.getString("dt_nascimento"));
    			aluno.setRa(resultadoConsultarAlunos.getString("ra"));
    			alunosTurma.add(aluno);
    		}
    		return alunosTurma;
    	} catch(SQLException searchfailure) {
    		searchfailure.printStackTrace();
    	}
    	return null;
    }
    // -- [END]

    // [BEGIN]--
    public String alterarTurma(int idtema, int idturma) {
    	String retorno = "";
	
		String alterarTurma = "UPDATE turma SET tema_idtema = ?"
				+ " WHERE idturma = ?";
		
		try(PreparedStatement preparedAlterarTurma =
				this.conexao.prepareStatement(alterarTurma);) {
			preparedAlterarTurma.setInt(1, idtema);
			preparedAlterarTurma.setInt(2, idturma);
			preparedAlterarTurma.execute();
			retorno = "Turma alterada com sucesso!";
			return retorno;
		} catch(SQLException changefailure) {
			changefailure.printStackTrace();
		}	
    	return null;
    }
    // -- [END]
    
    // [BEGIN] --
    public ArrayList<Integer> vagaDisponivel() {
    	ArrayList<Integer> qtdAluno = new ArrayList<Integer>();
    	int contador = 0;
    	
    	String listarTurmas = "SELECT idturma FROM turma"
    			+ " WHERE semestre_letivo = 1";

    	try(PreparedStatement preparedListarTurmas =
    			this.conexao.prepareStatement(listarTurmas);) {
    		ResultSet resultadoListarTurmas = preparedListarTurmas.executeQuery();
    		while(resultadoListarTurmas.next()) {
    			contador = 0;
    			int idturma = resultadoListarTurmas.getInt("idturma");
    			qtdAluno.add(idturma);
    			
    	    	String qtdAlunos = "SELECT aluno_idaluno FROM turma_aluno"
    	    			+ " WHERE turma_idturma = ?";
    			
    			try(PreparedStatement preparedQtdAlunos =
    					this.conexao.prepareStatement(qtdAlunos);) {
    				preparedQtdAlunos.setInt(1, idturma);
    				ResultSet resultadoQtdAlunos = preparedQtdAlunos.executeQuery();
    				while(resultadoQtdAlunos.next()) {
    					contador++;
    				}
    				qtdAluno.add(contador);
    			} catch(SQLException searchfailure) {
    				searchfailure.printStackTrace();
    			}
    		}
    		return qtdAluno;
    	} catch(SQLException searchfailure) {
    		searchfailure.printStackTrace();
    	}
    	return null;
    }
    // -- [END]
}
