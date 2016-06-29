<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@page isELIgnored="false"%>
<%@ include file="../include/bootstrap_head.html"%>
<script
	src="${pageContext.request.contextPath }/js/src/restaurant/menu/MenuList.js"></script>

<div>

	<br>
	<!-- <h1>Category:</h1> -->
	<div class='btn-group' data-toggle='buttons'>
		<label class='btn btn-default active' onchange='showMenuItemList()'>
			<input type='radio' name='menuItemListOptions' value='ALL'
			autocomplete='off' checked>All
		</label>
		<c:forEach items="${menuItemMap}" var="menuItemListEntry"
			varStatus="status">
			<label class='btn btn-default'
				value='${fn:replace(menuItemListEntry.key," ","")}${status.index}'
				onchange='showMenuItemList()'> <input type='radio'
				name='menuItemListOptions'
				value='${fn:replace(menuItemListEntry.key," ","")}${status.index}'
				autocomplete='off'>${menuItemListEntry.key}
			</label>
		</c:forEach>
	</div>

	<hr>
	<div>
		<button type="button" class="btn btn-primary btn-block" id="addBtn"
			onclick="window.location.href='../menuController/showMenuItemAddPage'">
			<span class="glyphicon glyphicon-plus"></span>Add dish
		</button>
	</div>
	<hr>


	<c:forEach items="${menuItemMap}" var="menuItemListEntry"
		varStatus="status">
		<div id='${fn:replace(menuItemListEntry.key," ","")}${status.index}'
			class="metnuItemList" hidden>
			<!--  col-xs-6 col-sm-4 col-md-2 col-lg-1 -->
			<h2>${menuItemListEntry.key}</h2>
			<div class="row">

				<c:forEach items="${menuItemListEntry.value}" var="menuItem">
					<div id="${menuItem.id}"
						class="col-xs-6 col-sm-4 col-md-2 col-lg-1">
						<div class="thumbnail">
							<div class="caption" id="ableBtn${menuItem.id}">
								<c:if test="${menuItem.status == '1'}">
									<a href="javascript:void(0)" class="btn btn-danger"
										role="button" onclick="disableMenuItem('${menuItem.id}')">Disable</a>
								</c:if>
								<c:if test="${menuItem.status == '0'}">
									<a href="javascript:void(0)" class="btn btn-success"
										role="button" onclick="enableMenuItem('${menuItem.id}')">Enable</a>
								</c:if>
							</div>
							<img src="${menuItem.imageUrl}" alt="">
							<div class="caption">
								<p>${menuItem.name}</p>
								<p>$ ${menuItem.price}</p>
								<a href="javascript:void(0)" class="btn btn-warning"
									role="button" onclick="editMenuItem('${menuItem.id}')">Edit</a>
								<a href="javascript:void(0)" class="btn btn-danger"
									role="button" onclick="delMenuItem('${menuItem.id}')">Delete</a>
							</div>
						</div>
					</div>
				</c:forEach>
			</div>
		</div>
	</c:forEach>

</div>

<%@ include file="../include/bootstrap_foot.html"%>

