package br.usjt.web.projetointegrado.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.usjt.web.projetointegrado.service.TemaService;

@WebServlet("/CadastrarTema.do")
public class CadastrarTema extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public CadastrarTema() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String retorno = "";
		String titulo = request.getParameter("inputTitulo");
		String introducao = request.getParameter("textareaIntroducao");
		String requisitos = request.getParameter("textareaRequisitos");
		
		TemaService temaService = new TemaService();
		retorno = temaService.cadastrarTema(titulo, introducao, requisitos);
		request.getSession().setAttribute("tryCadastrarTema", retorno);
		response.sendRedirect("/Projeto_Interdisciplinar/cadastrar-tema.jsp");
	}
}
