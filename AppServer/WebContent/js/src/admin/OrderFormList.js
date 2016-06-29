function genBillTable(orderForm) {

	if (orderForm.getType == "1") {
		$("#getType").text("Take out");
	} else if ((orderForm.getType == "2")) {
		$("#getType").text("Delivery");
	}

	$("#orderTime").text(orderForm.orderFormCreateDateTime);
	$("#custName").text(orderForm.customerName);
	$("#custAddress").text(orderForm.customerAddress);
	$("#custPhone").text(orderForm.customerPhone);

	$("#billTbody").empty();

	$str = '';
	var subtotal = 0.00;
	var orderItemArray = orderForm.orderItemArray;
	for ( var i in orderItemArray) {

		subtotal += Number(orderItemArray[i].orderItemPrice);
		var amount = orderItemArray[i].dishAmount;
		if (amount > 1) {
			$str += "<tr><td style='text-align: left'><font color='blue' size='4'>"
					+ orderItemArray[i].dishAmount
					+ "</font></td> <td>"
					+ orderItemArray[i].dishName
					+ "</td><td></td><td style='text-align: right'>"
					+ orderItemArray[i].orderItemPrice + "</td></tr>";
		} else {
			$str += "<tr><td style='text-align: left'><font size='4'>"
					+ orderItemArray[i].dishAmount + "</font></td> <td>"
					+ orderItemArray[i].dishName
					+ "</td><td></td><td style='text-align: right'>"
					+ orderItemArray[i].orderItemPrice + "</td></tr>";
		}

		var orderItemOptionItemArray = orderItemArray[i].orderItemOptionItemArray;
		for ( var j in orderItemOptionItemArray) {

			$str += "<tr><td></td><td></td><td>"
					+ orderItemOptionItemArray[j].name + "</td><td></td></tr>";
		}

	}

	$str += "<tr><td></td><td></td><td style='text-align: right'>Sub-total</td><td style='text-align: right'>"
			+ subtotal + "</td></tr>";
	$str += "<tr><td></td><td></td><td style='text-align: right'>Discount</td><td style='text-align: right'>"
			+ orderForm.discountFee + "</td></tr>";
	$str += "<tr><td></td><td></td><td style='text-align: right'>Delivery</td><td style='text-align: right'>"
			+ orderForm.deliveryFee + "</td></tr>";
	$str += "<tr><td></td><td></td><td style='text-align: right'>Tax</td><td style='text-align: right'>"
			+ orderForm.taxFee + "</td></tr>";
	$str += "<tr><td></td><td></td><td style='text-align: right'>Total</td><td style='text-align: right'>"
			+ orderForm.orderFormPrice + "</td></tr>";
	if (orderForm.tipsFee >= 0) {
		$str += "<tr><td></td><td></td><td style='text-align: right'>Tips</td><td style='text-align: right'>"
				+ orderForm.tipsFee + "</td></tr>";
	} else if (orderForm.tipsFee == -1) {
		$str += "<tr><td></td><td></td><td style='text-align: right'>Tips</td><td style='text-align: right'>Cash</td></tr>";
	}

	$("#billTbody").append($str);
}

function showOrderFormDetailModal(id) {
	console.log("showOrderFormDetailModal --> id : " + id);
	$.ajax({
		type : "POST",
		dataType : "json",
		url : "../orderController/getOrderForm",
		data : {
			orderFormId : id
		}
	}).done(function(data) {
		genBillTable(data);
	});

	console.log("showOrderFormDetailModal --> showModal");
	$("#orderFormDetailModal").modal("show");

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

$(function() {
	$('#orderFormTable').on('all.bs.table', function(e, name, args) {
	}).on('click-row.bs.table', function(e, row, $element) {
	})
});

function actionFormatter(value, row, index) {

	var formatter;
	var status = $('#status' + row._id).val();
	console.log(status);
	if (status == 2) {
		formatter = [
				'<a class="query ml10" href="javascript:void(0)" title="Query" id="query' + row._id + '">',
				'<i class="glyphicon glyphicon-eye-open"></i>',
				'</a>',
				'&nbsp;&nbsp;&nbsp;',
				'<a class="confirm ml10" href="javascript:void(0)" title="Confirm" id="confirm' + row._id + '">',
				'<i class="glyphicon glyphicon-ok"></i>',
				'</a>',
				'&nbsp;&nbsp;&nbsp;',
				'<a class="cancel ml10" href="javascript:void(0)" title="Cancel" id="cancel' + row._id + '">',
				'<i class="glyphicon glyphicon-remove"></i>', '</a>' ].join('');
	} else {
		formatter = [
				'<a class="query ml10" href="javascript:void(0)" title="Query" id="query' + row._id + '">',
				'<i class="glyphicon glyphicon-eye-open"></i>', '</a>' ]
				.join('');
	}

	return formatter;
}

window.actionEvents = {
	'click .query' : function(e, value, row, index) {
		showOrderFormDetailModal(row._id);
	},
	'click .confirm' : function(e, value, row, index) {
		$.ajax({
			type : "POST",
			dataType : "json",
			url : "../orderController/confirmOrderFormByRestaurant",
			data : {
				mId : row._id
			}
		}).done(function(data) {
			if (data) {
				$('#status' + row._id).val(3);
				$('#statusStr' + row._id).text("Restaurant Confirmed");
				$('#' + row._id).attr("class", "alert alert-success");
				$('#confirm' + row._id).remove();
				$('#cancel' + row._id).remove();
				$('#orderFormTable').bootstrapTable('resetView');
			}
		});
	},
	'click .cancel' : function(e, value, row, index) {
		$.ajax({
			type : "POST",
			dataType : "json",
			url : "../orderController/cancelOrderForm",
			data : {
				mId : row._id
			}
		}).done(function(data) {
			if (data) {
				$('#status' + row._id).val(0);
				$('#statusStr' + row._id).text("Canceled");
				$('#' + row._id).attr("class", "alert alert-danger");
				$('#confirm' + row._id).remove();
				$('#cancel' + row._id).remove();
				$('#orderFormTable').bootstrapTable('resetView');
			}
		});

	}
};

$(document).ready(function() {

});