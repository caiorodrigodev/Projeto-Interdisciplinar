package br.usjt.web.projetointegrado.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.usjt.web.projetointegrado.service.AtividadeService;

@WebServlet("/CadastrarAtividade.do")
public class CadastrarAtividade extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public CadastrarAtividade() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			String retorno = "";
			
			int idaluno = Integer.parseInt(request.getParameter("id"));
			String descricao = request.getParameter("textareaDescricao");
			String dt_inicio = request.getParameter("dataInicial");
			String dt_fim = request.getParameter("dataFinal");
			
			AtividadeService atvService = new AtividadeService();
			retorno = atvService.cadastrarAtividade(descricao, dt_inicio, dt_fim, idaluno);
			request.getSession().setAttribute("tryCadastrarAtividade", retorno);
			response.sendRedirect("/Projeto_Interdisciplinar/cadastrar-atividade.jsp?id=" + idaluno);
	}

}
