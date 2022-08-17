<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.net.URLEncoder"%>
<!doctype html>
<html lang="en" class="h-100">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author"
	content="Mark Otto, Jacob Thornton, and Bootstrap contributors">
<meta name="generator" content="Hugo 0.88.1">
<title>Home</title>

<link rel="canonical"
	href="https://getbootstrap.com/docs/5.1/examples/cover/">



<!-- Bootstrap core CSS -->
<link href="../assets/dist/css/bootstrap.min.css" rel="stylesheet">

<style>
.bd-placeholder-img {
	font-size: 1.125rem;
	text-anchor: middle;
	-webkit-user-select: none;
	-moz-user-select: none;
	user-select: none;
}

@media ( min-width : 768px) {
	.bd-placeholder-img-lg {
		font-size: 3.5rem;
	}
}
</style>


<!-- Custom styles for this template -->
<link href="../resources/css/manageMent.css" rel="stylesheet">
</head>
<body>
	<script>
		function next_form() {
			document.getElementById('aTag').submit();
		}
		function next2_form() {
			document.getElementById('aTag2').submit();
		}
	</script>
	<%-- header 영역 --%>
	<h1 style="text-align:center; margin-top: 50px;">재고 관리 페이지</h1>
	<div class="content-wrap">
		<%-- container 영역 --%>
		<table class="table table-bordered">
			<thead>
				<tr>
					<th scope="col"></th>
					<th scope="col">이미지</th>
					<th scope="col">카테고리명</th>
					<th scope="col">제품명</th>
					<th scope="col">가격</th>
					<th scope="col">총 재고량</th>
					<th scope="col">입출고</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="menu" items="${menuList}" varStatus="status">
					<tr>
						<th style="line-height: 50px;" scope="row">${status.index+1}</th>
						<td><img class="manageImg"
							src="../resources/img/menu/${menu.picture}.jpg"></td>
						<td>${menu.categoryName}</td>
						<td>${menu.name}</td>
						<td>${menu.price}</td>
						<td>${menu.quantity}</td>
						<td><input style="height: 30px; width: 30px;">
							<button style="height: 30px; width: 60px;">입고</button>
							<button style="height: 30px; width: 60px;">출고</button></td>
					</tr>
				</c:forEach>

			</tbody>
		</table>
	</div>


</body>
</html>