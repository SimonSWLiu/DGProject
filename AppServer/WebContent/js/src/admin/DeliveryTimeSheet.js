function clearModalDetail() {

	$("#availableDate").val("");
	$("#availableStartTime").val("");
	$("#availableEndTime").val("");
	$("#quantity").val("");
}

function refreshModalDetail(data) {

	$("#availableDate").val(data.availableDate);
	$("#availableStartTime").val(data.availableStartTime);
	$("#availableEndTime").val(data.availableEndTime);
	$("#quantity").val(data.quantity);
}

function showDeliveryTimeSheetDetailModal(id) {
	console.log(id);
	if (id == undefined) {

		clearModalDetail();
	} else {
		$
				.ajax(
						{
							type : "POST",
							dataType : "json",
							url : "../deliveryTimeSheetAdminController/getDeliveryTimeSheetById",
							data : {
								orderFormId : id
							}
						}).done(function(data) {
					refreshModalDetail(data);
				});
	}

	$("#deliveryTimeSheetDetailModal").modal("show");

}

function queryParams() {
	return {
		type : 'owner',
		sort : 'updated',
		direction : 'desc',
		per_page : 10,
		page : 1
	};
}

function actionFormatter(value, row, index) {

	var formatter;
	formatter = [
			'<a class="query ml10" href="javascript:void(0)" title="Query" id="query'
					+ row._id + '">',
			'<i class="glyphicon glyphicon-eye-open"></i>', '</a>' ].join('');

	return formatter;
}

window.actionEvents = {
	'click .query' : function(e, value, row, index) {
		showDriverDetailModal(row._id);
	}
};

function saveDeliveryTimeSheet() {

	var couponId = $('#deliveryTimeSheetId').val();

	var form = $("#detailForm");

	if (couponId == "") {

		form.attr("action",
				"../deliveryTimeSheetAdminController/addDeliveryTimeSheet");
	} else {

		form.attr("action",
				"../deliveryTimeSheetAdminController/editDeliveryTimeSheet");
	}

	form.submit();

}

$(document).ready(function() {

	$("#saveBtn").click(saveDeliveryTimeSheet);
});
