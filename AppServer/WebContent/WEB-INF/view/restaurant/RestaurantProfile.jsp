<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page isELIgnored="false"%>
<%@ include file="../include/bootstrap_head.html"%>
<script
	src="${pageContext.request.contextPath }/js/src/restaurant/RestaurantProfile.js"></script>
<script
	src="http://maps.google.com/maps/api/js?sensor=false&libraries=places"
	type="text/javascript"></script>
<script
	src="${pageContext.request.contextPath }/js/common/google_map_helper.js"></script>
<br>

<form id="restaurantProfileForm" method="post" class="form-horizontal"
	role="form" action="../menuController/editRestaurantDetails"
	enctype="multipart/form-data">

	<div class="form-group">
		<div class="col-sm-offset-1 col-sm-2">
			<label for="filePath" class="control-label">Logo</label>
		</div>
		<div class="col-sm-7">
			<input type="file" class="form-control" id="filePath" name="filePath"
				placeholder="file">
		</div>
	</div>

	<div class="form-group" id="cropperDiv" hidden>
		<div class="col-sm-offset-1 col-sm-2"></div>
		<div class="col-sm-7">
			<div class="eg-wrapper">
				<img class="cropper" src="" alt="Picture" id="logoSrc">
			</div>
		</div>
	</div>

	<c:if test="${not empty restaurant.mLogo}">
		<div class="form-group" id="imageDiv">
			<div class='col-sm-offset-1 col-sm-2'></div>
			<div class="col-sm-2">
				<div>
					<img style="width: 100%;" src="${ logoUrl }" alt="Picture">
				</div>
			</div>
		</div>
	</c:if>

	<div class="form-group">
		<div class="col-sm-offset-1 col-sm-2">
			<label for="name" class="control-label">Restaurant Name</label>
		</div>
		<div class="col-sm-3">
			<input type="text" class="form-control validate[required]" id="name" name="mName"
				placeholder="Name" value="${restaurant.mName}">
		</div>
	</div>

	<div class="form-group">
		<div class="col-sm-offset-1 col-sm-2">
			<label class="control-label">Support Delivery Type</label>
		</div>
		<div class="col-sm-7">
			<input type="checkbox" id='deliveryCheckBox' name='mIsDelivery'>Delivery&nbsp;&nbsp;
			<input type="checkbox" id='pickOutCheckBox' name='mIsPickOut'>Pick
			out&nbsp;&nbsp; <input type="checkbox" id='preOrderCheckBox'
				name='mIsPreOrder'>Pre-Order&nbsp;&nbsp;
		</div>
	</div>

	<div class='form-group'>
		<div class="col-sm-offset-1 col-sm-2">
			<label for='deliveryFee' class='control-label'>Delivery Fee</label>
		</div>
		<div class='col-sm-2'>
			<div class='input-group'>
				<span class='input-group-addon'>$</span> <input type='number'
					step="any" class='form-control validate[required]' id='deliveryFee'
					name='mDeliveryFee' value="${restaurant.mDeliveryFee}"
					placeholder='0.00' min='0' onblur='formatPrice(this);'>
			</div>
		</div>
	</div>

	<div class='form-group'>
		<div class="col-sm-offset-1 col-sm-2">
			<label for='freeDeliveryLimit' class='control-label'>Free
				Delivery limit</label>
		</div>
		<div class='col-sm-2'>
			<div class='input-group'>
				<span class='input-group-addon'>$</span> <input type='number'
					step="any" class='form-control validate[required]'
					id='freeDeliveryLimit' name='mFreeDeliveryLimit'
					value="${restaurant.mFreeDeliveryLimit}" placeholder='0.00' min='0'
					onblur='formatPrice(this);'>
			</div>
		</div>
		<div class='col-sm-5'>
			<p class="help-block">You can set a lager number if you want to
				set no limit of delivery fee.</p>
		</div>
	</div>

	<div class='form-group'>
		<div class="col-sm-offset-1 col-sm-2">
			<label for='minDeliveryTotal' class='control-label'>Minimal
				Delivery Total</label>
		</div>
		<div class='col-sm-2'>
			<div class='input-group'>
				<span class='input-group-addon'>$</span> <input type='number'
					step="any" class='form-control validate[required]'
					id='minDeliveryTotal' name='mMinDeliveryTotal'
					value="${restaurant.mMinDeliveryTotal}" placeholder='0.00' min='0'
					onblur='formatPrice(this);'>
			</div>
		</div>
		<div class='col-sm-5'>
			<p class="help-block">You can set 0 if you want to set no minimum
				bill of delivery fee.</p>
		</div>
	</div>

	<div class='form-group'>
		<div class="col-sm-offset-1 col-sm-2">
			<label for='deliveryDistance' class='control-label'>Delivery
				Distance</label>
		</div>
		<div class='col-sm-2'>
			<div class='input-group'>
				<input type='number' step="any"
					class='form-control validate[required]' id='deliveryDistance'
					name='mDeliveryDistance' value="${restaurant.mDeliveryDistance}"
					placeholder='0.00' min='0'> <span class='input-group-addon'>miles</span>
			</div>
		</div>
	</div>

	<div class="form-group">
		<div class="col-sm-offset-1 col-sm-2">
			<label for=""phone"" class="control-label">Phone</label>
		</div>
		<div class="col-sm-4">
			<input type="text" class="form-control validate[custom[phone]]"
				id="phone" name="mPhone" value="${restaurant.mPhone}"
				placeholder="ex: XXXXXXXXXX">
		</div>
	</div>
	
	<div class="form-group">
		<div class="col-sm-offset-1 col-sm-2">
			<label for="faxNum" class="control-label">Fax Number</label>
		</div>
		<div class="col-sm-4">
			<input type="text" class="form-control validate[custom[phone]]"
				id="faxNum" name="mFaxNum" value="${restaurant.mFaxNum}"
				placeholder="ex: XXXXXXXXXX">
		</div>
	</div>

	<div class="form-group">
		<div class="col-sm-offset-1 col-sm-2">
			<label for="email" class="control-label">Email</label>
		</div>
		<div class="col-sm-4">
			<input type="text" class="form-control validate[custom[email]]"
				id="email" name="mEmail" value="${restaurant.mEmail}" placeholder="">
		</div>
	</div>

	<div class="form-group">
		<div class="col-sm-offset-1 col-sm-2">
			<label for="street" class="control-label">Street</label>
		</div>
		<div class="col-sm-7">
			<input type="text" class="form-control" id="street" name="mStreet"
				placeholder="Street" value="${restaurant.mStreet}"
				onblur='formatAddress();'>
		</div>
	</div>

	<div class="form-group">
		<div class="col-sm-offset-1 col-sm-2">
			<label for="city" class="control-label">City</label>
		</div>
		<div class="col-sm-7">
			<input type="text" class="form-control" id="city" name="mCity"
				placeholder="City" value="${restaurant.mCity}"
				onblur='formatAddress();'>
		</div>
	</div>

	<div class="form-group">
		<div class="col-sm-offset-1 col-sm-2">
			<label for="state" class="control-label">State</label>
		</div>
		<div class="col-sm-7">
			<input type="text" class="form-control" id="state" name="mState"
				placeholder="Apt" value="${restaurant.mState}"
				onblur='formatAddress();'>
		</div>
	</div>

	<div class="form-group">
		<div class="col-sm-offset-1 col-sm-2">
			<label for="zipCode" class="control-label">Zip Code</label>
		</div>
		<div class="col-sm-7">
			<input type="text" class="form-control" id="zipCode" name="mZipCode"
				placeholder="Zip Code" value="${restaurant.mZipCode}"
				onblur='formatAddress();'>
		</div>
	</div>

	<div class="form-group">
		<div class="col-sm-offset-1 col-sm-2">
			<label for="address" class="control-label">Restaurant Address</label>
		</div>
		<div class="col-sm-7">
			<input type="text" class="form-control validate[required]" id="address" name="mAddress"
				placeholder="Address" value="${restaurant.mAddress}" readonly>
		</div>
	</div>

	<div class="form-group">
		<div class="col-sm-3"></div>
		<div class="col-sm-7">
			<div id="map_canvas" style='width: 100%; height: 400px;'></div>
		</div>
	</div>

	<div class="form-group">
		<div class="col-sm-offset-1 col-sm-2">
			<label class="control-label">Open Time</label>
		</div>
		<div class="col-sm-7">
			<table>
				<tr>
					<td>Mon</td>
					<td style="padding: 5px 5px 5px 5px">From</td>
					<td>
						<div class="input-group date form_datetime" data-date=""
							data-date-format="hh:ii" data-link-field="dtp_input1">
							<input class="form-control" size="30" type="text"
								value="${restaurant.mMonOpenStartTime}" name="mMonOpenStartTime"
								readonly> <span class="input-group-addon"><span
								class="glyphicon glyphicon-remove"
								onclick="$('input[name=mMonOpenStartTime]').val('');"></span></span> <span
								class="input-group-addon"><span
								class="glyphicon glyphicon-th"></span></span>
						</div>
					</td>
					<td style="padding: 5px 5px 5px 5px">To</td>
					<td>
						<div class="input-group date form_datetime" data-date=""
							data-date-format="hh:ii" data-link-field="dtp_input1">
							<input class="form-control" size="30" type="text"
								value="${restaurant.mMonOpenEndTime}" name="mMonOpenEndTime"
								readonly> <span class="input-group-addon"><span
								class="glyphicon glyphicon-remove"
								onclick="$('input[name=mMonOpenEndTime]').val('');"></span></span> <span
								class="input-group-addon"><span
								class="glyphicon glyphicon-th"></span></span>
						</div>
					</td>
				</tr>
				<tr>
					<td>Tue</td>
					<td style="padding: 5px 5px 5px 5px">From</td>
					<td>
						<div class="input-group date form_datetime" data-date=""
							data-date-format="hh:ii" data-link-field="dtp_input1">
							<input class="form-control" size="30" type="text"
								value="${restaurant.mTueOpenStartTime}" name="mTueOpenStartTime"
								readonly> <span class="input-group-addon"><span
								class="glyphicon glyphicon-remove"
								onclick="$('input[name=mTueOpenStartTime]').val('');"></span></span> <span
								class="input-group-addon"><span
								class="glyphicon glyphicon-th"></span></span>
						</div>
					</td>
					<td style="padding: 5px 5px 5px 5px">To</td>
					<td>
						<div class="input-group date form_datetime" data-date=""
							data-date-format="hh:ii" data-link-field="dtp_input1">
							<input class="form-control" size="30" type="text"
								value="${restaurant.mTueOpenEndTime}" name="mTueOpenEndTime"
								readonly> <span class="input-group-addon"><span
								class="glyphicon glyphicon-remove"
								onclick="$('input[name=mTueOpenEndTime]').val('');"></span></span> <span
								class="input-group-addon"><span
								class="glyphicon glyphicon-th"></span></span>
						</div>
					</td>
				</tr>
				<tr>
					<td>Wed</td>
					<td style="padding: 5px 5px 5px 5px">From</td>
					<td>
						<div class="input-group date form_datetime" data-date=""
							data-date-format="hh:ii" data-link-field="dtp_input1">
							<input class="form-control" size="30" type="text"
								value="${restaurant.mWedOpenStartTime}" name="mWedOpenStartTime"
								readonly> <span class="input-group-addon"><span
								class="glyphicon glyphicon-remove"
								onclick="$('input[name=mWedOpenStartTime]').val('');"></span></span> <span
								class="input-group-addon"><span
								class="glyphicon glyphicon-th"></span></span>
						</div>
					</td>
					<td style="padding: 5px 5px 5px 5px">To</td>
					<td>
						<div class="input-group date form_datetime" data-date=""
							data-date-format="hh:ii" data-link-field="dtp_input1">
							<input class="form-control" size="30" type="text"
								value="${restaurant.mWedOpenEndTime}" name="mWedOpenEndTime"
								readonly> <span class="input-group-addon"><span
								class="glyphicon glyphicon-remove"
								onclick="$('input[name=mWedOpenEndTime]').val('');"></span></span> <span
								class="input-group-addon"><span
								class="glyphicon glyphicon-th"></span></span>
						</div>
					</td>
				</tr>
				<tr>
					<td>Thu</td>
					<td style="padding: 5px 5px 5px 5px">From</td>
					<td>
						<div class="input-group date form_datetime" data-date=""
							data-date-format="hh:ii" data-link-field="dtp_input1">
							<input class="form-control" size="30" type="text"
								value="${restaurant.mThuOpenStartTime}" name="mThuOpenStartTime"
								readonly> <span class="input-group-addon"><span
								class="glyphicon glyphicon-remove"
								onclick="$('input[name=mThuOpenStartTime]').val('');"></span></span> <span
								class="input-group-addon"><span
								class="glyphicon glyphicon-th"></span></span>
						</div>
					</td>
					<td style="padding: 5px 5px 5px 5px">To</td>
					<td>
						<div class="input-group date form_datetime" data-date=""
							data-date-format="hh:ii" data-link-field="dtp_input1">
							<input class="form-control" size="30" type="text"
								value="${restaurant.mThuOpenEndTime}" name="mThuOpenEndTime"
								readonly> <span class="input-group-addon"><span
								class="glyphicon glyphicon-remove"
								onclick="$('input[name=mThuOpenEndTime]').val('');"></span></span> <span
								class="input-group-addon"><span
								class="glyphicon glyphicon-th"></span></span>
						</div>
					</td>
				</tr>
				<tr>
					<td>Fri</td>
					<td style="padding: 5px 5px 5px 5px">From</td>
					<td>
						<div class="input-group date form_datetime" data-date=""
							data-date-format="hh:ii" data-link-field="dtp_input1">
							<input class="form-control" size="30" type="text"
								value="${restaurant.mFriOpenStartTime}" name="mFriOpenStartTime"
								readonly> <span class="input-group-addon"><span
								class="glyphicon glyphicon-remove"
								onclick="$('input[name=mFriOpenStartTime]').val('');"></span></span> <span
								class="input-group-addon"><span
								class="glyphicon glyphicon-th"></span></span>
						</div>
					</td>
					<td style="padding: 5px 5px 5px 5px">To</td>
					<td>
						<div class="input-group date form_datetime" data-date=""
							data-date-format="hh:ii" data-link-field="dtp_input1">
							<input class="form-control" size="30" type="text"
								value="${restaurant.mFriOpenEndTime}" name="mFriOpenEndTime"
								readonly> <span class="input-group-addon"><span
								class="glyphicon glyphicon-remove"
								onclick="$('input[name=mFriOpenEndTime]').val('');"></span></span> <span
								class="input-group-addon"><span
								class="glyphicon glyphicon-th"></span></span>
						</div>
					</td>
				</tr>
				<tr>
					<td>Sat</td>
					<td style="padding: 5px 5px 5px 5px">From</td>
					<td>
						<div class="input-group date form_datetime" data-date=""
							data-date-format="hh:ii" data-link-field="dtp_input1">
							<input class="form-control" size="30" type="text"
								value="${restaurant.mSatOpenStartTime}" name="mSatOpenStartTime"
								readonly> <span class="input-group-addon"><span
								class="glyphicon glyphicon-remove"
								onclick="$('input[name=mSatOpenStartTime]').val('');"></span></span> <span
								class="input-group-addon"><span
								class="glyphicon glyphicon-th"></span></span>
						</div>
					</td>
					<td style="padding: 5px 5px 5px 5px">To</td>
					<td>
						<div class="input-group date form_datetime" data-date=""
							data-date-format="hh:ii" data-link-field="dtp_input1">
							<input class="form-control" size="30" type="text"
								value="${restaurant.mSatOpenEndTime}" name="mSatOpenEndTime"
								readonly> <span class="input-group-addon"><span
								class="glyphicon glyphicon-remove"
								onclick="$('input[name=mSatOpenEndTime]').val('');"></span></span> <span
								class="input-group-addon"><span
								class="glyphicon glyphicon-th"></span></span>
						</div>
					</td>
				</tr>
				<tr>
					<td>Sun</td>
					<td style="padding: 5px 5px 5px 5px">From</td>
					<td>
						<div class="input-group date form_datetime" data-date=""
							data-date-format="hh:ii" data-link-field="dtp_input1">
							<input class="form-control" size="30" type="text"
								value="${restaurant.mSunOpenStartTime}" name="mSunOpenStartTime"
								readonly> <span class="input-group-addon"><span
								class="glyphicon glyphicon-remove"
								onclick="$('input[name=mSunOpenStartTime]').val('');"></span></span> <span
								class="input-group-addon"><span
								class="glyphicon glyphicon-th"></span></span>
						</div>
					</td>
					<td style="padding: 5px 5px 5px 5px">To</td>
					<td>
						<div class="input-group date form_datetime" data-date=""
							data-date-format="hh:ii" data-link-field="dtp_input1">
							<input class="form-control" size="30" type="text"
								value="${restaurant.mSunOpenEndTime}" name="mSunOpenEndTime"
								readonly> <span class="input-group-addon"><span
								class="glyphicon glyphicon-remove"
								onclick="$('input[name=mSunOpenEndTime]').val('');"></span></span> <span
								class="input-group-addon"><span
								class="glyphicon glyphicon-th"></span></span>
						</div>
					</td>
				</tr>
			</table>
		</div>
	</div>

	<div class="form-group">
		<div class="col-sm-offset-1 col-sm-2">
			<label class="control-label">Delivery Time</label>
		</div>
		<div class="col-sm-7">
			<table>
				<tr>
					<td>Mon</td>
					<td style="padding: 5px 5px 5px 5px">From</td>
					<td>
						<div class="input-group date form_datetime" data-date=""
							data-date-format="hh:ii" data-link-field="dtp_input1">
							<input class="form-control" size="30" type="text"
								value="${restaurant.mMonDeliveryStartTime}"
								name="mMonDeliveryStartTime" readonly> <span
								class="input-group-addon"><span
								class="glyphicon glyphicon-remove"
								onclick="$('input[name=mMonDeliveryStartTime]').val('');"></span></span>
							<span class="input-group-addon"><span
								class="glyphicon glyphicon-th"></span></span>
						</div>
					</td>
					<td style="padding: 5px 5px 5px 5px">To</td>
					<td>
						<div class="input-group date form_datetime" data-date=""
							data-date-format="hh:ii" data-link-field="dtp_input1">
							<input class="form-control" size="30" type="text"
								value="${restaurant.mMonDeliveryEndTime}"
								name="mMonDeliveryEndTime" readonly> <span
								class="input-group-addon"><span
								class="glyphicon glyphicon-remove"
								onclick="$('input[name=mMonDeliveryEndTime]').val('');"></span></span>
							<span class="input-group-addon"><span
								class="glyphicon glyphicon-th"></span></span>
						</div>
					</td>
				</tr>
				<tr>
					<td>Tue</td>
					<td style="padding: 5px 5px 5px 5px">From</td>
					<td>
						<div class="input-group date form_datetime" data-date=""
							data-date-format="hh:ii" data-link-field="dtp_input1">
							<input class="form-control" size="30" type="text"
								value="${restaurant.mTueDeliveryStartTime}"
								name="mTueDeliveryStartTime" readonly> <span
								class="input-group-addon"><span
								class="glyphicon glyphicon-remove"
								onclick="$('input[name=mTueDeliveryStartTime]').val('');"></span></span>
							<span class="input-group-addon"><span
								class="glyphicon glyphicon-th"></span></span>
						</div>
					</td>
					<td style="padding: 5px 5px 5px 5px">To</td>
					<td>
						<div class="input-group date form_datetime" data-date=""
							data-date-format="hh:ii" data-link-field="dtp_input1">
							<input class="form-control" size="30" type="text"
								value="${restaurant.mTueDeliveryEndTime}"
								name="mTueDeliveryEndTime" readonly> <span
								class="input-group-addon"><span
								class="glyphicon glyphicon-remove"
								onclick="$('input[name=mTueDeliveryEndTime]').val('');"></span></span>
							<span class="input-group-addon"><span
								class="glyphicon glyphicon-th"></span></span>
						</div>
					</td>
				</tr>
				<tr>
					<td>Wed</td>
					<td style="padding: 5px 5px 5px 5px">From</td>
					<td>
						<div class="input-group date form_datetime" data-date=""
							data-date-format="hh:ii" data-link-field="dtp_input1">
							<input class="form-control" size="30" type="text"
								value="${restaurant.mWedDeliveryStartTime}"
								name="mWedDeliveryStartTime" readonly> <span
								class="input-group-addon"><span
								class="glyphicon glyphicon-remove"
								onclick="$('input[name=mWedDeliveryStartTime]').val('');"></span></span>
							<span class="input-group-addon"><span
								class="glyphicon glyphicon-th"></span></span>
						</div>
					</td>
					<td style="padding: 5px 5px 5px 5px">To</td>
					<td>
						<div class="input-group date form_datetime" data-date=""
							data-date-format="hh:ii" data-link-field="dtp_input1">
							<input class="form-control" size="30" type="text"
								value="${restaurant.mWedDeliveryEndTime}"
								name="mWedDeliveryEndTime" readonly> <span
								class="input-group-addon"><span
								class="glyphicon glyphicon-remove"
								onclick="$('input[name=mWedDeliveryEndTime]').val('');"></span></span>
							<span class="input-group-addon"><span
								class="glyphicon glyphicon-th"></span></span>
						</div>
					</td>
				</tr>
				<tr>
					<td>Thu</td>
					<td style="padding: 5px 5px 5px 5px">From</td>
					<td>
						<div class="input-group date form_datetime" data-date=""
							data-date-format="hh:ii" data-link-field="dtp_input1">
							<input class="form-control" size="30" type="text"
								value="${restaurant.mThuDeliveryStartTime}"
								name="mThuDeliveryStartTime" readonly> <span
								class="input-group-addon"><span
								class="glyphicon glyphicon-remove"
								onclick="$('input[name=mThuDeliveryStartTime]').val('');"></span></span>
							<span class="input-group-addon"><span
								class="glyphicon glyphicon-th"></span></span>
						</div>
					</td>
					<td style="padding: 5px 5px 5px 5px">To</td>
					<td>
						<div class="input-group date form_datetime" data-date=""
							data-date-format="hh:ii" data-link-field="dtp_input1">
							<input class="form-control" size="30" type="text"
								value="${restaurant.mThuDeliveryEndTime}"
								name="mThuDeliveryEndTime" readonly> <span
								class="input-group-addon"><span
								class="glyphicon glyphicon-remove"
								onclick="$('input[name=mThuDeliveryEndTime]').val('');"></span></span>
							<span class="input-group-addon"><span
								class="glyphicon glyphicon-th"></span></span>
						</div>
					</td>
				</tr>
				<tr>
					<td>Fri</td>
					<td style="padding: 5px 5px 5px 5px">From</td>
					<td>
						<div class="input-group date form_datetime" data-date=""
							data-date-format="hh:ii" data-link-field="dtp_input1">
							<input class="form-control" size="30" type="text"
								value="${restaurant.mFriDeliveryStartTime}"
								name="mFriDeliveryStartTime" readonly> <span
								class="input-group-addon"><span
								class="glyphicon glyphicon-remove"
								onclick="$('input[name=mFriDeliveryStartTime]').val('');"></span></span>
							<span class="input-group-addon"><span
								class="glyphicon glyphicon-th"></span></span>
						</div>
					</td>
					<td style="padding: 5px 5px 5px 5px">To</td>
					<td>
						<div class="input-group date form_datetime" data-date=""
							data-date-format="hh:ii" data-link-field="dtp_input1">
							<input class="form-control" size="30" type="text"
								value="${restaurant.mFriDeliveryEndTime}"
								name="mFriDeliveryEndTime" readonly> <span
								class="input-group-addon"><span
								class="glyphicon glyphicon-remove"
								onclick="$('input[name=mFriDeliveryEndTime]').val('');"></span></span>
							<span class="input-group-addon"><span
								class="glyphicon glyphicon-th"></span></span>
						</div>
					</td>
				</tr>
				<tr>
					<td>Sat</td>
					<td style="padding: 5px 5px 5px 5px">From</td>
					<td>
						<div class="input-group date form_datetime" data-date=""
							data-date-format="hh:ii" data-link-field="dtp_input1">
							<input class="form-control" size="30" type="text"
								value="${restaurant.mSatDeliveryStartTime}"
								name="mSatDeliveryStartTime" readonly> <span
								class="input-group-addon"><span
								class="glyphicon glyphicon-remove"
								onclick="$('input[name=mSatDeliveryStartTime]').val('');"></span></span>
							<span class="input-group-addon"><span
								class="glyphicon glyphicon-th"></span></span>
						</div>
					</td>
					<td style="padding: 5px 5px 5px 5px">To</td>
					<td>
						<div class="input-group date form_datetime" data-date=""
							data-date-format="hh:ii" data-link-field="dtp_input1">
							<input class="form-control" size="30" type="text"
								value="${restaurant.mSatDeliveryEndTime}"
								name="mSatDeliveryEndTime" readonly> <span
								class="input-group-addon"><span
								class="glyphicon glyphicon-remove"
								onclick="$('input[name=mSatDeliveryEndTime]').val('');"></span></span>
							<span class="input-group-addon"><span
								class="glyphicon glyphicon-th"></span></span>
						</div>
					</td>
				</tr>
				<tr>
					<td>Sun</td>
					<td style="padding: 5px 5px 5px 5px">From</td>
					<td>
						<div class="input-group date form_datetime" data-date=""
							data-date-format="hh:ii" data-link-field="dtp_input1">
							<input class="form-control" size="30" type="text"
								value="${restaurant.mSunDeliveryStartTime}"
								name="mSunDeliveryStartTime" readonly> <span
								class="input-group-addon"><span
								class="glyphicon glyphicon-remove"
								onclick="$('input[name=mSunDeliveryStartTime]').val('');"></span></span>
							<span class="input-group-addon"><span
								class="glyphicon glyphicon-th"></span></span>
						</div>
					</td>
					<td style="padding: 5px 5px 5px 5px">To</td>
					<td>
						<div class="input-group date form_datetime" data-date=""
							data-date-format="hh:ii" data-link-field="dtp_input1">
							<input class="form-control" size="30" type="text"
								value="${restaurant.mSunDeliveryEndTime}"
								name="mSunDeliveryEndTime" readonly> <span
								class="input-group-addon"><span
								class="glyphicon glyphicon-remove"
								onclick="$('input[name=mSunDeliveryEndTime]').val('');"></span></span>
							<span class="input-group-addon"><span
								class="glyphicon glyphicon-th"></span></span>
						</div>
					</td>
				</tr>
			</table>
		</div>
	</div>

	<div class="form-group">
		<div class="col-sm-offset-1 col-sm-2">
			<label for="description" class="control-label">Description</label>
		</div>
		<div class="col-sm-7">
			<textarea rows="3" class="form-control" id="description"
				name="mDescription" placeholder="Description"
				value="${restaurant.mDescription}"></textarea>
		</div>
	</div>

	<div class="form-group">
		<div class="col-sm-offset-3 col-sm-2">
			<button type="button" class="btn btn-primary" id="submitBtn">Submit</button>
		</div>
	</div>

	<input type='hidden' id='restaurantId' value='${restaurant.mId}'>
	<input type='hidden' id='dataWidth' name='width'> <input
		type='hidden' id='dataHeight' name='height'> <input
		type='hidden' id='imgUrl' name='base64Str'> <input
		type='hidden' id='latitude' name='mLatitude' value="0"> <input
		type='hidden' id='longitude' name='mLongitude' value="0"> <input
		type='hidden' id='isDelivery' name='mIsDelivery'  value="0"> <input
		type='hidden' id='isPickOut' name='mIsPickOut'  value="0"> <input
		type='hidden' id='isPreOrder' name='mIsPreOrder'  value="0">

</form>

<%@ include file="../include/bootstrap_foot.html"%>

