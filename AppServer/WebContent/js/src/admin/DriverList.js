function refreshModalDetail(data) {

}

function showDriverDetailModal(id) {
	$.ajax({
		type : "POST",
		dataType : "json",
		url : "../driverAdminController/getDriverById",
		data : {
			orderFormId : id
		}
	}).done(function(data) {
		refreshModalDetail(data);
	});

	$("#driverDetailModal").modal("show");

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

$(document).ready(function() {

});
