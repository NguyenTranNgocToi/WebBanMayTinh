<%@ page pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@  taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="f"%>



<h2 class="alert alert-success">REVENUE BY CATEGORY</h2>
<table class="table table-hover">
<thead>
	<tr>
		<th>Category</th>
		<th>Quantity</th>
		<th>Revenue</th>
		<th>Min Price</th>
		<th>Max Price</th>
		<th>AVG Price</th>
	
	</tr>
</thead>
<tbody>
<c:forEach var ="e" items="${data}">
	<tr>
		<td>${e[0]}</td>
		<td>${e[1]}</td>
		<td>$<f:formatNumber value="${e[2]}" pattern="#,###.00"/></td>
		<td>$<f:formatNumber value="${e[3]}" pattern="#,###.00"/></td>
		<td>$<f:formatNumber value="${e[4]}" pattern="#,###.00"/></td>
		<td>$<f:formatNumber value="${e[5]}" pattern="#,###.00"/></td>
	
		
	</tr>

</c:forEach>
	
</tbody>
</table>

