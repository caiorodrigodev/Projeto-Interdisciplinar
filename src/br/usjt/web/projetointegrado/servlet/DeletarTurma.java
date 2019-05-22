package br.usjt.web.projetointegrado.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.usjt.web.projetointegrado.service.TurmaService;

@WebServlet("/DeletarTurma.do")
public class DeletarTurma extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public DeletarTurma() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String retorno = "";
		int idturma = Integer.parseInt(request.getParameter("id"));
		
		TurmaService turmaService = new TurmaService();
		retorno = turmaService.deletarTurma(idturma);
		request.getSession().setAttribute("tryDeletarTurma", retorno);
		if(retorno.equals("Turma deletada com sucesso!")) {
			response.sendRedirect("/Projeto_Interdisciplinar/ConsultarTurma.do");
		} else {
			response.sendRedirect("/Projeto_Interdisciplinar/ConsultarTurma.do?id=" + idturma);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
