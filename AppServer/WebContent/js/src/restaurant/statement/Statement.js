function query() {

	var fromDateTime = $('#fromDateTime').val();
	var toDateTime = $('#toDateTime').val();

	var newUrl = '../menuController/queryStatement?fromDateTime=?1&toDateTime=?2';

	newUrl = newUrl.replace('?1', fromDateTime);
	newUrl = newUrl.replace('?2', toDateTime);

	$('#statementTable').bootstrapTable('refresh', {
		silent : true,
		url : newUrl
	});
	
	queryPie(fromDateTime, toDateTime);
	queryLine(fromDateTime, toDateTime);
}

function queryRecent(interval, amount) {

	var fromDateTime = dateFormat.changeDate(new Date(), interval, amount, 'yyyy-MM-dd HH:mm:ss'); 
	var toDateTime = dateFormat.format(new Date(), 'yyyy-MM-dd HH:mm:ss');
	
	var newUrl = '../menuController/queryStatement?fromDateTime=?1&toDateTime=?2';
	newUrl = newUrl.replace('?1', fromDateTime);
	newUrl = newUrl.replace('?2', toDateTime);

	$('#statementTable').bootstrapTable('refresh', {
		silent : true,
		url : newUrl
	});
	
	queryPie(fromDateTime, toDateTime);
	queryLine(fromDateTime, toDateTime);
}

function labelFormatter(label, series) {
	return "<div style='font-size:8pt; text-align:center; padding:2px; color:white;'>"
			+ label
			+ "<br/>"
			+ Math.round(series.percent * 100)
			/ 100
			+ "%</div>";
}

function queryPie(fromDateTime, toDateTime) {

	var newUrl = "../menuController/queryPaymentPie?fromDateTime=?1&toDateTime=?2";
	
	newUrl = newUrl.replace('?1', fromDateTime);
	newUrl = newUrl.replace('?2', toDateTime);
	
	var placeholder = $("#placeholder3");

	$.ajax({
		type : "POST",
		dataType : "json",
		url : newUrl,
		data : {}
	}).done(function(data) {

		placeholder.unbind();

		$.plot(placeholder, data, {
			series : {
				pie : {
					innerRadius : 0.5,
					show : true,
					label : {
						show : true,
						radius : 1,
						formatter : labelFormatter,
						background : {
							opacity : 0.8
						}
					}
				}
			}
		});

	});
}

function queryLine(fromDateTime, toDateTime) {

	var newUrl = "../menuController/queryPaymentAxe?fromDateTime=?1&toDateTime=?2";
	
	newUrl = newUrl.replace('?1', fromDateTime);
	newUrl = newUrl.replace('?2', toDateTime);
	
	$.ajax({
		type : "POST",
		dataType : "json",
		url : newUrl,
		data : {}
	}).done(function(data) {
		
		$.plot("#placeholder", [ {
			data : data[0],
			label : "Sale",
		}
		/*
		 * , { data : data[1], label : "USD/EUR exchange rate", yaxis : 2, }
		 */ 
		], {
			xaxes : [ {
				mode : "time"
			} ],
			yaxes : [ {
				min : 0
			}, {
				alignTicksWithAxis : 1,
				position : "right",
			} ],
			legend : {
				position : "nw"
			}
		});
		
		$.plot("#placeholder2", [ {
			data : data[1],
			label : "Order Amount",
		}], {
			xaxes : [ {
				mode : "time"
			} ],
			yaxes : [ {
				min : 0
			}, {
				alignTicksWithAxis : 1,
				position : "right",
			} ],
			legend : {
				position : "nw"
			}
		});

	})

}

function euroFormatter(v, axis) {
	return v.toFixed(axis.tickDecimals) + "€";
}

var dateFormat;

$(document).ready(function() {

	dateFormat = new DateFormat();

	$('.query_date').datetimepicker({
		weekStart : 1,
		todayBtn : 1,
		autoclose : 1,
		minView : 0,
		todayHighlight : 1,
		startView : 2,
		forceParse : 0,
		showMeridian : 1
	});

	queryPie();

	queryLine();

});
