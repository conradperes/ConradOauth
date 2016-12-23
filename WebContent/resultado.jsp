<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	Primeiro Nome: ${sessionScope.perfil['primeiro_nome']} <br>
	Último Nome: ${sessionScope.perfil['ultimo_nome']}<br>
	Email: ${sessionScope.perfil['email']}<br>
	Foto: <img src="${sessionScope.perfil['imagem']}" height="100"/><br>
	Sexo: ${sessionScope.perfil['serxo']}<br>


</body>
</html>