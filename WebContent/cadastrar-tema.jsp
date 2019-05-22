<%@ include file='include/template/organisms/top.jsp'%>
<%	String titulo = "Cadastrar Tema"; %>

<title><%=titulo%></title>

<div class="container col-8 p-5">
	<h1><strong><p class="rainbow mt-2 mb-3"><%=titulo%></p></strong></h1>
	<form action="CadastrarTema.do" method="post">
		<div class="form-row">
			<div class="form-group col-12">
				<label>Título do tema</label> 
				<input type="text" name="inputTitulo" class="form-control" placeholder="Insira o título" required>
			</div>
		</div>
		<div class="form-row">
			<div class="form-group col-12">
				<label>Descrição</label>
					<textarea name="textareaIntroducao" class="form-control" placeholder="Insira a introdução" rows="3" required></textarea>
			</div>
		</div>
		<div class="form-row">
			<div class="form-group col-12">
				<label>Requisitos</label>
					<textarea name="textareaRequisitos" class="form-control" placeholder="Insira os requisitos" rows="4" required></textarea>
			</div>
		</div>
		<div class="form-group my-3">
			<button type="submit" class="btn btn-primary">Cadastrar</button>
		</div>
	</form>
<%
String respostaCadastrarTema = (String)request.getSession().getAttribute("tryCadastrarTema");

String scriptCadastrarTema = "";
					
if(respostaCadastrarTema == null) {
	respostaCadastrarTema = "";
} else {
	scriptCadastrarTema = "alert('" + respostaCadastrarTema + "');";
	request.getSession().setAttribute("tryCadastrarTema", null);
}
%>

<script>
<%=scriptCadastrarTema%>
</script>
</div>

<%@ include file="/include/template/organisms/bottom.jsp" %>