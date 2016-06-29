
function addIngredientTypeRow() {
	
	var ingredientTypeIndex = $("tr[name='ingredientTypeTr']").length;
	
	$str = '';
	
	$str += "<tr name='ingredientTypeTr'>";
	$str += "	<td><button type='button' class='btn btn-default btn-xs tooltips'  onclick='delIngredientTypeRow(this);' data-toggle='tooltip' data-placement='left' title='Delete ingredient type.'><span class='glyphicon glyphicon-minus'></span></button></td>";
	$str += "		<td>";
	$str += "			<table>";
	$str += "			<tr>";
	$str += "					<td>";
	$str += "					    <input type='radio' name='isMandatory_" + ingredientTypeIndex + "' value='1' autocomplete='off' checked>Mandatory";
	$str += "					    <input type='radio' name='isMandatory_" + ingredientTypeIndex + "' value='0' autocomplete='off'>Optional";
	$str += "					&nbsp;&nbsp;";
	$str += "				</td>";
	$str += "				<td>";
	$str += "					    <input type='radio' name='isSingle_" + ingredientTypeIndex + "' value='1' autocomplete='off' checked>Single";
	$str += "					    <input type='radio' name='isSingle_" + ingredientTypeIndex + "' value='0' autocomplete='off'>Multiple";
	$str += "					&nbsp;&nbsp;";
	$str += "				</td>";
	$str += "				<td><label>Type</label></td>";
	$str += "				<td><input class='validate[required]' type='text' id='ingredientType_" + ingredientTypeIndex + "' placeholder='Type Name'>&nbsp;&nbsp;</td>";
	$str += "			</tr>";
	$str += "			<tr>";
	$str += "				<td colspan='5'>";
	$str += "					<table id='ingredientTable'>";
	$str += "						<tr name='ingredientTr'>";
	$str += "							<td><input class='validate[required]' type='text' id='ingredientName_" + ingredientTypeIndex + "_0' placeholder='Name'>&nbsp;&nbsp;</td>";
	$str += "							<td><label>$</label><input class='validate[required]' type='number' id='ingredientPrice_" + ingredientTypeIndex + "_0' placeholder='Price' min='0' onblur='formatPrice(this);'>&nbsp;&nbsp;</td>";
	$str += "							<td><button type='button' class='btn btn-default btn-xs tooltips' onclick='delIngredientRow(this);' data-toggle='tooltip' data-placement='right' title='Delete ingredient.'><span class='glyphicon glyphicon-minus'></span></button></td>";
	$str += "						</tr>";
	$str += "					</table>";
	$str += "					<div style=''>";
	$str += "						<button type='button' class='btn btn-default btn-xs tooltips' id='ingredientRowAddBtn' onclick='addIngredientRow(this);' data-toggle='tooltip' data-placement='right' title='Add ingredient.'>";
	$str += "							<span class='glyphicon glyphicon-plus'></span>";
	$str += "						</button>";
	$str += "					</div>";
	$str += "					<hr>";
	$str += "				</td>";
	$str += "			</tr>";
	$str += "		</table>";
	$str += "	</td>";
	$str += "</tr>";
	
	$("#ingredientTypeTable").append($str);
	
	$(".tooltips").tooltip();
}

function addIngredientRow(element) {
	
	var ingredientTypeIndex = $(element).parents("tr[name='ingredientTypeTr']").index();
	var ingredientIndex = $(element).parents("tr[name='ingredientTypeTr']").find("table[id='ingredientTable'] tr").length;
	
	$str = '';
	$str += "<tr name='ingredientTr'>";
	$str += "	<td><input class='validate[required]' type='text' id='ingredientName_" + ingredientTypeIndex + "_" + ingredientIndex + "' placeholder='Name'>&nbsp;&nbsp;</td>";
	$str += "	<td><label>$</label><input class='validate[required]' type='number' id='ingredientPrice_" + ingredientTypeIndex + "_" + ingredientIndex + "' placeholder='Price' min='0' onblur='formatPrice(this);'>&nbsp;&nbsp;</td>";
	$str += "	<td><button type='button' class='btn btn-default btn-xs tooltips'><span class='glyphicon glyphicon-minus' onclick='delIngredientRow(this);' data-toggle='tooltip' data-placement='right' title='Delete ingredient.'></span></button></td>";
	$str += "</tr>";
	
	$(element).parents("tr[name='ingredientTypeTr']").find("table[id='ingredientTable']").append($str);
	
	$(".tooltips").tooltip();
}

function delIngredientTypeRow(element) {
	$(element).parents("tr").remove();
}

function delIngredientRow(element) {
	
	$(element).parents('#ingredientTable tr').remove();
}

function refreshIngredientTypeListModel() {

	var ingredientTypeTrs = $("tr[name='ingredientTypeTr']");
	var ingredientTypeListDataModel = [];
	
	for ( var i = 0; i < ingredientTypeTrs.length; i++) {

		var isMandatory = $("input[name='isMandatory_" + i + "']:radio:checked").val();
		var isSingle = $("input[name='isSingle_" + i + "']:radio:checked").val();
		var type = $("#ingredientType_" + i).val();
		
		var ingredientTrs = ingredientTypeTrs.eq(i).find("tr[name='ingredientTr']");
		var ingredientListDataModel = [];
		
		for ( var j = 0; j < ingredientTrs.length; j++) {
			
			var ingredientName = $("#ingredientName_" + i + "_" + j).val();
			var ingredientPrice = $("#ingredientPrice_" + i + "_" + j).val();
			
			ingredientListDataModel[j] = "{ingredientName:" + ingredientName + ",ingredientPrice:" + ingredientPrice + "}";
		}
		
		ingredientTypeListDataModel[i] = "{isMandatory:" + isMandatory + ",isSingle:" + isSingle + ",type:" + type + ",ingredient:[" + ingredientListDataModel + "]}";
	}
	
	$("#ingredientJsonStr").val("[" + ingredientTypeListDataModel.toString() + "]");
}

function uploadImg() {
    
        
    var file_data = $('#filePath').prop('files')[0];   
    var form_data = new FormData();                  
    form_data.append('file', file_data);
    $.ajax({
                url: '../commonController/generateBase64String', // point to server-side PHP script 
                dataType: 'text',  // what to expect back from the PHP script, if anything
                cache: false,
                contentType: false,
                processData: false,
                data: form_data,                         
                type: 'post',
                success: function(base64){
                	$(".cropper").cropper("replace", base64);
                	$("#cropperDiv").show();
                }
     });
}

function submitDishCategory(){
	var categroyName = $('#categoryName').val();
	
	if(categroyName != null)
	{
		$.ajax({
			type : "POST",
			dataType:"json",
//			contentType:"application/json",
			url : "../menuController/addDishCategory",
			data : {
				mName : categroyName
			}
		}).done(function(data) {
			$('#diahCategoryModal').hide();
			reloadDishCategoryOptions(data);
			$('#categoryName').val("");
		});
	}
	else{
	    $.messager.alert("Tips", "Please input name!");
	}
}

function reloadDishCategoryOptions(dishCategory){
	$("#dishCategorySelect option").remove();
	$.ajax({
		type : "POST",
		dataType:"json",
//		contentType:"application/json",
		url : "../menuController/getDishCategoryList",
		data : {}
	}).done(function(data) {
		for(var i in data){
			var optionValue = data[i].id;
			var optionText = data[i].name;
			$("<option></option>").val(optionValue).text(optionText).appendTo("#dishCategorySelect");
		}
	});
}

function loadIngredientTypeListModel() {

    var ingredientJsonStr = $('#ingredientJsonStr').val();
    var ingredientTypeArray = jQuery.parseJSON(ingredientJsonStr);
    
    // [create ingredient type table
    for(var m = 0; m < ingredientTypeArray.length; m++) {
        
        $str = '';
        
        $str += "<tr name='ingredientTypeTr'>";
        $str += "   <td><button type='button' class='btn btn-default btn-xs tooltips'  onclick='delIngredientTypeRow(this);' data-toggle='tooltip' data-placement='left' title='Delete ingredient type.'><span class='glyphicon glyphicon-minus'></span></button></td>";
        $str += "       <td>";
        $str += "           <table>";
        $str += "           <tr>";
        $str += "                   <td>";
//        $str += "                   <div class='btn-group' data-toggle='buttons'>";
//        $str += "                     <label class='btn btn-default active'>";
        $str += "                       <input type='radio' name='isMandatory_" + m + "' value='1' autocomplete='off' checked>Mandatory";
//        $str += "                     </label>";
//        $str += "                     <label class='btn btn-default'>";
        $str += "                       <input type='radio' name='isMandatory_" + m + "' value='0' autocomplete='off'>Optional";
//        $str += "                     </label>";
//        $str += "                   </div>";
        $str += "                   &nbsp;&nbsp;";
        $str += "               </td>";
        $str += "               <td>";
//        $str += "                   <div class='btn-group' data-toggle='buttons'>";
//        $str += "                     <label class='btn btn-default active'>";
        $str += "                       <input type='radio' name='isSingle_" + m + "' value='1' autocomplete='off' checked>Single";
//        $str += "                     </label>";
//        $str += "                     <label class='btn btn-default'>";
        $str += "                       <input type='radio' name='isSingle_" + m + "' value='0' autocomplete='off'>Multiple";
//        $str += "                     </label>";
//        $str += "                   </div>";
        $str += "                   &nbsp;&nbsp;";
        $str += "               </td>";
        $str += "               <td><label>Type</label></td>";
        $str += "               <td><input class='validate[required]' type='text' id='ingredientType_" + m + "' placeholder='Type Name'>&nbsp;&nbsp;</td>";
        $str += "           </tr>";
        $str += "           <tr>";
        $str += "               <td colspan='5'>";
        $str += "                   <table id='ingredientTable'>";
        // [create ingredient table
        var ingredient = ingredientTypeArray[m].ingredient;
        for(var n = 0; n < ingredient.length; n++){
            $str += "                       <tr name='ingredientTr'>";
            $str += "                           <td><input class='validate[required]' type='text' id='ingredientName_" + m + "_" + n + "' placeholder='Name'>&nbsp;&nbsp;</td>";
            $str += "                           <td><label>$</label><input class='validate[required]' type='number' id='ingredientPrice_" + m + "_" + n + "' placeholder='Price' min='0' onblur='formatPrice(this);'>&nbsp;&nbsp;</td>";
            $str += "                           <td><button type='button' class='btn btn-default btn-xs tooltips' onclick='delIngredientRow(this);' data-toggle='tooltip' data-placement='right' title='Delete ingredient.'><span class='glyphicon glyphicon-minus'></span></button></td>";
            $str += "                       </tr>";
        }
        // ]
        $str += "                   </table>";
        $str += "                   <div style=''>";
        $str += "                       <button type='button' class='btn btn-default btn-xs tooltips' id='ingredientRowAddBtn' onclick='addIngredientRow(this);' data-toggle='tooltip' data-placement='right' title='Add ingredient.'>";
        $str += "                           <span class='glyphicon glyphicon-plus'></span>";
        $str += "                       </button>";
        $str += "                   </div>";
        $str += "                   <hr>";
        $str += "               </td>";
        $str += "           </tr>";
        $str += "       </table>";
        $str += "   </td>";
        $str += "</tr>";
        
        $("#ingredientTypeTable").append($str);
    }
    
    $(".tooltips").tooltip();
    // ]
    
    var ingredientTypeTrs = $("tr[name='ingredientTypeTr']");
    for ( var i = 0; i < ingredientTypeTrs.length; i++) {

        var isMandatory = ingredientTypeArray[i].isMandatory;
            $("input[name='isMandatory_" + i + "']:radio").each(function(){
                if($(this).val()==isMandatory){
                    $(this).attr("checked",'true');
                }
            })
            
                
        var isSingle = ingredientTypeArray[i].isSingle;
            $("input[name='isSingle_" + i + "']:radio").each(function(){
                if($(this).val()==isSingle){
                    $(this).attr("checked",'checked');
                }
            })
            
        var type = ingredientTypeArray[i].type;
            $("#ingredientType_" + i).val(type);
        
        var ingredientTrs = ingredientTypeTrs.eq(i).find("tr[name='ingredientTr']");
        var ingredient = ingredientTypeArray[i].ingredient;
        
        for ( var j = 0; j < ingredientTrs.length; j++) {
            
            var ingredientName = ingredient[j].ingredientName;
                $("#ingredientName_" + i + "_" + j).val(ingredientName);
            var ingredientPrice = new format_helper().formatPrice(ingredient[j].ingredientPrice);
                $("#ingredientPrice_" + i + "_" + j).val(ingredientPrice);
        }
    }
    
}

function formatPrice(element){
    
    var price = new format_helper().formatPrice($(element).val());
    $(element).val(price);
}

$(document).ready(function() {
	
	initDataCropper();
	
	$("#dishEditForm").validationEngine();
	$("#dishCategoryForm").validationEngine();
	
	$(".tooltips").tooltip();
	
	
	$('#filePath').change(uploadImg);
	
	$("#ingredientTypeRowAddBtn").click(addIngredientTypeRow);
	
	$("#submitBtn").click(function(){

	    var form = $("#dishEditForm");
		refreshIngredientTypeListModel();
		var menuItemId = $('#dishId').val();
		if(menuItemId!=""){
		    form.attr("action","../menuController/editMenuItem");
		    new form_helper().appendForm(form,'mId',menuItemId);
		}
		else{
			form.attr("action","../menuController/addMenuItem");
		}
		form.submit();
		
	});
	
	$("#dishCategorySubmitBtn").click(submitDishCategory);
	
	loadIngredientTypeListModel();
	
});
