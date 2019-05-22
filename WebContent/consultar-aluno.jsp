<%@page import="br.usjt.web.projetointegrado.model.Aluno"%>
<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@include file='include/template/organisms/top.jsp'%>
<%String titulo = "Temas cadastrados";%>
 
    <title><%=titulo%></title>
    
<% 
String ra = request.getParameter("ra");
    if(ra == null) {
	%>

    <div class="container col-lg-2 my-5">
      <form action="ConsultarAluno.do" method="post">
        <label class="sr-only" for="inlineFormInputGroupUsername2">Aluno</label>
          <div class="input-group mb-2 mr-sm-2">
            <input type="text" class="form-control" name="inputRA" placeholder="R.A. do Aluno">
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
      <form action="ConsultarAluno.do" method="post">
        <label class="sr-only" for="inlineFormInputGroupUsername2">Aluno</label>
          <div class="input-group mb-2 mr-sm-2">
            <input type="text" class="form-control" name="inputRA" value="<%=ra%>" placeholder="R.A. do Aluno">
              <div class="input-group-prepend">
            <input type="submit" class="btn btn-primary input-group-text far rounded-right text-light" value="&#xf002;">
          </div>
        </div>
      </form>
    </div>

	<%
	}
    try {
	if((boolean)request.getSession().getAttribute("consultarGet") == true) {
		String respostaConsultarAluno = "";
		String scriptConsultarAluno = "";
		try {
			Aluno aluno = (Aluno)request.getSession().getAttribute("tryConsultarAluno");	
			if(aluno != null) {		
			%>	
				<div class="container-fluid mt-5" style="height: auto;">
					<table class="table table-striped table-bordered">
						<thead align="center">
							<tr>
								<th scope="col">ID</th>
								<th scope="col">Nome</th>
								<th scope="col">E-mail</th>
								<th scope="col">Telefone</th>
								<th scope="col">Sexo</th>
								<th scope="col">Data Nasc.</th>
								<th scope="col">R.A.</th>
								<th colspan="2"></th>
							</tr>
						</thead>
						<tbody align="center" style="heigt:1000px;">			
							<tr>
								<td scope="col"><%=aluno.getIdaluno()%></td>
								<td scope="col"><%=aluno.getNome()%></td>
								<td scope="col"><%=aluno.getEmail()%></td>
								<td scope="col"><%=aluno.getFone()%></td>
								<td scope="col"><%=aluno.getSexo()%></td>
								<td scope="col"><%=aluno.getDt_nascimento()%></td>
								<td scope="col"><%=aluno.getRa()%></td>
								<td align="center" class="text-primary" scope="col"> <a href="/Projeto_Interdisciplinar/DeletarAluno.do?id=<%=aluno.getIdaluno()%>">&nbsp;<i class="fal fa-trash-alt"></i>&nbsp;</a></td>
								<td align="center" class="text-primary" scope="col"> <a href="/Projeto_Interdisciplinar/editar-aluno.jsp?ra=<%=aluno.getRa()%>"><i class="fal fa-edit"></i></a></td>
							</tr>
			 			</tbody>
					</table>
				</div>
			<%
			request.getSession().setAttribute("raConsultado", aluno.getRa());
			request.getSession().setAttribute("consultarGet", false);
			}		
		} catch(ClassCastException cce) {
			respostaConsultarAluno = (String)request.getSession().getAttribute("tryConsultarAluno");
			 	
			if(respostaConsultarAluno == null) {
				respostaConsultarAluno = "";
			} else {
				scriptConsultarAluno = "alert('" + respostaConsultarAluno + "');";
				request.getSession().setAttribute("tryConsultarAluno", null);
			}	
			%>
			
			<script>
			<%=scriptConsultarAluno%>
			</script>
			
			<%
		}
	} else {
		request.getSession().setAttribute("tryConsultarAluno", null);
	}
} catch(NullPointerException nulo) {
	
}

String respostaAlterarDadosAluno = (String)request.getSession().getAttribute("tryAlterarDadosAluno");

String scriptAlterarDadosAluno = "";
					
if(respostaAlterarDadosAluno == null) {
	respostaAlterarDadosAluno = "";
} else {
	scriptAlterarDadosAluno = "alert('" + respostaAlterarDadosAluno + "');";
	request.getSession().setAttribute("tryAlterarDadosAluno", null);
}

%>

<script>
<%=scriptAlterarDadosAluno%>
</script>


<%
String respostaDeletarAluno = (String)request.getSession().getAttribute("tryDeletarAluno");

String scriptDeletarAluno = "";
					
if(respostaDeletarAluno == null) {
	respostaDeletarAluno = "";
} else {
	scriptDeletarAluno = "alert('" + respostaDeletarAluno + "');";
	request.getSession().setAttribute("tryDeletarAluno", null);
}
%>

<script>
<%=scriptDeletarAluno%>
</script>

<%@include file='/include/template/organisms/bottom.jsp'%>