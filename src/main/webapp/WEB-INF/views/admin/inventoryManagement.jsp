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
	function chageSelectedValue() {
	var yourTestSelect = document.getElementById("TestSelect");
    
	// select element에서 선택된 option의 value가 저장됩니다.   
	var selectedValue = yourTestSelect.options[yourTestSelect.selectedIndex].value;
    
    document.getElementById("scriptTest").value = selectedValue;
	}
</script>




	<%-- header 영역 --%>
	<select id="TestSelect" name="SelectValue" onchange="chageSelectedValue()">
		<option value="" selected disabled>카테고리명</option>
		<c:forEach var="x" items="${cateList}" varStatus="status">
			<option value="${x.name}">${x.name}</option>
		</c:forEach>
	</select>

	<div class="content-wrap">
		<%-- container 영역 --%>
		<table id="example" class="table table-bordered">
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
							
						<td><input id="scriptTest"></td>
						
						<td>${menu.name}</td>
						<td>${menu.price}</td>
						<td>${menu.quantity}</td>
						<td
							style="display: flex; justify-content: center; align-items: center;"><input
							style="height: 30px; width: 50px; text-align: center;">
							<button class="buttonStyle">입고</button>
							<button class="buttonStyle">출고</button></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	<div class="aTagWrap">
		<a class="aTagStyle" aria-current="page"
			href='<c:url value="/controller/admin"/>'>뒤로가기</a>
	</div>



</body>
</html>