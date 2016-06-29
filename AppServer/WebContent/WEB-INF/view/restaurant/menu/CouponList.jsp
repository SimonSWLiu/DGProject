<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page isELIgnored="false"%>
<%@ include file="../include/bootstrap_head.html"%>
<script
	src="${pageContext.request.contextPath }/js/src/restaurant/menu/CouponList.js"></script>
<script
	src="${pageContext.request.contextPath }/lib/bootstrap-table/dist/extensions/export/bootstrap-table-export.min.js"></script>
<script
	src="${pageContext.request.contextPath }/lib/bootstrap-jquery-plugin/jquery.base64.js"></script>
<script
	src="${pageContext.request.contextPath }/lib/bootstrap-jquery-plugin/tableExport.js"></script>
	
<br>

<form action="" name="couponListForm" id="couponListForm" method="post">

	<div id="toolbar" class="btn-group">
		<button type="button" class="btn btn-danger" id="addBtn" onclick="showCouponDetailModel('');">
			<i class="glyphicon glyphicon-plus"></i>
		</button>
	</div>
	
	<div>
		<table id="couponListTable" data-toggle="table"
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
					<th style="width: 20%;" data-sortable="true">Create Time</th>
					<th style="width: 30%;" data-sortable="true">Coupon Name</th>
					<th style="width: 20%;" data-sortable="true">Coupon Description</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${ couponList }" var="coupon">

					<tr id="${coupon.mId}">
						<td style="width: 5%;"></td>
						<td style="width: 10%;"></td>
						<td style="width: 20%;">${ coupon.mCreateTimestamp }</td>
						<td style="width: 30%;">${ coupon.mName }</td>
						<td style="width: 20%;">${ coupon.mDescription }</td>
					</tr>
				
				</c:forEach>
			</tbody>
		</table>
	</div>

</form>

<br>

	<!-- Modal -->
	<div class='modal fade' id='couponDetailModal' tabindex='-1'
		role='dialog' aria-labelledby='couponDetailModalLabel' aria-hidden='true' >
		<div class='modal-dialog modal-lg' >
			<div class='modal-content'>
				<div class='modal-header'>
					<button type='button' class='close' data-dismiss='modal'>
						<span aria-hidden='true'>&times;</span><span class='sr-only'>Close</span>
					</button>
					<h4 class='modal-title' id='couponDetailModalLabel'>Coupon Detail</h4>
				</div>
				<div class='modal-body'>

					<form id="couponDetailForm" method="post" class="form-horizontal">
					
						<input type="text" id="couponId" hidden>
						
						<div class="form-group">
							<div class="col-sm-2">
								<label for="couponName" class="control-label"
									style="text-align: left">Coupon Name</label>
							</div>
							<div class="col-sm-4 ">
								<input type="text" class="form-control validate[required]"
									id="couponName" name="mName" placeholder="Name"
									value="${coupon.mName}">
							</div>
						</div>
					
						<div class="form-group">
							<div class="col-sm-2">
								<label for="discountTargetSelect" class="control-label"
									style="text-align: left">Discount Target</label>
							</div>
							<div class="col-sm-7">
								<select class="form-control validate[required]"
									id="discountTargetSelect" name="mTargetType">
									<option value="1">Item</option>
									<option value="2">Row Bill</option>
								</select>
							</div>
						</div>
					
						<div class="form-group" id="discountTargetTableDiv" hidden>
							<div class="col-sm-2"></div>
							<div class="col-sm-10">
								<table id="discountTargetTable">
									<thead>
										<th>Existing Dish</th>
										<th></th>
										<th>Selected Dish</th>
									</thead>
									<tbody>
										<tr>
											<td style="width: 300px">
											<select class="form-control"
												id="discountTargetLeftSelect" multiple>
											</select>
											</td>
											<td style="width: 200px; text-align: center">
												<button type="button" class="btn btn-default btn-xs"
													onclick="exchange('discountTargetRightSelect','discountTargetLeftSelect','ALL')">
													<span class="glyphicon glyphicon-chevron-left"></span> <span
														class="glyphicon glyphicon-chevron-left"></span>
												</button>
												<button type="button" class="btn btn-default btn-xs"
													onclick="exchange('discountTargetRightSelect','discountTargetLeftSelect','SELECTED')">
													<span class="glyphicon glyphicon-chevron-left"></span>
												</button>
												<button type="button" class="btn btn-default btn-xs"
													onclick="exchange('discountTargetLeftSelect','discountTargetRightSelect','SELECTED')">
													<span class="glyphicon glyphicon-chevron-right"></span>
												</button>
												<button type="button" class="btn btn-default btn-xs"
													onclick="exchange('discountTargetLeftSelect','discountTargetRightSelect','ALL')">
													<span class="glyphicon glyphicon-chevron-right"></span> <span
														class="glyphicon glyphicon-chevron-right"></span>
												</button>
											</td>
											<td style="width: 300px"><select
												class="form-control validate[required]"
												id="discountTargetRightSelect" name="couponTargetDishIds"
												multiple>
											</select></td>
										</tr>
									</tbody>
								</table>
					
							</div>
						</div>
					
						<div class="form-group">
							<div class="col-sm-2">
								<label for="couponTypeSelect" class="control-label"
									style="text-align: left">Coupon Type</label>
							</div>
							<div class="col-sm-7">
								<div>
									<table>
										<tr>
											<td><select class="form-control validate[required]"
												id="couponTypeSelect" name="mType">
													<option value="1">Special Price</option>
													<option value="2">$ Off</option>
													<option value="3">% Off</option>
											</select></td>
											<td><input type="number" class="form-control" id="couponTypeAmount"
												name="mTypeAmount" placeholder="0" value="" min="0"></td>
										</tr>
									</table>
								</div>
							</div>
						</div>
					
						<div class="form-group">
							<div class="col-sm-2">
								<label for="extraCriSelect" class="control-label"
									style="text-align: left">Extra Criteria (Optional)</label>
							</div>
							<div class="col-sm-7">
								<select class="form-control" id="extraCriSelect"
									name="mExtraCriType">
									<option value="0">Please select if required</option>
									<option value="1">Items in current order</option>
									<option value="2">Minimum Row Bill</option>
								</select>
							</div>
						</div>
					
						<div class="form-group" id="extraCriTableDiv" hidden>
							<div class="col-sm-2"></div>
							<div class="col-sm-10">
								<table id="extraCriTable">
									<thead>
										<th>Existing Dish</th>
										<th></th>
										<th>Selected Dish</th>
									</thead>
									<tbody>
										<tr>
											<td style="width: 300px">
												<select class="form-control" id="extraCriLeftSelect" multiple></select>
											</td>
											<td style="width: 200px; text-align: center">
												<button type="button" class="btn btn-default btn-xs"
													onclick="exchange('extraCriRightSelect','extraCriLeftSelect','ALL')">
													<span class="glyphicon glyphicon-chevron-left"></span> <span
														class="glyphicon glyphicon-chevron-left"></span>
												</button>
												<button type="button" class="btn btn-default btn-xs"
													onclick="exchange('extraCriRightSelect','extraCriLeftSelect','SELECTED')">
													<span class="glyphicon glyphicon-chevron-left"></span>
												</button>
												<button type="button" class="btn btn-default btn-xs"
													onclick="exchange('extraCriLeftSelect','extraCriRightSelect','SELECTED')">
													<span class="glyphicon glyphicon-chevron-right"></span>
												</button>
												<button type="button" class="btn btn-default btn-xs"
													onclick="exchange('extraCriLeftSelect','extraCriRightSelect','ALL')">
													<span class="glyphicon glyphicon-chevron-right"></span> <span
														class="glyphicon glyphicon-chevron-right"></span>
												</button>
											</td>
											<td style="width: 300px">
											<select class="form-control validate[required]" id="extraCriRightSelect"
												name="couponExtraCriDishIds" multiple>
											</select>
											</td>
										</tr>
									</tbody>
								</table>
							</div>
						</div>
					
						<div class="form-group" id="extraCriAmountDiv" hidden>
							<div class="col-sm-2"></div>
							<div class="col-sm-2">
								Minimum price
								<input type="number" class="form-control validate[required]"
									id="extraCriAmount" name="mExtraCriAmount" placeholder="0" value=""
									min="0">
							</div>
						</div>
					
						<div class="form-group">
							<div class="col-sm-2">
								<label for="couponQuantity" class="control-label"
									style="text-align: left">Coupon Quantity</label>
							</div>
							<div class="col-sm-2">
								<input type="number" class="form-control" id="couponQuantity"
									name="mTotalQuantity" placeholder=""
									value="${coupon.mTotalQuantity}" min="0">
							</div>
						</div>
					
						<div class="form-group">
							<div class="col-sm-2">
								<label for="effectiveDate" class="control-label"
									style="text-align: left">Effective Date</label>
							</div>
							<div class="col-sm-7">
								<table>
									<tr>
										<td style="padding: 5px 5px 5px 5px">From</td>
										<td>
											<div class="input-group date effective_date" data-date=""
												data-date-format="yyyy-mm-dd" data-link-field="dtp_input1">
												<input
													class="form-control validate[required,custom[date],future[now]]"
													size="30" type="text" id="effectiveDate" name="mEffectiveDate"
													value="" readonly> <span class="input-group-addon"><span
													class="glyphicon glyphicon-remove"></span></span> <span
													class="input-group-addon"><span
													class="glyphicon glyphicon-th"></span></span>
											</div>
										</td>
										<td style="padding: 5px 5px 5px 5px">To</td>
										<td>
											<div class="input-group date effective_date" data-date=""
												data-date-format="yyyy-mm-dd" data-link-field="dtp_input1">
												<input
													class="form-control validate[required,custom[date],future[mEffectiveDate]]"
													size="30" type="text" id="maturityDate" name="mMaturityDate"
													value="" readonly> <span class="input-group-addon"><span
													class="glyphicon glyphicon-remove"></span></span> <span
													class="input-group-addon"><span
													class="glyphicon glyphicon-th"></span></span>
											</div>
										</td>
									</tr>
								</table>
							</div>
						</div>
					
						<div class="form-group">
							<div class="col-sm-2">
								<label for="mOpeningTime" class="control-label">Weeks</label>
							</div>
							<div class="col-sm-7">
								<button type='button' class='btn btn-default' id='couponWholeWeekBtn'>Whole week</button>
							</div>
						</div>
					
						<div class="form-group" id="weekdayChexkbox">
							<div class="col-sm-2"></div>
							<div class="col-sm-7">
								<input type="checkbox" id='couponWeek1' name='mWeeks' value="1"
									class="weekday validate[groupRequired[weekday]">Mon&nbsp;&nbsp;
								<input type="checkbox" id='couponWeek2' name='mWeeks' value="2"
									class="weekday validate[groupRequired[weekday]">Tue&nbsp;&nbsp;
								<input type="checkbox" id='couponWeek3' name='mWeeks' value="3"
									class="weekday validate[groupRequired[weekday]">Wed&nbsp;&nbsp;
								<input type="checkbox" id='couponWeek4' name='mWeeks' value="4"
									class="weekday validate[groupRequired[weekday]">Thu&nbsp;&nbsp;
								<input type="checkbox" id='couponWeek5' name='mWeeks' value="5"
									class="weekday validate[groupRequired[weekday]">Fri&nbsp;&nbsp;
								<input type="checkbox" id='couponWeek6' name='mWeeks' value="6"
									class="weekday validate[groupRequired[weekday]">Sat&nbsp;&nbsp;
								<input type="checkbox" id='couponWeek7' name='mWeeks' value="7"
									class="weekday validate[groupRequired[weekday]">Sun&nbsp;&nbsp;
							</div>
						</div>
						<div class="form-group">
							<div class="col-sm-2">
								<label for="mOpeningTime" class="control-label">Available
									Time</label>
							</div>
							<div class="col-sm-7">
								<table>
									<tr>
										<td style="padding: 5px 5px 5px 5px">From</td>
										<td>
											<div class="input-group date available_time" data-date=""
												data-date-format="hh:ii" data-link-field="dtp_input1">
												<input class="form-control validate[future[now]]" size="30"
													type="text" value="${coupon.mAvailableStartTime}"
													id="availableStartTime" name="mAvailableStartTime" readonly>
												<span class="input-group-addon"><span
													class="glyphicon glyphicon-remove"></span></span> <span
													class="input-group-addon"><span
													class="glyphicon glyphicon-th"></span></span>
											</div>
										</td>
										<td style="padding: 5px 5px 5px 5px">To</td>
										<td>
											<div class="input-group date available_time" data-date=""
												data-date-format="hh:ii" data-link-field="dtp_input1">
												<input
													class="form-control validate[condRequired[mAvailableStartTime],future[mAvailableStartTime]]"
													size="30" type="text" value="${coupon.mAvailableEndTime}"
													id="availableEndTime" name="mAvailableEndTime" readonly>
												<span class="input-group-addon"><span
													class="glyphicon glyphicon-remove"></span></span> <span
													class="input-group-addon"><span
													class="glyphicon glyphicon-th"></span></span>
											</div>
										</td>
									</tr>
								</table>
							</div>
						</div>
					
						<div class="form-group">
							<div class="col-sm-2">
								<label for="description" class="control-label"
									style="text-align: left">Description</label>
							</div>
							<div class="col-sm-7">
								<textarea rows="3" class="form-control" id="couponDesc"
									name="mDescription" placeholder="Description"
									value="${coupon.mDescription}"></textarea>
							</div>
						</div>
						
					</form>
						
				</div>
				
				<div class='modal-footer'>
					<button type='button' class='btn btn-default'
						data-dismiss='modal'>Close</button>
					<button type='button' class='btn btn-primary'
						id='couponDetialSubmitBtn'>Save</button>
				</div>
				
			</div>
		</div>
	</div>
		
</div>

<%@ include file="../include/bootstrap_foot.html"%>
