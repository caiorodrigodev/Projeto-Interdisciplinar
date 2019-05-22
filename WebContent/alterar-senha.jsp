<%@include file='include/template/organisms/top.jsp'%>
<%String titulo = "Alterar Senha";%>

<title><%=titulo%></title>

<div class="container col-8 p-5">
	<h1>
		<p class="text-primary mt-2 mb-3"><%=titulo%></p>
	</h1>
	<form action="AlterarSenha.do" method="post">
		<div class="form-group">
			<label>Nova senha</label>
			<input type="password" class="form-control" name="inputNovaSenha" placeholder="**********" required>
		</div>	
		<div class="form-group">
			<label>Confirmar nova senha</label>
			<input type="password" class="form-control" name="inputConfirmarSenha" placeholder="**********" required>
		</div>
		<div class="form-group my-3">
			<button type="submit" class="btn btn-primary">Salvar dados</button>
		</div>
	</form>
</div>

<%
String respostaAlterarSenha = (String)request.getSession().getAttribute("tryAlterarSenha");
String scriptAlterarSenha = "";
if(respostaAlterarSenha == null) {
	respostaAlterarSenha = "";
} else {
	scriptAlterarSenha = "alert('" + respostaAlterarSenha + "');";
	request.getSession().setAttribute("tryAlterarSenha", null);
}
%>	

<script>
<%=scriptAlterarSenha%>
</script>

<%@include file ="include/template/organisms/bottom.jsp"%>