package br.usjt.web.projetointegrado.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.usjt.web.projetointegrado.service.AlunoService;

@WebServlet("/AlterarDadosAluno.do")
public class AlterarDadosAluno extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public AlterarDadosAluno() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getSession().setAttribute("consultarGet", true);
		String retorno = "";
		
		String nomeAtual = (String)request.getSession().getAttribute("alunoNomeAtual");
		String emailAtual = (String)request.getSession().getAttribute("alunoEmailAtual");
		String telefoneAtual = (String)request.getSession().getAttribute("alunoTelefoneAtual");
		String dt_nascAtual = (String)request.getSession().getAttribute("alunoDtNascAtual");
		String senhaAtual = (String)request.getSession().getAttribute("alunoSenhaAtual");
		String ra = request.getParameter("ra");
		
		String nome = request.getParameter("inputNome");
		String email = request.getParameter("inputEmail");
		String telefone = request.getParameter("inputTelefone");
		String dt_nasc = request.getParameter("inputDtNasc");
		String senha = request.getParameter("inputSenha");
		
		AlunoService alunoService = new AlunoService();
		retorno = alunoService.alterarDadosAluno(nomeAtual, nome, emailAtual, email,
				telefoneAtual, telefone, dt_nascAtual, dt_nasc, senhaAtual, senha, ra);
		request.getSession().setAttribute("tryAlterarDadosAluno", retorno);
		System.out.println((String)request.getSession().getAttribute("tryAlterarDadosAluno"));
		
		request.getSession().setAttribute("alunoNomeAtual", null);
		request.getSession().setAttribute("alunoEmailAtual", null);
		request.getSession().setAttribute("alunoTelefoneAtual", null);
		request.getSession().setAttribute("alunoDtNascAtual", null);
		request.getSession().setAttribute("alunoSenhaAtual", null);
		
		response.sendRedirect("/Projeto_Interdisciplinar/ConsultarAluno.do?ra=" + ra);
	}

}
