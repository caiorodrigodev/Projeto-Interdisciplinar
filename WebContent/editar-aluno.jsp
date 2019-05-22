<%@page import="br.usjt.web.projetointegrado.model.Aluno"%>
<%@page import="br.usjt.web.projetointegrado.service.AlunoService"%>

<%
String ra = request.getParameter("ra");

AlunoService alunoService = new AlunoService();
Aluno consultaAlunoService = alunoService.consultarAluno(ra);
/*String teste = "ID: " + consultaAlunoService.getIdaluno()
		+ "\nNome: " + consultaAlunoService.getNome()
		+ "\nE-mail: " + consultaAlunoService.getEmail()
		+ "\nFone: " + consultaAlunoService.getFone()
		+ "\nSexo: " + consultaAlunoService.getSexo()
		+ "\nData Nasc.: " + consultaAlunoService.getDt_nascimento()
		+ "\nSenha: " + consultaAlunoService.getSenha()
		+ "\nR.A.: " + consultaAlunoService.getRa();
try {
	if(consultaAlunoService.turma.getSigla() != null) {
		teste += "\nID turma: " + consultaAlunoService.turma.getIdTurma()
			+ "\nSigla: " + consultaAlunoService.turma.getSigla()
			+ "\nSemestre: " + consultaAlunoService.turma.getSemestreLetivo();
		if(consultaAlunoService.turma.getIdTema() != 0) {
			teste += "\nTema: " + consultaAlunoService.tema.getTitulo();
		} else {
			teste += "\nTema: SEM TEMA"; 
		}
	}
} catch(NullPointerException nulo) {
	
}
try {
	if(consultaAlunoService.grupo.getNome() != null) {
		teste += "\nID grupo: " + consultaAlunoService.grupo.getIdGrupo()
			+ "\nNome do grupo: " + consultaAlunoService.grupo.getNome();
			if(consultaAlunoService.grupo.getIdProfessor() != 0) {
				teste += "\nOrientador: " + consultaAlunoService.professor.getNome();
			} else {
				teste += "\nOrientador: SEM ORIENTADOR";
			}
	}
} catch(NullPointerException nulo) { 
	
}
System.out.println(teste);*/
request.getSession().setAttribute("alunoNomeAtual", consultaAlunoService.getNome());
request.getSession().setAttribute("alunoEmailAtual", consultaAlunoService.getEmail());
request.getSession().setAttribute("alunoTelefoneAtual", consultaAlunoService.getFone());
request.getSession().setAttribute("alunoDtNascAtual", consultaAlunoService.getDt_nascimento());
request.getSession().setAttribute("alunoSenhaAtual", consultaAlunoService.getSenha());
%>

<%@ include file='include/template/organisms/top.jsp' %>
<% String titulo = "Editar Aluno"; %>

<title><%=titulo%></title>

<div class="container col-8 p-5">
	<h1>
		<p class="text-primary mt-2 mb-3"><%=titulo%></p>
	</h1>
	<form action="AlterarDadosAluno.do?ra=<%=ra%>" method="post">

		<div class="form-row">
			<div class="form-group col-md-6">
				<label>Nome</label>
				<input type="text" class="form-control"	name="inputNome" value="<%=consultaAlunoService.getNome()%>" required>
			</div>
			<div class="form-group col-md-6">
				<label>E-mail</label>
				<input type="email"	name="inputEmail" class="form-control" value="<%=consultaAlunoService.getEmail()%>" required>
			</div>
		</div>

		<div class="form-row">
			<div class="form-group col-md-4">
				<label>Telefone</label>
				<input type="text" name="inputTelefone" id="cel" class="form-control" value="<%=consultaAlunoService.getFone()%>" required>
			</div>
			<div class="form-group col-md-4">
				<label>Data de Nascimento</label>
				<input type="text" class="form-control" name="inputDtNasc" id="dataNascimento" value="<%=consultaAlunoService.getDt_nascimento()%>">
			</div>
			<div class="form-group col-md-4">
				<label>Senha</label>
				<input type="password" class="form-control" name="inputSenha" value="<%=consultaAlunoService.getSenha()%>">
			</div>
		</div>
		
		<div class="form-row">
            <div class="form-group col-6">
            <label>Turma</label>
                <select name="selectTurma" class="form-control">
                    <option selected>Selecionar turma</option>
					<!-- CÓDIGO AQUI -->
                </select>
            </div>
            <div class="form-group col-6">
            <label>Grupo</label>
                <select name="selectGrupo" class="form-control">
                    <option selected>Selecionar grupo</option>
					<!-- CÓDIGO AQUI -->
                </select>
            </div>
		</div>
		<div class="form-group my-3">
			<button type="submit" class="btn btn-primary">Salvar dados</button>
		</div>
	</form>
</div>

<%
String respostaAlterarDados = (String)request.getSession().getAttribute("tryAlterarDados");
String scriptAlterarDados = "";
if(respostaAlterarDados == null) {
	respostaAlterarDados = "";
} else {
	scriptAlterarDados = "alert('" + respostaAlterarDados + "');";
	request.getSession().setAttribute("tryAlterarDados", null);
}
%>	

<script>
<%
if(!scriptAlterarDados.equals("alert('');")) {
%>
<%= scriptAlterarDados %>
<%
}
%>
</script>

<%@ include file ="include/template/organisms/bottom.jsp" %>