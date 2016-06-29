<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page isELIgnored="false"%>
<%@ include file="./include/bootstrap_head.html"%>
<script src="${pageContext.request.contextPath }/js/src/Login.js"></script>

<script language="JavaScript">
	if (window != top)
		top.location.href = location.href;
</script>

<style type="text/css">
div.c-wrapper {
	width: 100%; /* for example */
	margin: auto;
}

.carousel-inner>.item>img, .carousel-inner>.item>a>img {
	width: 100%; /* use this, or not */
	margin: auto;
}
</style>

<div class="c-wrapper">
	<div id="carousel-example-generic" class="carousel slide"
		data-ride="carousel">
		<!-- Indicators -->
		<ol class="carousel-indicators">
			<li data-target="#carousel-example-generic" data-slide-to="0"
				class="active"></li>
			<li data-target="#carousel-example-generic" data-slide-to="1"></li>
			<li data-target="#carousel-example-generic" data-slide-to="2"></li>
		</ol>

		<!-- Wrapper for slides -->
		<div class="carousel-inner" role="listbox">
			<div class="item active">
				<img
					src="${pageContext.request.contextPath }/images/png/homepage_1.png"
					alt="...">
				<div class="carousel-caption">Pic 1</div>
			</div>
			<div class="item">
				<img
					src="${pageContext.request.contextPath }/images/png/homepage_1.png"
					alt="...">
				<div class="carousel-caption">Pic 2</div>
			</div>
			<div class="item">
				<img
					src="${pageContext.request.contextPath }/images/png/homepage_1.png"
					alt="...">
				<div class="carousel-caption">Pic 3</div>
			</div>
		</div>

		<!-- Controls -->
		<a class="left carousel-control" href="#carousel-example-generic"
			role="button" data-slide="prev"> <span
			class="glyphicon glyphicon-chevron-left"></span> <span
			class="sr-only">Previous</span>
		</a> <a class="right carousel-control" href="#carousel-example-generic"
			role="button" data-slide="next"> <span
			class="glyphicon glyphicon-chevron-right"></span> <span
			class="sr-only">Next</span>
		</a>
	</div>
</div>

<hr />

<div class='form-group'>
	<form id="loginForm" action="login" class="form-horizontal"
		style="width: 100%; text-align: center;" method="POST"
		onKeyPress="enterDown(event);">
		<div class="row">
			<font style="color: red">${message}</font>
		</div>
		<div>
			<label>User Name:</label> <input value="" type="text" name="mEmail"
				id="userName"
				style="font-size: 15px; width: 100px; height: 20px; color: #00547c; border: 1px solid #20b8de; line-height: 18px; padding-left: 5px;">
			&nbsp;&nbsp; <label>Password:</label> <input value="" type="password"
				name="mPassword" id="password"
				style="font-size: 15px; width: 100px; height: 20px; color: #00547c; border: 1px solid #20b8de; line-height: 18px; padding-left: 5px;">
			&nbsp;&nbsp;
			<button type="botton" class="btn btn-primary" id="signInBtn">Sign
				in</button>
			&nbsp;&nbsp; <a
				href="../loginController/showMerchantRegistrationPage"
				style="font-size: 10px;">Sign Up</a>
		</div>
		<hr />
		<a href="../loginController/showAdminLoginPage"
			style="font-size: 10px;">Translate to Admin Login</a>
		<hr>
		<div>Copyright by&nbsp;&copy;&nbsp;One Menu</div>
		<div>
			Technical Support:&nbsp;<a href="javascript:void(0)" target="_blank">One
				Menu Development Team</a>
		</div>
	</form>
</div>

</body>

<%@ include file="./include/bootstrap_foot.html"%>
