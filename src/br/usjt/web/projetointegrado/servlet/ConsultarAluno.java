package br.usjt.web.projetointegrado.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.usjt.web.projetointegrado.model.Aluno;
import br.usjt.web.projetointegrado.service.AlunoService;

@WebServlet("/ConsultarAluno.do")
public class ConsultarAluno extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public ConsultarAluno() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getSession().setAttribute("consultarGet", true);
		
		String ra = request.getParameter("ra");
		
		try {
			int raInt = Integer.parseInt(ra);
			AlunoService alunoService = new AlunoService();
			Aluno respostaAlunoService = alunoService.consultarAluno(ra);
			request.getSession().setAttribute("tryConsultarAluno", respostaAlunoService);
			response.sendRedirect("/Projeto_Interdisciplinar/consultar-aluno.jsp?ra=" + ra);
		} catch(NumberFormatException formatfailure) {
			if(request.getSession().getAttribute("tryDeletarAluno").equals("Aluno deletado com sucesso!")) {
				request.getSession().setAttribute("tryConsultarAluno", null);
				response.sendRedirect("/Projeto_Interdisciplinar/consultar-aluno.jsp");
			} else {
				response.sendRedirect("/Projeto_Interdisciplinar/consultar-aluno.jsp?ra=" + ra);
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getSession().setAttribute("consultarGet", true);
		
		String retorno = "";
		String ra = request.getParameter("inputRA");
		
		if(!ra.equals("")) {
			try {
				int raInt = Integer.parseInt(ra);
				AlunoService alunoService = new AlunoService();
				Aluno respostaAlunoService = alunoService.consultarAluno(ra);
				if(respostaAlunoService != null) {
					request.getSession().setAttribute("tryConsultarAluno", respostaAlunoService);
					response.sendRedirect("/Projeto_Interdisciplinar/consultar-aluno.jsp?ra=" + ra);
				} else {
					retorno = "Nenhum aluno foi encontrado com esse R.A..";
					request.getSession().setAttribute("tryConsultarAluno", retorno);
					response.sendRedirect("/Projeto_Interdisciplinar/consultar-aluno.jsp");
				}
			} catch(NumberFormatException formatfailure) {
				retorno = "Certifique-se de que preencheu todos os campos"
						+ " corretamente!";
				request.getSession().setAttribute("tryConsultarAluno", retorno);
				response.sendRedirect("/Projeto_Interdisciplinar/consultar-aluno.jsp");
			}
		} else {
			retorno = "Preencha todos os campos!";
			request.getSession().setAttribute("tryConsultarAluno", retorno);
			response.sendRedirect("/Projeto_Interdisciplinar/consultar-aluno.jsp");
		}
	}
}
