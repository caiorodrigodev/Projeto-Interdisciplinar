<%
String perfil = "";
if(request.getSession().getAttribute("admin") == null) {
	perfil = "Aluno";
} else if((int)request.getSession().getAttribute("admin") == 0) {
	perfil = "Professor";
} else {
	perfil = "Administrador";
}
%>
<!-- Sidebar  -->
<nav id="sidebar">
	<div id="dismiss">
		<i class="fas fa-arrow-left"></i>
	</div>

	<div class="sidebar-header">
		<a href="index.jsp"><img src="img/logo.png" width="60%" alt="Logo"></a>
	</div>

	<ul class="list-unstyled components">
		<center>
			<img src="img/user.jpg" width="35%" alt="Foto"
				class="rounded-circle mb-0"><br><small><%=perfil%></small>
		</center> 
		<!-- Mostra o tipo de perfil -->
		<li class="mt-4"><a href="#homeSubmenu" data-toggle="collapse" aria-expanded="false">Cadastro</a>
			<ul class="collapse list-unstyled" id="homeSubmenu">
				<li><a href="cadastrar-professor.jsp">Cadastrar Professor</a></li>
				<li><a href="cadastrar-aluno.jsp">Cadastrar Aluno</a></li>
				<li><a href="cadastrar-turma.jsp">Cadastrar Turma</a></li>
				<li><a href="cadastrar-tema.jsp">Cadastrar Tema</a></li>
				<!--  <li><a href="cadastrar-banca.jsp">Cadastrar Banca</a></li>  -->
				<li><a href="cadastrar-atividade.jsp?id=<%=request.getSession().getAttribute("idusuario")%>">Cadastrar Atividade</a></li>
			</ul>
		</li>
		<li><a href="#listar"
			data-toggle="collapse" aria-expanded="false">Consultar</a>
			<ul class="collapse list-unstyled" id="listar">
				<li><a href="consultar-tema.jsp">Consultar temas</a></li>
				<li><a href="consultar-turma.jsp">Consultar turmas</a></li>
				<li><a href="consultar-aluno.jsp">Consultar alunos</a></li>
				<li><a href="consultar-professor.jsp">Consultar Professor</a></li>
			</ul>
		</li>
		
		<!--  <li><a href="#">Listar</a> <a href="#pageSubmenu"
			data-toggle="collapse" aria-expanded="false">Pages</a>
			<ul class="collapse list-unstyled" id="pageSubmenu">
				<li><a href="#">Page 1</a></li>
				<li><a href="#">Page 2</a></li>
				<li><a href="#">Page 3</a></li>
			</ul></li>
		<li><a href="#">Portfolio</a></li> -->
		<li><a href="ListarInscricoes.do">Inscrições do Vestibular</a></li>
	</ul>

	<ul class="list-unstyled CTAs">
		<!--  <li><a
			href="https://bootstrapious.com/tutorial/files/sidebar.zip"
			class="download">Download source</a></li>
		<li><a href="https://bootstrapious.com/p/bootstrap-sidebar"
			class="article">Back to article</a></li> -->
	</ul>
</nav>