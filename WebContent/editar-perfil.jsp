<%
String nome = (String) request.getSession().getAttribute("nome");
String email = (String) request.getSession().getAttribute("email");
String fone = (String) request.getSession().getAttribute("fone");
String dt_nascimento = (String) request.getSession().getAttribute("dt_nascimento");
String sexo = (String) request.getSession().getAttribute("sexo");
String senha = (String) request.getSession().getAttribute("senha");
String ra = (String) request.getSession().getAttribute("ra");
%>

<%@ include file='include/template/organisms/top.jsp' %>
<% String titulo = "Alterar Dados"; %>

<title><%=titulo%></title>

<div class="container col-8 p-5">
	<h1>
		<p class="text-primary mt-2 mb-3"><%=titulo%></p>
	</h1>
	<form action="AlterarDados.do" method="post">

		<div class="form-group">
			<label>Nome</label>
			<input type="text" class="form-control"	name="nome" value="<%=nome%>" readonly>
		</div>

		<div class="form-row">
			<div class="form-group col-md-4">
				<label>E-mail</label>
				<input type="email"	name="inputEmail" class="form-control" value="<%=email%>" required>
			</div>
			<div class="form-group col-md-4">
				<label>Telefone</label>
				<input type="text" name="inputTelefone" class="form-control" value="<%=fone%>" required>
			</div>
			<div class="form-group col-md-4">
				<label>Data de Nascimento</label>
				<input type="text" class="form-control" value="<%=dt_nascimento%>" readonly>
			</div>
		</div>
		<div class="row pl-3"><small>Para alterar a senha &nbsp;<a class="text-primary" href="alterar-senha.jsp" style="text-decoration:underline;">clique aqui</a></small></div>
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