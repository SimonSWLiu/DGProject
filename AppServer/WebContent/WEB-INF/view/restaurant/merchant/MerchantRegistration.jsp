<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page isELIgnored="false"%>
<%@ include file="../include/bootstrap_head.html"%>

<body>

	<form id="dishEditForm" method="post" class="form-horizontal"
		role="form" enctype="multipart/form-data"
		action="../loginController/merchantRegister">

		<div class="form-group">
			<div class="col-sm-offset-1 col-sm-11">
				<h1>Merchant registration</h1>
			</div>
		</div>

		<div class="form-group">
			<div class="col-sm-offset-1 col-sm-2">
				<label for="email" class="control-label">E-mail</label>
			</div>
			<div class="col-sm-4">
				<input type="email"
					class="form-control  validate[required,custom[email]],ajax[ajaxEmailCall]"
					id="email" name="mEmail" placeholder="Enter email">
			</div>
		</div>

		<div class="form-group">
			<div class="col-sm-offset-1 col-sm-2">
				<label for="password" class="control-label">Password</label>
			</div>
			<div class="col-sm-4">
				<input type="password"
					class="form-control validate[required,minSize[8],maxSize[20]]"
					id="password" name="mPassword" placeholder="8-20 characters"
					maxlength="20">
			</div>
		</div>

		<div class="form-group">
			<div class="col-sm-offset-1 col-sm-2">
				<label for="confirmPassword" class="control-label">Confirm</label>
			</div>
			<div class="col-sm-4">
				<input type="password"
					class="form-control validate[required,equals[password],minSize[8],maxSize[20]]"
					id="confirmPassword" placeholder="8-20 characters" maxlength="20">
			</div>
		</div>

		<div class="form-group">
			<div class="col-sm-offset-1 col-sm-2">
				<label for="merchantName" class="control-label">Merchant
					Name</label>
			</div>
			<div class="col-sm-2">
				<input type="text" class="form-control validate[required]"
					id="merchantName" name="mMerchant.mFirstName"
					placeholder="First Name">
			</div>
			&nbsp;&nbsp;
			<div class="col-sm-2">
				<input type="text" class="form-control validate[required]"
					id="merchantName" name="mMerchant.mLastName"
					placeholder="Last Name">
			</div>
		</div>

		<div class="form-group">
			<div class="col-sm-offset-1 col-sm-2">
				<label for="phone" class="control-label">Phone</label>
			</div>
			<div class="col-sm-4">
				<input type="text"
					class="form-control validate[required,custom[phone]]" id="phone"
					name="mMerchant.mPhone" placeholder="Phone">
			</div>
		</div>

		<div class="form-group">
			<div class="col-sm-offset-3 col-sm-4">
				<button type="submit" class="btn btn-primary">Next</button>
			</div>
		</div>

	</form>

	<script type="text/javascript">
// This method is called right before the ajax form validation request
// it is typically used to setup some visuals ("Please wait...");
// you may return a false to stop the request 
function beforeCall(form, options){
	console.log("Right before the AJAX form validation call");
	return true;
}
    
// Called once the server replies to the ajax form validation request
function ajaxValidationCallback(status, form, json, options){
	console.log(status);
        
	if (status === true) {
		alert("the form is valid!");
		// uncomment these lines to submit the form to form.action
		// form.validationEngine('detach');
		// form.submit();
		// or you may use AJAX again to submit the data
	}
}

$(document).ready(function() {
	
	//$("#email").blur(validatorEmail);
	
	//$("#dishEditForm").validationEngine();
	
	$("#dishEditForm").validationEngine();
	
});
</script>

</body>

<%@ include file="../include/bootstrap_foot.html"%>

