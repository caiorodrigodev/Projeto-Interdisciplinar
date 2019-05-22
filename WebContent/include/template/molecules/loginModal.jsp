<!-- Modal -->
<div class="modal fade" id="exampleModalCenter" tabindex="-1"
	role="dialog" aria-labelledby="exampleModalCenterTitle"
	aria-hidden="true">
	<div class="modal-dialog modal-dialog-centered" role="document">
		<div class="modal-content bg-modal">
			<div class="modal-header">
				<h5 class="modal-title text-center" style="color: white;">Acessar
					sua conta</h5>
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<div class="modal-body">

				<form class="text-light" action="Login.do" method="post">
					<div class="form-group">
						<label>R.A.</label> <input type="text" class="form-control"
							name="inputRA" placeholder="Insira seu R.A.">
					</div>
					<div class="form-group">
						<label>Senha</label> <input type="password" class="form-control"
							name="inputSenha" placeholder="**************">
					</div>
					<div class="form-group float-right">
						<button type="submit" class="btn btn-outline-light">Acessar</button>
					</div>
				</form>
			</div>
		</div>
	</div>
</div>

<% String respostaLogin = (String)request.getSession().getAttribute("trylogin");
if(respostaLogin == null) {
	respostaLogin = "";
} %>
<%=respostaLogin%>