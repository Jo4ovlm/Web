<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Tela de dados Restritos!</title>
	</head>
	<body>
		Janela de acesso restrito!<br/>
		Usuário que acessou:
		<% out.println(request.getSession().getAttribute("usuario").toString()); %><br/>
		<form action = "logout" method="POST">
			<input type="submit" value="Logout"/>
		</form>
	</body>
</html>