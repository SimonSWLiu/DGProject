<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page isELIgnored="false"%>
<%@ include file="../include/bootstrap_head.html"%>

<style type="text/css">

	.demo-container {
		position: relative;
		height: 400px;
	}

	#placeholder {
		width: 100%;
		height: 100%;
	}

	#placeholder2 {
		width: 100%;
		height: 100%;
	}
	
	#placeholder3 {
		width: 50%;
		height: 100%;
	}
	
	#menu {
		position: absolute;
		top: 20px;
		left: 625px;
		bottom: 20px;
		right: 20px;
		width: 200px;
	}

	#menu button {
		display: inline-block;
		width: 200px;
		padding: 3px 0 2px 0;
		margin-bottom: 4px;
		background: #eee;
		border: 1px solid #999;
		border-radius: 2px;
		font-size: 16px;
		-o-box-shadow: 0 1px 2px rgba(0,0,0,0.15);
		-ms-box-shadow: 0 1px 2px rgba(0,0,0,0.15);
		-moz-box-shadow: 0 1px 2px rgba(0,0,0,0.15);
		-webkit-box-shadow: 0 1px 2px rgba(0,0,0,0.15);
		box-shadow: 0 1px 2px rgba(0,0,0,0.15);
		cursor: pointer;
	}

	#description {
		margin: 15px 10px 20px 10px;
	}
</style>
<script
	src="${pageContext.request.contextPath }/lib/bootstrap-jquery-plugin/jquery.flot.min.js"></script>
<script
	src="${pageContext.request.contextPath }/lib/bootstrap-jquery-plugin/jquery.flot.pie.min.js"></script>
<script
	src="${pageContext.request.contextPath }/lib/bootstrap-jquery-plugin/jquery.flot.time.min.js"></script>
<script
	src="${pageContext.request.contextPath }/js/src/restaurant/statement/Statement.js"></script>

<br>
<br>
<form action="" name="restaurantApprovalForm"
	id="restaurantApprovalForm" method="post">

	<div class="form-group">
		<div>
			<table>
				<tr>
					<td style="padding: 0px 0px 0px 0px"><label>From</label></td>
					<td>
						<div class="input-group date query_date" data-date=""
							data-date-format="yyyy-mm-dd hh:mm:ss"
							data-link-field="dtp_input1">
							<input
								class="form-control validate[required,custom[date],future[now]]"
								size="30" type="text" id="fromDateTime" name="fromDateTime"
								value="" readonly> <span class="input-group-addon"><span
								class="glyphicon glyphicon-remove"></span></span> <span
								class="input-group-addon"><span
								class="glyphicon glyphicon-th"></span></span>
						</div>
					</td>
					<td style="padding: 0px 0px 0px 0px"><label>To</label></td>
					<td>
						<div class="input-group date query_date" data-date=""
							data-date-format="yyyy-mm-dd hh:mm:ss"
							data-link-field="dtp_input1">
							<input
								class="form-control validate[required,custom[date],future[mEffectiveDate]]"
								size="30" type="text" id="toDateTime" name="toDateTime" value=""
								readonly> <span class="input-group-addon"><span
								class="glyphicon glyphicon-remove"></span></span> <span
								class="input-group-addon"><span
								class="glyphicon glyphicon-th"></span></span>
						</div>
					</td>
					<td>
						<button type="button" class="btn btn-default" id="addBtn"
							onclick="query();">
							<i class="glyphicon glyphicon-search"></i>
						</button>
					</td>
				</tr>
			</table>
		</div>
	</div>

	<div id="toolbar" class="btn-group">
		<button type="button" class="btn btn-default"
			onclick="queryRecent('DATE',-1);">Recent 1 day</button>
		<button type="button" class="btn btn-default"
			onclick="queryRecent('DATE',-7);">Recent 1 week</button>
		<button type="button" class="btn btn-default"
			onclick="queryRecent('MONTH',-1);">Recent 1 month</button>
		<button type="button" class="btn btn-default"
			onclick="queryRecent('MONTH',-3);">Recent 3 months</button>
	</div>

	<div>
		<table id="statementTable" data-toggle="table"
			data-url="../menuController/queryStatement" data-show-toggle="true"
			data-show-columns="true" data-toolbar="#toolbar">
			<thead>
				<tr>
					<th style="width: 20%;" data-sortable="true"
						data-field="paymentType">Payment Type</th>
					<th style="width: 10%;" data-sortable="true" data-field="netSales">Net
						Sales</th>
					<th style="width: 10%;" data-sortable="true" data-field="tax">Tax</th>
					<th style="width: 10%;" data-sortable="true" data-field="salesTax">Sales
						+ Tax</th>
					<th style="width: 10%;" data-sortable="true"
						data-field="deliveryFee">Delivery Fee</th>
					<th style="width: 10%;" data-sortable="true" data-field="tips">Tips</th>
					<th style="width: 10%;" data-sortable="true"
						data-field="paymentGatewayFees">Payment Gateway Fees</th>
					<th style="width: 10%;" data-sortable="true" data-field="netIncome">Net
						Income</th>
				</tr>
			</thead>
		</table>
	</div>

	<div id="content">
		<div class="demo-container">
			<br>
			<div id="placeholder" class="demo-placeholder"></div>
			<br>
			<div id="placeholder2" class="demo-placeholder"></div>
			<br>
			<div id="placeholder3" class="demo-placeholder"></div>
		</div>
	</div>
	
<%@ include file="../include/bootstrap_foot.html"%>