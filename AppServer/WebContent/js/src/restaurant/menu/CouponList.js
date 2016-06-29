function delCoupon(id) {

	$.ajax({
		type : "POST",
		url : "../menuController/delCouponItem",
		data : {
			mId : id
		}
	}).done(function(msg) {

		$.messager.alert("Result", "Operation success.");
		location.href = "../menuController/showCouponListPage";
	});
}


var existingDishsList;

function loadExistingDishsList() {

	$.ajax({
		type : "POST",
		url : "../menuController/getDishBeanList",
		data : {
		}
	}).done(
			function(data) {
				
				existingDishsList = data;
				
			});
}

function refreshDishSelect(){
	
	$("#discountTargetLeftSelect").empty();
	$("#extraCriLeftSelect").empty();
	for ( var i in existingDishsList) {
		var optionValue = existingDishsList[i].id;
		var optionText = existingDishsList[i].name;
		$("<option></option>").val(optionValue).text(optionText)
				.appendTo("#discountTargetLeftSelect");
		$("<option></option>").val(optionValue).text(optionText)
				.appendTo("#extraCriLeftSelect");
	}
}

function clearCouponDetail() {
	
	$("#couponId").val("");
	$("#couponName").val("");
	$("#discountTargetSelect").val(1);
	$("#couponTypeSelect").val(1);
	$("#couponTypeAmount").val("");
	$("#extraCriSelect").val(0);
	$("#extraCriAmount").val("");
	$("#couponQuantity").val("");
	$("#availableStartTime").val("");
	$("#availableEndTime").val("");
	$("#effectiveDate").val("");
	$("#maturityDate").val("");
	$("#couponDesc").val("");
	$(".weekday").prop("checked", false);
	$("#discountTargetRightSelect").empty();
	$("#extraCriRightSelect").empty();
	refreshDishSelect();
}


function refreshCouponDetail(id) {
	
	if (id == "") {
		
		clearCouponDetail();
		
	} else {
		
		refreshDishSelect();
		$
				.ajax({
					type : "POST",
					url : "../menuController/getCouponById",
					data : {
						mId : id
					}
				})
				.done(
						function(data) {

							$("#couponId").val(data.id);
							$("#couponName").val(data.name);
							$("#discountTargetSelect").val(data.targetType);
							$("#couponTypeSelect").val(data.type);
							$("#couponTypeAmount").val(data.typeAmount);
							$("#extraCriSelect").val(data.extraCriType);
							$("#extraCriAmount").val(data.extraCriAmount);
							$("#couponQuantity").val(data.quantity);
							$("#availableStartTime").val(
									data.availableStartTime);
							$("#availableEndTime").val(data.availableEndTime);
							$("#effectiveDate").val(data.effectiveDate);
							$("#maturityDate").val(data.maturityDate);
							$("#couponDesc").val(data.description);

							$("#discountTargetRightSelect").empty();
							var couponTargetDishsList = data.couponTargetDishsList;
							for ( var i in couponTargetDishsList) {
								var optionValue = couponTargetDishsList[i].id;
								console.log("optionValue : " + optionValue);
								$(
										"#discountTargetLeftSelect option[value='"
												+ optionValue + "']").appendTo(
										'#discountTargetRightSelect');
							}

							$("#extraCriRightSelect").empty();
							var couponExtraCriDishsList = data.couponExtraCriDishsList;
							for ( var i in couponExtraCriDishsList) {
								var optionValue = couponExtraCriDishsList[i].id;
								$(
										"#extraCriLeftSelect option[value='"
												+ optionValue + "']").appendTo(
										'#extraCriRightSelect');
							}

							$('#' + 'discountTargetLeftSelect option')
									.dblclick(
											function() {
												$(this)
														.appendTo(
																'#discountTargetRightSelect');
												$(this)
														.dblclick(
																function() {
																	$(this)
																			.appendTo(
																					'#discountTargetLeftSelect');
																});
											});

							$('#' + 'discountTargetRightSelect option')
									.dblclick(
											function() {
												$(this)
														.appendTo(
																'#discountTargetLeftSelect');
												$(this)
														.dblclick(
																function() {
																	$(this)
																			.appendTo(
																					'#discountTargetRightSelect');
																});
											});

							$('#' + 'extraCriLeftSelect option')
									.dblclick(
											function() {
												$(this).appendTo(
														'#extraCriRightSelect');
												$(this)
														.dblclick(
																function() {
																	$(this)
																			.appendTo(
																					'#extraCriLeftSelect');
																});
											});

							$('#' + 'extraCriRightSelect option')
									.dblclick(
											function() {
												$(this).appendTo(
														'#extraCriLeftSelect');
												$(this)
														.dblclick(
																function() {
																	$(this)
																			.appendTo(
																					'#extraCriRightSelect');
																});
											});

							if (data.weeks != null) {
								var weeks = data.weeks.split(",");
								for ( var i in weeks) {
									var val = weeks[i];
									$("#couponWeek" + val)
											.prop("checked", true);
								}
							}

						});
	}

	showDiscountTarget();
	showExtraCri();
}


function showCouponDetailModel(id) {

	refreshCouponDetail(id);
	$('#couponDetailModal').modal('show');

}

$(function() {
	$('#couponListTable').on('all.bs.table', function(e, name, args) {
		console.log('Event:', name, ', data:', args);
	}).on('click-row.bs.table', function(e, row, $element) {

	})
});

function actionFormatter(value, row, index) {
	return [ '<a class="edit ml10" href="javascript:void(0)" title="Edit">',
			'<i class="glyphicon glyphicon-edit"></i>', '</a>',
			'&nbsp;&nbsp;&nbsp;',
			'<a class="delete ml10" href="javascript:void(0)" title="Delete">',
			'<i class="glyphicon glyphicon-remove"></i>', '</a>' ].join('');
}

window.actionEvents = {
	'click .edit' : function(e, value, row, index) {
		showCouponDetailModel(row._id);
	},
	'click .delete' : function(e, value, row, index) {
		delCoupon(row._id);
	}
};

function showDiscountTarget() {

	selectVal = $('#discountTargetSelect').children('option:selected').val();

	if (1 == selectVal) {
		$("#discountTargetTableDiv").show();
	} else {
		$("#discountTargetTableDiv").hide();
	}
}

function showExtraCri() {

	selectVal = $('#extraCriSelect').children('option:selected').val();

	if (1 == selectVal) {
		$("#extraCriAmountDiv").hide();
		$("#extraCriTableDiv").show();
	} else if (2 == selectVal) {
		$("#extraCriTableDiv").hide();
		$("#extraCriAmountDiv").show();
	} else {
		$("#extraCriTableDiv").hide();
		$("#extraCriAmountDiv").hide();
	}
}

function exchange(fromSelect, toSelect, action) {

	if (action == 'ALL') {
		$('#' + fromSelect + ' option').appendTo('#' + toSelect);
	} else if (action == 'SELECTED') {
		$('#' + fromSelect + ' option:selected').appendTo('#' + toSelect);
	}

	$('#' + fromSelect + ' option').dblclick(function() {
		$(this).appendTo('#' + toSelect);
	});

	$('#' + toSelect + ' option').dblclick(function() {
		$(this).appendTo('#' + fromSelect);
	});
}

function weekCheckBox() {

	$(".weekday").prop("checked", true);
}

function saveCoupon() {

	$('#discountTargetRightSelect option').each(function() {

		$(this).attr("selected", true);
	});

	$('#extraCriRightSelect option').each(function() {

		$(this).attr("selected", true);
	});

	var couponId = $('#couponId').val();

	var form = $("#couponDetailForm");

	if (couponId == "") {

		form.attr("action", "../menuController/addCouponItem");
	} else {

		form.attr("action", "../menuController/editCouponItem?mId=" + couponId);
	}

	form.submit();

}

$(document).ready(function() {

	$("#couponEditForm").validationEngine();

	$('.effective_date').datetimepicker({
		weekStart : 1,
		todayBtn : 1,
		autoclose : 1,
		minView : 2,
		todayHighlight : 1,
		startView : 2,
		forceParse : 0,
		showMeridian : 1
	});

	$('.available_time').datetimepicker({
		weekStart : 1,
		todayBtn : 1,
		autoclose : 1,
		maxView : 1,
		todayHighlight : 1,
		startView : 1,
		forceParse : 0,
		showMeridian : 1,
		pickerPosition : "bottom-left"
	});

	$("#discountTargetSelect").change(showDiscountTarget);
	$("#extraCriSelect").change(showExtraCri);

	$("#couponDetialSubmitBtn").click(saveCoupon);
	$("#couponWholeWeekBtn").click(weekCheckBox);

	loadExistingDishsList();
});
