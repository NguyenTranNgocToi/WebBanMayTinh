<%@ page pageEncoding="utf-8"%>
<%@ taglib uri = "http://java.sun.com/jstl/core_rt" prefix="c"%>



<nav class="navbar navbar-inverse">
	<div class="container-fluid">
		<div class="navbar-header">
			<a class="navbar-brand" href="/admin/home/index">Trang Chủ</a>
		</div>
		<ul class="nav navbar-nav">
			<li class="dropdown">
		        <a class="dropdown-toggle" data-toggle="dropdown" href="#">Quản Lý<span class="caret"></span></a>
		 
		        			 <ul class="dropdown-menu">
						          <li><a href="/admin/category/index">Loại</a></li>
						          <li><a href="/admin/product/index">Sản phẩm</a></li>
						          <li><a href="/admin/customer/index">Khách hàng</a></li>
						          <li><a href="/admin/order/index">Đơn hàng</a></li>
			        		</ul>    	
       		</li>
			
			<li class="dropdown">
		        <a class="dropdown-toggle" data-toggle="dropdown" href="#">Thống kê<span class="caret"></span></a>
		 
		        			 <ul class="dropdown-menu">
						          <li><a href="/admin/inventory/index">Tồn kho theo loại</a></li>
						          <li><a href="/admin/revenue/category">Doanh số theo loại</a></li>
						          <li><a href="/admin/revenue/customer">Doanh số theo khách hàng</a></li>
						          <li><a href="/admin/revenue/year">Doanh số theo năm</a></li>
						          <li><a href="/admin/revenue/quarter">Doanh số theo quý</a></li>
						          <li><a href="/admin/revenue/month">Doanh số theo tháng</a></li>
			        		</ul>    	
       		</li>
       		
       		
       			<li class="dropdown">
		        <a class="dropdown-toggle" data-toggle="dropdown" href="#">Tài khoản<span class="caret"></span></a>
		 
		        			 <ul class="dropdown-menu">
								<li><a href="/admin/account/change">Đổi mật khẩu</a></li>
						        <li><a href="/admin/account/edit">Cập nhật tài khoản</a></li>
						        <li><a href="/account/logout">Đăng xuất</a></li>  
			       		 	</ul>
       		</li>
	
		
			
		</ul>

	</div>
</nav>