<%@page import="br.usjt.web.projetointegrado.model.Tema"%>
<%@page import="java.util.ArrayList"%>
<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@include file='include/template/organisms/top.jsp'%>
<%String titulo = "Temas cadastrados";%>
 
    <title><%=titulo%></title>
    
<%
String id = request.getParameter("id");
if(id == null) {
%>

    <div class="container col-lg-2 my-5">
      <form action="ConsultarTema.do" method="post">
        <label class="sr-only" for="inlineFormInputGroupUsername2">Usuário</label>
          <div class="input-group mb-2 mr-sm-2">
            <input type="text" class="form-control" name="inputIDTema" placeholder="ID do Tema">
              <div class="input-group-prepend">
            <input type="submit" class="btn btn-primary input-group-text far rounded-right text-light" value="&#xf002;">
          </div>
        </div>
      </form>
    </div>
	
<%
} else {
%>

    <div class="container col-lg-2 my-5">
      <form action="ConsultarTema.do" method="post">
        <label class="sr-only" for="inlineFormInputGroupUsername2">Usuário</label>
          <div class="input-group mb-2 mr-sm-2">
            <input type="text" class="form-control" name="inputIDTema" placeholder="ID do Tema">
              <div class="input-group-prepend">
            <input type="submit" class="btn btn-primary input-group-text far rounded-right text-light" value="&#xf002;">
          </div>
        </div>
      </form>
    </div>

<%
}
%>

<%
try {
	if((boolean)request.getSession().getAttribute("consultarGet") == true) {
		String respostaConsultarTema = "";
		String scriptConsultarTema = "";
		try {
			Tema tema = (Tema)request.getSession().getAttribute("tryConsultarTema");	
			if(tema != null) {		
			%>	
<div class="container-fluid mt-5" style="height: auto;">
    <table class="table table-striped table-bordered text-center" align="center">
        <tr>
            <th width="65px">ID</th>
            <th width="500px">Data cadastro</th>
            <th width="600px">Título</th>
            <th colspan="2">Ação</th>
        </tr>		
        <tr>
            <td><%=tema.getIdTema()%></td>
            <td><%=tema.getDt_cadastro()%></td>
            <td><%=tema.getTitulo()%></td>
            <td rowspan="3" align="center" class="text-primary" style="vertical-align: middle"> <a href="/Projeto_Interdisciplinar/DeletarTema.do?id=<%=tema.getIdTema()%>">&nbsp;<i class="fal fa-trash-alt"></i>&nbsp;</a></td>
            <td rowspan="3" align="center" class="text-primary" style="vertical-align: middle"> <a href="/Projeto_Interdisciplinar/editar-tema.jsp?id=<%=tema.getIdTema()%>"><i class="fal fa-edit"></i></a></td>
        </tr>
        <tr>
            <th colspan="2">Introdução</th>
            <th>Requisitos</th>
        </tr>		
        <tr>
            <td colspan="2" class="text-truncate" style="max-width: 170px;" data-toggle="tooltip" data-placement="right" title="<%=tema.getIntroducao()%>"><%=tema.getIntroducao()%></td>
            <td class="text-truncate" style="max-width: 170px;" data-toggle="tooltip" data-placement="right" title="<%=tema.getRequisitos()%>"><%=tema.getRequisitos()%></td>
        </tr>
    </table>
</div>
			<% 
			request.getSession().setAttribute("consultarGet", false);
			}		
		} catch(ClassCastException cce) {
			respostaConsultarTema = (String)request.getSession().getAttribute("tryConsultarTema");
			 	
			if(respostaConsultarTema == null) {
				respostaConsultarTema = "";
			} else {
				scriptConsultarTema = "alert('" + respostaConsultarTema + "');";
				request.getSession().setAttribute("tryConsultarTema", null);
			}	
			%>
			
			<script>
			<%=scriptConsultarTema%>
			</script>
			
			<%
		}
	} else {
		request.getSession().setAttribute("tryConsultarTema", null);
	}
} catch(NullPointerException nulo) {
	
}

String respostaAlterarTema = (String)request.getSession().getAttribute("tryAlterarTema");

String scriptAlterarTema = "";
					
if(respostaAlterarTema == null) {
	respostaAlterarTema = "";
} else {
	scriptAlterarTema = "alert('" + respostaAlterarTema + "');";
	request.getSession().setAttribute("tryAlterarTema", null);
}

%>

<script>
<%=scriptAlterarTema%>
</script>

<%
String respostaDeletarTema = (String)request.getSession().getAttribute("tryDeletarTema");

String scriptDeletarTema = "";
					
if(respostaDeletarTema == null) {
	respostaDeletarTema = "";
} else {
	scriptDeletarTema = "alert('" + respostaDeletarTema + "');";
	request.getSession().setAttribute("tryDeletarTema", null);
}
%>

<script>
<%=scriptDeletarTema%>
</script>

<%@include file='/include/template/organisms/bottom.jsp'%>