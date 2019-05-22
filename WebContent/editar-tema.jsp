<%@page import="br.usjt.web.projetointegrado.service.TemaService"%>
<%@page import="java.util.ArrayList"%>
<%@page import="br.usjt.web.projetointegrado.model.Tema"%>
<%@ include file='include/template/organisms/top.jsp'%>
<%	String titulo = "Editar Tema"; %>

<title><%=titulo%></title>

<%
int id = Integer.parseInt(request.getParameter("id"));
TemaService temaService = new TemaService();
Tema consultar = temaService.consultarTema(id);
%>

<div class="container col-8 p-5">
	<h1><strong><p class="rainbow mt-2 mb-3"><%=titulo%></p></strong></h1>
	<form action="AlterarTema.do?id=<%=id%>" method="post">
		<div class="form-row">
			<div class="form-group col-12">
				<label>Requisitos</label>
					<textarea name="textareaRequisitos" class="form-control" rows="4" required><%= consultar.getRequisitos() %></textarea>
			</div>
		</div>
		<div class="form-group my-3">
			<button type="submit" onclick="myFunction()" class="btn btn-primary">Cadastrar</button>
		</div>
	</form>
<%
String respostaTema = (String)request.getSession().getAttribute("tryAlterarTema");

String script = "";
					
if(respostaTema == null) {
	respostaTema = "";
} else {
	script = "alert('" + respostaTema + "');";
	request.getSession().setAttribute("tryAlterarTema", null);
}
%>

<script>
<%=script%>
</script>

</div>

<%@ include file="/include/template/organisms/bottom.jsp" %>