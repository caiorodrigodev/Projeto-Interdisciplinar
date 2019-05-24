<%@page import="br.usjt.web.projetointegrado.model.Aluno"%>
<%@page import="java.util.ArrayList"%>
<%@page import="br.usjt.web.projetointegrado.model.Turma"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@include file='include/template/organisms/top.jsp'%>

<%String titulo = "Consultar turmas";%>
 
    <title><%=titulo%></title>
    
<%
String id = request.getParameter("id");
if(id == null) {
%>

    <div class="container col-lg-2 my-5">
      <form action="ConsultarTurma.do" method="post">
        <label class="sr-only" for="inlineFormInputGroupUsername2">Usuário</label>
          <div class="input-group mb-2 mr-sm-2">
            <input type="text" class="form-control" name="inputIDTurma" placeholder="ID da Turma">
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
      <form action="ConsultarTurma.do" method="post">
        <label class="sr-only" for="inlineFormInputGroupUsername2">Usuário</label>
          <div class="input-group mb-2 mr-sm-2">
            <input type="text" class="form-control" name="inputIDTurma" placeholder="ID da Turma">
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
		String respostaConsultarTurma = "";
		String scriptConsultarTurma = "";
		try {
			Turma turma = (Turma)request.getSession().getAttribute("tryConsultarTurma");
			ArrayList<Aluno> alunosTurma = (ArrayList<Aluno>)request.getSession().getAttribute("tryConsultarAlunos");
			if(turma != null) {
			%>
			<div class="container-fluid mt-5">			
				<table class="table table-striped table-bordered">
					<thead align="center">
						<tr>
							<th scope="col">ID</th>
							<th scope="col">Sigla</th>
							<th scope="col">Semestre</th>
							<th scope="col">Ano</th>
							<th scope="col">Tema</th>
							<th scope="col">Qtd de Alunos</th>
            				<th colspan="2">Ação</th>
						</tr>
					</thead>
					<tbody align="center">	
						<tr>
							<td scope="col"><%=turma.getIdTurma()%></td>
							<td scope="col"><%=turma.getSigla()%></td>
							<td scope="col"><%=turma.getSemestreLetivo()%></td>
							<td scope="col"><%=turma.getAnoLetivo()%></td>
							<% try { if(turma.tema.getTitulo() != null) { %>
							<td scope="col"><%=turma.tema.getTitulo()%></td>
							<% } } catch(NullPointerException npe) { %>
							<td scope="col">SEM TEMA</td>
							<% } %>
							<td scope="col"><%=alunosTurma.size()%></td>
							<td align="center" class="text-primary" style="vertical-align: middle"> <a href="/Projeto_Interdisciplinar/DeletarTurma.do?id=<%=turma.getIdTurma()%>">&nbsp;<i class="fal fa-trash-alt"></i>&nbsp;</a></td>
    				        <td align="center" class="text-primary" style="vertical-align: middle"> <a href=""><i class="fal fa-edit"></i></a></td>
						</tr>
					</tbody>
				</table>
			</div>	
			<%
			}
			if(alunosTurma != null) {
			%>
				<div class="container-fluid mt-5">	
					<div class="accordion" id="accordionExample">
				  		<div class="card">
				    		<div class="card-header text-center" id="headingOne">
				      			<h2 class="mb-0">
				        			<button class="btn btn-link" type="button" data-toggle="collapse" data-target="#collapseOne" aria-expanded="true" aria-controls="collapseOne">
				          				<i class="fas fa-arrow-down"></i>
				         			</button>
				      			</h2>
				    		</div>	    
				    	<div id="collapseOne" class="collapse show" aria-labelledby="headingOne" data-parent="#accordionExample">
				      		<div class="card-body">
								<table class="table table-striped table-bordered">
									<thead align="center">
										<tr>
											<th scope="col">ID</th>
											<th scope="col">Nome</th>
											<th scope="col">E-mail</th>
											<th scope="col">Fone</th>
											<th scope="col">Sexo</th>
											<th scope="col">Data Nasc.</th>
											<th scope="col">R.A.</th>
										</tr>
									</thead>
									<tbody align="center">			
				<%
				for(int i = 0; i < alunosTurma.size(); i++) {
				%>	
										<tr>
											<td scope="col"><%=alunosTurma.get(i).getIdaluno()%></td>
											<td scope="col"><%=alunosTurma.get(i).getNome()%></td>
											<td scope="col"><%=alunosTurma.get(i).getEmail()%></td>
											<td scope="col"><%=alunosTurma.get(i).getFone()%></td>
											<td scope="col"><%=alunosTurma.get(i).getSexo()%></td>
											<td scope="col"><%=alunosTurma.get(i).getDt_nascimento()%></td>
											<td scope="col"><%=alunosTurma.get(i).getRa()%></td>
										</tr>
				<%
				}
				%>
									</tbody>
								</table>
				      		</div>
				    	</div>
				  	</div>
				 </div>
			</div>
			<%
			request.getSession().setAttribute("consultarGet", false);
			}
		} catch(ClassCastException cpe) {
			respostaConsultarTurma = (String)request.getSession().getAttribute("tryConsultarTurma");
		 	
			if(respostaConsultarTurma == null) {
				respostaConsultarTurma = "";
			} else {
				scriptConsultarTurma = "alert('" + respostaConsultarTurma + "');";
				request.getSession().setAttribute("tryConsultarTurma", null);
			}	
			%>
			
			<script>
			<%=scriptConsultarTurma%>
			</script>
			
			<%
		}
	} else {
		request.getSession().setAttribute("tryConsultarTurma", null);
	}
} catch(NullPointerException nulo) {
	
}
%>	

<%@ include file = "include/template/organisms/bottom.jsp" %>
