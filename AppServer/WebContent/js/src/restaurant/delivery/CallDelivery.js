function validatePriceInput() {

	var orderFormTotalFee = $("#orderFormTotalFee").val();
	if (orderFormTotalFee == "") {

		$("#orderFormTotalFee").css("border", "3px solid red");
		$("#orderFormTotalFee").focus();
		return false;
	} else {
		console.log("return");
		$("#orderFormTotalFee").css("border", "1px solid gray");
		$("#orderFormTotalFeeStr").text("$" + orderFormTotalFee);
		return true;
	}
}

function showTimeChooser(isOneMenuOrdreForm) {

	if (isOneMenuOrdreForm == true) {
		$("#oneMenuCallBtnGroup").show();
		$("#thirdPartyCallBtnGroup").hide();
	} else {

		if (!validatePriceInput())
			return;

		$("#oneMenuCallBtnGroup").hide();
		$("#thirdPartyCallBtnGroup").show();
	}

	$('#timeChooserModal').modal('show');
}

function hideTimeChooser() {

	$("#oneMenuCallBtnGroup").hide();
	$("#thirdPartyCallBtnGroup").hide();
	$('#timeChooserModal').modal('hide');
}

function callDelivery(minInterval) {

	var orderFormTotalFee = $("#orderFormTotalFee").val();
	
	$.ajax({

		type : "POST",
		dataType : "json",
		url : "../deliveryController/addThirdPartyOrderForm",
		data : {
			minInterval : minInterval,
			mTotalFee : orderFormTotalFee
		}
	}).done(function(result) {

		if (result) {
			hideTimeChooser();
		} else {
			$.messager.alert("Server Error.");
		}
		;
	});

}

function alertOrderForm() {

	$.ajax({

		type : "POST",
		dataType : "json",
		url : "../deliveryController/getPendingOrderFormList",
		data : {

		}
	}).done(function(orderFormList) {

		if (orderFormList.length > 0) {
			addOrderFormNofication(orderFormList);
		}
	}).fail(function(result){
		
		console.log("alertOrderForm Fail --> " + result);
		window.location.href="../loginController/showLoginPage";
	});
}

function addOrderFormNofication(orderFormList) {

	var length = orderFormList.length;
	$("#noficationBadge").text(length);
	
	if (length>0) {
		$.flashSound.play('alert', true);
	}
	
	for ( var i in orderFormList) {

		var orderFormId = orderFormList[i].orderFormId;
		var orderFormCode = orderFormList[i].orderFormCode;

		var orderFormCreateTimestamp = orderFormList[i].orderFormCreateTimestamp;
		var curTimestamp = orderFormList[i].curTimestamp;

		var intervalStr = dateFormat.showInterval(orderFormCreateTimestamp,
				curTimestamp);

		if (!$("#" + orderFormId).length) {

			$str = '';

			$str += "<div class='panel panel-warning' id='" + orderFormId
					+ "'>";
			$str += "	<div class='panel-heading'>";
			$str += "		<div class='row'>";
			$str += "			<div class='col-xs-5 col-sm-6 col-md-6 col-lg-6'>";
			$str += "				<p><strong class='panel-title'>";
			$str += "					<a data-toggle='collapse' data-parent='#collapse"
					+ orderFormId + "'";
			$str += "						href='#collapse" + orderFormId + "'> ";
			$str += "						<span class='glyphicon glyphicon-exclamation-sign' aria-hidden='true'></span>";
			$str += "						<span class='sr-only'>Error:</span>#"
					+ orderFormCode + "";
			$str += "					</a>";
			$str += "				</strong></p>";
			$str += "				<p><small id='interval" + orderFormId + "'>"
					+ intervalStr + "</small></p>";
			$str += "			</div>";
			$str += "			<div class='col-xs-7 col-sm-6 col-md-6 col-lg-6' style='text-align:right'>";
			$str += "				<button type='button' class='btn btn-primary' onclick='confirmOrderForm(this);'>";
			$str += "					<span class='glyphicon glyphicon-ok'></span>Confirm";
			$str += "				</button>";
			$str += "				<button type='button' class='btn btn-danger' onclick='cancelOrderForm(this);'>";
			$str += "					<span class='glyphicon glyphicon-remove'></span>Cancel";
			$str += "				</button>";
			$str += "			</div>";
			$str += "		</div>";
			$str += "	</div>";
			$str += "	<div id='collapse" + orderFormId
					+ "' class='panel-collapse collapse'>";
			$str += "		<div class='panel-body'>Order From Info</div>";
			$str += "	</div>";
			$str += "</div>";

			$("#orderFormAccordion").append($str);
			
		} else {
			$('#interval' + orderFormId).text(intervalStr);
		}
	}
}

function confirmOrderForm(element) {

	var id = $(element).parents("div.panel").attr("id");
	$("#orderFormIdInput").val(id);
	showTimeChooser(true);

}

function cancelOrderForm(element) {

	var id = $(element).parents("div.panel").attr("id");

	$.messager.confirm("Warning", "Do you really want to cancel this order?",

	function() {

		$.ajax({

			type : "POST",
			dataType : "json",
			url : "../deliveryController/cancelOrderFormByRestaurant",
			data : {
				mId : id
			}
		}).done(function(result) {

			if (result) {
				var orderFormDiv = $("#" + id);
				orderFormDiv.attr("class", "panel panel-danger");
				orderFormDiv.fadeOut(1000, function() {
					var nextId = orderFormDiv.next().attr("id");
					$("#collapse" + nextId).collapse('show');
					orderFormDiv.remove();
				});
				var length = $("#noficationBadge").text() - 1;
				$("#noficationBadge").text(length);

			} else {

				$.messager.alert("Server Error.");
			}
			;

		});

	});
}

function callDeliveryForOrderForm(minInterval) {

	var id = $("#orderFormIdInput").val();

	var url = "";

	if (minInterval != -1) {

		url = "../deliveryController/callDeliveryForOrderForm";
	} else {

		url = "../deliveryController/confirmOrderFormByRestaurant";
	}

	$.ajax({

		type : "POST",
		dataType : "json",
		url : url,
		data : {
			mId : id,
			minInterval : minInterval
		}
	}).done(function(result) {

		if (result) {
			hideTimeChooser();
			var orderFormDiv = $("#" + id);
			orderFormDiv.attr("class", "panel panel-success");
			orderFormDiv.fadeOut(1000, function() {
				var nextId = orderFormDiv.next().attr("id");
				$("#collapse" + nextId).collapse('show');
				orderFormDiv.remove();
			});
			var length = $("#noficationBadge").text() - 1;
			$("#noficationBadge").text(length);

		} else {

			$.messager.alert("Server Error.");
		}
		;

	});

}

function formatPrice(element) {
	var value = $(element).val();
	if (value != "") {
		var price = new format_helper().formatPrice(value);
		$(element).val(price);
	}
}

var dateFormat;

$(document).ready(
		function() {

			$.flashSound('../../lib/flashSound/iphone.mp3', {
				id : 'alert'
			});

			dateFormat = new DateFormat();

			$('#noficationCollapse').on(
					'shown.bs.collapse',
					function() {
						var firstCollapseId = $('#noficationCollapse').find(
								"div.collapse:first").attr("id");
						// $("#" + firstCollapseId).collapse('show');
					})

			alertOrderForm();

			setInterval("alertOrderForm()", 5000);

		});
