package br.usjt.web.projetointegrado.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.usjt.web.projetointegrado.service.TemaService;

@WebServlet("/AlterarTema.do")
public class AlterarTema extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
    public AlterarTema() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String retorno = "";
		int idtema = Integer.parseInt(request.getParameter("id"));
		String requisitos = request.getParameter("textareaRequisitos");
		
		
		TemaService temaService = new TemaService();
		retorno = temaService.alterarTema(requisitos, idtema);
		request.getSession().setAttribute("tryAlterarTema", retorno);
		if(retorno.equals("Tema alterado com sucesso!")
				|| retorno.equals("O tema está cadastrado em uma atividade já entregue!")) {
			response.sendRedirect("/Projeto_Interdisciplinar/ConsultarTema.do?id=" + idtema);
		} else {
			response.sendRedirect("/Projeto_Interdisciplinar/editar-tema.jsp?id=" + idtema);
		}			
	}
}
