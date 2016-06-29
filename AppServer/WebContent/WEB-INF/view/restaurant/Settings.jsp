<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page isELIgnored="false"%>
<%@ include file="../include/bootstrap_head.html"%>
<script
	src="${pageContext.request.contextPath }/js/src/restaurant/Settings.js"></script>

<form id="" method="post" class="form-horizontal" role="form" action="">

	<div class="form-group">
		<div class="col-sm-offset-1 col-sm-11">
			<h1>Settings</h1>
		</div>
	</div>

	<div class="form-group">
		<div class="col-sm-offset-1 col-sm-2">
			<label for="filePath" class="control-label">Restaurant Status</label>
		</div>
		<div class="col-sm-7">
			<div class="caption" id="ableBtn">
				<c:if test="${restaurant.mStatus == '1'}">
					<a href="javascript:void(0)" class="btn btn-danger" role="button"
						onclick="disableRestaurant('${restaurant.mId}')">Disable</a>
				</c:if>
				<c:if test="${restaurant.mStatus == '0'}">
					<a href="javascript:void(0)" class="btn btn-success" role="button"
						onclick="enableRestaurant('${restaurant.mId}')">Enable</a>
				</c:if>
			</div>
		</div>
	</div>

</form>

<%@ include file="../include/bootstrap_foot.html"%>

