<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<form action="Login.do" method="post">
		R.A.:  <input type="text" name="inputRA" required></br>
		Senha: <input type="password" name="inputSenha" required></br>
		<input type="submit" value="Entrar">
	</form>
<%
String respostaLogin = (String)request.getSession().getAttribute("trylogin");
if(respostaLogin == null) {
	respostaLogin = "";
}
%>
<%=respostaLogin%>
</body>
</html>