<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page isELIgnored="false"%>
<%@ include file="../include/bootstrap_head.html"%>
<script
	src="${pageContext.request.contextPath }/js/src/restaurant/order/OrderFormList.js"></script>
<script
	src="${pageContext.request.contextPath }/lib/bootstrap-table/dist/extensions/export/bootstrap-table-export.min.js"></script>
<script
	src="${pageContext.request.contextPath }/lib/bootstrap-jquery-plugin/jquery.base64.js"></script>
<script
	src="${pageContext.request.contextPath }/lib/bootstrap-jquery-plugin/tableExport.js"></script>

<br>

<form action="" name="restaurantApprovalForm"
	id="restaurantApprovalForm" method="post">

	<div>
		<table id="orderFormTable" data-toggle="table"
			data-query-params="queryParams" data-pagination="true"
			data-search="true" data-show-refresh="false" data-show-toggle="true"
			data-show-columns="true" data-show-export="false"
			data-click-to-select="false"
			data-height="520">
			<thead>
				<tr>
					<!-- <th style="width: 5%;" data-checkbox="true"></th> -->
					<th style="width: 10%;" data-field="action"
						data-formatter="actionFormatter" data-events="actionEvents">Action</th>
					<th style="width: 20%;" data-sortable="true">Time</th>
					<th style="width: 30%;" data-sortable="true">Customer</th>
					<th style="width: 20%;" data-sortable="true">Price</th>
					<th style="width: 15%;" data-sortable="true">Status</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${ orderFormList }" var="orderForm">
					<input type="text" id="status${orderForm.mId}" value="${ orderForm.mStatus }" hidden>
					<c:if test="${orderForm.mStatus == '0'}">
						<tr id="${orderForm.mId}" class="alert alert-danger">
							<td style="width: 10%;"></td>
							<td style="width: 20%;">${ orderForm.mCreateTimestamp }</td>
							<td style="width: 30%;">${ orderForm.mTrade.mCustomerName }</td>
							<td style="width: 20%;">${ orderForm.mPrice }</td>
							<td style="width: 15%;" id="statusStr${orderForm.mId}">Canceled</td>
						</tr>
					</c:if>
					<c:if test="${orderForm.mStatus == '1'}">
						<tr id="${orderForm.mId}" class="alert alert-success"  status="${ orderForm.mStatus }">
							<td style="width: 10%;"></td>
							<td style="width: 20%;">${ orderForm.mCreateTimestamp }</td>
							<td style="width: 30%;">${ orderForm.mTrade.mCustomerName }</td>
							<td style="width: 20%;">${ orderForm.mPrice }</td>
							<td style="width: 15%;" id="statusStr${orderForm.mId}">Finished</td>
						</tr>
					</c:if>
					<c:if test="${orderForm.mStatus == '2'}">
						<tr id="${orderForm.mId}" class="alert alert-warning"  status="${ orderForm.mStatus }">
							<td style="width: 10%;"></td>
							<td style="width: 20%;">${ orderForm.mCreateTimestamp }</td>
							<td style="width: 30%;">${ orderForm.mTrade.mCustomerName }</td>
							<td style="width: 20%;">${ orderForm.mPrice }</td>
							<td style="width: 15%;" id="statusStr${orderForm.mId}">Pending</td>
						</tr>
					</c:if>
					<c:if test="${orderForm.mStatus == '3'}">
						<tr id="${orderForm.mId}" class="alert alert-warning"  status="${ orderForm.mStatus }">
							<td style="width: 10%;"></td>
							<td style="width: 20%;">${ orderForm.mCreateTimestamp }</td>
							<td style="width: 30%;">${ orderForm.mTrade.mCustomerName }</td>
							<td style="width: 20%;">${ orderForm.mPrice }</td>
							<td style="width: 15%;" id="statusStr${orderForm.mId}">Restaurant Confirmed</td>
						</tr>
					</c:if>
					<c:if test="${orderForm.mStatus == '4'}">
						<tr id="${orderForm.mId}" class="alert alert-warning"  status="${ orderForm.mStatus }">
							<td style="width: 10%;"></td>
							<td style="width: 20%;">${ orderForm.mCreateTimestamp }</td>
							<td style="width: 30%;">${ orderForm.mTrade.mCustomerName }</td>
							<td style="width: 20%;">${ orderForm.mPrice }</td>
							<td style="width: 15%;" id="statusStr${orderForm.mId}">Call Delivery</td>
						</tr>
					</c:if>
					<c:if test="${orderForm.mStatus == '5'}">
						<tr id="${orderForm.mId}" class="alert alert-warning"  status="${ orderForm.mStatus }">
							<td style="width: 10%;"></td>
							<td style="width: 20%;">${ orderForm.mCreateTimestamp }</td>
							<td style="width: 30%;">${ orderForm.mTrade.mCustomerName }</td>
							<td style="width: 20%;">${ orderForm.mPrice }</td>
							<td style="width: 15%;" id="statusStr${orderForm.mId}">On Delivery</td>
						</tr>
					</c:if>

				</c:forEach>
			</tbody>
		</table>
	</div>

	<br>

	<!-- Order Form Detial Modal -->
	<div class='modal fade' id='orderFormDetailModal' tabindex='-1'
		role='dialog' aria-labelledby='orderFormDetailModalLabel'
		aria-hidden='true'>
		<div class='modal-dialog modal-lg'>
			<div class='modal-content'>
				<div class='modal-header'>
					<button type='button' class='close' data-dismiss='modal'>
						<span aria-hidden='true'>&times;</span><span class='sr-only'>Close</span>
					</button>
					<h4 class='modal-title' id='orderFormDetailModalLabel'>Detail</h4>
				</div>

				<div class='modal-body'>

					<div id="detailDiv">

						<div class="row">
							<div class="col-sm-2">
								<label for="getType" class="control-label text-left">Get
									Type:</label>
							</div>
							<div class="col-sm-10" id='getType'></div>
						</div>
						<div class="row">
							<div class="col-sm-2">
								<label for="orderTime" class="control-label text-left">Order
									Time:</label>
							</div>
							<div class="col-sm-10" id="orderTime"></div>
						</div>
						<div class="row">
							<div class="col-sm-2">
								<label for="custName" class="control-label text-left">Customer
									Name</label>
							</div>
							<div class="col-sm-10" id="custName"></div>
						</div>
						<div class="row">
							<div class="col-sm-2">
								<label for="custAddress" class="control-label text-left">Customer
									Address</label>
							</div>
							<div class="col-sm-10" id="custAddress"></div>
						</div>
						<div class="row">
							<div class="col-sm-2">
								<label for="custPhone" class="control-label text-left">Customer
									Phone</label>
							</div>
							<div class="col-sm-10" id="custPhone"></div>
						</div>
						<table class="table table-hover" id="billTable">
							<thead>
								<tr>
									<th>QTY</th>
									<th colspan="2">Item</th>
									<th style="text-align: right">Price</th>
								</tr>
							</thead>
							<tbody id="billTbody">
							</tbody>
						</table>
					</div>

				</div>

				<div class='modal-footer'>
					<button type='button' class='btn btn-default' data-dismiss='modal'>Close</button>
				</div>
			</div>
		</div>
	</div>

	<%@ include file="../include/bootstrap_foot.html"%>