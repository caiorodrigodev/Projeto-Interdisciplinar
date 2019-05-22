<%@page import="br.usjt.web.projetointegrado.service.TemaService"%>
<%@ include file='include/template/organisms/top.jsp'%>
<% String titulo = "Cadastrar Atividade"; %>

<title><%=titulo%></title>

<%
int id = Integer.parseInt(request.getParameter("id"));
TemaService temaService = new TemaService();
String retorno = temaService.tituloTema(id);
%>

<div class="container col-sm-12 col-md-10 col-lg-6 p-5">
	<h1>
		<strong><p class="rainbow mt-2 mb-3"><%=titulo%></p></strong>
	</h1>
    <div class="container col-sm-10 col-md-10 col-lg-8 mt-5">
        <form class="form-login" action="CadastrarAtividade.do?id=<%=id%>" method="post">
			
			<p><h5>Tema: <%= retorno %></h5></p>
				
	        <label>Descrição</label>
	            <div class="form-row">
	                <div class="form-group col-12">
	                    <textarea class="form-control" name="textareaDescricao" placeholder="Digite a Descrição"></textarea>
	                </div>
	            </div>
	
	        <label>Data Inicial</label>
	            <div class="form-row">
	                <div class="input-group mb-3 col-md-12">
	                    <input type="date" name="dataInicial" class="form-control">
	                        <div class="input-group-append form_datetime">
	                            <span class="input-group-text"><i class="fal fa-calendar-alt"></i></span>
	                        </div>
	                </div>
	            </div>
	
	        <label>Data Final</label>
	            <div class="form-row">
	                <div class="input-group mb-3 col-md-12">
					    <input type="date" name="dataFinal" class="form-control">
						   <div class="input-group-append form_datetime">
	                           <span class="input-group-text"><i class="fal fa-calendar-alt"></i></span>
	                        </div>
					</div>
	            </div>
				
			<div class="form-group my-4">
				<center>
					<button type="submit" class="btn btn-primary">Cadastrar</button>
				</center>
			</div>
		</form>
	</div>
	
<%	
String respostaCadastrarAtividade = (String)request.getSession().getAttribute("tryCadastrarAtividade");

String scriptCadastrarAtividade = "";
					
if(respostaCadastrarAtividade == null) {
	respostaCadastrarAtividade = "";
} else {
	scriptCadastrarAtividade = "alert('" + respostaCadastrarAtividade + "');";
	request.getSession().setAttribute("tryCadastrarAtividade", null);
}
%>

<script>
<%=scriptCadastrarAtividade%>
</script>
</div>

<%@ include file="/include/template/organisms/bottom.jsp" %>