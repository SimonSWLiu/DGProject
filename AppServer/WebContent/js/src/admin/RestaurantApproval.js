function refreshModalDetail(data) {

}

function showRestDetailModal(id) {
	$.ajax({
		type : "POST",
		dataType : "json",
		url : "../restaurantAdminController/getRestaurantById",
		data : {
			orderFormId : id
		}
	}).done(function(data) {
		refreshModalDetail(data);
	});

	$("#restDetailModal").modal("show");

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
			'<i class="glyphicon glyphicon-eye-open"></i>',
			'</a>',
			'&nbsp;&nbsp;&nbsp;',
			'<a class="approve ml10" href="javascript:void(0)" title="approve" id="approve'
					+ row._id + '">',
			'<i class="glyphicon glyphicon-ok"></i>',
			'</a>',
			'&nbsp;&nbsp;&nbsp;',
			'<a class="reject ml10" href="javascript:void(0)" title="reject" id="reject'
					+ row._id + '">',
			'<i class="glyphicon glyphicon-remove"></i>', '</a>' ].join('');

	return formatter;
}

window.actionEvents = {
	'click .query' : function(e, value, row, index) {
		showDriverDetailModal(row._id);
	},
	'click .approve' : function(e, value, row, index) {
		$.ajax({
			type : "POST",
			dataType : "json",
			url : "../restaurantAdminController/approveRestaurantById",
			data : {
				mId : row._id
			}
		}).done(function(data) {
			if (data) {
				$('#' + row._id).remove();
				$('#restTable').bootstrapTable('resetView');
			}
		});
	},
	'click .reject' : function(e, value, row, index) {
		$.ajax({
			type : "POST",
			dataType : "json",
			url : "../restaurantAdminController/rejectRestaurantById",
			data : {
				mId : row._id
			}
		}).done(function(data) {
			if (data) {
				$('#' + row._id).remove();
				$('#restTable').bootstrapTable('resetView');
			}
		});

	}
};

$(document).ready(function() {

});
