/**
 * @param str_url
 *            目标url
 * @param getOrPost
 *            "get" or "post"
 * @param map_data
 *            发送的数据, 直接 JSON.stringigy(对象) 即可
 * @param beforeSendFunction(jqXHR,
 *            settings) 发送请求之前调用的方法
 * @param successFunction(data,
 *            textStatus, jqXHR) 成功后调用的方法
 * @param errorFunction(jqXHR,
 *            textStatus, errorThrown) 失败调用的方法
 * @param completeFunction(jqXHR,
 *            textStatus) 完成发送后调用的方法
 *            <p>
 *            这个是用来向服务器发送的请求的工具方法
 *            </p>
 */
function sendJSONData(str_url, getOrPost, map_data, beforeSendFunction,
		successFunction, errorFunction, completeFunction , aIsSync) {
	
	var isSync = true;
	if( aIsSync != undefined)
	{
		isSync = aIsSync;
	}
	
	var ret = null;
	ret = $.ajax({
		url : str_url,
		type : getOrPost,
		data : map_data,
		async: isSync, 
		contentType : "application/json; charset=utf-8",
		dataType : "json",
		beforeSend : function(jqXHR,settings){
			if(typeof(beforeSendFunction)=="function")
			   beforeSendFunction(jqXHR, settings);
			},
		success : function(data,textStatus,jqXHR){
			if(typeof(successFunction)=="function")
			   successFunction(data, textStatus, jqXHR);
			},
		error : function(jqXHR, textStatus, errorThrown){
			if(typeof(errorFunction)=="function")
			   errorFunction(jqXHR, textStatus, errorThrown);
			},
		complete : function(jqXHR, textStatus){
			if(typeof(completeFunction)=="function")
			   completeFunction(jqXHR, textStatus);
			}
	});
	
	
	if(ret)
	{
		return ret.responseText;
	}
};