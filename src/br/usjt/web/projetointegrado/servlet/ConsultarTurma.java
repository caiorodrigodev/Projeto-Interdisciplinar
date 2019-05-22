package br.usjt.web.projetointegrado.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.usjt.web.projetointegrado.model.Aluno;
import br.usjt.web.projetointegrado.model.Professor;
import br.usjt.web.projetointegrado.model.Turma;
import br.usjt.web.projetointegrado.service.ProfessorService;
import br.usjt.web.projetointegrado.service.TurmaService;

@WebServlet("/ConsultarTurma.do")
public class ConsultarTurma extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public ConsultarTurma() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	request.getSession().setAttribute("tryConsultarTurma", null);
    	request.getSession().setAttribute("tryConsultarAlunos", null);	
	}
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getSession().setAttribute("consultarGet", true);
		String retorno = "";
		
		String idturma = request.getParameter("inputIDTurma");
		
		if(!idturma.equals("")) {
			try {
				int idturmaInt = Integer.parseInt(idturma);
				TurmaService turmaService = new TurmaService();
				Turma turma = turmaService.consultarTurma(idturmaInt);
				if(turma != null) {
					request.getSession().setAttribute("tryConsultarTurma", turma);
					ArrayList<Aluno> alunosTurma = turmaService.alunosDaTurma(idturmaInt);
					request.getSession().setAttribute("tryConsultarAlunos", alunosTurma);	
					response.sendRedirect("/Projeto_Interdisciplinar/consultar-turma.jsp?id=" + idturma);
				} else {
					retorno = "Não foi encontrada nenhuma turma com esse ID!";
					request.getSession().setAttribute("tryConsultarTurma", retorno);
					response.sendRedirect("/Projeto_Interdisciplinar/consultar-turma.jsp");
				}
			} catch(NumberFormatException formatfailure) {
				formatfailure.printStackTrace();
				retorno = "Certifique-se de que preencheu"
						+ " os campos corretamente!";
				request.getSession().setAttribute("tryConsultarTurma", retorno);
				response.sendRedirect("/Projeto_Interdisciplinar/consultar-turma.jsp");
			}
		} else {
			retorno = "Preencha todos os campos!";
			request.getSession().setAttribute("tryConsultarTurma", retorno);
			response.sendRedirect("/Projeto_Interdisciplinar/consultar-turma.jsp");
		}
	}
}
