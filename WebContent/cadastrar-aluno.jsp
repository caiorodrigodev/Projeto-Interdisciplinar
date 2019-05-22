<%@ include file='include/template/organisms/top.jsp'%>
<%	String titulo = "Cadastrar Aluno"; %>

<title><%=titulo%></title>

<div class="container col-8 p-5">
	<h1><strong><p class="rainbow mt-2 mb-3"><%=titulo%></p></strong></h1>
	<form action="CadastrarAluno.do" method="post">
		<div class="form-row">
			<div class="form-group col-md-4">
				<label>Nome</label> 
				<input type="text" name="inputNome" class="form-control" placeholder="Insira seu nome" required>
			</div>
			<div class="form-group col-md-4">
				<label >Sobrenome</label>
				<input type="text" name="inputSobrenome" class="form-control" placeholder="Insira seu sobrenome" required>
			</div>
			<div class="form-group col-md-4">
				<label>E-mail</label>
				 <input type="email" name="inputEmail" class="form-control" placeholder="Insira seu e-mail" required>
			</div>
		</div>
		<div class="form-row">
			<div class="form-group col-md-4">
				<label>Telefone</label>
				 <input type="text" name="inputTelefone" id="cel" class="form-control" placeholder="(11) 9 8521-4563" required>
			</div>
			<div class="form-group col-md-4">
				<label>Data de Nascimento</label>
				<input type="text" name="inputDtNascimento" id="dataNascimento" class="form-control" placeholder="31 / 02 / 1999" required>
			</div>
			<div class="form-group col-md-4">
				<label>R.A.</label>
				<input type="text" name="inputDtNascimento" class="form-control" placeholder="818129041" minlength="9" maxlength="9" onkeypress="return somenteNumeros(event)" required>
			</div>
		</div>
		
		<div class="form-row">
			<div class="form-group col-md-6">
				<label>Senha</label>
				<input type="password" name="inputSenha" class="form-control" placeholder="**********" required>
			</div>
			
			<div class="form-group col-md-6">
				<label>Confirmar senha</label>
				<input type="password" name="inputConfirmarSenha" class="form-control" placeholder="**********" required>
			</div>
		</div>

		<div class="custom-control custom-radio custom-control-inline">
			<input type="radio" name="inputSexo" id="masculino" value="Masculino" class="custom-control-input">
			<label class="custom-control-label" for="masculino">Masculino</label>
		</div>
			
		<div class="custom-control custom-radio custom-control-inline">
			<input type="radio" name="inputSexo" id="feminino" value="Feminino" class="custom-control-input">
			<label class="custom-control-label" for="feminino">Feminino</label>
		</div>
				
		<div class="form-group my-3">
			<button type="submit" class="btn btn-primary">Cadastrar</button>
		</div>
	</form>
<%
String respostaInscricao = (String)request.getSession().getAttribute("tryinscricao");
if(respostaInscricao == null) {
	respostaInscricao = "";
}
%>
<%=respostaInscricao%>
</div>

<%@ include file="/include/template/organisms/bottom.jsp" %>