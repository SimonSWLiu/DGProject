<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page isELIgnored="false"%>
<%@ include file="../include/bootstrap_head.html"%>
<script
	src="${pageContext.request.contextPath }/js/src/restaurant/ChangePassword.js"></script>

<form id="passwordForm" method="post" class="form-horizontal"
	role="form" action="../menuController/changePassword">

	<div class="form-group">
		<div class="col-sm-offset-1 col-sm-11">
			<h1>Change Password</h1>
		</div>
	</div>

	<div class="form-group">
		<div class="col-sm-offset-1 col-sm-2">
			<font style="color: red">${message}</font>
		</div>
	</div>

	<div class="form-group">
		<div class="col-sm-offset-1 col-sm-2">
			<label for="password" class="control-label">Old Password</label>
		</div>
		<div class="col-sm-4">
			<input type="password"
				class="form-control validate[required,minSize[8],maxSize[20]]"
				id="oriPassword" name="oriPassword" placeholder="8-20 characters"
				maxlength="20">
		</div>
	</div>

	<div class="form-group">
		<div class="col-sm-offset-1 col-sm-2">
			<label for="password" class="control-label">New Password</label>
		</div>
		<div class="col-sm-4">
			<input type="password"
				class="form-control validate[required,minSize[8],maxSize[20]]"
				id="curPassword" name="curPassword" placeholder="8-20 characters"
				maxlength="20">
		</div>
	</div>

	<div class="form-group">
		<div class="col-sm-offset-1 col-sm-2">
			<label for="confirmPassword" class="control-label">Confirm
				Password</label>
		</div>
		<div class="col-sm-4">
			<input type="password"
				class="form-control validate[required,equals[curPassword],minSize[8],maxSize[20]]"
				id="confirmPassword" placeholder="8-20 characters" maxlength="20">
		</div>
	</div>

	<div class="form-group">
		<div class="col-sm-offset-3 col-sm-4">
			<button type=submit class="btn btn-primary">Submit</button>
		</div>
	</div>

</form>

<%@ include file="../include/bootstrap_foot.html"%>

