package br.usjt.web.projetointegrado.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.usjt.web.projetointegrado.model.Professor;
import br.usjt.web.projetointegrado.service.ProfessorService;

@WebServlet("/ConsultarProfessor.do")
public class ConsultarProfessor extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public ConsultarProfessor() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getSession().setAttribute("consultarGet", true);
		
		String matricula = request.getParameter("mat");
		
		try {
			int matriculaInt = Integer.parseInt(matricula);
			ProfessorService profService = new ProfessorService();
			Professor respostaProfService = profService.consultarProfessor(matricula);
			request.getSession().setAttribute("tryConsultarProfessor", respostaProfService);
			response.sendRedirect("/Projeto_Interdisciplinar/consultar-professor.jsp?mat=" + matricula);
		} catch(NumberFormatException formatfailure) {
			if(request.getSession().getAttribute("tryDeletarProfessor").equals("Professor deletado com sucesso!")) {
				request.getSession().setAttribute("tryConsultarProfessor", null);
				response.sendRedirect("/Projeto_Interdisciplinar/consultar-professor.jsp");
			} else {
				response.sendRedirect("/Projeto_Interdisciplinar/consultar-professor.jsp?mat=" + matricula);
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getSession().setAttribute("consultarGet", true);
		String retorno = "";
		
		String matricula = request.getParameter("inputMatricula");
		
		if(!matricula.equals("")) {
			try {
				int matriculaInt = Integer.parseInt(matricula);
				ProfessorService profService = new ProfessorService();
				Professor respostaProfService = profService.consultarProfessor(matricula);
				if(respostaProfService != null) {
					request.getSession().setAttribute("tryConsultarProfessor", respostaProfService);
					response.sendRedirect("/Projeto_Interdisciplinar/consultar-professor.jsp?mat=" + matricula);
				} else {
					retorno = "Nenhum professor encontrado com essa matricula!";
					request.getSession().setAttribute("tryConsultarProfessor", retorno);
					response.sendRedirect("/Projeto_Interdisciplinar/consultar-professor.jsp");
				}
			} catch(NumberFormatException formatfailure) {
				retorno = "Certifique-se de que preencheu todos os campos"
						+ " corretamente!";
				request.getSession().setAttribute("tryConsultarProfessor", retorno);
				response.sendRedirect("/Projeto_Interdisciplinar/consultar-professor.jsp");
			}
		} else {
			retorno = "Preencha todos os campos!";
			request.getSession().setAttribute("tryConsultarProfessor", retorno);
			response.sendRedirect("/Projeto_Interdisciplinar/consultar-professor.jsp");
		}
	}

}
