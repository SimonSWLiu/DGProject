

function enableRestaurant(id) {

    $.ajax({
        type: "POST",
        dataType: "json",
        url: "../menuController/enableRestaurant",
        data: {
            mId: id
        }
    }).done(function(data) {
       
        if(data){
            $("#ableBtn").empty();
            $str = "";
            $str = "<a href='#' class='btn btn-danger' role='button' onclick='disableRestaurant(" + id + ");'>Disable</a>";
            $("#ableBtn").append($str);
        }
        
    });
}


function disableRestaurant(id) {

    $.ajax({
        type: "POST",
        dataType: "json",
        url: "../menuController/disableRestaurant",
        data: {
            mId: id
        }
    }).done(function(data) {
        $("#ableBtn").empty();
        $str = "";
        $str = "<a href='#' class='btn btn-success' role='button' onclick='enableRestaurant(" + id + ");'>Enable</a>";
        $("#ableBtn").append($str);
    });
}


$(document).ready(function() {


});
