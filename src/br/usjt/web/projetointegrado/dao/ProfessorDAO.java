package br.usjt.web.projetointegrado.dao;

import br.usjt.web.projetointegrado.model.Banca;
import br.usjt.web.projetointegrado.model.Grupo;
import br.usjt.web.projetointegrado.model.Professor;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ProfessorDAO {

	Connection conexao;
	private int id;

	public ProfessorDAO() {

		try {
			this.conexao = ConnectionFactory.conectar();
		} catch (SQLException connectionfail) {
			connectionfail.printStackTrace();
		}
	}
	
	// [BEGIN] --
	public String cadastrarProfessor(Professor professor) {
		String retorno = "";
		
		String cadastrarProfessor = "INSERT INTO usuario VALUES (null, ?, ?, ?, ?, ?, ?)";

		try(PreparedStatement preparedCadastrarProfessor
				= this.conexao.prepareStatement(cadastrarProfessor,
				PreparedStatement.RETURN_GENERATED_KEYS);) {
			preparedCadastrarProfessor.setString(1, professor.getNome());
			preparedCadastrarProfessor.setString(2, professor.getEmail());
			preparedCadastrarProfessor.setString(3, professor.getFone());
			preparedCadastrarProfessor.setString(4, professor.getSexo());
			preparedCadastrarProfessor.setString(5, professor.getDt_nascimento());
			preparedCadastrarProfessor.setString(6, professor.getSenha());
			preparedCadastrarProfessor.execute();
			ResultSet resultadoCadastroProfessor = preparedCadastrarProfessor.getGeneratedKeys();
			if (resultadoCadastroProfessor.next()) {
				id = resultadoCadastroProfessor.getInt(1);	
				
				String cadastroMatricula = "INSERT INTO professor VALUES (?, ?, ?)";
				
				try(PreparedStatement preparedCadastroMatricula
						= this.conexao.prepareStatement(cadastroMatricula);) {
					preparedCadastroMatricula.setInt(1, id);
					preparedCadastroMatricula.setInt(2, professor.getAdministrador());
					preparedCadastroMatricula.setString(3, professor.getMatricula());
					preparedCadastroMatricula.execute();
					retorno = "Professor cadastrado com sucesso!";
					return retorno;
				} catch(SQLException registerfail) {
					registerfail.printStackTrace();
				}
			}
		} catch(SQLException registerfail) {
			registerfail.printStackTrace();
		}
		return null;
	}
	
	// [BEGIN] --
	public String deletarProfessor(int idprofessor) {
		String retorno = "";
		
		String consultarProfessor = "SELECT idprofessor FROM professor"
				+ " WHERE idprofessor = ?";
		
		try(PreparedStatement preparedConsultarProfessor =
				this.conexao.prepareStatement(consultarProfessor);) {
			preparedConsultarProfessor.setInt(1, idprofessor);
			ResultSet resultadoConsultarProfessor = preparedConsultarProfessor.executeQuery();
			if(resultadoConsultarProfessor.next()) {
				String deletarProfessorGrupo = "UPDATE grupo SET"
						+ " professor_idprofessor = null WHERE"
						+ " professor_idprofessor = ?";
				
				try(PreparedStatement preparedDeletarProfessorGrupo =
						this.conexao.prepareStatement(deletarProfessorGrupo);) {
					preparedDeletarProfessorGrupo.setInt(1, idprofessor);
					preparedDeletarProfessorGrupo.execute();
				} catch(SQLException deletefailure) {
					deletefailure.printStackTrace();
				}
				
				String deletarProfessorBanca = "DELETE FROM professores_banca"
						+ " WHERE professor_idprofessor = ?";
				
				try(PreparedStatement preparedDeletarProfessorBanca =
						this.conexao.prepareStatement(deletarProfessorBanca);) {
					preparedDeletarProfessorBanca.setInt(1, idprofessor);
					preparedDeletarProfessorBanca.execute();
				} catch(SQLException deletefailure) {
					deletefailure.printStackTrace();
				}
				
				String deletarProfessor = "DELETE FROM professor"
						+ " WHERE idprofessor = ?";
				
				try(PreparedStatement preparedDeletarProfessor =
						this.conexao.prepareStatement(deletarProfessor);) {
					preparedDeletarProfessor.setInt(1, idprofessor);
					preparedDeletarProfessor.execute();
				} catch(SQLException deletefailure) {
					deletefailure.printStackTrace();
				}
				
				String deletarUsuario = "DELETE FROM usuario"
						+ " WHERE idusuario = ?";
				
				try(PreparedStatement preparedDeletarUsuario =
						this.conexao.prepareStatement(deletarUsuario);) {
					 preparedDeletarUsuario.setInt(1, idprofessor);
					 preparedDeletarUsuario.execute();
					 retorno = "Professor deletado com sucesso!";
					 return retorno;
				} catch(SQLException deletefailure) {
					deletefailure.printStackTrace();
				}
			} else {
				retorno = "Nenhum professor encontrado com esse ID.";
				return retorno;
			}
		} catch(SQLException searchfailure) {
			searchfailure.printStackTrace();
		}	
		return null;
	}
	// -- [END]
	
	// [BEGIN] --
	public Professor consultarProfessor(String matricula) {
		Professor professor = new Professor();
		
		String consultaProfessor = "SELECT idusuario, usuario.nome, email, fone,"
				+ " sexo, dt_nascimento, senha, administrador, matricula FROM professor"
				+ " JOIN usuario ON professor.idprofessor = usuario.idusuario" 
				+ " WHERE matricula = ?";
		
		try(PreparedStatement preparedConsultaProfessor =
				this.conexao.prepareStatement(consultaProfessor);) {
			preparedConsultaProfessor.setString(1, matricula);
			ResultSet resultadoConsultaProfessor = preparedConsultaProfessor.executeQuery();
			if(resultadoConsultaProfessor.next()) {
				professor.setIdprofessor(resultadoConsultaProfessor.getInt("idusuario"));
				professor.setNome(resultadoConsultaProfessor.getString("usuario.nome"));
				professor.setEmail(resultadoConsultaProfessor.getString("email"));
				professor.setFone(resultadoConsultaProfessor.getString("fone"));
				professor.setSexo(resultadoConsultaProfessor.getString("sexo"));
				professor.setDt_nascimento(resultadoConsultaProfessor.getString("dt_nascimento"));
				professor.setSenha(resultadoConsultaProfessor.getString("senha"));
				professor.setAdministrador(resultadoConsultaProfessor.getInt("administrador"));
				professor.setMatricula(resultadoConsultaProfessor.getString("matricula"));
				return professor;
			}
		} catch(SQLException searchfailure) {
			searchfailure.printStackTrace();
		}
		return null;
	}
	// -- [END]
}
