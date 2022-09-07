<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
<meta charset="utf-8">
<title>Agenda telefônica</title>
<link rel="icon" href="imagem/phone_icon.png">
<link rel="stylesheet" href="style.css">
</head>
<body>
	<h1>Alterar Contato</h1>
	<form name="frmContato" action="update">
		<table>
			<tr>
				<td><input type="text" name="id" id="caixa3" readonly value="<%out.print(request.getAttribute("id"));%>"></td>
			</tr>
			<tr>
				<td><input type="text" name="nome" class="Caixa1"value="<%out.print(request.getAttribute("nome"));%>"></td>
			</tr>
			<tr>
				<td><input type="text" name="idade" class="Caixa2"value="<%out.print(request.getAttribute("idade"));%>"></td>
			</tr>

			<tr>
				<td><input type="text" name="telefone" class="Caixa1"value="<%out.print(request.getAttribute("telefone"));%>"></td>
			</tr>

		</table>
		<input type="button" value="Salvar" class="Botao1" onclick="validar()">
	</form>
	<script src="scripts/validador.js"></script>
</body>
</html>