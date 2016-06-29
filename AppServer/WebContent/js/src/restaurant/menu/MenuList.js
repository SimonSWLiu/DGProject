function showMenuItemList() {

    var value = $("input[name='menuItemListOptions']:radio:checked").val();
    console.log(value);
    if (value == "ALL") {
        $('.metnuItemList').fadeIn(1000);
    } else {
        $('.metnuItemList').hide();
        $("#" + value).fadeIn(1000);
    }
}

function editMenuItem(id) {
    if(id != null) {
        var jsonObjectArray = [];
        jsonObjectArray = form_helper.pushJsonObjectArray(jsonObjectArray, "mId",
                        id);
        form_helper.postJsonObjectArray("../menuController/showMenuItemEditPage",
                        jsonObjectArray);
    }
}

function delMenuItem(id) {
    $.messager.confirm("Warning", "Do you really want to delete this item?",
                    function() {

                        $.ajax({
                            type: "POST",
                            dataType: "json",
                            url: "../menuController/delMenuItem",
                            data: {
                                mId: id
                            }
                        }).done(function(data) {
                            if (data) {
                                $('#' + id).remove()
                            } else {
                                $.messager.alert("Result", "Delete failed!");
                            }
                        });
                    });
}


function enableMenuItem(id) {

    $.ajax({
        type: "POST",
        dataType: "json",
        url: "../menuController/enableMenuItem",
        data: {
            mId: id
        }
    }).done(function(data) {
       
        if(data){
            $("#ableBtn" + id).empty();
            $str = "";
            $str = "<a href='javascript:void(0)' class='btn btn-danger' role='button' onclick='disableMenuItem(" + id + ");'>Disable</a>";
            $("#ableBtn" + id).append($str);
        }
        
    });
}


function disableMenuItem(id) {

    $.ajax({
        type: "POST",
        dataType: "json",
        url: "../menuController/disableMenuItem",
        data: {
            mId: id
        }
    }).done(function(data) {
        $("#ableBtn" + id).empty();
        $str = "";
        $str = "<a href='javascript:void(0)' class='btn btn-success' role='button' onclick='enableMenuItem(" + id + ");'>Enable</a>";
        $("#ableBtn" + id).append($str);
    });
}

var form_helper;
$(document).ready(function() {
    form_helper = new form_helper();

    showMenuItemList();
});
