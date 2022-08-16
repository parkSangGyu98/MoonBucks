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
		function next2_form() {
			document.getElementById('aTag2').submit();
		}
	</script>
	<%-- header 영역 --%>
	<div class="cover-container d-flex w-100 h-100 p-3 mx-auto flex-column">
		<header class="mb-auto">
			<div style="margin-left: -180px;">
				<h3 class="float-md-start mb-0">MoonBucks</h3>
				<nav style="margin-right: -180px;"
					class="nav nav-masthead justify-content-center float-md-end">
					<a class="nav-link" href="/MoonBucks/controller/home">Home</a> <a
						class="nav-link active" aria-current="page"
						href="/MoonBucks/controller/admin">Admin</a>
				</nav>
			</div>
		</header>
		<%-- container 영역 --%>
		<main style="margin-left: -180px; width: 1000px;" class="container">
			<div style="margin-bottom: 150px;">
				<form id="aTag" action="add_category" method="post"
					enctype="multipart/form-data">
					<h1 style="margin-top: 30px;">Add Category</h1>
					<div class="row mb-2">
						<input placeholder="카테고리명" style="text-align: center;" type="text"
							name="name">
						<div class="row mb-2">
							이미지 : <input type="file" name="file">
						</div>
					</div>
					<button class="button-style">Add</button>
				</form>
				<form id="aTag" action="updateCategoryPic" method="post"
					enctype="multipart/form-data">
					<h1 style="margin-top: 30px;">Update Category Picture</h1>
					<div class="row mb-2">
						<select style="text-align: center; width: 200px;" name="name">
							<option value="unknown">카테고리명</option>
							<c:forEach var="x" items="${category}">
								<option value="${x.name}">${x.name}</option>
							</c:forEach>
						</select>
					</div>
					<div class="row mb-2">
						이미지 : <input type="file" name="file">
					</div>
					<button class="button-style">Update</button>
				</form>
				<form id="aTag" action="deleteCategory" method="post"">
					<h1 style="margin-top: 30px;">Delete Category</h1>
					<div class="row mb-2">
						<select style="text-align: center; width: 200px;" name="name">
							<option value="unknown">카테고리명</option>
							<c:forEach var="x" items="${category}">
								<option value="${x.name}">${x.name}</option>
							</c:forEach>
						</select>
					</div>
					<button class="button-style">Delete</button>
				</form>
				<div style="margin-top: 150px;">
					---------------------------------------------------------------------------------
				</div>
			</div>
			<div style="margin-bottom: 150px;">
				<form id="aTag" action="readMenu" method="post">
					<h1 style="margin-top: 30px;">Read Menu</h1>
					<div class="row mb-2">
						<select style="text-align: center; width: 200px;" name="name">
							<option value="unknown">카테고리명</option>
							<c:forEach var="x" items="${category}">
								<option value="${x.name}">${x.name}</option>
							</c:forEach>
						</select>
					</div>
					<button class="button-style">Read</button>
				</form>
				<form id="aTag" action="add_menu" method="post"
					enctype="multipart/form-data">
					<h1 style="margin-top: 30px;">Add Menu</h1>
					<div class="row mb-2">
						<input placeholder="메뉴명" style="text-align: center;" type="text"
							name="name">
					</div>
					<div class="row mb-2">
						<input class="styleman moreStyle" placeholder="메뉴 가격"
							type="number" min="0" required name="price">
					</div>
					<div class="row mb-2">
						<input class="styleman moreStyle" placeholder="메뉴 입고량"
							type="number" min="0" required name="quantity">
					</div>
					<div class="row mb-2">
						<select style="text-align: center;" name="categoryName">
							<option value="unknown">메뉴 카테고리명</option>
							<c:forEach var="x" items="${category}">
								<option value="${x.name}">${x.name}</option>
							</c:forEach>
						</select>
					</div>
					<div class="row mb-2">
						이미지 : <input type="file" name="file">
					</div>
					<button class="button-style">Add</button>
				</form>

				<form id="aTag" action="updateMenu" method="post">
					<h1 style="margin-top: 30px;">Update Menu</h1>
					<div class="row mb-2">
						<select style="text-align: center;" name="prevName">
							<option value="unknown">변경 전 메뉴명</option>
							<c:forEach var="x" items="${menu}">
								<option value="${x.name}">${x.name}</option>
							</c:forEach>
						</select>
					</div>
					<div class="row mb-2">
						<input placeholder="변경 후 메뉴명" style="text-align: center;"
							type="text" name="afterName">
					</div>
					<div class="row mb-2">
						<input class="styleman moreStyle" placeholder="변경 후 가격"
							type="number" min="0" required name="afterPrice">
					</div>
					<button class="button-style">Update</button>
				</form>

				<form id="aTag" action="deleteMenu" method="post">
					<h1 style="margin-top: 30px;">Delete Menu</h1>
					<div class="row mb-2">
						<select style="text-align: center;" name="name">
							<option value="unknown">메뉴명</option>
							<c:forEach var="x" items="${menu}">
								<option value="${x.name}">${x.name}</option>
							</c:forEach>
						</select>
					</div>
					<button class="button-style">Delete</button>
				</form>
			</div>
			<div style="margin-top: 150px;">
				---------------------------------------------------------------------------------
			</div>
			<form id="aTag" action="addInventory" method="post">
				<h1 style="margin-top: 30px;">Add Inventory</h1>
				<div class="row mb-2">
					<select style="text-align: center; width: 450px;" name="name">
						<option value="unknown">메뉴명</option>
						<c:forEach var="x" items="${menu}">
							<option value="${x.name}">${x.name}- 현재고 : ${x.quantity}</option>
						</c:forEach>
					</select>
				</div>
				<div class="row mb-2">
					<input class="styleman moreStyle" placeholder="수량" type="number"
						min="0" required name="quantity">
				</div>
				<button class="button-style">Add</button>
			</form>
			<form id="aTag" action="deleteInventory" method="post">
				<h1 style="margin-top: 30px;">Delete Inventory</h1>
				<div class="row mb-2">
					<select style="text-align: center; width: 450px;" name="name">
						<option value="unknown">메뉴명</option>
						<c:forEach var="x" items="${menu}">
							<option value="${x.name}">${x.name}- 현재고 : ${x.quantity}</option>
						</c:forEach>
					</select>
				</div>
				<div class="row mb-2">
					<input class="styleman moreStyle" placeholder="수량" type="number"
						min="0" required name="quantity">
				</div>
				<button class="button-style">Delete</button>
			</form>

		</main>

		<%-- footer 영역 --%>
		<footer class="mt-auto text-white-50">
			<p>&copy; 2021 Company, Inc. All rights reserved.</p>
		</footer>
	</div>



</body>
</html>

