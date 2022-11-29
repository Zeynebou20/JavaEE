<title>Bienvenue admin dans la gestion utilisateurs</title>
<link href="css/style.css" rel="stylesheet">
<div id="container">
<p><%= request.getAttribute("error") != null ? request.getAttribute("error") : ""  %></p> <br>
<fieldset>
<legend>Connexion</legend>
<form method="post">
<input type="text" name="login" placeholder="Login"><br>
<input type="password" name="pass" placeholder="Mot de passe"><br>
<input type="submit" value="Se connecter">
</form></fieldset>
</div>
</body>
</html>