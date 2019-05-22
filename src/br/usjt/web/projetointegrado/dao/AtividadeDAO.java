package br.usjt.web.projetointegrado.dao;

import br.usjt.web.projetointegrado.model.Atividade;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AtividadeDAO {
    
    Connection conexao;
    
    public AtividadeDAO() {      
        try {
            this.conexao = ConnectionFactory.conectar();
        } catch (SQLException connectionfail) {
            connectionfail.printStackTrace();
        }
    }
    
    // [BEGIN] --
    public String cadastrarAtividade(Atividade atv, int idaluno) {
    	String retorno = "";
    	int idgrupo = 0;
    	int idtema = 0;
    	boolean userGrupo = true;
    	boolean grupoCadastrado = false;
    	int idatividade = 0;
    	
    	String grupoUser = "SELECT grupo_idgrupo, tema_idtema FROM turma_aluno"
    			+ " JOIN turma ON turma_aluno.turma_idturma = turma.idturma"
    			+ " WHERE aluno_idaluno = ?";
    	
    	try(PreparedStatement preparedGrupoUser =
    			this.conexao.prepareStatement(grupoUser);) {
    		preparedGrupoUser.setInt(1, idaluno);
    		ResultSet resultadoGrupoUser = preparedGrupoUser.executeQuery();
    		if(resultadoGrupoUser.next()) {
    			idgrupo = resultadoGrupoUser.getInt("grupo_idgrupo");
    			idtema = resultadoGrupoUser.getInt("tema_idtema");
    			
    			String cadastradoGrupo = "SELECT grupo_idgrupo FROM entrega"
    					+ " WHERE grupo_idgrupo = ?";
    			
    			try(PreparedStatement preparedCadastradoGrupo =
    					this.conexao.prepareStatement(cadastradoGrupo);) {
    				preparedCadastradoGrupo.setInt(1, idgrupo);
    				ResultSet resultadoCadastradoGrupo = preparedCadastradoGrupo.executeQuery();
    				if(resultadoCadastradoGrupo.next()) {
    					grupoCadastrado = true;
    					retorno = "A atividade deste grupo já está cadastrada!";
    					return retorno;
    				}
    			} catch(SQLException searchfailure) {
    				searchfailure.printStackTrace();
    			}
    		} else {
    			userGrupo = false;
    			retorno = "Você não está em nenhuma turma/grupo"
    					+ " ou sua turma não tem um tema definido.";
    			return retorno;
    		}
    	} catch(SQLException searchfailure) {
    		searchfailure.printStackTrace();
    	}
    	
    	if(userGrupo == true && grupoCadastrado == false) {
    		String cadastrarAtividade = "INSERT INTO atividade"
    				+ " VALUES(null, ?, ?, ?, ?)";
    		
    		try(PreparedStatement preparedCadastrarAtividade =
    				this.conexao.prepareStatement(cadastrarAtividade,
    						PreparedStatement.RETURN_GENERATED_KEYS)) {
    			preparedCadastrarAtividade.setInt(1, idtema);
    			preparedCadastrarAtividade.setString(2, atv.getDescricao());
    			preparedCadastrarAtividade.setString(3, atv.getDt_inicio());
    			preparedCadastrarAtividade.setString(4, atv.getDt_fim());
    			preparedCadastrarAtividade.execute();
    			ResultSet resultadoCadastrarAtividade = preparedCadastrarAtividade.getGeneratedKeys();
    			if(resultadoCadastrarAtividade.next()) {
    				idatividade = resultadoCadastrarAtividade.getInt(1);
    				
    				String cadastrarEntrega = "INSERT INTO entrega"
    						+ " VALUES(null, ?, ?, ?)";
    				
    				try(PreparedStatement preparedCadastrarEntrega =
    						this.conexao.prepareStatement(cadastrarEntrega);) {
    					SimpleDateFormat formatData = new SimpleDateFormat("dd/MM/yyyy");
    					Date date = new Date();
    					String data = formatData.format(date);
    					
    					preparedCadastrarEntrega.setInt(1, idatividade);
    					preparedCadastrarEntrega.setInt(2, idgrupo);
    					preparedCadastrarEntrega.setString(3, data);
    					preparedCadastrarEntrega.execute();
    					retorno = "Atividade cadastrada com sucesso!";
    					return retorno;
    				} catch(SQLException registerfail) {
    					registerfail.printStackTrace();
    				}
    			}
    		} catch(SQLException registerfail) {
    			registerfail.printStackTrace();
    		}
    	}
    	return null;
    }
    // -- [END]
}
