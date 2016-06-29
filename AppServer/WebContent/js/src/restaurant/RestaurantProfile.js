function formatPrice(element) {

	var price = new format_helper().formatPrice($(element).val());
	$(element).val(price);
}

function formatAddress() {

	var street = $('#street').val();
	var city = $('#city').val();
	var state = $('#state').val();
	var zipCode = $('#zipCode').val();

	var address = '';
	if (street != '')
		address += street + ', ';
	if (city != '')
		address += city + ', ';
	if (state != '')
		address += state + ', ';
	if (zipCode != '')
		address += zipCode;

	$('#address').val(address);
	
	var geo = new geoEach("map_canvas");
	geo.toLatLng(address,function(){
		$("#latitude").val(geo.getLatitude()); 
		$("#longitude").val(geo.getLongitude());
		console.log($("#latitude").val()+","+$("#longitude").val());
	});
}

function uploadImg() {

	var file_data = $('#filePath').prop('files')[0];
	var form_data = new FormData();
	form_data.append('file', file_data);

	$.ajax({
		url : '../commonController/generateBase64String',// server-side
		dataType : 'text', // what to return back
		cache : false,
		contentType : false,
		processData : false,
		data : form_data,
		type : 'post',
		success : function(response) {
			$(".cropper").cropper("replace", response);
			$("#cropperDiv").show();
			$('.form_datetime').datetimepicker({
				weekStart : 1,
				todayBtn : 1,
				autoclose : 1,
				todayHighlight : 1,
				startView : 1,
				minView : 0,
				maxView : 1,
				forceParse : 0,
				pickerPosition : "bottom-left"
			});
		}
	});
}

$(document).ready(function() {

	$("#restaurantProfileForm").validationEngine();

	$('.form_datetime').datetimepicker({
		weekStart : 1,
		todayBtn : 1,
		autoclose : 1,
		maxView : 1,
		todayHighlight : 1,
		startView : 1,
		forceParse : 0,
		pickerPosition : "top-left"
	});

	initDataCropper();

	$('#filePath').change(uploadImg);

	var geo = new geoEach("map_canvas");
	// 批量解析地名到经纬度
	$('#address').change(function() {
		console.log(this.val());
		geo.toLatLng(this.val(),function(){
			$("#latitude").val(geo.getLatitude()); 
			$("#longitude").val(geo.getLongitude());
			console.log($("#latitude").val()+","+$("#longitude").val());
		});
	})
	
	$("#submitBtn").click(function() {

		var isDelivery;
		var isPickOut;
		var isPreOrder;

		if ($("deliveryCheckBox").prop("checked")) {
			$("#isDelivery").val(true);
		} else {
			$("#isDelivery").val(false);
		}

		if ($("pickOutCheckBox").prop("checked")) {
			$("#isPickOut").val(true);
		} else {
			$("#isPickOut").val(false);
		}

		if ($("preOrderCheckBox").prop("checked")) {
			$("#isPreOrder").val(true);
		} else {
			$("#isPreOrder").val(false);
		}
		
		var form = $("#restaurantProfileForm");
		var restaurantId = $('#restaurantId').val();
		if (restaurantId != "") {
			form.attr("action", "../menuController/editRestaurantProfile");
		} else {
			form.attr("action", "../loginController/registerRestaurant");
		}
		form.submit();

	});

});
