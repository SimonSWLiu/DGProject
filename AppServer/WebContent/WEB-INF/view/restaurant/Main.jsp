<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page isELIgnored="false"%>
<%@ include file="../include/bootstrap_head.html"%>

<!-- MetisMenu CSS -->
<link
	href="${pageContext.request.contextPath }/startbootstrap/bower_components/metisMenu/dist/metisMenu.min.css"
	rel="stylesheet">

<!-- Timeline CSS -->
<link
	href="${pageContext.request.contextPath }/startbootstrap/dist/css/timeline.css"
	rel="stylesheet">

<!-- Custom CSS -->
<link
	href="${pageContext.request.contextPath }/startbootstrap/dist/css/sb-admin-2.css"
	rel="stylesheet">

<!-- Morris Charts CSS -->
<link
	href="${pageContext.request.contextPath }/startbootstrap/bower_components/morrisjs/morris.css"
	rel="stylesheet">

<!-- Custom Fonts -->
<link
	href="${pageContext.request.contextPath }/startbootstrap/bower_components/font-awesome/css/font-awesome.min.css"
	rel="stylesheet" type="text/css">


</head>

<body>

	<div id="wrapper">

		<!-- Navigation -->
		<nav class="navbar navbar-default navbar-static-top" role="navigation"
			style="margin-bottom: 0">
			<div class="navbar-header">
				<a class="navbar-brand" target="mainframe" style="cursor: pointer;"
					href="../orderController/showOrderFormList">One menu</a>
			</div>
			<!-- /.navbar-header -->

			<ul class="nav navbar-top-links navbar-right">
			
			<!-- /.dropdown 
			<li class="dropdown">
                    <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                        <i class="fa fa-bell fa-fw"></i>  <i class="fa fa-caret-down"></i>
                    </a>
                    <ul class="dropdown-menu dropdown-alerts">
                        <li>
                            <a href="#">
                                <div>
                                    <i class="fa fa-comment fa-fw"></i> New Comment
                                    <span class="pull-right text-muted small">4 minutes ago</span>
                                </div>
                            </a>
                        </li>
                        <li class="divider"></li>
                        <li>
                            <a href="#">
                                <div>
                                    <i class="fa fa-twitter fa-fw"></i> 3 New Followers
                                    <span class="pull-right text-muted small">12 minutes ago</span>
                                </div>
                            </a>
                        </li>
                        <li class="divider"></li>
                        <li>
                            <a href="#">
                                <div>
                                    <i class="fa fa-envelope fa-fw"></i> Message Sent
                                    <span class="pull-right text-muted small">4 minutes ago</span>
                                </div>
                            </a>
                        </li>
                        <li class="divider"></li>
                        <li>
                            <a href="#">
                                <div>
                                    <i class="fa fa-tasks fa-fw"></i> New Task
                                    <span class="pull-right text-muted small">4 minutes ago</span>
                                </div>
                            </a>
                        </li>
                        <li class="divider"></li>
                        <li>
                            <a href="#">
                                <div>
                                    <i class="fa fa-upload fa-fw"></i> Server Rebooted
                                    <span class="pull-right text-muted small">4 minutes ago</span>
                                </div>
                            </a>
                        </li>
                        <li class="divider"></li>
                        <li>
                            <a class="text-center" href="#">
                                <strong>See All Alerts</strong>
                                <i class="fa fa-angle-right"></i>
                            </a>
                        </li>
                    </ul>
                </li>
                /.dropdown -->


				<!-- /.dropdown -->
				<li class="dropdown"><a class="dropdown-toggle"
					data-toggle="dropdown" href="#"> <i class="fa fa-user fa-fw"></i>
						<i class="fa fa-caret-down"></i>
				</a>
					<ul class="dropdown-menu dropdown-user">
						<!-- 
                    	<li>
                    	<a target="mainframe" style="cursor: pointer;" href="../loginController/showMerchantRegistrationPage" ><i class="fa fa-user fa-fw"></i> Merchant Profile</a>
                        </li>
                         -->
						<li><a target="mainframe" style="cursor: pointer;"
							href="../menuController/showRestaurantProfilePage"><i
								class="fa fa-user fa-fw"></i> Restaurant Profile</a></li>
						<li><a target="mainframe" style="cursor: pointer;"
							href="../menuController/showSettingsPage"><i
								class="fa fa-gear fa-fw"></i> Settings</a></li>
						<li><a target="mainframe" style="cursor: pointer;"
							href="../menuController/showChangePasswordPage"><i
								class="fa fa-star fa-fw"></i> Change Password</a></li>
						<li class="divider"></li>
						<li><a href="../loginController/logout"><i
								class="fa fa-sign-out fa-fw"></i> Logout</a></li>
					</ul> <!-- /.dropdown-user --></li>
				<!-- /.dropdown -->
					
			</ul>
			<!-- /.navbar-top-links -->

			<div class="navbar-default sidebar" role="navigation">
				<div class="sidebar-nav navbar-collapse">
					<ul class="nav" id="side-menu">
						<li><a target="mainframe" style="cursor: pointer;"
							href="../deliveryController/showCallDelivery">Delivery</a></li>
						<li><a target="mainframe" style="cursor: pointer;"
							href="../orderController/showOrderFormList">Orders</a></li>
						<li><a target="mainframe" style="cursor: pointer;"
							href="../menuController/showDishCategoryEditPage">Dish
								Category</a></li>
						<li><a target="mainframe" style="cursor: pointer;"
							href="../menuController/showMenuListPage">Menu Items</a></li>
						<li><a target="mainframe" style="cursor: pointer;"
							href="../menuController/showCouponListPage">Coupon</a></li>
						<li><a target="mainframe" style="cursor: pointer;"
							href="../menuController/showStatementPage">Statement</a></li>
					</ul>
				</div>
				<!-- /.sidebar-collapse -->
			</div>
			<!-- /.navbar-static-side -->
		</nav>

		<div id="page-wrapper">
			<iframe frameborder="no" style="border: none; margin: 0; padding: 0;"
				name="mainframe" id="mainframe" width="100%" scrolling="auto"
				src="../deliveryController/showCallDelivery"
				onload="adjustIFramesHeightOnLoad(this);"></iframe>
		</div>
		<!-- /#page-wrapper -->

	</div>

	<div style="width: 100%; text-align: center;">
		<div>Copyright by&nbsp;&copy;&nbsp;One Menu</div>
		<div>
			Technical Support:&nbsp;<a href="#" target="_blank">One Menu
				Development Team</a>
		</div>
	</div>

	<!-- /#wrapper -->

	<!-- jQuery -->
	<%-- <script src="${pageContext.request.contextPath }/startbootstrap/bower_components/jquery/dist/jquery.min.js"></script> --%>

	<!-- Bootstrap Core JavaScript -->
	<%-- <script src="${pageContext.request.contextPath }/startbootstrap/bower_components/bootstrap/dist/js/bootstrap.min.js"></script> --%>

	<!-- Metis Menu Plugin JavaScript -->
	<script
		src="${pageContext.request.contextPath }/startbootstrap/bower_components/metisMenu/dist/metisMenu.min.js"></script>

	<!-- Morris Charts JavaScript -->
	<script
		src="${pageContext.request.contextPath }/startbootstrap/bower_components/raphael/raphael-min.js"></script>
	<script
		src="${pageContext.request.contextPath }/startbootstrap/bower_components/morrisjs/morris.min.js"></script>

	<!-- Custom Theme JavaScript -->
	<script
		src="${pageContext.request.contextPath }/startbootstrap/dist/js/sb-admin-2.js"></script>

	<script language="javascript" type="text/javascript">
		//Auto adjust iframe height
		function adjustIFramesHeightOnLoad(iframe) {
			var iframeHeight = Math
					.min(
							iframe.contentWindow.window.document.documentElement.scrollHeight,
							iframe.contentWindow.window.document.body.scrollHeight) + 500;
			$(iframe).height(iframeHeight);
		}

		$(document).ready(function() {

		});
	</script>

</body>
</html>
