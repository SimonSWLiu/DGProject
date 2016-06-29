<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page isELIgnored="false"%>
<%@ include file="../include/bootstrap_head.html"%>
<script
	src="${pageContext.request.contextPath }/js/src/restaurant/menu/DishCategoryEdit.js"></script>
<script
	src="${pageContext.request.contextPath }/lib/bootstrap-jquery-plugin/jquery.base64.js"></script>
<script
	src="${pageContext.request.contextPath }/lib/bootstrap-jquery-plugin/tableExport.js"></script>

<form action="" name="dishCategoryEditForm" id="dishCategoryEditForm"
	method="post">

	<div id="toolbar" class="btn-group">
		<button type="button" class="btn btn-danger" id="addBtn" onclick="showDishCategoryModel('');">
			<i class="glyphicon glyphicon-plus"></i>
		</button>
	</div>

	<div>
		<table id="dishCategoryListTable" data-toggle="table"
			data-query-params="queryParams" data-pagination="true"
			data-search="true" data-show-refresh="true" data-show-toggle="true"
			data-show-columns="true" data-show-export="false"
			data-click-to-select="false" data-toolbar="#toolbar"
			data-toolbar="#toolbar1" data-height="500">
			<thead>
				<tr>
					<th style="width: 5%;" data-checkbox="true"></th>
					<th style="width: 10%;" data-field="action"
						data-formatter="actionFormatter" data-events="actionEvents">Action</th>
					<th style="width: 20%;" data-sortable="true">Create Date</th>
					<th style="width: 30%;" data-sortable="true">Name</th>
					<th style="width: 20%;" data-sortable="true">sequence</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${ dishCategoryBeanList }" var="dishCategory">

					<tr id="${dishCategory.id}">
						<td style="width: 5%;"></td>
						<td style="width: 10%;"></td>
						<td style="width: 20%;">${ dishCategory.createDate }</td>
						<td style="width: 30%;">${ dishCategory.name }</td>
						<td style="width: 20%;">${ dishCategory.sequence }</td>
					</tr>

				</c:forEach>
			</tbody>
		</table>
	</div>

</form>

<br>

<!-- Dish Category Modal -->
<div class='modal fade' id='diahCategoryModal' tabindex='-1'
	role='dialog' aria-labelledby='myModalLabel' aria-hidden='true'>
	<div class='modal-dialog modal-lg'>
		<div class='modal-content'>
			<div class='modal-header'>
				<button type='button' class='close' data-dismiss='modal'>
					<span aria-hidden='true'>&times;</span><span class='sr-only'>Close</span>
				</button>
				<h4 class='modal-title' id='myModalLabel'>Dish category Detail</h4>
			</div>

			<div class='modal-body'>

				<form id="couponDetailForm" method="post" class="form-horizontal">

					<input type="text" id="dishCategoryId" hidden>
					
					<div class="form-group">
						<div class="col-sm-4">
							<label for="dishCategoryName" class="control-label"
								style="text-align: left">Dish Category Name</label>
						</div>
						<div class="col-sm-4 ">
							<input type="text" class="form-control validate[required]"
								id="dishCategoryName" placeholder="Name" value="">
						</div>
					</div>

					<div class="form-group">
						<div class="col-sm-4">
							<label for="dishCategorySeq" class="control-label"
								style="text-align: left">Dish Category Sequence</label>
						</div>
						<div class="col-sm-4 ">
							<input type="text" class="form-control validate[required]"
								id="dishCategorySeq" placeholder="Sequence" value="">
						</div>
					</div>

				</form>
			</div>

			<div class='modal-footer'>
				<button type='button' class='btn btn-default' data-dismiss='modal'>Close</button>
				<button type='button' class='btn btn-primary' id='dishCategorySubmitBtn'>Save</button>
			</div>
		</div>
	</div>
</div>

<%@ include file="../include/bootstrap_foot.html"%>

