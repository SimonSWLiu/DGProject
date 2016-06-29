<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@page isELIgnored="false"%>
<%@ include file="../include/bootstrap_head.html"%>
<script
	src="${pageContext.request.contextPath }/js/src/restaurant/menu/MenuItemEdit.js"></script>

<form id="dishEditForm" method="post" class="form-horizontal"
	role="form" enctype="multipart/form-data">

	<br> <br>

	<div class='form-group'>
		<div class='col-sm-2'>
			<label for="filePath" class="control-label">Photo</label>
		</div>
		<div class="col-sm-7">
			<input type="file" class="form-control" id="filePath" name="filePath"
				placeholder="file">
		</div>
	</div>

	<div class="form-group" id="cropperDiv" hidden>
		<div class='col-sm-2'></div>
		<div class="col-sm-7">
			<div class="eg-wrapper">
				<img class="cropper" src="" alt="Picture">
			</div>
		</div>
	</div>

	<%-- <c:if test="${fn:length(menuItem.imageUrl)>0}"> --%>
	<c:if test="${not empty menuItem.imageUrl}">
		<div class="form-group" id="imageDiv">
			<div class='col-sm-2'></div>
			<div class="col-sm-7">
				<div>
					<img style="width: 100%;" id="image" src="${menuItem.imageUrl}"
						alt="Picture">
				</div>
			</div>
		</div>
	</c:if>

	<div class='form-group'>
		<div class='col-sm-2'>
			<label for="dishName" class="control-label">Dish Category</label>
		</div>
		<div class="col-sm-4">
			<select class='form-control validate[required]'
				id="dishCategorySelect" name="mDishCategory.mId">
				<c:forEach items="${dishCategoryList}" var="dishCategory"
					varStatus="status">
					<c:if test="${ dishCategory.id == menuItem.dishCategoryId}">
						<option value="${dishCategory.id}" selected>${dishCategory.name}</option>
					</c:if>
					<c:if test="${ dishCategory.id != menuItem.dishCategoryId}">
						<option value="${dishCategory.id}">${dishCategory.name}</option>
					</c:if>
				</c:forEach>
			</select>
		</div>
		<div class="col-sm-1">
			<button type='button'
				class='form-control btn btn-default btn-xs tooltips'
				id='dishcategoryAddBtn' data-toggle="modal"
				data-target="#diahCategoryModal" data-toggle="tooltip"
				data-placement="right" title="Add dish category.">
				<span class='glyphicon glyphicon-plus'></span>
			</button>
		</div>
	</div>

	<div class='form-group'>
		<div class='col-sm-2'>
			<label for="dishName" class="control-label">Dish Name</label>
		</div>
		<div class="col-sm-4">
			<input type="text" class="form-control validate[required]"
				id="dishName" name="mName" placeholder="Name"
				value="${menuItem.name}">
		</div>
	</div>

	<div class='form-group'>
		<div class='col-sm-2'>
			<label for="dishName" class="control-label">Other Name</label>
		</div>
		<div class="col-sm-4">
			<input type="text" class="form-control"
				id="dishOtherName" name="mOtherName" placeholder="Other Name"
				value="${menuItem.otherName}">
		</div>
	</div>
	
	<div class='form-group'>
		<div class='col-sm-2'>
			<label for="ingredientType" class="control-label">Ingredient</label>
		</div>
		<div class="col-sm-7">
			<table id='ingredientTypeTable' style=''>
			</table>
			<div style='padding-top: 10px;'>
				<button type='button' class='btn btn-default btn-xs tooltips'
					id='ingredientTypeRowAddBtn' data-toggle="tooltip"
					data-placement="right" title='Add ingredient type.'>
					<span class='glyphicon glyphicon-plus'></span>
				</button>
			</div>
		</div>
	</div>

	<div class='form-group'>
		<div class='col-sm-2'>
			<label for='price' class='control-label'>Price</label>
		</div>
		<div class='col-sm-2'>
			<div class='input-group'>
				<span class='input-group-addon'>$</span> <input type='number'
					class='form-control validate[required]' id='price' name='mPrice'
					placeholder='0.00' value='${menuItem.price}' min='0'
					onblur='formatPrice(this);'>
			</div>
		</div>
	</div>

	<div class='form-group'>
		<div class='col-sm-2'>
			<label for='description' class='control-label'>Description</label>
		</div>
		<div class='col-sm-7'>
			<textarea rows='3' class='form-control' id='description'
				name='mDescription' placeholder='Description'>${menuItem.desc}</textarea>
		</div>
	</div>

	<div class='form-group'>
		<div class='col-sm-offset-2 col-sm-2'>
			<button type='button' class='btn btn-primary' id='submitBtn'>Submit</button>
		</div>
	</div>

	<!-- Hidden values -->
	<div>
		<input id='dishId' value='${menuItem.id}' type='hidden'> <input
			id='dataWidth' name='width' type='hidden'> <input
			id='dataHeight' name='height' type='hidden'> <input
			id='imgUrl' name='base64Str' type='hidden'> <input
			id='ingredientJsonStr' name='mCustomization'
			value='${menuItem.customization}' type='hidden'>

	</div>
</form>

<!-- Dish Category Modal -->
<div class='modal fade' id='diahCategoryModal' tabindex='-1'
	role='dialog' aria-labelledby='myModalLabel' aria-hidden='true'>
	<form id='dishCategoryForm'>
		<div class='modal-dialog'>
			<div class='modal-content'>
				<div class='modal-header'>
					<button type='button' class='close' data-dismiss='modal'>
						<span aria-hidden='true'>&times;</span><span class='sr-only'>Close</span>
					</button>
					<h4 class='modal-title' id='myModalLabel'>Add dish category</h4>
				</div>
				<div class='modal-body'>

					<label>Dish Category Name:</label> <input
						class='validate[required]' type='text' id='categoryName'
						name='mName' placeholder='Name'>
				</div>
				<div class='modal-footer'>
					<button type='button' class='btn btn-default' data-dismiss='modal'>Close</button>
					<button type='button' class='btn btn-primary'
						id='dishCategorySubmitBtn'>Save</button>
				</div>
			</div>
		</div>
	</form>
</div>

</form>

<%@ include file="../include/bootstrap_foot.html"%>

