<%@page import="beans.Utilisateur"%>
<%@page import="java.util.ArrayList"%>
<% String BaseURL = request.getContextPath();  %>


<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Bienvenue admin dans la gestion utilisateurs</title>
<link href="<%=BaseURL%>/css/style.css" rel="stylesheet">
</head>
<body>

<div class="topnav">
	<p id="title">
	Gestion utilisateurs
	</p>
  <a href="<%=BaseURL%>/list">Accueil</a>
  <a href="<%=BaseURL%>/add">Ajouter un utilisateur</a>
</div>