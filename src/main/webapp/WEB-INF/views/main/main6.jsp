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
<link href="../resources/css/main_page.css" rel="stylesheet">
<link href="../resources/css/cover.css" rel="stylesheet">
</head>
<body class="d-flex h-100 text-center text-white bg-dark">
	<script>
		function next_form() {
			document.getElementById('aTag').submit();
		}
		function next_form2() {
			document.getElementById('aTag2').submit();
		}
	</script>
	<%-- header 영역 --%>
	<div class="cover-container d-flex w-100 h-100 p-3 mx-auto flex-column">
		<header class="mb-auto">
			<div style="margin-left: -180px;">
				<h3 class="float-md-start mb-0">Java Cafe</h3>
				<nav style="margin-right: -180px;"
					class="nav nav-masthead justify-content-center float-md-end">
					<a class="nav-link active" aria-current="page"
						href="/javaCafe/controller/home">Home</a> <a class="nav-link"
						href="/javaCafe/controller/admin">Admin</a>
				</nav>
			</div>
		</header>
		<%-- container 영역 --%>
		<main style="margin-left: -180px; width: 1000px;" class="container">

			<h1 style="margin-top: 30px;">주문내역을 확인해 주세요.</h1>
			<div class="row mb-2">
				<div class="col-md-6">
					<div
						style="display: flex; justify-content: center; line-height: 30px; height: 50px;"
						class="row g-0 border rounded overflow-hidden flex-md-row mb-4 shadow-sm h-md-250 position-relative">
						<a href="/javaCafe/controller/main5" class="nav-link hoverStyle">뒤로가기</a>
					</div>
				</div>
			</div>
			<div class="row mb-2">
				<div class="col-md-6">
					<form id="aTag" action="main6" method="post"
						style="margin-bottom: 3px;">
						<div
							style="display: flex; justify-content: center; line-height: 30px; height: 50px;"
							class="row g-0 border rounded overflow-hidden flex-md-row mb-4 shadow-sm h-md-250 position-relative">
							<a onclick="next_form()" class="nav-link hoverStyle">더 고르기</a>
						</div>
					</form>
				</div>
			</div>
			<div class="row mb-2">
				<div class="col-md-6">
					<div
						style="display: flex; justify-content: center; line-height: 30px; height: 50px;"
						class="row g-0 border rounded overflow-hidden flex-md-row mb-4 shadow-sm h-md-250 position-relative">
						<a href="/javaCafe/controller/home" class="nav-link hoverStyle">처음으로</a>
					</div>
				</div>
			</div>
			<div class="row mb-2" style="height: 100px;">
				<div class="col-md-6">
					<form id="aTag2" action="goPayment" method="post">
						<div
							style="display: flex; justify-content: center; line-height: 30px; height: 50px;"
							class="row g-0 border rounded overflow-hidden flex-md-row mb-4 shadow-sm h-md-250 position-relative">
							<a onclick="next_form2()" class="nav-link hoverStyle">주문하기</a>
						</div>
					</form>
				</div>
			</div>


			<p style="height: 20px;">장바구니</p>
			<div class="row mb-2">
				<div style="width: 100%;" class="col-md-6">
					<div
						style="width: 800px; display: flex; justify-content: center; line-height: 150px; height: 100%;"
						class="row g-0 border rounded overflow-hidden flex-md-row mb-4 shadow-sm h-md-250 position-relative">
						<ul>
							<c:forEach var="item" items="${order}" varStatus="status">
								<c:if test="${order != null}">
									<li style="height: 50px; line-height: 100px;">주문${status.index+1})
										${item.categoryName} - ${item.name} - ${item.size} -
										${item.count} - ${item.take} : ${(item.price + item.sizePrice)* item.count }원</li>
								</c:if>
							</c:forEach>
						</ul>
					</div>
				</div>
			</div>
		</main>

		<%-- footer 영역 --%>
		<footer class="mt-auto text-white-50">
			<p>&copy; 2021 Company, Inc. All rights reserved.</p>
		</footer>
	</div>



</body>
</html>

