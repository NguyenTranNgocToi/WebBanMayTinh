<%@ page pageEncoding="utf-8"%>
<%@ taglib uri = "http://java.sun.com/jstl/core_rt" prefix="c"%>



<nav class="navbar navbar-inverse">
	<div class="container-fluid">
		<div class="navbar-header">
			<a class="navbar-brand" href="/home/index">Trang Chủ</a>
		</div>
		<ul class="nav navbar-nav">
			<li ><a href="/home/about">Giới thiệu</a></li>
			<li><a href="/home/contact">Liên lạc</a></li>
			<li><a href="/home/feedback">Phản hồi</a></li>
			<li class="dropdown">
		        <a class="dropdown-toggle" data-toggle="dropdown" href="#"><span class="glyphicon glyphicon-user"></span> Tài Khoản<span class="caret"></span></a>
		        	<c:choose>
		        		<c:when test="${empty sessionScope.user}">
		        			 <ul class="dropdown-menu">
						          <li><a href="/account/login">Đăng nhập</a></li>
						          <li><a href="/account/register">Đăng ký</a></li>
						          <li><a href="#">Quên mật khẩu</a></li>
			        		</ul>
		        		</c:when>
		        		<c:otherwise>
		        			 <ul class="dropdown-menu">
						     	<li><a href="/account/change">Đổi mật khẩu</a></li>
						        <li><a href="/account/edit">Cập nhật tài khoản</a></li>
						        <li><a href="/order/list">Đơn hàng đã mua</a></li>
						        <li><a href="/order/items">Mặt hàng đã mua</a></li>
						        <li><a href="/account/logout">Đăng xuất</a></li>  
			       		 	</ul>
		        		</c:otherwise>
		        	</c:choose>
		        	
		        	
		        	
			      
			        
		     </li>
		</ul>
		
	</div>
</nav>