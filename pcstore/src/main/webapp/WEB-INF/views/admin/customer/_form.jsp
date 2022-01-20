<%@ page pageEncoding="utf-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>



<div class="panel panel-default">
	<div class="panel-body">
		<form:form action="${base}/index" modelAttribute="entity" enctype="multipart/form-data">
		
		<div class="row">
			<div class="form-group col-sm-6">
				<label>Id</label>
				<form:input path="id" class="form-control" readonly="${!empty entity.id}" />
				<form:errors path="id"/>
			
			</div>
			
			<div class="form-group col-sm-6">
				<label>Password</label>
				<form:input path="password" class="form-control" />
				<form:errors path="password"/>
			</div>
		</div>
		
			
			
		<div class="row">
				<div class="form-group col-sm-6">
				<label>Full Name</label>
				<form:input path="fullname" class="form-control" />
				<form:errors path="fullname"/>
			</div>
			
			<div class="form-group col-sm-6">
				<label>Email</label>
				<form:input path="email" class="form-control" />
				<form:errors path="email"/>
			</div>
		</div>	
			
		<div class="row">
			<div class="form-group col-sm-6">
				<label>Activated</label>
				<div class="form-control">
					<form:radiobutton path="activated" value="true" label="Yes" checked="checked"/>
					<form:radiobutton path="activated" value="false" label="No"/>
				
				</div>
				
			</div>
			
			<div class="form-group col-sm-6">
				<label>Role</label>
				<div class="form-control">
					<form:radiobutton path="admin" value="true" label="Admin"/>
					<form:radiobutton path="admin" value="false" label="User" checked="checked"/>
				
				</div>
				
			</div>
		</div>	
			
			
			
			
		<div class="row">
				<div class="form-group  col-sm-12">
				<label>Photo</label>
				<input type="file" name="photo_file" class="form-control">
				<form:hidden path="photo"  />
			</div>
			
			<div class="form-group  col-sm-12">
				<button class="btn btn-primary" formaction="${base}/create">Create</button>
				<button class="btn btn-warning" formaction="${base}/update">Update</button>
				<button class="btn btn-danger" formaction="${base}/delete">Delete</button>
				<a class="btn btn-default" href="${base}/index">Reset</a>
				
			</div>
		</div>	
		
 		</form:form>
		
	</div>
</div>

