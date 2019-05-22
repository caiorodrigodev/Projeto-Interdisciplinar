package br.usjt.web.projetointegrado.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.usjt.web.projetointegrado.model.Inscricao;
import br.usjt.web.projetointegrado.service.InscricaoService;

@WebServlet("/ListarInscricoes.do")
public class ListarInscricoes extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public ListarInscricoes() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		InscricaoService inscService = new InscricaoService();
		ArrayList<Inscricao> inscricoes = inscService.listarInscricoes();
		request.getSession().setAttribute("tryInscricoes", inscricoes);
		response.sendRedirect("/Projeto_Interdisciplinar/listar-inscricoes.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
