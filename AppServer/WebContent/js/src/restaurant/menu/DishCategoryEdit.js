function delDishCategory(id) {

    $.messager.confirm("Warning", "Do you really want to delete this item?",
                    function() {
    	
    	var dishCategoryList = [];
    	
//    	$(":checkbox:checked").each(function() {
//    		var dishCategoryId = $(this).val();
//    		var dishCategory = new Object();
//    		dishCategory.id = dishCategoryId;
//    		dishCategoryList.push(JSON.stringify(dishCategory));
//    	});
    
    	var dishCategory = new Object();
    	dishCategory.id = id;
		dishCategoryList.push(JSON.stringify(dishCategory));
    	
    	$.ajax({
    		type : "POST",
    		dataType:"json",
    //		contentType:"application/json",
    		url : "../menuController/delDishCategory",
    		data : {
    		    dishCategoryListStr : "[" + dishCategoryList.toString() + "]"
    		}
    	}).done(function(result) {
    	    if(result.success){
    	        location.href ="../menuController/showDishCategoryEditPage";
    	    }
    	    else{
    	        $.messager.alert("Result", "Delete failed. Please check whether dishs under the dish category. If not, please contact admin.");
    	    }
    		
    	});
    });
}

function saveDishCategory(){
	
	var dishCategoryId = $('#dishCategoryId').val();
    var dishCategoryName = $('#dishCategoryName').val();
    var dishCategorySeq = $('#dishCategorySeq').val();
    console.log(dishCategoryId);
    
    if(dishCategoryId == "")
    {
        $.ajax({
            type : "POST",
            dataType:"json",
            url : "../menuController/addDishCategory",
            data : {
                mName : dishCategoryName,
                mSequence : dishCategorySeq
            }
        }).done(function(data) {
        	
            $('#diahCategoryModal').hide();
            $('#categoryName').val("");
            location.href ="../menuController/showDishCategoryEditPage";
            
        });
    }
    else {
    	
    	$.ajax({
            type : "POST",
            dataType:"json",
            url : "../menuController/editDishCategory",
            data : {
            	
            	mId : dishCategoryId,
                mName : dishCategoryName,
                mSequence : dishCategorySeq
            }
        }).done(function(data) {
        	
            $('#diahCategoryModal').hide();
            $('#categoryName').val("");
            location.href ="../menuController/showDishCategoryEditPage";
            
        });
    }
}


function showDishCategoryModel(id) {
	
	if(id == '') {
		
		$("#dishCategoryId").val('');
    	$("#dishCategoryName").val('');
    	$("#dishCategorySeq").val('');
	}
	else {
		
		$.ajax({
	        type : "POST",
	        url : "../menuController/getDishCategoryById",
	        data : {
	        	dishCategoryId : id
	        }
	    }).done(function(data) {
	        
	    	$("#dishCategoryId").val(data.id);
	    	$("#dishCategoryName").val(data.name);
	    	$("#dishCategorySeq").val(data.sequence);
	        
	    });
	}
	
	
	$('#diahCategoryModal').modal('show');
		
}

$(function() {
	$('#dishCategoryListTable').on('all.bs.table', function(e, name, args) {
		
	}).on('click-row.bs.table', function(e, row, $element) {

	})
});

function actionFormatter(value, row, index) {
	return [ '<a class="finish ml10" href="javascript:void(0)" title="Edit">',
			'<i class="glyphicon glyphicon-edit"></i>', '</a>',
			'&nbsp;&nbsp;&nbsp;',
			'<a class="cancel ml10" href="javascript:void(0)" title="Remove">',
			'<i class="glyphicon glyphicon-remove"></i>', '</a>' ].join('');
}


window.actionEvents = {
	'click .finish' : function(e, value, row, index) {
		showDishCategoryModel(row._id);
	},
	'click .cancel' : function(e, value, row, index) {
		delDishCategory(row._id);
	}
};

$(document).ready(function() {
	
	$("#dishCategorySubmitBtn").click(saveDishCategory);
    
});