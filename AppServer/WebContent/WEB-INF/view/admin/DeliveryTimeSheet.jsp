<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page isELIgnored="false"%>
<%@ include file="../include/bootstrap_head.html"%>
<script
	src="${pageContext.request.contextPath }/js/src/admin/DeliveryTimeSheet.js"></script>

<br>

<div>

	<div id="toolbar" class="btn-group">
		<button type="button" class="btn btn-danger" id="addBtn"
			onclick="showDeliveryTimeSheetDetailModal();">
			<i class="glyphicon glyphicon-plus"></i>
		</button>
	</div>


	<table id="driverTable" data-toggle="table"
		data-query-params="queryParams" data-pagination="true"
		data-search="true" data-show-refresh="false" data-show-toggle="true"
		data-show-columns="true" data-show-export="false"
		data-toolbar="#toolbar" data-click-to-select="false" data-height="520">
		<thead>
			<tr>
				<th style="width: 20%; text-align: center;" data-sortable="true">Date</th>
				<th style="width: 20%;" data-sortable="true">Available Start
					Time</th>
				<th style="width: 20%;" data-sortable="true">Available End Time</th>
				<th style="width: 20%;" data-sortable="true">Available Quantity</th>
				<th style="width: 20%;" data-sortable="true">Total Quantity</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${ deliveryTimeSheetBeanList }"
				var="deliveryTimeSheetBean">
				<tr id="${deliveryTimeSheetBean.id}">
					<td style="width: 20%;">${ deliveryTimeSheetBean.availableDate }</td>
					<td style="width: 20%;">${ deliveryTimeSheetBean.availableStartTime }</td>
					<td style="width: 20%;">${ deliveryTimeSheetBean.availableEndTime }</td>
					<td style="width: 20%;">${ deliveryTimeSheetBean.availableQuantity }</td>
					<td style="width: 20%;">${ deliveryTimeSheetBean.quantity }</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</div>

<br>

<!-- Order Form Detial Modal -->
<div class='modal fade' id='deliveryTimeSheetDetailModal' tabindex='-1'
	role='dialog' aria-labelledby='deliveryTimeSheetDetailModalLabel'
	aria-hidden='true'>
	<div class='modal-dialog modal-lg'>
		<div class='modal-content'>
			<div class='modal-header'>
				<button type='button' class='close' data-dismiss='modal'>
					<span aria-hidden='true'>&times;</span><span class='sr-only'>Close</span>
				</button>
				<h4 class='modal-title' id='deliveryTimeSheetDetailModalLabel'>Detail</h4>
			</div>

			<div class='modal-body'>

				<div id="detailDiv">

					<form id="detailForm" method="post" class="form-horizontal">
						<input type="text" id="deliveryTimeSheetId" name="mId" hidden>					
						<div class="form-group">
							<div class="col-sm-3">
								<label for="availableDate" class="control-label text-left">Available
									Date:</label>
							</div>
							<div class="col-sm-4">
								<input type="text" class="form-control validate[required]"
									id="availableDate" name="mAvailableDate"
									placeholder="YYYY-MM-DD">
							</div>
						</div>
						<div class="form-group">
							<div class="col-sm-3">
								<label for="availableStartTime" class="control-label text-left">Available
									Start Time:</label>
							</div>
							<div class="col-sm-4" id="">
								<input type="text" class="form-control validate[required]"
									id="availableStartTime" name="mAvailableStartTime"
									placeholder="hh:mm">
							</div>
						</div>
						<div class="form-group">
							<div class="col-sm-3">
								<label for="availableEndTime" class="control-label text-left">Available
									End Time:</label>
							</div>
							<div class="col-sm-4">
								<input type="text" class="form-control validate[required]"
									id="availableEndTime" name="mAvailableEndTime"
									placeholder="hh:mm">
							</div>
						</div>
						<div class="form-group">
							<div class="col-sm-3">
								<label for="quantity" class="control-label text-left">Total
									Quantity:</label>
							</div>
							<div class="col-sm-4">
								<input type="text" class="form-control validate[required]"
									id="quantity" name="mQuantity" placeholder="">
							</div>
						</div>
					</form>
				</div>

			</div>

			<div class='modal-footer'>
				<button type='button' class='btn btn-default' data-dismiss='modal'>Close</button>
				<button type='button' class='btn btn-primary' id='saveBtn'>Save</button>
			</div>
		</div>
	</div>
</div>

<%@ include file="../include/bootstrap_foot.html"%>