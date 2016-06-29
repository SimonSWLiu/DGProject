<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page isELIgnored="false"%>
<%@ include file="../include/bootstrap_head.html"%>
<script
		src="${pageContext.request.contextPath }/lib/flashSound/jquery.flashSound-1.2.js"></script>
<script
	src="${pageContext.request.contextPath }/js/src/restaurant/delivery/CallDelivery.js"></script>

<style type="text/css">
.btn-circle {
	width: 30px;
	height: 30px;
	text-align: center;
	padding: 6px 0;
	font-size: 12px;
	line-height: 1.428571429;
	border-radius: 15px;
}

.btn-circle.btn-lg {
	width: 50px;
	height: 50px;
	padding: 10px 16px;
	font-size: 18px;
	line-height: 1.33;
	border-radius: 25px;
}

.btn-circle.btn-xl {
	width: 200px;
	height: 200px;
	padding: 10px 16px;
	font-size: 24px;
	line-height: 1.33;
	border-radius: 165px;
}

</style>


<br>
<div class="panel-group" id="noficationAccordion">
	<div class="panel panel-info">
		<div class="panel-heading">
			<h4 class="panel-title">
				<a data-toggle="collapse" data-parent="#noficationAccordion"
					href="#noficationCollapse"><span class="badge"
					id="noficationBadge">0</span> incoming orders.</a>
			</h4>
		</div>
		<div id="noficationCollapse" class="panel-collapse collapse">
			<div class="panel-body">
				<!-- { accordion -->
				<div class="panel-group" id="orderFormAccordion">
					<!-- { collapseOne
					<div class="panel panel-warning orderForm" id="9999">
						<div class="panel-heading">
							<div class="row">
								<div class="col-xs-2 col-sm-6 col-md-6 col-lg-6-12
									<strong class="panel-title">
										<a data-toggle="collapse" data-parent="#orderFormAccordion"
											href="#collapseOne"> 
											<span class='glyphicon glyphicon-exclamation-sign' aria-hidden='true'></span>
											<span class='sr-only'>Error:</span>#99999999999999
										</a>
									</strong>
									<p><small id="interval9999"> 5 mins ago </small><p>
								</div>
								<div class="col-xs-10 col-sm-6 col-md-6 col-lg-6" style="text-align:right">
									<button type='button' class='btn btn-primary' onclick='confirmOrderForm(this);'>
										<span class='glyphicon glyphicon-ok'></span>Confirm
									</button>
									<button type='button' class='btn btn-danger' onclick='cancelOrderForm(this);'>
										<span class='glyphicon glyphicon-remove'></span>Cancel
									</button>
								</div>
							</div>
						</div>
						<div id="collapseOne" class="panel-collapse collapse">
							<div class="panel-body">Order From Info</div>
						</div>
					</div>
				} collapseOne -->
				</div>
				<!-- } accordion -->
			</div>
		</div>
	</div>
</div>

<div class="centering text-center" border="1">

	<div style="padding-top:10px;padding-bottom:10px;">
		<input type='number' id='orderFormTotalFee' name="mTotalFee" style="width:200px;height:100px;font-size:50px"
			placeholder='0.00' min='0' onblur='formatPrice(this);'>
	</div>
	
	<div>
		<a href="javascript:void(0)"><button type="button"
				class="btn btn-danger btn-circle btn-xl" id="callBtn"
				onclick="showTimeChooser(false);">
				<span class="glyphicon glyphicon-phone-alt"></span> Call Delivery
			</button></a>
	</div>
</div>

<!-- Time Chooser Modal -->
<div class='modal fade' id='timeChooserModal' tabindex='-1'
	role='dialog' aria-labelledby='timeChooserModalLabel'
	aria-hidden='true'>
	<div class='modal-dialog modal-sm'>
		<div class='modal-content'>
			<div class='modal-header'>
				<button type='button' class='close' data-dismiss='modal'>
					<span aria-hidden='true'>&times;</span><span class='sr-only'>Close</span>
				</button>
				<h4 class='modal-title' id='timeChooserModalLabel'>Choose
					Delivery Time</h4>
			</div>

			<div class='modal-body'>
				<div class="centering text-center" style="width: 100%">

					<!-- { One Menu Chooser -->
					<div class="row" id="oneMenuCallBtnGroup" hidden>
						<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12" style="padding-top:10px;padding-bottom:10px;">
							<button type="button" class="btn btn-default btn-lg"
								id="5minsBtn" onclick="callDeliveryForOrderForm(5);">
								<span class="glyphicon glyphicon-time"></span> 5 Mins
							</button>
						</div>
						<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12" style="padding-top:10px;padding-bottom:10px;">
							<button type="button" class="btn btn-default btn-lg"
								id="10minsBtn" onclick="callDeliveryForOrderForm(10);">
								<span class="glyphicon glyphicon-time"></span> 10 Mins
							</button>
						</div>
						<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12" style="padding-top:10px;padding-bottom:10px;">
							<button type="button" class="btn btn-default btn-lg"
								id="15minsBtn" onclick="callDeliveryForOrderForm(15);">
								<span class="glyphicon glyphicon-time"></span> 15 Mins
							</button>
						</div>
						<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12" style="padding-top:10px;padding-bottom:10px;">
							<button type="button" class="btn btn-default btn-lg"
								id="30minsBtn" onclick="callDeliveryForOrderForm(30);">
								<span class="glyphicon glyphicon-time"></span> 30 Mins
							</button>
						</div>
						<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12" style="padding-top:10px;padding-bottom:10px;">
							<button type='button' class='btn btn-default btn-lg'
								onclick='callDeliveryForOrderForm(-1);'>
								<span class='glyphicon glyphicon-time'></span> No Need
							</button>
						</div>
					</div>
					<!-- } One Menu Chooser -->

					<!-- { Third Party Chooser -->
					<div class="row" id="thirdPartyCallBtnGroup" hidden>
						<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12" style="padding-top:10px;padding-bottom:10px;">
							<h1 id="orderFormTotalFeeStr"></h1>
						</div>
						<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12" style="padding-top:10px;padding-bottom:10px;">
							<button type="button" class="btn btn-default btn-lg"
								id="5minsBtn" onclick="callDelivery(5);">
								<span class="glyphicon glyphicon-time"></span> 5 Mins
							</button>
						</div>
						<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12" style="padding-top:10px;padding-bottom:10px;">
							<button type="button" class="btn btn-default btn-lg"
								id="10minsBtn" onclick="callDelivery(10);">
								<span class="glyphicon glyphicon-time"></span> 10 Mins
							</button>
						</div>
						<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12" style="padding-top:10px;padding-bottom:10px;">
							<button type="button" class="btn btn-default btn-lg"
								id="15minsBtn" onclick="callDelivery(15);">
								<span class="glyphicon glyphicon-time"></span> 15 Mins
							</button>
						</div>
						<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12" style="padding-top:10px;padding-bottom:10px;">
							<button type="button" class="btn btn-default btn-lg"
								id="30minsBtn" onclick="callDelivery(30);">
								<span class="glyphicon glyphicon-time"></span> 30 Mins
							</button>
						</div>
					</div>
				</div>
				<!-- } Third Party Chooser -->

			</div>

			<div class='modal-footer'>
				<button type='button' class='btn btn-default' data-dismiss='modal'>Close</button>
			</div>
		</div>
	</div>
</div>

<input type="text" id="orderFormIdInput" hidden>

<%@ include file="../include/bootstrap_foot.html"%>