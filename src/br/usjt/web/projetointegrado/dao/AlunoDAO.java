package br.usjt.web.projetointegrado.dao;

import br.usjt.web.projetointegrado.model.Aluno;
import br.usjt.web.projetointegrado.model.Turma;
import br.usjt.web.projetointegrado.model.Tema;
import br.usjt.web.projetointegrado.model.Grupo;
import br.usjt.web.projetointegrado.model.Professor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;

public class AlunoDAO {
    
    private Connection conexao;

    public AlunoDAO() {      
        try {           
            this.conexao = ConnectionFactory.conectar();
        } catch (SQLException connectionfail) {           
            connectionfail.printStackTrace();
        }
    }

    // [] TUDO RELACIONADO A ADMINISTRADORES []
    
    // [BEGIN] --
    public String cadastrarAluno(Aluno aluno, int idturma) {
    	Random random = new Random();
    	
    	String retorno = "";
    	
		int raRandom = random.nextInt(99);   	
    	int idusuario = 0;
    	int raAtual = 0;
    	String raFinal = "";
    	
        String cadastrarAluno = "INSERT INTO usuario VALUES (null, ?, ?, ?, ?, ?, ?)";
        
        try(PreparedStatement preparedCadastrarAluno = 
        		this.conexao.prepareStatement(cadastrarAluno,
                        PreparedStatement.RETURN_GENERATED_KEYS);) {
        	preparedCadastrarAluno.setString(1, aluno.getNome());
        	preparedCadastrarAluno.setString(2, aluno.getEmail());
        	preparedCadastrarAluno.setString(3, aluno.getFone());
        	preparedCadastrarAluno.setString(4, aluno.getSexo());
        	preparedCadastrarAluno.setString(5, aluno.getDt_nascimento());
        	preparedCadastrarAluno.setString(6, aluno.getSenha());
        	preparedCadastrarAluno.execute();
            ResultSet resultadoCadastrarAluno = preparedCadastrarAluno.getGeneratedKeys();
            if (resultadoCadastrarAluno.next()) {
                idusuario = resultadoCadastrarAluno.getInt(1);
                
                String geradorRA = "SELECT raatual FROM ragerador";
                
                try(PreparedStatement preparedGeradorRA = 
                		this.conexao.prepareStatement(geradorRA);) {
                	ResultSet resultadoGeradorRA = preparedGeradorRA.executeQuery();
                	if(resultadoGeradorRA.next()) {
                		raAtual = resultadoGeradorRA.getInt("raatual");
                		String stringRaAtual = Integer.toString(raAtual);
                		String meioRa = "";
                		if(raRandom < 10) {
                			meioRa = Integer.toString(raRandom);
                			meioRa = "0" + meioRa;
                		} else {
                			meioRa = Integer.toString(raRandom);
                		}
                		raFinal = "8181" + meioRa + stringRaAtual;
                		
                		String cadastrarRA = "INSERT INTO aluno VALUES (?, ?)";
                		
                		try(PreparedStatement preparedCadastrarRA =
                        		this.conexao.prepareStatement(cadastrarRA);) {              
                			preparedCadastrarRA.setInt(1, idusuario);
                			preparedCadastrarRA.setString(2, raFinal);
                			preparedCadastrarRA.execute();
                        } catch (SQLException registerfail) {
                        	registerfail.printStackTrace();
                        }
                	}
                } catch(SQLException searchfailure) {
                	searchfailure.printStackTrace();
                }

                String CadastrarTurmaAluno = "INSERT INTO turma_aluno VALUES(null, ?, ?, null)";
                
                try(PreparedStatement preparedCadastrarTurmaAluno =
                		this.conexao.prepareStatement(CadastrarTurmaAluno);) {
                	preparedCadastrarTurmaAluno.setInt(1, idusuario);
                	preparedCadastrarTurmaAluno.setInt(2, idturma);
                	preparedCadastrarTurmaAluno.execute();
                } catch (SQLException registerfail) {
                	registerfail.printStackTrace();
                }

                String maisGeradorRA = "UPDATE ragerador SET raatual = ? WHERE raatual = ?";
                
                try(PreparedStatement preparedMaisGeradorRA = 
                		this.conexao.prepareStatement(maisGeradorRA);) {
                	preparedMaisGeradorRA.setInt(1, raAtual + 1);
                	preparedMaisGeradorRA.setInt(2, raAtual);
                	preparedMaisGeradorRA.execute();
                } catch(SQLException changefailure) {
                	changefailure.printStackTrace();
                }
            }      
            retorno = "Aluno cadastrado com sucesso!";
            return retorno;
        } catch (SQLException registerfail) {
        	registerfail.printStackTrace();
        }
    	return null;
    }
    // -- [END]
    
    // [BEGIN] --
    public String deletarAluno(int idaluno) {
    	String retorno = "";
    	
    	String consultarAluno = "SELECT idaluno FROM aluno"
    			+ " WHERE idaluno = ?";
    	
    	try(PreparedStatement preparedConsultarAluno =
    			this.conexao.prepareStatement(consultarAluno);) {
    		preparedConsultarAluno.setInt(1, idaluno);
    		ResultSet resultadoConsultarAluno = preparedConsultarAluno.executeQuery();
    		if(resultadoConsultarAluno.next()) {
    			String deletarDeTurmaAluno = "DELETE FROM turma_aluno WHERE aluno_idaluno = ?";
    	        
    	        try(PreparedStatement preparedTurmaAluno =
    	                this.conexao.prepareStatement(deletarDeTurmaAluno);) {                  
    	        	preparedTurmaAluno.setInt(1, idaluno);
    	        	preparedTurmaAluno.execute();
    	        } catch (SQLException deletefailure) {            
    	        	deletefailure.printStackTrace();
    	        }     

    	        String deletarDeAluno = "DELETE FROM aluno WHERE idaluno = ?";
    	        
    	        try(PreparedStatement preparedAluno =
    	                this.conexao.prepareStatement(deletarDeAluno);) {                       
    	        	preparedAluno.setInt(1, idaluno);
    	        	preparedAluno.execute();
    	        } catch (SQLException deletefailure) {            
    	        	deletefailure.printStackTrace();
    	        }      

    	        String deletarDeUsuario = "DELETE FROM usuario WHERE idusuario = ?";
    	        
    	        try(PreparedStatement preparedUsuario =
    	                this.conexao.prepareStatement(deletarDeUsuario);) {                       
    	        	preparedUsuario.setInt(1, idaluno);
    	        	preparedUsuario.execute();
    	        	retorno = "Aluno deletado com sucesso!";
    	        	return retorno;
    	        } catch (SQLException deletefailure) {           
    	        	deletefailure.printStackTrace();
    	        }
    		} else {
    			retorno = "Nenhum aluno encontrado com esse ID.";
    			return retorno;
    		}
    	} catch(SQLException searchfailure) {
    		searchfailure.printStackTrace();
    	}
    	return null;
    }
    // -- [END]
    
    // [] TUDO RELACIONADO A PROFESSORES/ADMINISTRADORES []

    // [BEGIN] --
    public Aluno consultarAluno(String ra) {
        Aluno aluno = new Aluno();
        
        String consultaGeral = "SELECT idusuario, usuario.nome, email, fone, sexo, dt_nascimento, senha,"
        		+ " ra, idturma, sigla, semestre_letivo, titulo, idgrupo, professor_idprofessor, grupo.nome FROM turma_aluno"
        		+ " JOIN turma ON turma_aluno.turma_idturma = turma.idturma"
        		+ " JOIN tema ON turma.tema_idtema = tema.idtema"
        		+ " JOIN grupo ON turma_aluno.grupo_idgrupo = grupo.idgrupo" 
        		+ " JOIN aluno ON turma_aluno.aluno_idaluno = aluno.idaluno" 
        		+ " JOIN usuario ON aluno.idaluno = usuario.idusuario"
        		+ " WHERE ra = ?";
        
        try(PreparedStatement preparedConsultaGeral =
        		this.conexao.prepareStatement(consultaGeral);) {
        	preparedConsultaGeral.setString(1, ra);
        	ResultSet resultadoConsultaGeral = preparedConsultaGeral.executeQuery();
        	if(resultadoConsultaGeral.next()) {
        		Turma turma = new Turma();
        		Tema tema = new Tema();
        		Grupo grupo = new Grupo();
        		
        		aluno.setIdaluno(resultadoConsultaGeral.getInt("idusuario"));
        		aluno.setNome(resultadoConsultaGeral.getString("usuario.nome"));
        		aluno.setEmail(resultadoConsultaGeral.getString("email"));
        		aluno.setFone(resultadoConsultaGeral.getString("fone"));
        		aluno.setSexo(resultadoConsultaGeral.getString("sexo"));
        		aluno.setDt_nascimento(resultadoConsultaGeral.getString("dt_nascimento"));
        		aluno.setSenha(resultadoConsultaGeral.getString("senha"));
        		aluno.setRa(resultadoConsultaGeral.getString("ra"));
        		turma.setIdTurma(resultadoConsultaGeral.getInt("idturma"));
        		turma.setSigla(resultadoConsultaGeral.getString("sigla"));
        		turma.setSemestreLetivo(resultadoConsultaGeral.getInt("semestre_letivo"));
        		tema.setTitulo(resultadoConsultaGeral.getString("titulo"));
        		grupo.setIdGrupo(resultadoConsultaGeral.getInt("idgrupo"));
        		grupo.setNome(resultadoConsultaGeral.getString("grupo.nome"));
        		grupo.setIdProfessor(resultadoConsultaGeral.getInt("professor_idprofessor"));
        		
        		aluno.turma = turma;
        		aluno.tema = tema;
        		aluno.grupo = grupo;
        		
        		try {
        			int idprofessor = resultadoConsultaGeral.getInt("professor_idprofessor");
        			
        			String orientador = "SELECT nome FROM professor"
            				+ " JOIN usuario ON professor.idprofessor = usuario.idusuario"
            				+ "	WHERE idprofessor = ?";
            		
            		try(PreparedStatement preparedOrientador =
            				this.conexao.prepareStatement(orientador);) {
            			preparedOrientador.setInt(1, resultadoConsultaGeral.getInt("professor_idprofessor"));
            			ResultSet resultadoOrientador = preparedOrientador.executeQuery();
            			if(resultadoOrientador.next()) {
            				Professor professor = new Professor();
            				
            				professor.setNome(resultadoOrientador.getString("nome"));
            				
            				aluno.professor = professor;
            			}
            		} catch(SQLException searchfailure) {
            			searchfailure.printStackTrace();
            		}
        		} catch(NullPointerException semProfessor) {
        			Professor professor = new Professor();
        			
        			professor.setIdprofessor(0);
        		}
        		return aluno;
        	} else {
        		String consultaTurma = "";
        		
        		String temTema = "SELECT titulo FROM turma_aluno" 
        				+ " JOIN turma ON turma_aluno.turma_idturma = turma.idturma" 
        				+ " JOIN tema ON turma.tema_idtema = tema.idtema"
        				+ " JOIN aluno ON turma_aluno.aluno_idaluno = aluno.idaluno" 
        				+ " WHERE ra = ?";
        		
        		try(PreparedStatement preparedTemTema =
        				this.conexao.prepareStatement(temTema);) {
        			preparedTemTema.setString(1, ra);
        			ResultSet resultadoTemTema = preparedTemTema.executeQuery();
        			if(resultadoTemTema.next()) {
        				consultaTurma = "SELECT idusuario, usuario.nome, email, fone, sexo, dt_nascimento, senha," 
                				+ "	ra, idturma, sigla, semestre_letivo, tema_idtema, titulo FROM turma_aluno" 
                				+ "	JOIN turma ON turma_aluno.turma_idturma = turma.idturma" 
                				+ "	JOIN tema ON turma.tema_idtema = tema.idtema" 
                				+ "	JOIN aluno ON turma_aluno.aluno_idaluno = aluno.idaluno" 
                				+ "	JOIN usuario ON aluno.idaluno = usuario.idusuario" 
                				+ "	WHERE ra = ?";
        			} else {
        				consultaTurma = "SELECT idusuario, usuario.nome, email, fone, sexo, dt_nascimento, senha," 
        						+ " ra, idturma, sigla, semestre_letivo, tema_idtema FROM turma_aluno" 
        						+ " JOIN turma ON turma_aluno.turma_idturma = turma.idturma"
        						+ " JOIN aluno ON turma_aluno.aluno_idaluno = aluno.idaluno" 
        						+ " JOIN usuario ON aluno.idaluno = usuario.idusuario" 
        						+ " WHERE ra = ?";
        			}
        		} catch(SQLException searchfailure) {
        			searchfailure.printStackTrace();
        		}
        		
        		try(PreparedStatement preparedConsultaTurma =
        				this.conexao.prepareStatement(consultaTurma);) {
        			preparedConsultaTurma.setString(1, ra);
        			ResultSet resultadoConsultaTurma = preparedConsultaTurma.executeQuery();
        			if(resultadoConsultaTurma.next()) {
        				Turma turma = new Turma();
        				Tema tema = new Tema();
        				
        				aluno.setIdaluno(resultadoConsultaTurma.getInt("idusuario"));
        				aluno.setNome(resultadoConsultaTurma.getString("usuario.nome"));
        				aluno.setEmail(resultadoConsultaTurma.getString("email"));
        				aluno.setFone(resultadoConsultaTurma.getString("fone"));
        				aluno.setSexo(resultadoConsultaTurma.getString("sexo"));
        				aluno.setDt_nascimento(resultadoConsultaTurma.getString("dt_nascimento"));
        				aluno.setSenha(resultadoConsultaTurma.getString("senha"));
        				aluno.setRa(resultadoConsultaTurma.getString("ra"));
        				turma.setIdTurma(resultadoConsultaTurma.getInt("idturma"));
        				turma.setSigla(resultadoConsultaTurma.getString("sigla"));
        				turma.setSemestreLetivo(resultadoConsultaTurma.getInt("semestre_letivo"));
        				if(resultadoConsultaTurma.getInt("tema_idtema") != 0) {
        					tema.setTitulo(resultadoConsultaTurma.getString("titulo"));
        				} else {
        					turma.setIdTema(0);
        				}
        				
        				aluno.turma = turma;
        				aluno.tema = tema;
        				return aluno;
        			} else {
        				String ConsultaAluno = "SELECT idaluno, nome, email, fone, sexo, dt_nascimento,"
                		+ " ra, senha FROM aluno"
                		+ " JOIN usuario ON aluno.idaluno = usuario.idusuario" 
                		+ " WHERE ra = ?";
                
		                try(PreparedStatement preparedConsultaAluno = 
		                        this.conexao.prepareStatement(ConsultaAluno);) {   
		                	preparedConsultaAluno.setString(1, ra);
		                    ResultSet resultadoConsultaAluno;            
		                    resultadoConsultaAluno = preparedConsultaAluno.executeQuery();
		                    if(resultadoConsultaAluno.next()) {
		                    	aluno.setIdaluno(resultadoConsultaAluno.getInt("idaluno"));
		                    	aluno.setNome(resultadoConsultaAluno.getString("usuario.nome"));
		                    	aluno.setEmail(resultadoConsultaAluno.getString("email"));
		                    	aluno.setFone(resultadoConsultaAluno.getString("fone"));
		                    	aluno.setSexo(resultadoConsultaAluno.getString("sexo"));
		                    	aluno.setDt_nascimento(resultadoConsultaAluno.getString("dt_nascimento"));
		                    	aluno.setRa(resultadoConsultaAluno.getString("ra"));
		                    	aluno.setSenha(resultadoConsultaAluno.getString("senha"));
		                    	return aluno;
		                    }
                } catch (SQLException searchfailure) {    
                	searchfailure.printStackTrace();
                }
        			}
        		} catch(SQLException searchfailure) {
        			searchfailure.printStackTrace();
        		}
        	}
        } catch(SQLException searchfailure) {
        	searchfailure.printStackTrace();
        }
        return null;
    }
    // [END] --   
    
    public String alterarDadosAluno(String nome, String emailAtual, String email,
    		String telefoneAtual, String telefone, String dt_nasc, String senha, String ra) {
    	String retorno = "";
    	boolean problema = false;
    	
    	if(!emailAtual.equals(email)) {
	    	String emailUtilizado = "SELECT email FROM usuario"
	    			+ " WHERE email = ?";
	    	
	    	try(PreparedStatement preparedEmailUtilizado =
	    			this.conexao.prepareStatement(emailUtilizado);) {
	    		preparedEmailUtilizado.setString(1, email);
	    		ResultSet resultadoEmailUtilizado = preparedEmailUtilizado.executeQuery();
	    		if(resultadoEmailUtilizado.next()) {
	    			retorno += "E-mail já está sendo utilizado!";
	    			problema = true;
	    		}
	    	} catch(SQLException searchfailure) {
	    		searchfailure.printStackTrace();
	    	}
    	}
    	
    	if(!telefoneAtual.equals(telefone)) {
	    	String numeroUtilizado = "SELECT fone FROM usuario"
	    			+ " WHERE fone = ?";
	    	
	    	try(PreparedStatement preparedNumeroUtilizado =
	    			this.conexao.prepareStatement(numeroUtilizado);) {
	    		preparedNumeroUtilizado.setString(1, telefone);
	    		ResultSet resultadoNumeroUtilizado = preparedNumeroUtilizado.executeQuery();
	    		if(resultadoNumeroUtilizado.next()) {
	    			retorno += "Número de telefone já está sendo utilizado!";
	    			problema = true;
	    		}
	    	} catch(SQLException searchfailure) {
	    		searchfailure.printStackTrace();
	    	}
    	}
    	
    	if(problema == false) {
	    	String alterarDadosAluno = "UPDATE aluno JOIN usuario ON aluno.idaluno = usuario.idusuario"
	    			+ " SET nome = ?, email = ?, fone = ?, dt_nascimento = ?, senha = ? WHERE ra = ?";
	    	
	    	try(PreparedStatement preparedAlterarDadosAluno =
	    			this.conexao.prepareStatement(alterarDadosAluno);) {
	    		preparedAlterarDadosAluno.setString(1, nome);
	    		preparedAlterarDadosAluno.setString(2, email);
	    		preparedAlterarDadosAluno.setString(3, telefone);
	    		preparedAlterarDadosAluno.setString(4, dt_nasc);
	    		preparedAlterarDadosAluno.setString(5, senha);
	    		preparedAlterarDadosAluno.setString(6, ra);
	    		preparedAlterarDadosAluno.execute();
	    		retorno = "Dados alterados com sucesso!";
	    		return retorno;
	    	} catch(SQLException changefailure) {
	    		changefailure.printStackTrace();
	    	}
    	} else {
    		return retorno;
    	}
    	return null;
    }
}
