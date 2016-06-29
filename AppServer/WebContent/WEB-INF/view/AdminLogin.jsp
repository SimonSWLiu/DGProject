<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page isELIgnored="false"%>
<%@ include file="./include/bootstrap_head.html"%>
<script src="${pageContext.request.contextPath }/js/src/AdminLogin.js"></script>

<div>
	<h1 style="text-align: center;">Admin Login</h1>
	<hr>
	<form id="loginForm" action="adminLogin"
		style="width: 100%; text-align: center;" method="POST"
		onKeyPress="enterDown(event);">
		<div>
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
			&nbsp;
		</div>
		<hr>
		<div>Copyright by&nbsp;&copy;&nbsp;One Menu</div>
		<div>
			Technical Support:&nbsp;<a href="javascript:void(0)" target="_blank">One Menu
				Development Team</a>
		</div>
	</form>
</div>

</body>

<script type="text/javascript">

$(document).ready(function(){ 


});
</script>

<%@ include file="./include/bootstrap_foot.html"%>
