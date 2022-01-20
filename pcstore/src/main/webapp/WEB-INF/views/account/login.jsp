<%@ page pageEncoding="utf-8"%>
<h2>Login</h2>
<h4>${message}</h4>


<form action="/account/login" method="post">
	<div class="form-group">
		<label>User Name</label>
		<input name="id" class="form-control" value="${uid}">
	</div>
	<div class="form-group">
		<label>Pass Word</label>
		<input name="pw" class="form-control" value="${pw}" type="password">
	</div>
	<div class="form-group">
		<div class="form-control">
			<input name="rm" type="checkbox">
			<label>Remeber?</label>	
		</div>
	</div>
	<div class="form-group">
		<button class="btn btn-default">Login</button>
	</div>
</form>

