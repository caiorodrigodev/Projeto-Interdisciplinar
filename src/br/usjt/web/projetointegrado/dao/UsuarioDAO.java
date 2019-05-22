package br.usjt.web.projetointegrado.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.usjt.web.projetointegrado.model.Aluno;
import br.usjt.web.projetointegrado.model.Professor;
import br.usjt.web.projetointegrado.model.Usuario;

public class UsuarioDAO {

	Connection conexao;
	
	public UsuarioDAO() {

		try {

			this.conexao = ConnectionFactory.conectar();
		} catch (SQLException connectionfail) {

			connectionfail.printStackTrace();
		}
	}

	// [] TUDO RELACIONADO A USUÁRIO []
	
	// -- [BEGIN]
	public Usuario login(String ra, String senha) {	
		Usuario userLogin = new Usuario();
		Professor profLogin = new Professor();
		Aluno alunoLogin = new Aluno();
		
		String loginProfessor = "SELECT idprofessor, nome, email, fone, sexo, dt_nascimento,"
				+ " senha, administrador, matricula FROM professor JOIN usuario"
				+ " ON usuario.idusuario = professor.idprofessor"
				+ " WHERE matricula = ? AND senha = ?";
		String loginAluno = "SELECT idaluno, nome, email, fone, sexo, dt_nascimento,"
				+ " senha, ra FROM aluno JOIN usuario ON usuario.idusuario = aluno.idaluno"
				+ " WHERE ra = ? AND senha = ?";		
		try (PreparedStatement preparedProfessor =
				this.conexao.prepareStatement(loginProfessor);) {		
			preparedProfessor.setString(1, ra);
			preparedProfessor.setString(2, senha);			
			ResultSet resultadoProfessor = preparedProfessor.executeQuery();
			if(resultadoProfessor.next()) {
				profLogin.setIdprofessor(resultadoProfessor.getInt("idprofessor"));
				profLogin.setNome(resultadoProfessor.getString("nome"));
				profLogin.setEmail(resultadoProfessor.getString("email"));
				profLogin.setFone(resultadoProfessor.getString("fone"));
				profLogin.setSexo(resultadoProfessor.getString("sexo"));
				profLogin.setDt_nascimento(resultadoProfessor.getString("dt_nascimento"));
				profLogin.setSenha(resultadoProfessor.getString("senha"));		
				profLogin.setAdministrador(resultadoProfessor.getInt("administrador"));
				profLogin.setMatricula(resultadoProfessor.getString("matricula"));
				userLogin.professor = profLogin;
				return userLogin;
			} else {
				try (PreparedStatement preparedAluno =
						this.conexao.prepareStatement(loginAluno);) {				
					preparedAluno.setString(1, ra);
					preparedAluno.setString(2, senha);			
					ResultSet resultadoAluno = preparedAluno.executeQuery();
					if(resultadoAluno.next()) {
						alunoLogin.setIdaluno(resultadoAluno.getInt("idaluno"));
						alunoLogin.setNome(resultadoAluno.getString("nome"));
						alunoLogin.setEmail(resultadoAluno.getString("email"));
						alunoLogin.setFone(resultadoAluno.getString("fone"));
						alunoLogin.setSexo(resultadoAluno.getString("sexo"));
						alunoLogin.setDt_nascimento(resultadoAluno.getString("dt_nascimento"));
						alunoLogin.setSenha(resultadoAluno.getString("senha"));				
						alunoLogin.setRa(resultadoAluno.getString("ra"));
						userLogin.aluno = alunoLogin;
						return userLogin;
					}
				} catch (SQLException loginfail) {					
					loginfail.printStackTrace();
				}
			}
		} catch (SQLException loginfail) {			
			loginfail.printStackTrace();
		}		
		return null;
	}
	// [END] -- 
    
    // [BEGIN] --
    public void alterarSenha(String senha, int idusuario) {
        String alterarSenha = "UPDATE usuario SET senha = ?"
        		+ " WHERE idusuario = ?";        
        try (PreparedStatement preparedAltSenha = 
                this.conexao.prepareStatement(alterarSenha);) {            
        	preparedAltSenha.setString(1, senha);
        	preparedAltSenha.setInt(2, idusuario);
        	preparedAltSenha.execute();            
        } catch (SQLException changefailure) {            
        	changefailure.printStackTrace();
        }
    }
    // -- [END]
    
    // [BEGIN] --
    public void alterarEmail(String email, int idusuario) {  
    	boolean emailUtilizado = false;
    	String procurarEmail = "SELECT email FROM usuario"
    			+ " WHERE email = ?";
    	try(PreparedStatement preparedUtilizadoEmail =
    			this.conexao.prepareStatement(procurarEmail);) {
    		preparedUtilizadoEmail.setString(1, email);
    		ResultSet utilizadoEmail = preparedUtilizadoEmail.executeQuery();
    		if(utilizadoEmail.next()) {
    			emailUtilizado = true;
    		}
    	} catch(SQLException searchfailure) {
    		searchfailure.printStackTrace();
    	}
    	if(emailUtilizado == false) {
	        String alterarEmail = "UPDATE usuario SET email = ?"
	        		+ " WHERE idusuario = ?";      
	        try(PreparedStatement preparedAltEmail = 
	                this.conexao.prepareStatement(alterarEmail);) {            
	        	preparedAltEmail.setString(1, email);
	        	preparedAltEmail.setInt(2, idusuario);
	        	preparedAltEmail.execute();
	        } catch (SQLException changefailure) {           
	        	changefailure.printStackTrace();
	        }
    	}
    }
    // -- [END] 
    
    // [BEGIN] --
    public void alterarFone(String fone, int idusuario) {
    	boolean nmrUtilizado = false;
    	String procurarNmr = "SELECT fone FROM usuario"
    			+ " WHERE fone = ?";
    	try(PreparedStatement preparedUtilizadoNmr =
    			this.conexao.prepareStatement(procurarNmr);) {
    		preparedUtilizadoNmr.setString(1, fone);
    		ResultSet utilizadoNmr = preparedUtilizadoNmr.executeQuery();
    		if(utilizadoNmr.next()) {
    			nmrUtilizado = true;
    		}
    	} catch(SQLException searchfailure) {
    		searchfailure.printStackTrace();
    	}
    	if(nmrUtilizado == false ) {
    		String alterarFone = "UPDATE usuario SET fone = ?"
    				+ " WHERE idusuario = ?";
    		try(PreparedStatement preparedAltFone =
    				this.conexao.prepareStatement(alterarFone);) {
    			preparedAltFone.setString(1, fone);
    			preparedAltFone.setInt(2, idusuario);
    			preparedAltFone.execute();
    		} catch(SQLException changefailure) {
    			changefailure.printStackTrace();
    		}
    	}
    }
    // -- [END]
}
