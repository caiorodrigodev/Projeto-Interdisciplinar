package br.usjt.web.projetointegrado.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.usjt.web.projetointegrado.service.UsuarioService;

@WebServlet("/AlterarDados.do")
public class AlterarDados extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public AlterarDados() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String retorno = "";
		
		int idusuario = (int)request.getSession().getAttribute("idusuario");
		String emailAtual = (String)request.getSession().getAttribute("email");
		String email = request.getParameter("inputEmail");
		String foneAtual = (String)request.getSession().getAttribute("fone");
		String fone = request.getParameter("inputTelefone");
		boolean problema = false;
		
		if(email.length() > 30) {
			retorno += "O e-mail ultrapassou o limite de caracteres!";
			problema = true;
		}
		if(fone.length() > 15) {
			retorno += "O telefone ultrapasssou o limite de caracteres!";
			problema = true;
		}
		
		if(problema == false) {
			if(!email.equals("") && !fone.equals("")) {
				UsuarioService userService = new UsuarioService();
				ArrayList<String> alteracoes = userService.alterarDados(emailAtual, email, foneAtual, fone, idusuario);
				if(alteracoes.size() > 0) {
					for(int i = 0; i < alteracoes.size(); i++) {
						if(alteracoes.get(i).equals("fone")) {
							retorno = "Dados alterados com sucesso!";
							request.getSession().setAttribute("fone", fone);
						} else {
							retorno = "Dados alterados com sucesso!";
							request.getSession().setAttribute("email", email);
						}
					}
				}
			} else {
				retorno = "Preencha todos os campos!";
			}
		}
		request.getSession().setAttribute("tryAlterarDados", retorno);
		response.sendRedirect("/Projeto_Interdisciplinar/editar-perfil.jsp");
	}
}
