<%@page import="br.usjt.web.projetointegrado.model.Professor"%>
<%@page import="br.usjt.web.projetointegrado.service.ProfessorService"%>

<%
String matricula = request.getParameter("mat");

ProfessorService profService = new ProfessorService();
Professor consultaProfService = profService.consultarProfessor(matricula);
%>

<%@ include file='include/template/organisms/top.jsp' %>
<% String titulo = "Editar Professor"; %>

<title><%=titulo%></title>

<div class="container col-8 p-5">
	<h1>
		<p class="text-primary mt-2 mb-3"><%=titulo%></p>
	</h1>
	<form action="AlterarProfessor.do" method="post">

		<div class="form-row">
			<div class="form-group col-md-6">
				<label>Nome</label>
				<input type="text" class="form-control"	name="nome" value="<%=consultaProfService.getNome()%>" required>
			</div>
			<div class="form-group col-md-6">
				<label>E-mail</label>
				<input type="email"	name="inputEmail" class="form-control" value="<%=consultaProfService.getEmail()%>" required>
			</div>
		</div>

		<div class="form-row">
			<div class="form-group col-md-4">
				<label>Telefone</label>
				<input type="text" name="inputTelefone" id="cel" class="form-control" value="<%=consultaProfService.getFone()%>" required>
			</div>
			<div class="form-group col-md-4">
				<label>Data de Nascimento</label>
				<input type="text" class="form-control" id="dataNascimento" value="<%=consultaProfService.getDt_nascimento()%>">
			</div>
			<div class="form-group col-md-4">
				<label>Senha</label>
				<input type="password" class="form-control" value="<%=consultaProfService.getSenha()%>">
			</div>
		</div>
		<div class="form-row">
			<div class="custom-control custom-checkbox form-check-inline ml-1">
				<input type="checkbox" name="inputAdmin" value="1" class="custom-control-input" id="Administrador">
				<label class="custom-control-label" for="Administrador">Administrador?</label>
			</div>
		</div>
		<div class="form-group my-3">
			<button type="submit" class="btn btn-primary">Salvar dados</button>
		</div>
	</form>

</div>

	<%	String respostaAlterarDados = (String)request.getSession().getAttribute("tryAlterarDados");
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