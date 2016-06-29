
function enterDown(event) {
    if (event.keyCode == '13') {
    	signIn();
    }
}


function signIn() {
    var form = $('#loginForm');
    form.submit();
}

$(document).ready(function() {

	$("#signInBtn").click(signIn);
});