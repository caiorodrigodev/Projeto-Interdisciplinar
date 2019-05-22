package br.usjt.web.projetointegrado.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.usjt.web.projetointegrado.service.AlunoService;

@WebServlet("/DeletarAluno.do")
public class DeletarAluno extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public DeletarAluno() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String retorno = "";
		int idaluno = Integer.parseInt(request.getParameter("id"));
		
		AlunoService alunoService = new AlunoService();
		retorno = alunoService.deletarAluno(idaluno);
		request.getSession().setAttribute("tryDeletarAluno", retorno);
		if(retorno.equals("Aluno deletado com sucesso!")) {
			response.sendRedirect("/Projeto_Interdisciplinar/ConsultarAluno.do");
		} else {
			response.sendRedirect("/Projeto_Interdisciplinar/ConsultarAluno.do?ra=" + request.getSession().getAttribute("raConsultado"));
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
