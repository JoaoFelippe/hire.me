<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<!-- <meta http-equiv="refresh" content=1;url="https://${URL}"> -->

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

</head>
<script>
	function ativa(id,div) {
		console.log(id)
		var div = document.getElementById(div);
		if (div.style.display == 'none') {
			document.getElementById(id).disabled = true;
			div.style.display = 'block';
		} 
	}
</script>

<body>

	<input type="button" id="ShortUrl" value="Shorten" onClick="ativa('ShortUrl','divShortUrl')">


	<div id='divShortUrl' style='display: none'>
		<form
			action="${pageContext.request.contextPath}/spring/ShortUrl/short"
			method="get">
			<fieldset>
				<legend>Encurtator de URLs</legend>
				Digite a url<br> <input name="url_alias" type="text"><br>
				Digite um custon Alias(Opcional)<br> <input name="url_alias"
					type="text"><br> <input type="submit" value="Enviar">
			</fieldset>
		</form>

	</div>
	
	<input type="button" id="ReturnUrl" value="Retrieve" onClick="ativa('ReturnUrl','divReturnUrl')">
	<div id='divReturnUrl' style='display: none'>
		<form action="${pageContext.request.contextPath}/spring/ShortUrl/url"
			method="get">
			<fieldset>
				<legend>Retorno URL</legend>
				Digite o link<br> <input name="alias" type="text"
					value="http://shortener/u/"><br> <input type="submit"
					value="Enviar">

			</fieldset>
		</form>


	</div>


<br>
	${Print}
	${Error}

</body>

</html>