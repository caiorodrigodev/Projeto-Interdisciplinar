<%@page import="br.usjt.web.projetointegrado.model.Professor"%>
<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@include file='include/template/organisms/top.jsp'%>
<%String titulo = "Temas cadastrados";%>
 
    <title><%=titulo%></title>
    
<% String matricula = request.getParameter("mat");
   if(matricula == null) { %>
	
    <div class="container col-lg-2 my-5">
      <form action="ConsultarProfessor.do" method="post">
        <label class="sr-only">Professor</label>
          <div class="input-group mb-2 mr-sm-2">
            <input type="text" class="form-control" name="inputMatricula" placeholder="Matrícula">
              <div class="input-group-prepend">
            <input type="submit" class="btn btn-primary input-group-text far rounded-right text-light" value="&#xf002;">
          </div>
        </div>
      </form>
    </div>
	
<% } else { %>
	
    <div class="container col-lg-2 my-5">
      <form action="ConsultarProfessor.do" method="post">
        <label class="sr-only">Professor</label>
          <div class="input-group mb-2 mr-sm-2">
            <input type="text" class="form-control" name="inputMatricula" value="<%=matricula%>" placeholder="Matrícula">
              <div class="input-group-prepend">
            <input type="submit" class="btn btn-primary input-group-text far rounded-right text-light" value="&#xf002;">
          </div>
        </div>
      </form>
    </div>
	
<% } try {
	 if((boolean)request.getSession().getAttribute("consultarGet") == true) {
		String respostaConsultarProfessor = "";
		String scriptConsultarProfessor = "";
		try {
			Professor professor = (Professor)request.getSession().getAttribute("tryConsultarProfessor");	
			if(professor != null) {		
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
								<th scope="col">Matricula</th>
								<th scope="col">Administrador</th>
								<th colspan="2"></th>
							</tr>
						</thead>
						<tbody align="center" style="heigt:1000px;">			
							<tr>
								<td scope="col"><%=professor.getIdprofessor()%></td>
								<td scope="col"><%=professor.getNome()%></td>
								<td scope="col"><%=professor.getEmail()%></td>
								<td scope="col"><%=professor.getFone()%></td>
								<td scope="col"><%=professor.getSexo()%></td>
								<td scope="col"><%=professor.getDt_nascimento()%></td>
								<td scope="col"><%=professor.getMatricula()%></td>
								<%
								if(professor.getAdministrador() == 0) {
								%>
									<td scope="col">Não</td>
								<%
								} else {
								%>
									<td scope="col">Sim</td>
								<%
								}
								%>
								
								<td align="center" class="text-primary" scope="col"> <a href="/Projeto_Interdisciplinar/DeletarProfessor.do?id=<%=professor.getIdprofessor()%>">&nbsp;<i class="fal fa-trash-alt"></i>&nbsp;</a></td>
								<td align="center" class="text-primary" scope="col"> <a href="/Projeto_Interdisciplinar/editar-professor.jsp?mat=<%=professor.getMatricula()%>"><i class="fal fa-edit"></i></a></td>
							</tr>
			 			</tbody>
					</table>
				</div>
			<%
			request.getSession().setAttribute("matriculaConsultado", professor.getMatricula());
			request.getSession().setAttribute("consultarGet", false);
			}		
		} catch(ClassCastException cce) {
			respostaConsultarProfessor = (String)request.getSession().getAttribute("tryConsultarProfessor");
			 	
			if(respostaConsultarProfessor == null) {
				respostaConsultarProfessor = "";
			} else {
				scriptConsultarProfessor = "alert('" + respostaConsultarProfessor + "');";
				request.getSession().setAttribute("tryConsultarProfessor", null);
			}	
			%>
			
			<script>
			<%=scriptConsultarProfessor%>
			</script>
			
			<%
		}
	} else {
		request.getSession().setAttribute("tryConsultarProfessor", null);
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
String respostaDeletarProfessor = (String)request.getSession().getAttribute("tryDeletarProfessor");

String scriptDeletarProfessor = "";
					
if(respostaDeletarProfessor == null) {
	respostaDeletarProfessor = "";
} else {
	scriptDeletarProfessor = "alert('" + respostaDeletarProfessor + "');";
	request.getSession().setAttribute("tryDeletarProfessor", null);
}
%>

<script>
<%=scriptDeletarProfessor%>
</script>

<%@include file='/include/template/organisms/bottom.jsp'%>