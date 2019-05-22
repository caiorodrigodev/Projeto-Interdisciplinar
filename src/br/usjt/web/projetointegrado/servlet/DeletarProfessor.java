package br.usjt.web.projetointegrado.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.usjt.web.projetointegrado.service.ProfessorService;

@WebServlet("/DeletarProfessor.do")
public class DeletarProfessor extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public DeletarProfessor() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String retorno = "";
		
		int idprofessor = Integer.parseInt(request.getParameter("id"));
		
		ProfessorService profService = new ProfessorService();
		retorno = profService.deletarProfessor(idprofessor);
		request.getSession().setAttribute("tryDeletarProfessor", retorno);
		if(retorno.equals("Professor deletado com sucesso!")) {
			response.sendRedirect("/Projeto_Interdisciplinar/ConsultarProfessor.do");
		} else {
			response.sendRedirect("/Projeto_Interdisciplinar/ConsultarProfessor.do?mat=" + request.getSession().getAttribute("matriculaConsultado"));
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
