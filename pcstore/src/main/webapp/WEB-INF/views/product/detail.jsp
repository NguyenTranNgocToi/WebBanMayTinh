<%@ page pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="f" %>

<div class ="row">
	<div class="col-sm-5 text-center">
		<img class="detail-img" src="/static/images/product/${product.image}">
	</div>
	<div class="col-sm-7">
		<ul class="detail-info">
			<li>Name: ${product.name}</li>
			<li>unit Price:<f:formatNumber value="${product.unitPrice}" pattern="#,###.00" /> VNĐ </li>
			<li>product Date:<f:formatDate value="${product.productDate}" pattern="dd-MM-yyyy"/> </li>
			<li>nameVN: ${product.category.nameVN}</li>
			<li>quantity: ${product.quantity}</li>
			<li>discount:<f:formatNumber value="${product.discount}" type="percent" /></li>
			<li>view Count: ${product.viewCount}</li>
			<li>available: ${product.available?'Yes':'No'}</li>
			<li>special: ${product.special?'Yes':'No'}</li>
		</ul>
	</div>
</div>


<div class="text-justify">${product.description}</div>


<ul class="nav nav-tabs">
  <li class="active"><a data-toggle="tab" href="#tab1">Hàng Cùng Loại</a></li>
  <li><a data-toggle="tab" href="#tab2">Hàng Yêu Thích</a></li>
  <li><a data-toggle="tab" href="#tab3">Hàng Đã Xem</a></li>
</ul>

<div class="tab-content">
  <div id="tab1" class="tab-pane fade in active">
    <div class ="row">
		<c:forEach var="p" items="${list}">
			<div class="col-sm-3">
				<a href="/product/detail/${p.id}"> 
					<img class="img" src="/static/images/product/${p.image}">
				</a>
			</div>	
		</c:forEach>
	</div>
  </div>
  <div id="tab2" class="tab-pane fade">
    <div class ="row">
		<c:forEach var="p" items="${favo}">
		<div class="col-sm-3">
			<a href="/product/detail/${p.id}"> 
				<img class="img" src="/static/images/product/${p.image}">
			</a>
		</div>	
		</c:forEach>
	</div>
  </div>
  <div id="tab3" class="tab-pane fade">
    <div class ="row">
		<c:forEach var="p" items="${viewed}">
			<div class="col-sm-3">
				<a href="/product/detail/${p.id}"> 
					<img class="img" src="/static/images/product/${p.image}">
				</a>
			</div>	
		</c:forEach>
	</div>
  </div>
</div>












