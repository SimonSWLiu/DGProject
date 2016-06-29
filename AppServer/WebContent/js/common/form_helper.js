//
function form_helper() {

}

form_helper.prototype.pushJsonObjectArray = function(array, name, value) {

    var obj = new Object();
    obj.name = name;
    obj.value = value;
    array.push(obj);

    return array;
}

form_helper.prototype.postJsonObjectArray = function(URL, jsonObjectArray) {

    $("body").append("<form></form>");
    $("body").find("form").attr("action", URL);
    $("body").find("form").attr("method", "post");
    $("body").find("form").attr("display", "none");

    for ( var i in jsonObjectArray) {
        var inputName = jsonObjectArray[i].name;
        var inputValue = jsonObjectArray[i].value;
        $("body").find("form").append(
                        "<input type='text' name='" + inputName + "' value = '"
                                        + inputValue + "' hidden></input>");
    }
    $("body").find("form").submit();
}

// format: '[{"name":' + inputName + ',"value":' + inputValue + '},{"name":' +
// inputName + ',"value":' + inputValue + '}]'
form_helper.prototype.postJsonArrayStr = function(URL, jsonArrayStr) {

    var jsonObject = jQuery.parseJSON(jsonArrayStr);
    $("body").append("<form></form>");
    $("body").find("form").attr("action", URL);
    $("body").find("form").attr("method", "post");
    $("body").find("form").attr("display", "none");

    for ( var i in jsonObject) {
        var inputName = jsonObject[i].name;
        var inputValue = jsonObject[i].value;
        $("body").find("form").append(
                        "<input type='text' name='" + inputName + "' value = '"
                                        + inputValue + "' hidden></input>");
    }
    $("body").find("form").submit();
}

form_helper.prototype.appendForm = function(form, inputName, inputValue) {
    form.append("<input type='text' name='" + inputName + "' value = '"
                    + inputValue + "' hidden></input>");
}
