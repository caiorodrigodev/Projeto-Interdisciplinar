<%@ page import="br.usjt.web.projetointegrado.model.Inscricao" %>
<%@ page import="java.util.ArrayList" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>

<%@include file='include/template/organisms/top.jsp'%>
<% String titulo = "Inscrições do Vestibular"; %> 
    <title><%=titulo%></title>
<% ArrayList<Inscricao> inscricoes = (ArrayList<Inscricao>)request.getSession().getAttribute("tryInscricoes");	
	if(inscricoes != null) { %>	
		<div class="container-fluid mt-5" style="height: auto;">
			<table class="table table-striped table-bordered">
				<thead align="center">
					<tr>
						<th scope="col">ID</th>
						<th scope="col">Nome</th>
						<th scope="col">E-mail</th>
						<th scope="col">Fone</th>
						<th scope="col">Sexo</th>
						<th scope="col">Data de Nasc.</th>
						<th>Nota</th>
						<th colspan="2">Situação</th>	
					</tr>
				</thead>
				<tbody align="center" style="heigt:1000px;">	 
		<% for(int i = 0; i < inscricoes.size(); i++) { %>			
					<tr>
						<td scope="col"><%=inscricoes.get(i).getIdinscricao()%></td>
						<td scope="col"><%=inscricoes.get(i).getNome()%></td>
						<td scope="col"><%=inscricoes.get(i).getEmail()%></td>
						<td scope="col"><%=inscricoes.get(i).getFone()%></td>
						<td scope="col"><%=inscricoes.get(i).getSexo()%></td>
						<td scope="col"><%=inscricoes.get(i).getDt_nascimento()%></td>
						<td><%=inscricoes.get(i).getNota()%></td>
						<td align="center" scope="col"> <a href="/Projeto_Interdisciplinar/Situacao.do?id=<%=inscricoes.get(i).getIdinscricao()%>&cond=accept"><i class="fas fa-check text-success"></i></a></td>
						<td align="center" scope="col"> <a href="/Projeto_Interdisciplinar/Situacao.do?id=<%=inscricoes.get(i).getIdinscricao()%>&cond=reject"><i class="fas fa-times text-danger"></i></a></td>
					</tr>
		<% } %>
	 			</tbody>
			</table>
		</div> 
	<% } %>

<%@ include file='/include/template/organisms/bottom.jsp'%>