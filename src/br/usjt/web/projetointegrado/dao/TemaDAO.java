package br.usjt.web.projetointegrado.dao;

import br.usjt.web.projetointegrado.model.Tema;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.sql.Connection;
import java.util.Date;

public class TemaDAO {
    
    Connection conexao;
    
    public TemaDAO() {       
        try {
            this.conexao = ConnectionFactory.conectar();
        } catch (SQLException connectionfail) {
            connectionfail.printStackTrace();
        }
    }
    
    // [] TUDO RELACIONADO A ADMINISTRADORES/PROFESSORES []
        
    // [BEGIN] --
    public String cadastrarTema(Tema tema) { 
    	String retorno = "";
    	boolean tituloUso = false;
    	
    	String tituloUtilizado = "SELECT idtema FROM tema"
    			+ " WHERE titulo = ?";
    	
    	try(PreparedStatement preparedTituloUtilizado =
    			this.conexao.prepareStatement(tituloUtilizado);) {
    		preparedTituloUtilizado.setString(1, tema.getTitulo());
    		ResultSet resultadoTituloUtilizado = preparedTituloUtilizado.executeQuery();
    		if(resultadoTituloUtilizado.next()) {
    			tituloUso = true;
    			retorno += "Não pode existir dois temas com títulos idênticos!";
    		}
    	} catch(SQLException searchfailure) {
    		searchfailure.printStackTrace();
    	}
    	
    	boolean introducaoUso = false;
    	
    	String introducaoUtilizado = "SELECT idtema FROM tema"
    			+ " WHERE introducao = ?";
    	
    	try(PreparedStatement preparedIntroducaoUtilizado =
    			this.conexao.prepareStatement(introducaoUtilizado);) {
    		preparedIntroducaoUtilizado.setString(1, tema.getIntroducao());
    		ResultSet resultadoIntroducaoUtilizado = preparedIntroducaoUtilizado.executeQuery();
    		if(resultadoIntroducaoUtilizado.next()) {
    			introducaoUso = true;
    			retorno += "Não pode existir dois temas com introduções idênticas!";
    		}
    	} catch(SQLException searchfailure) {
    		searchfailure.printStackTrace();
    	}
    	
    	boolean requisitosUso = false;
    	
    	String requisitosUtilizado = "SELECT idtema FROM tema"
    			+ " WHERE requisitos = ?";
    	
    	try(PreparedStatement preparedRequisitosUtilizado =
    			this.conexao.prepareStatement(requisitosUtilizado);) {
    		preparedRequisitosUtilizado.setString(1, tema.getRequisitos());
    		ResultSet resultadoRequisitosUtilizado = preparedRequisitosUtilizado.executeQuery();
    		if(resultadoRequisitosUtilizado.next()) {
    			requisitosUso = true;
    			retorno += "Não pode existir dois temas com requisitos idênticos!";
    		}
    	} catch(SQLException searchfailure) {
    		searchfailure.printStackTrace();
    	}
    	
    	if(tituloUso == false && introducaoUso == false && requisitosUso == false) {
	        String cadastrarTema = "INSERT INTO tema VALUES (null, ?, ?, ?, ?)";
	        
	        try (PreparedStatement preparedCadastrarTema =
	                this.conexao.prepareStatement(cadastrarTema);) {
	        	SimpleDateFormat dataFormat = new SimpleDateFormat("dd/MM/yyyy");
	    		Date dataCadastro = new Date();
	    		String dataFormatada = dataFormat.format(dataCadastro);
	    		preparedCadastrarTema.setString(1, dataFormatada);
	    		preparedCadastrarTema.setString(2, tema.getTitulo());
	    		preparedCadastrarTema.setString(3, tema.getIntroducao());
	    		preparedCadastrarTema.setString(4, tema.getRequisitos());
	    		preparedCadastrarTema.execute();
	    		retorno = "Tema cadastrado com sucesso!";
	    		return retorno;
	        } catch (SQLException registerfail) {           
	        	registerfail.printStackTrace();
	        }
    	} else {
    		return retorno;
    	}
    	return null;
    }
    // -- [END]
    
    // [BEGIN] --
    public String deletarTema(int idtema) { 
    	String retorno = "";
    	boolean temaUso = false;
    	
    	String temaUtilizado = "SELECT tema_idtema FROM atividade"
    			+ " WHERE tema_idtema = ?";
    	
    	try(PreparedStatement preparedTemaUtilizado =
    			this.conexao.prepareStatement(temaUtilizado);) {
    		preparedTemaUtilizado.setInt(1, idtema);
    		ResultSet resultadoTemaUtilizado = preparedTemaUtilizado.executeQuery();
    		if(resultadoTemaUtilizado.next()) {
    			temaUso = true;
    			retorno = "O tema está cadastrado em uma atividade já"
    					+ " entregue!";
    			return retorno;
    		}
    	} catch(SQLException searchfailure) {
    		searchfailure.printStackTrace();
    	}
    	
    	if(temaUso == false) {
	        String alterarTema = "UPDATE turma SET tema_idtema = null WHERE tema_idtema = ?";
	        
	        try (PreparedStatement preparedAlterarTema =
	                this.conexao.prepareStatement(alterarTema);) {	            
	        	preparedAlterarTema.setInt(1, idtema);
	            preparedAlterarTema.execute();
	        } catch (SQLException deletefailure) {            
	        	deletefailure.printStackTrace();
	        }
	        
	        String deletarTema = "DELETE FROM tema WHERE idtema = ?";
	        
	        try (PreparedStatement preparedDeletarTema =
	                this.conexao.prepareStatement(deletarTema);) {	            
	        	preparedDeletarTema.setInt(1, idtema);
	        	preparedDeletarTema.execute();
	        } catch (SQLException deletefailure) {	            
	        	deletefailure.printStackTrace();
	        }
	        retorno = "Tema deletado com sucesso!";
	        return retorno;
    	}
    	return null;
    }
    // -- [END]
    
    // [BEGIN] --
    public Tema consultarTema(int idtema) {       
        Tema tema = new Tema();
        
        String consultarTema = "SELECT * FROM tema WHERE idtema = ?";
        
        try (PreparedStatement preparedConsultarTema =
                this.conexao.prepareStatement(consultarTema);) {           
        	preparedConsultarTema.setInt(1, idtema);
        	preparedConsultarTema.execute();
            ResultSet resultadoConsultarTema = preparedConsultarTema.executeQuery();
            if(resultadoConsultarTema.next()) {
                tema.setIdTema(resultadoConsultarTema.getInt("idtema"));
                tema.setDt_cadastro(resultadoConsultarTema.getString("dt_cadastro"));
                tema.setTitulo(resultadoConsultarTema.getString("titulo"));
                tema.setIntroducao(resultadoConsultarTema.getString("introducao"));
                tema.setRequisitos(resultadoConsultarTema.getString("requisitos"));              
                return tema;
            }
        } catch (SQLException searchfailure) {
        	searchfailure.printStackTrace();
        }
        return null;
    }
    // -- [END]
    
    // [BEGIN] --
    public String alterarTema(String requisitos, int idtema) {
    	String retorno = "";
    	boolean temaUso = false;
    	
    	String temaUtilizado = "SELECT tema_idtema FROM atividade"
    			+ " WHERE tema_idtema = ?";
    	
    	try(PreparedStatement preparedTemaUtilizado =
    			this.conexao.prepareStatement(temaUtilizado);) {
    		preparedTemaUtilizado.setInt(1, idtema);
    		ResultSet resultadoTemaUtilizado = preparedTemaUtilizado.executeQuery();
    		if(resultadoTemaUtilizado.next()) {
    			temaUso = true;
    			retorno = "O tema está cadastrado em uma atividade já"
    					+ " entregue!";
    			return retorno;
    		}
    	} catch(SQLException searchfailure) {
    		searchfailure.printStackTrace();
    	}
    	
    	if(temaUso == false) {
	    	boolean requisitosUso = false;
	    	
	        String requisitosUtilizado = "SELECT requisitos FROM tema"
	        		+ " WHERE requisitos = ?";
	        
	        try(PreparedStatement preparedRequisitosUtilizado =
	        		this.conexao.prepareStatement(requisitosUtilizado);) {
	        	preparedRequisitosUtilizado.setString(1, requisitos);
	        	ResultSet resultadoRequisitosUtilizado = preparedRequisitosUtilizado.executeQuery();
	        	if(resultadoRequisitosUtilizado.next()) {
	        		requisitosUso = true;
	        		retorno = "Não pode existir dois temas com requisitos idênticos!";
	        		return retorno;
	        	}
	        } catch(SQLException searchfailure) {
	        	searchfailure.printStackTrace();
	        }
	    	
	        if(temaUso == false && requisitosUso == false) {
		        String alterarTema = "UPDATE tema SET requisitos = ? WHERE idtema = ?";
		        
		        try (PreparedStatement prepared =
		                this.conexao.prepareStatement(alterarTema);) {
		            
		            prepared.setString(1, requisitos);
		            prepared.setInt(2, idtema);
		            prepared.execute();
		            retorno = "Tema alterado com sucesso!";
		            return retorno;
		        } catch (SQLException changefailure) {
		        	changefailure.printStackTrace();
		        }
	        }
    	}
        return null;
    }
    // -- [END]
    
    // [BEGIN] --
    public ArrayList<Tema> listarTemas() { 
    	ArrayList<Tema> arrayTemas = new ArrayList<Tema>();

        String listarTemas = "SELECT * FROM tema ORDER BY dt_cadastro DESC";        
        
        try (PreparedStatement preparedListarTemas =
                this.conexao.prepareStatement(listarTemas);) {        
            ResultSet resultadoListarTemas = preparedListarTemas.executeQuery();
            while(resultadoListarTemas.next()) {
            	Tema tema = new Tema();
                tema.setIdTema(resultadoListarTemas.getInt("idtema"));
                tema.setTitulo(resultadoListarTemas.getString("titulo"));
                tema.setIntroducao(resultadoListarTemas.getString("introducao"));
                
                arrayTemas.add(tema);
            }
            return arrayTemas;
        } catch (SQLException searchfailure) {            
        	searchfailure.printStackTrace();
        }
        return null;
    }
    // -- [END]
    
    public String tituloTema(int idaluno)  {
    	String retorno = "";
    
    	String tituloTema = "SELECT titulo FROM turma_aluno"
    			+ " JOIN turma ON turma_aluno.turma_idturma = turma.idturma"
    			+ " JOIN tema ON turma.tema_idtema = tema.idtema"
    			+ " WHERE aluno_idaluno = ?";
    	
    	try(PreparedStatement preparedTituloTema =
    			this.conexao.prepareStatement(tituloTema);) {
    		preparedTituloTema.setInt(1, idaluno);
    		ResultSet resultadoTituloTema = preparedTituloTema.executeQuery();
    		if(resultadoTituloTema.next()) {
    			retorno = resultadoTituloTema.getString("titulo");
    			return retorno;
    		}
    	} catch(SQLException searchfailure) {
    		searchfailure.printStackTrace();
    	}
    	return null;
    }
}
