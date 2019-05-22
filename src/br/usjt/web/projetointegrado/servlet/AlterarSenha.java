package br.usjt.web.projetointegrado.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.usjt.web.projetointegrado.service.UsuarioService;

@WebServlet("/AlterarSenha.do")
public class AlterarSenha extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
    public AlterarSenha() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		String retorno = "";
		
		int idusuario = (int)request.getSession().getAttribute("idusuario");
		String senhaAtual = (String)request.getSession().getAttribute("senha");
		String senha = request.getParameter("inputNovaSenha");
		String confirmarSenha = request.getParameter("inputConfirmarSenha");
		
		UsuarioService userService = new UsuarioService();
		retorno = userService.alterarSenha(senhaAtual, senha, confirmarSenha, idusuario);
		request.getSession().setAttribute("tryAlterarSenha", retorno);
		if(retorno.equals("Senha alterada com sucesso!")) {
			request.getSession().setAttribute("senha", senha);
		}
		response.sendRedirect("/Projeto_Interdisciplinar/alterar-senha.jsp");
	}
}