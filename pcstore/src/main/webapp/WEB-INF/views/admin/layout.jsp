<%@ page pageEncoding="utf-8"%>
<%@ taglib uri= "http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<title>PC Story</title>
	<tiles:insertAttribute name= "head"/>


</head>
<body>

	<div class="container">
		<header class = "row">
			<h1 class ="alert alert-success">Administrator Tool</h1>
		</header>
		<nav class = "row">
			<tiles:insertAttribute name= "menu"/>
		
		</nav>
		<div class = "row">
				<tiles:insertAttribute name= "body"/>
		</div>
		<footer class = "row">
			<tiles:insertAttribute name= "footer"/>
		
		</footer>
	</div>

</body>
</html>