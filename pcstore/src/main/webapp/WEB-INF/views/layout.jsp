<%@ page pageEncoding="utf-8"%>
<%@ taglib uri= "http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<title>PC Story</title>
	<tiles:insertAttribute name= "head"/>
	<style>
	
		


	
	
		
</style>
</head>
<body>

	<div class="container">
		<header class = "row">
		
				<h1 class ="alert alert-success">PC Store </h1>
		</header>
		<nav class = "row">
			<tiles:insertAttribute name= "menu"/>
		
		</nav>
		<div class = "row">
			<div class="midder">
			
				<article class = "col-sm-9" style="">
					<tiles:insertAttribute name= "body"/>
				</article>
				<aside class = "col-sm-3">
					<tiles:insertAttribute name= "aside"/>
				
				
				</aside>
			
			</div>
			
		</div>
		
		<footer class = "row">
		
			<tiles:insertAttribute name= "footer"/>
		
		</footer>
	</div>

</body>
</html>