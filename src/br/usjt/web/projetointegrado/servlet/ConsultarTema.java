package br.usjt.web.projetointegrado.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.usjt.web.projetointegrado.model.Tema;
import br.usjt.web.projetointegrado.service.TemaService;

@WebServlet("/ConsultarTema.do")
public class ConsultarTema extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public ConsultarTema() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getSession().setAttribute("consultarGet", true);
		
		String idtema = request.getParameter("id");
		
		try {
			int idtemaInt = Integer.parseInt(idtema);
			TemaService temaService = new TemaService();
			Tema respostaTemaService = temaService.consultarTema(idtemaInt);
			request.getSession().setAttribute("tryConsultarTema", respostaTemaService);
			response.sendRedirect("/Projeto_Interdisciplinar/consultar-tema.jsp?id=" + idtema);
		} catch(NumberFormatException formatfailure) {
			if(request.getSession().getAttribute("tryDeletarTema").equals("Tema deletado com sucesso!")) {
				request.getSession().setAttribute("tryConsultarTema", null);
				response.sendRedirect("/Projeto_Interdisciplinar/consultar-tema.jsp");
			} else {
				response.sendRedirect("/Projeto_Interdisciplinar/consultar-tema.jsp?id=" + idtema);
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getSession().setAttribute("consultarGet", true);
		
		String retorno = "";
		String idtema = request.getParameter("inputIDTema");
		
		if(!idtema.equals("")) {
			try {
				int idtemaInt = Integer.parseInt(idtema);
				TemaService temaService = new TemaService();
				Tema respostaTemaService = temaService.consultarTema(idtemaInt);
				if(respostaTemaService != null) {
					request.getSession().setAttribute("tryConsultarTema", respostaTemaService);
					response.sendRedirect("/Projeto_Interdisciplinar/consultar-tema.jsp?id=" + idtema);
				} else {
					retorno = "Não foi encontrado nenhum tema com esse ID!";
					request.getSession().setAttribute("tryConsultarTema", retorno);
					response.sendRedirect("/Projeto_Interdisciplinar/consultar-tema.jsp");
				}
			} catch(NumberFormatException formatfail) {
				retorno = "Certifique-se de que preencheu"
						+ " os campos corretamente!";
				request.getSession().setAttribute("tryConsultarTema", retorno);
				response.sendRedirect("/Projeto_Interdisciplinar/consultar-tema.jsp");
			}
		} else {
			retorno = "Preencha todos os campos!";
			request.getSession().setAttribute("tryConsultarTema", retorno);
			response.sendRedirect("/Projeto_Interdisciplinar/consultar-tema.jsp");
		}
	}
}
