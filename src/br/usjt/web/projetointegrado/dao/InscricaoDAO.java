package br.usjt.web.projetointegrado.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Random;

import br.usjt.web.projetointegrado.model.Inscricao;

public class InscricaoDAO {

	private Connection conexao;
	
	public InscricaoDAO() {
		try {           
            this.conexao = ConnectionFactory.conectar();
        } catch (SQLException connectionfail) {     
            connectionfail.printStackTrace();
        }
	}
	
	// {} MÉTODOS USADOS PELA PRÓPRIA INSCRIÇÃO {}
	
	// [BEGIN] --
	public String cadastrarInscricao(Inscricao insc) {		
		String retorno = "";
		boolean emailUso = false;
		boolean numeroUso = false;
		
		String emailUtilizadoInsc = "SELECT email FROM inscricao"
				+ " WHERE email = ?";
		
		try(PreparedStatement preparedEmailUtilizadoInsc =
				this.conexao.prepareStatement(emailUtilizadoInsc);) {
			preparedEmailUtilizadoInsc.setString(1, insc.getEmail());
			ResultSet resultadoEmailUtilizadoInsc = preparedEmailUtilizadoInsc.executeQuery();
			if(resultadoEmailUtilizadoInsc.next()) {
				emailUso = true;
			} else {
				String emailUtilizadoUser = "SELECT email FROM usuario"
		    			+ " WHERE email = ?";
		    	
		    	try(PreparedStatement preparedEmailUtilizadoUser =
		    			this.conexao.prepareStatement(emailUtilizadoUser);) {
		    		preparedEmailUtilizadoUser.setString(1, insc.getEmail());
		    		ResultSet resultadoEmailUtilizadoUser = preparedEmailUtilizadoUser.executeQuery();
		    		if(resultadoEmailUtilizadoUser.next()) {
		    			emailUso = true;
		    		}
		    	} catch(SQLException searchfailure) {
		    		searchfailure.printStackTrace();
		    	}
			}
		} catch(SQLException searchfailure) {
			searchfailure.printStackTrace();
		}		
    	
		if(emailUso == true) {
			retorno += "E-mail já está sendo utilizado!";
		}
		
		String numeroUtilizadoInsc = "SELECT fone FROM inscricao"
				+ " WHERE fone = ?";
		
		try(PreparedStatement preparedNumeroUtilizadoInsc =
				this.conexao.prepareStatement(numeroUtilizadoInsc);) {
			preparedNumeroUtilizadoInsc.setString(1, insc.getFone());
			ResultSet resultadoNumeroUtilizadoInsc = preparedNumeroUtilizadoInsc.executeQuery();
			if(resultadoNumeroUtilizadoInsc.next()) {
				numeroUso = true;
			} else {
				String numeroUtilizadoUser = "SELECT fone FROM usuario"
		    			+ " WHERE fone = ?";
		    	
		    	try(PreparedStatement preparedNumeroUtilizadoUser =
		    			this.conexao.prepareStatement(numeroUtilizadoUser);) {
		    		preparedNumeroUtilizadoUser.setString(1, insc.getFone());
		    		ResultSet resultadoNumeroUtilizadoUser = preparedNumeroUtilizadoUser.executeQuery();
		    		if(resultadoNumeroUtilizadoUser.next()) {
		    			numeroUso = true;	
		    		}
		    	} catch(SQLException searchfailure) {
		    		searchfailure.printStackTrace();
		    	}
			}
		} catch(SQLException searchfailure) {
			searchfailure.printStackTrace();
		}		   	
		
		if(numeroUso == true) {
			retorno += "Número de telefone já está sendo utilizado!";
		}
		
    	if(emailUso == false && numeroUso == false) {
    		Random randomNota = new Random();
    		
			String cadastrarInscricao = "INSERT INTO inscricao VALUES(null,"
					+ " ?, ?, ?, ?, ?, ?, ?)";
			
			Double nota = randomNota.nextDouble() * 10 + 4;
			nota = Double.valueOf(String.format(Locale.US, "%.1f", nota));
			if(nota > 10.0) {
				nota = 10.0;
			}
			
			try(PreparedStatement preparedCadastrarInscricao =
					this.conexao.prepareStatement(cadastrarInscricao);) {
				preparedCadastrarInscricao.setString(1, insc.getNome());
				preparedCadastrarInscricao.setString(2, insc.getEmail());
				preparedCadastrarInscricao.setString(3, insc.getFone());
				preparedCadastrarInscricao.setString(4, insc.getSexo());
				preparedCadastrarInscricao.setString(5, insc.getDt_nascimento());
				preparedCadastrarInscricao.setString(6, insc.getSenha());
				preparedCadastrarInscricao.setDouble(7, nota);
				preparedCadastrarInscricao.execute();
				retorno = "Inscrição cadastrada com sucesso!";
				return retorno;
			} catch(SQLException registerfail) {
				registerfail.printStackTrace();
			}
    	} else if(emailUso == true || numeroUso == true) {
    		return retorno;
    	}
    	return null;
	}
	// -- [END]
	
	// [BEGIN] --
	public ArrayList<Inscricao> listarInscricoes() {
		ArrayList<Inscricao> inscricoes = new ArrayList<Inscricao>();
		String consultaInscricao = "SELECT * FROM inscricao";
		try(PreparedStatement preparedConsulta = 
				this.conexao.prepareStatement(consultaInscricao);) {
			ResultSet resultadoInscricao = preparedConsulta.executeQuery();
			while(resultadoInscricao.next()) {
				Inscricao insc = new Inscricao();
				insc.setIdinscricao(resultadoInscricao.getInt("idinscricao"));
				insc.setNome(resultadoInscricao.getString("nome"));
				insc.setEmail(resultadoInscricao.getString("email"));
				insc.setFone(resultadoInscricao.getString("fone"));
				insc.setSexo(resultadoInscricao.getString("sexo"));
				insc.setDt_nascimento(resultadoInscricao.getString("dt_nascimento"));
				insc.setNota(resultadoInscricao.getDouble("nota"));
				inscricoes.add(insc);
			}
			return inscricoes;
		} catch(SQLException searchfailure) {
			searchfailure.printStackTrace();
		}
		return null;
	}
	// -- [END]
	
	// {} MÉTODOS USADOS JUNTAMENTE COM O CADASTRO ALUNO {}
	
	// [BEGIN] --
	public Inscricao consultarInscricao(int idinscricao) {
		String consultarInscricao = "SELECT * FROM inscricao"
				+ " WHERE idinscricao = ?";
		
		try(PreparedStatement preparedConsultarInscricao =
				this.conexao.prepareStatement(consultarInscricao);) {
			preparedConsultarInscricao.setInt(1, idinscricao);
			ResultSet resultadoConsultarInscricao = preparedConsultarInscricao.executeQuery();
			if(resultadoConsultarInscricao.next()) {
				Inscricao insc = new Inscricao();
				insc.setIdinscricao(resultadoConsultarInscricao.getInt("idinscricao"));
				insc.setNome(resultadoConsultarInscricao.getString("nome"));
				insc.setEmail(resultadoConsultarInscricao.getString("email"));
				insc.setFone(resultadoConsultarInscricao.getString("fone"));
				insc.setSexo(resultadoConsultarInscricao.getString("sexo"));
				insc.setDt_nascimento(resultadoConsultarInscricao.getString("dt_nascimento"));
				insc.setSenha(resultadoConsultarInscricao.getString("senha"));
				return insc;
			}
		} catch(SQLException searchfailure) {
			searchfailure.printStackTrace();
		}		
		return null;
	}
	// -- [END]
	
	// [BEGIN] --
	public String excluirInscricao(int idinscricao) {
		String retorno = "";
		
		String excluirInscricao = "DELETE FROM inscricao"
				+ " WHERE idinscricao = ?";
		try(PreparedStatement preparedExcluir =
				this.conexao.prepareStatement(excluirInscricao);) {
			preparedExcluir.setInt(1, idinscricao);
			preparedExcluir.execute();
			retorno = "Inscrição deletada com sucesso!";
			return retorno;
		} catch(SQLException deletefailure) {
			deletefailure.printStackTrace();
		}
		return null;
	}
	// -- [END]
}
