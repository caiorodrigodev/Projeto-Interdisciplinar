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
import br.usjt.web.projetointegrado.model.Inscricao;
import br.usjt.web.projetointegrado.service.AlunoService;
import br.usjt.web.projetointegrado.service.InscricaoService;
import br.usjt.web.projetointegrado.service.TurmaService;

@WebServlet("/Situacao.do")
public class Situacao extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public Situacao() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String retorno = "";
		int idinscricao = Integer.parseInt(request.getParameter("id"));
		String situacao = request.getParameter("cond");
		
		if(situacao.equals("accept")) {
			AlunoService alunoService = new AlunoService();
			retorno = alunoService.cadastrarAluno(idinscricao);
			request.getSession().setAttribute("trySituacao", retorno);
			response.sendRedirect("/Projeto_Interdisciplinar/ListarInscricoes.do");
		} else {
			InscricaoService inscricaoService = new InscricaoService();
			inscricaoService.excluirInscricao(idinscricao);
			response.sendRedirect("/Projeto_Interdisciplinar/ListarInscricoes.do");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
