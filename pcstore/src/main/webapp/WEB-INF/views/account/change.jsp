<%@ page pageEncoding="utf-8"%>



<h2>Change pass word</h2>
<h4>${message}</h4>


<form action="/account/change"  method="post">
	<div class="form-group">
		<label>User Name</label>
		<input name="id" class="form-control" />
		
	</div>
	<div class="form-group">
		<label>Current Pass Word</label>
		<input name="pass" class="form-control" type="password"/>
	</div>
	<div class="form-group">
		<label>New Password </label>
		<input name="pass1" class="form-control" type="password"/>
	</div>
	<div class="form-group">
		<label>Confirm New Password </label>
		<input name="pass2" class="form-control" type="password"/>
	</div>
	<div class="form-group">
		<button class="btn btn-default">Change</button>
	</div>
</form>

