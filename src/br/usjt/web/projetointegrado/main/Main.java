package br.usjt.web.projetointegrado.main;

import java.util.ArrayList;

import br.usjt.web.projetointegrado.model.Aluno;
import br.usjt.web.projetointegrado.model.Tema;
import br.usjt.web.projetointegrado.model.Turma;
import br.usjt.web.projetointegrado.service.AtividadeService;
import br.usjt.web.projetointegrado.service.TemaService;
import br.usjt.web.projetointegrado.service.TurmaService;


public class Main {
    
    public static void main(String[] args) {
    	
    	//////////////////////////////////////////INÍCIO TEMA //////////////////////////////////////////
    	
        /* ~~ CADASTRAR TEMA ~~
         * ▼ RETORNOS ESPERADOS ▼
         * Para títulos maiores que 100 caracteres: Título ultrapassou o limite de 100 caracteres!
         * Para introduções maiores que 250 caracteres: Introdução ultrapassou o limite de 250 caracteres!
         * Para requisitos maiores que 1000 caracteres: Requisito ultrapassou o limite de 1000 caracteres!
         * Para títulos iguais aos de outro tema: Não pode existir dois temas com títulos idênticos!
         * Para introduções iguais aos de outro tema: Não pode existir dois temas com introduções idênticas!
         * Para requisitos iguais aos de outro tema: Não pode existir dois temas com requisitos idênticos!
         * Para caso de sucesso: Tema cadastrado com sucesso!
         * ▼ TESTE ▼
         */
    	/*TemaService temaService = new TemaService();
        String titulo = "";
        String introducao = "";
        String requisitos = "";
        String retornoCaracteres = temaService.cadastrarTema(titulo, introducao, requisitos);
        System.out.println(retornoCaracteres);*/
        
    	/* ~~ DELETAR TEMA ~~
    	 * ▼ RETORNOS ESPERADOS ▼
    	 * Para tema com atividade já cadastrada: O tema está cadastrado em uma atividade já entregue!
    	 * Para caso de sucesso: Tema deletado com sucesso!
    	 * ▼ TESTE ▼
    	 */
    	/*TemaService temaService = new TemaService();
    	int idtema = 0;
    	String retorno = temaService.deletarTema(idtema);
        System.out.println(retorno);*/
        
    	/* ~~ CONSULTAR TEMA ~~
    	 * ▼ RETORNOS ESPERADOS ▼
    	 * Para nenhum tema encontrado: Não foi encontrado nenhum tema com esse título!
    	 * Para tema encontrado: Mostrar os dados desse tema.
    	 * ▼ TESTE ▼
    	 */
    	/*TemaService temaService = new TemaService();
    	String titulo = "";
    	Tema consultaTema = temaService.consultarTema(titulo);
        if(consultaTema != null) {
        	System.out.println("ID: " + consultaTema.getIdTema()
        			+ "\nData cadastro: " + consultaTema.getDt_cadastro()
        			+ "\nTítulo: " + consultaTema.getTitulo()
        			+ "\nIntrodução: " + consultaTema.getIntroducao()
        			+ "\nRequisitos: " + consultaTema.getRequisitos());
        } else {
        	System.out.println("Não foi encontrado nenhum tema com esse título!");
        }*/
        
    	/* ~~ ALTERAR TEMA ~~
    	 * ▼ RETORNOS ESPERADOS ▼
    	 * Para requisitos maiores que 1000 caracteres: Requisito ultrapassou o limite de 1000 caracteres!
    	 * Para tema com atividade já cadastrada: O tema está cadastrado em uma atividade já entregue!
    	 * Para requisitos iguais aos de outro tema: Não pode existir dois temas com requisitos idênticos!
    	 * Para caso de sucesso: Tema alterado com sucesso!
    	 * ▼ TESTE ▼
    	 */
    	/*TemaService temaService = new TemaService();
    	String requisitos = "";
    	int idtema = 0;
        String retorno = temaService.alterarTema(requisitosCaracteres, idtema);
        System.out.println(retorno);*/
        
    	/* ~~ LISTAR TEMAS ~~
    	 * ▼ RETORNOS ESPERADOS ▼
    	 * Para nenhuma turma encontrada: Ainda não possuí­mos nenhum tema cadastrado.
    	 * Para turmas encontradas: Listar todas as turmas.
    	 * ▼ TESTE ▼
    	 */
    	/*TemaService temaService = new TemaService();
        ArrayList<Tema> listarTemas = temaService.listarTemas();
        if(listarTemas != null) {
        	for(int i = 0; i < listarTemas.size(); i++) {
        		System.out.println("ID: " + listarTemas.get(i).getIdTema()
    					+ "\nData cadastro: " + listarTemas.get(i).getDt_cadastro()
		    			+ "\nTítulo: " + listarTemas.get(i).getTitulo()
		    			+ "\nIntrodução: " + listarTemas.get(i).getIntroducao()
		    			+ "\nRequisitos: " + listarTemas.get(i).getRequisitos());
        	}
        } else {
        	System.out.println("Ainda não possuí­mos nenhum tema cadastrado.");
        }*/
    	
    	////////////////////////////////////////// FIM TEMA //////////////////////////////////////////
    	
    	//////////////////////////////////////// INÍCIO TURMA ////////////////////////////////////////
    	
    	/* ~~ CADASTRAR TURMA ~~
    	 * ▼ RETORNOS ESPERADOS ▼
    	 * Para siglas maiores que 10 caracteres: Sigla passou o limite de 10 caracteres!
    	 * Para semestres maiores que 2: O ano é composto por apenas 2 semestres!
    	 * Para anos menores que o atual: Uma nova turma não pode ser cadastrada antes do ano atual!
    	 * Para siglas iguais as de outra turma: Sigla já está em uso!
    	 * Para caso de sucesso: Turma cadastrada com sucesso!
    	 * ▼ TESTE ▼
    	 */
    	/*TurmaService turmaService = new TurmaService();
    	String sigla = "";
    	int semestreLetivo = 0;
    	int anoLetivo = 0;
    	int idtema = 0;
    	String retorno = turmaService.cadastrarTurma(sigla, semestreLetivo, anoLetivo, idtema);
    	System.out.println(retorno);*/
    	
    	/* ~~ DELETAR TURMA ~~
    	 * ▼ RETORNOS ESPERADOS ▼
    	 * Para caso de sucesso: Turma deletada com sucesso!
    	 * ▼ TESTE ▼
    	 */
    	/*TurmaService turmaService = new TurmaService();
    	int idturma = 0;
    	String retorno = turmaService.deletarTurma(idturma);
    	System.out.println(retorno);*/
    	
    	/* ~~ CONSULTAR TURMA ~~
    	 * ▼ RETORNOS ESPERADOS ▼
    	 * Para nenhuma turma encontrada: Não foi encontrada nenhuma turma com esse ID!
    	 * Para turma encontrada: Mostrar os dados dessa turma.
    	 * Para turma com tema definido: Título do tema.
    	 * Para turma sem tema definido: SEM TEMA.
    	 * Para turma com alunos definidos: Quantidade de alunos igual x alunos e lista todos abaixo.
    	 * Para turma sem alunos definidos: Quantidade de alunos igual a 0.
    	 * ▼ TESTE ▼
    	 */
    	/*TurmaService turmaService = new TurmaService();
    	String retorno = "";
    	int idturma = 0;
    	Turma consultaTurma = turmaService.consultarTurma(idturma);
    	ArrayList<Aluno> qtdAluno = turmaService.alunosDaTurma(idturma);
        if(consultaTurma != null) {
        	retorno += "ID: " + consultaTurma.getIdTurma()
        			+ "\nSigla: " + consultaTurma.getSigla()
        			+ "\nSemestre letivo: " + consultaTurma.getSemestreLetivo()
        			+ "\nAno letivo: " + consultaTurma.getAnoLetivo();
        	try {
    			if(consultaTurma.tema.getTitulo() != null) {
    				retorno += "\nTema: " + consultaTurma.tema.getTitulo();
    			}
        	} catch(NullPointerException nulo) {
        		retorno += "\nTema: SEM TEMA";
        	}
        	retorno += "\nQtd alunos: " + qtdAluno.size();
        	
        	for(int i = 0; i < qtdAluno.size(); i++) {
        		retorno += "\nID: " + qtdAluno.get(i).getIdaluno()
        				+ "\nNome: " + qtdAluno.get(i).getNome()
        				+ "\nE-mail: " + qtdAluno.get(i).getEmail()
        				+ "\nFone: " + qtdAluno.get(i).getFone()
        				+ "\nSexo: " + qtdAluno.get(i).getSexo()
        				+ "\nData nasc.: " + qtdAluno.get(i).getDt_nascimento()
        				+ "\nR.A.: " + qtdAluno.get(i).getRa();
        	}
        	System.out.println(retorno);
        } else {
        	System.out.println("Não foi encontrada nenhuma turma com esse ID!");
        }*/
    	
    	/* ~~ ALETERAR TURMA ~~
    	 * ▼ RETORNOS ESPERADOS ▼
    	 * Para caso de sucesso: Turma alterada com sucesso!
    	 * ▼ TESTE ▼
    	 */
    	/*TurmaService turmaService = new TurmaService();
    	int idtema = 0;
    	int idturma = 0;
    	String retorno = turmaService.alterarTema(idtema, idturma);
    	System.out.println(retorno);*/
    	
    	/* ~~ CADASTRAR ATIVIDADE ~~
    	 * ▼ RETORNOS ESPERADOS ▼
    	 * Para alunos que não tem turmas ou grupos definidos ou turma sem tema definido: Você não está em nenhuma turma/grupo ou sua turma não tem um tema definido.
    	 * Para atividades já cadastradas por outro membro do grupo: A atividade deste grupo já está cadastrada!
    	 * Para caso de sucesso: Atividade cadastrada com sucesso!
    	 * ▼ TESTE ▼
    	 */
    	/*AtividadeService atvService = new AtividadeService();
    	int idaluno = 1;
    	String descricao = "";
    	String dt_inicio = "";
    	String dt_fim = "";
    	String retorno = atvService.cadastrarAtividade(descricao, dt_inicio, dt_fim, idaluno);
    	System.out.println(retorno);*/
    }   
}
