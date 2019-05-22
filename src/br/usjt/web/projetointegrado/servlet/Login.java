package br.usjt.web.projetointegrado.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.usjt.web.projetointegrado.model.Usuario;
import br.usjt.web.projetointegrado.service.UsuarioService;

@WebServlet("/Login.do")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public Login() {
        super();
        
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		HttpSession session = request.getSession();	
		String retorno = "";
		String ra = request.getParameter("inputRA");
		String senha = request.getParameter("inputSenha");
		
		UsuarioService userService = new UsuarioService();
		if(userService.login(ra, senha) != null) {
			Usuario user = userService.login(ra, senha);
			if(user.professor == null) {
				session.setAttribute("idusuario", user.aluno.getIdaluno());
				session.setAttribute("nome", user.aluno.getNome());
				session.setAttribute("email", user.aluno.getEmail());
				session.setAttribute("fone", user.aluno.getFone());
				session.setAttribute("sexo", user.aluno.getSexo());
				session.setAttribute("dt_nascimento", user.aluno.getDt_nascimento());
				session.setAttribute("senha", user.aluno.getSenha());
				session.setAttribute("ra", user.aluno.getRa());
			} else {
				session.setAttribute("idusuario", user.professor.getIdprofessor());
				session.setAttribute("nome", user.professor.getNome());
				session.setAttribute("email", user.professor.getEmail());
				session.setAttribute("fone", user.professor.getFone());
				session.setAttribute("sexo", user.professor.getSexo());
				session.setAttribute("dt_nascimento", user.professor.getDt_nascimento());
				session.setAttribute("senha", user.professor.getSenha());
				session.setAttribute("ra", user.professor.getMatricula());
				session.setAttribute("admin", user.professor.getAdministrador());
			}
		} else {
			retorno = "R.A. ou Senha inv�lidos!";
			session.setAttribute("trylogin", retorno);
		}
		response.sendRedirect("/Projeto_Interdisciplinar/index.jsp");
	}
}
