<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ page import="model.JavaBeans"%>
<%@ page import="java.util.ArrayList"%>
<%

 
ArrayList<JavaBeans> lista = (ArrayList<JavaBeans>) request.getAttribute("contato");
%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
<meta charset="utf-8">
<title>Agenda Telefônica</title>
<link rel="icon" href="imagem/caderno.png">
<link rel="stylesheet" href="style.css">
</head>
<body>
	<h1>Agenda Telefônica</h1>
	<a href="novo.html" class="Botao1">Novo Contato</a>
	<a href ="report" class="Botao2">Relatório</a>
	<table id="tabela">
		<thead>
			<tr>
				<th>Id</th>
				<th>Nome</th>
				<th>Idade</th>
				<th>Telefone</th>
				<th>Opções</th>
				
			</tr>
		</thead>
		<tbody>
			<%
			for (int i = 0; i < lista.size(); i++) {
			%>
			
			<tr>
				<td><%=lista.get(i).getId()%></td>
				<td><%=lista.get(i).getNome()%></td>
				<td><%=lista.get(i).getIdade()%></td>
				<td><%=lista.get(i).getTelefone()%></td>
				<td><a href="select?id=<%=lista.get(i).getId() %>"class="Botao1">Alterar</a>
					<a href="javascript: confirmar(<%=lista.get(i).getId() %>)"class="Botao2">Excluir</a>
				</td>
		</tr>
		<%} %>
		</tbody>
	</table>
	<script src="scripts/confirmador.js"></script>
</body>
</html>