<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="br.usjt.web.projetointegrado.model.Tema"%>
<%@page import="java.util.ArrayList"%>
<%@page import="br.usjt.web.projetointegrado.service.TemaService"%>
<%@ include file='include/template/organisms/top.jsp'%>
<% String titulo = "Cadastrar Turma"; %>

<title><%=titulo%></title>

<div class="container col-sm-12 col-md-10 col-lg-6 p-5">
	<h1>
		<strong><p class="rainbow mt-2 mb-3"><%=titulo%></p></strong>
	</h1>

		<div class="container col-sm-10 col-md-10 col-lg-8 mt-5">

			<form class="form-login" action="CadastrarTurma.do" method="post">

			<label for="inputID">Código Identificador</label>
			<div class="form-row">
			<div class="form-group col-12">
			<input type="text" name="inputSigla" id="sigla" class="form-control somenteLetras" placeholder="CC2AN-BUA" onkeypress="return handleInput(event)" required>
			</div>
			</div>

			<label>Periodo Letivo</label>
			<div class="form-row">
			<div class="form-group col-md-6" required>
				<select name="selectSemeste" class="form-control">
					<option selected disabled>XX</option>
					<option value="1">01</option>
					<option value="2">02</option>
				</select>
			</div>

			<div class="form-group col-md-6" required>
				<select name="selectAno" class="form-control">
					<option selected disabled>YYYY</option>
					<%
					SimpleDateFormat formatDate = new SimpleDateFormat("dd/MM/yyyy");
					Date date = new Date();
					String data = formatDate.format(date);
					int ano = Integer.parseInt(data.substring(6, 10));
					for(int i = 0; i < 6; i++) {
					%>
						<option value="<%=ano%>"><%=ano%></option>
					<%
						ano+=1;
					}
					%>
				</select>
			</div>
			</div>
			
			<label>Tema</label>
			<div class="form-group col-14">
				<select name="selectTema" class="form-control">
					<option selected>Selecionar tema</option>
					<%
					TemaService temaService = new TemaService();
					ArrayList<Tema> temas = temaService.listarTemas();
					if(temas != null) {
						for(int i = 0; i < temas.size(); i++) {
						%>
							<option value="<%=temas.get(i).getIdTema()%>" title="<%=temas.get(i).getIntroducao()%>"><%=temas.get(i).getTitulo()%></option>
						<%
						}
					}
					%>
				</select>
			</div>
			
			<div class="form-group my-4">
				<center>
					<button type="submit" class="btn btn-primary">Cadastrar</button>
				</center>
			</div>

		</form>
	</div>
		
		<%	String respostaInscricao = (String)request.getSession().getAttribute("tryCadastrarTurma");
		String script = "";
		if(respostaInscricao == null) {
			respostaInscricao = "";
		} else {
			script = "alert('" + respostaInscricao + "');";
			request.getSession().setAttribute("tryCadastrarTurma", null);
		}
		%>		
		
	<script>
	<%=	script %>
	</script>	
</div>


<%@ include file="/include/template/organisms/bottom.jsp" %>