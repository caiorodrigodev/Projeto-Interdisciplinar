package br.usjt.web.projetointegrado.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.usjt.web.projetointegrado.service.TemaService;

@WebServlet("/DeletarTema.do")
public class DeletarTema extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public DeletarTema() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String retorno = "";
		int idtema = Integer.parseInt(request.getParameter("id"));
		
		TemaService temaService = new TemaService();
		retorno = temaService.deletarTema(idtema);
		request.getSession().setAttribute("tryDeletarTema", retorno);
		if(retorno.equals("Tema deletado com sucesso!")) {
			response.sendRedirect("/Projeto_Interdisciplinar/ConsultarTema.do");
		} else {
			response.sendRedirect("/Projeto_Interdisciplinar/ConsultarTema.do?id=" + idtema);
		}		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
}
