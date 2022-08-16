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
	</script>
	<%-- header 영역 --%>
	<div class="cover-container d-flex w-100 h-100 p-3 mx-auto flex-column">
		<header class="mb-auto">
			<div style="margin-left: -180px;">
				<h3 class="float-md-start mb-0">MoonBucks</h3>
				<nav style="margin-right: -180px;"
					class="nav nav-masthead justify-content-center float-md-end">
					<a class="nav-link active" aria-current="page"
						href="/MoonBucks/controller/home">Home</a> <a class="nav-link"
						href="/MoonBucks/controller/admin">Admin</a>
				</nav>
			</div>
		</header>
		<%-- container 영역 --%>
		<form id="aTag" action="home" method="post"
			style="display: flex; justify-content: center;">
			<a href="/MoonBucks/controller/main" style="color: white;" class="nav-link">
			<h1 style="border-radius: 10px; width: 200px; padding: 0;">Click Here</h1></a>
		</form>
		<%-- footer 영역 --%>
		<footer class="mt-auto text-white-50">
			<p>&copy; 2021 Company, Inc. All rights reserved.</p>
		</footer>
	</div>



</body>
</html>

