package br.usjt.web.projetointegrado.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.usjt.web.projetointegrado.service.TurmaService;

@WebServlet("/CadastrarTurma.do")
public class CadastrarTurma extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public CadastrarTurma() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String retorno = "";
		
		String sigla = request.getParameter("inputSigla");
		try {
			int semestre_letivo = Integer.parseInt(request.getParameter("selectSemeste"));
			int ano_letivo = Integer.parseInt(request.getParameter("selectAno"));
			int idtema = Integer.parseInt(request.getParameter("selectTema"));
			System.out.println("semestre" + semestre_letivo + "\nano" + ano_letivo);
			TurmaService turmaService = new TurmaService();
			retorno = turmaService.cadastrarTurma(sigla, semestre_letivo, ano_letivo, idtema);
			request.getSession().setAttribute("tryCadastrarTurma", retorno);
			response.sendRedirect("/Projeto_Interdisciplinar/cadastrar-turma.jsp");
		} catch(NumberFormatException formatfailure) {
			formatfailure.printStackTrace();
			retorno = "Certifique-se de que preencheu os campos"
					+ " corretamente!";
			request.getSession().setAttribute("tryCadastrarTurma", retorno);
			response.sendRedirect("/Projeto_Interdisciplinar/cadastrar-turma.jsp");
		}
	}

}
