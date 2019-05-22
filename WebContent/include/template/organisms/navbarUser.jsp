<nav class="navbar navbar-expand-lg navbar-light bg-primary bg-nav my-0">
	<div class="container-fluid">

		<button type="button" id="sidebarCollapse" class="btn btn-bars">
			<i class="fas fa-bars"></i>
		</button>
		<ul class="nav navbar-nav mr-auto">
			<li class="nav-item mx-2"><a class="nav-link" href="index.jsp">Home
					&nbsp; <i class="fal fa-home-lg-alt"></i>
			</a> <!-- Coloca o nome do Usuário --></li>
		</ul>

		<button class="btn btn-dark d-inline-block d-lg-none ml-auto"
			type="button" data-toggle="collapse"
			data-target="#navbarSupportedContent"
			aria-controls="navbarSupportedContent" aria-expanded="false"
			aria-label="Toggle navigation">
			<i class="fas fa-align-justify"></i>
		</button>

		<div class="collapse navbar-collapse navbar-dark"
			id="navbarSupportedContent">
			<ul class="nav navbar-nav ml-auto">
			
				<li class="nav-item mx-2"> <!-- Username -->
					<a class="nav-link disabled" href="#">
						<%=request.getSession().getAttribute("nome")%> &nbsp; <i class="fal fa-user"></i>
					</a>
				</li>
				
				<li class="nav-item mx-2">  <!-- Editar Perfil -->
					<a class="nav-link" href="editar-perfil.jsp?id=<%=request.getSession().getAttribute("idusuario")%>">
						Editar Perfil &nbsp; <i class="fal fa-user-cog"></i>
					</a>
				</li>
				
				<li class="nav-item mx-2">
					<a class="nav-link" href="sair.do">
						Sair &nbsp; <i class="fal fa-power-off"></i>
					</a>
				</li>
			</ul>
		</div>
	</div>
</nav>