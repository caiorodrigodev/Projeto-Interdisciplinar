package br.usjt.web.projetointegrado.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.usjt.web.projetointegrado.service.InscricaoService;

@WebServlet("/CadastrarInscricao.do")
public class CadastrarInscricao extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    public CadastrarInscricao() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nome = request.getParameter("inputNome");
		String sobrenome = request.getParameter("inputSobrenome");
		String email = request.getParameter("inputEmail");
		String fone = request.getParameter("inputTelefone");
		String dtNascimento = request.getParameter("inputDtNascimento");
		String senha = request.getParameter("inputSenha");
		String confirmarSenha = request.getParameter("inputConfirmarSenha");
		String sexo = request.getParameter("inputSexo");
		
		InscricaoService inscService = new InscricaoService();
		String respostaInscricao = inscService.cadastrarInscricao(nome, sobrenome,
				email, fone, dtNascimento, senha, confirmarSenha, sexo);
		request.getSession().setAttribute("tryCadastrarInscricao", respostaInscricao);
		response.sendRedirect("/Projeto_Interdisciplinar/cadastrar-inscricao.jsp");
	}

}
