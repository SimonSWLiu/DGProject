//window.onload = function() {
//	
//	var geo = new geoEach("map_canvas");
//	
//	var toLatLngBtn = document.getElementById('toLatLngBtn');
//	// 批量解析地名到经纬度
//	toLatLngBtn.onclick = function() {
//		var area = document.getElementById('addrs').value;
//		area = area.split('\n');
//		var sh;
//		var i = 0;
//		sh = window.setInterval(function() {
//			if (i < area.length) {
//				geo.toLatLng(area[i]);
//			} else {
//				window.clearInterval(sh);
//			}
//			i++;
//		}, 500);
//	}
//	
//	// 批量解析经纬度到地名
//	var toAddressBtn = document.getElementById('toAddressBtn');
//	toAddressBtn.onclick = function() {
//		var latLngs = document.getElementById('latLngs').value;
//		latLngs = latLngs.split('\n');
//		var sh;
//		var i = 0;
//		var sh = window.setInterval(function() {
//			if (i < latLngs.length) {
//				geo.toAddress(latLngs[i]);
//			} else {
//				window.clearInterval(sh);
//			}
//			i++;
//		}, 500);
//	}
//	
//}

/*
 * 函数名 geoEach() 功能：地名解析成经纬度,经纬度解析成地名 
 * 作者 yanue 
 * 初始化 var geo = new geoEach(); 
 */
function geoEach(element) {
	// 初始化地图
	geoEach.map = new google.maps.Map(document.getElementById(element), {
		center : new google.maps.LatLng(40.8025545, -77.88947089999999),
		zoom : 12,
		mapTypeId : google.maps.MapTypeId.ROADMAP
	});
	// 添加一个marker
	geoEach.marker = new google.maps.Marker({
		map : geoEach.map,
		position : new google.maps.LatLng(40.8025545, -77.88947089999999)
	});
	// 实例化Geocoder服务
	geoEach.geocoder = new google.maps.Geocoder();
}

geoEach.address = null;// 需要解析的地名
geoEach.latLng = null;// 需要解析的经纬度字符串
geoEach.latitude = null;
geoEach.longitude = null;

geoEach.prototype = {
		
	getAddress : function(){
		return geoEach.address;
	},
	getLatLng : function(){
		return geoEach.latLng;
	},
	getLatitude : function(){
		return geoEach.latitude;
	},
	getLongitude : function(){
		return geoEach.longitude;
	},
	// 解析地名
	toLatLng : function(address, callback) {
		// 接收
		geoEach.address = address;
		// 执行geocode解析地名
		geoEach.geocoder.geocode({
			// 传入地名
			'address' : geoEach.address
		}, function(results, status) {
			// create div
			if (status == google.maps.GeocoderStatus.OK) {
				// 获取解析后的经纬度
				var location = results[0].geometry.location;
				// 切换地图位置
				geoEach.map.setCenter(location);
				geoEach.marker.setPosition(location);
				
				// location's format like (lat,lng), so remove '(' and ')'
				geoEach.latLng = location.toString().substr(1,
						location.toString().indexOf(')') - 1);
				
				var latLngStrArr = geoEach.latLng.replace(/[(^\s+)(\s+$)]/g, "").replace(
						'，', ',').split(',');
				// 第一个值为纬度并转化为float类型
				geoEach.latitude = parseFloat(latLngStrArr[0]);
				// 第二个值为经度并转化为float类型
				geoEach.longitude = parseFloat(latLngStrArr[1]);
				
				callback();
				
			} else {
				console.log(geoEach.address + " --> Error status " + status);
			}
		});
	},
	// 解析经纬度
	toAddress : function(latLng, callback) {
		geoEach.latLng = latLng.toString();
		// 去除中间所有空格，将中文'，'号替换成英文','并按','分割
		geoEach.latLng = geoEach.latLng.replace(/[(^\s+)(\s+$)]/g, "").replace(
				'，', ',').split(',');
		// 第一个值为纬度并转化为float类型
		geoEach.latitude = parseFloat(geoEach.latLng[0]);
		// 第二个值为经度并转化为float类型
		geoEach.longitude = parseFloat(geoEach.latLng[1]);
		// 执行geocode解析经纬度
		geoEach.geocoder.geocode({
			// 传入经纬度
			'location' : new google.maps.LatLng(geoEach.latitude, geoEach.longitude)
		}, function(results, status) {
			if (status == google.maps.GeocoderStatus.OK) {
				// 获取解析后的经纬度
				var location = results[0].geometry.location;
				// 切换地图位置
				geoEach.map.setCenter(location);
				geoEach.marker.setPosition(location);
				// 获取解析后的地址
				geoEach.address = results[0].formatted_address;

				callback();
				
			} else {
				console.log(geoEach.latLng + " --> error " + status);
			}
		});
	}
}

//$(document).ready(function() {
//
//	var geo = new geoEach("map_canvas");
//	
//	// 批量解析地名到经纬度
//	$('#toLatLngBtn').click(function() {
//		var addr = $('#addr').val();
//		geo.toLatLng(addr,function(){
//			console.log("TestCallback --> geo.latitude --> " + geo.getLatitude() + ", geo.longitude --> " + geo.getLongitude());
//		});
//	})
//	
//	// 批量解析经纬度到地名
//	$('#toAddressBtn').click(function() {
//		var latLng = $('#latLng').val();
//		geo.toAddress(latLng);
//	})
//})




