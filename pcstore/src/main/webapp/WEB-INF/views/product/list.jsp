<%@ page pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>

<c:forEach var="p" items="${list}">
	<div class="col-sm-4">
		<div class="thumbnail">
			<a href="/product/detail/${p.id}"> <img class="pcstore-product"
				src="/static/images/product/${p.image}">
			</a>
			
			<div class="caption" >
				<div data-id="${p.id}" class="pull-right">
					<button class="btn-btn-sm btn-danger btn-add-to-cart">
						<i class="glyphicon glyphicon-shopping-cart"></i>
					</button>
					<button class="btn-btn-sm btn-warning btn-star">
						<i class="glyphicon glyphicon-star"></i>
					</button>
					<button class="btn-btn-sm btn-success btn-open-dialog" data-toggle="modal" data-target="#myModal">
						<i class="glyphicon glyphicon-envelope"></i>
					</button>
				</div>
				<p>${p.name}</p>
				<p>${p.unitPrice} $</p>
			</div>
			

		</div>
	</div>
</c:forEach>

<jsp:include page="dialog.jsp"></jsp:include>