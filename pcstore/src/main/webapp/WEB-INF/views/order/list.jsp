<%@ page pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>



<h2>Order List</h2>
<table class="table">
<thead>
	<tr>
		<th>id</th>
		<th>Order Date</th>
		<th>Address</th>
		<th>Amount</th>
	</tr>
</thead>
<tbody>
<c:forEach var ="o" items="${orders}">
	<tr>
		<td>${o.id}</td>
		<td>${o.orderDate}</td>
		<td>${o.address}</td>
		<td>${o.amount}</td>
		
		<td>
			<a href="/order/detail/${o.id}" class="btn btn-sm btn-warning" >
				detail
			</a>
		</td>
	</tr>
</c:forEach>
	
</table>