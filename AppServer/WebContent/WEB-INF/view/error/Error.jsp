<%@ page language="java" contentType="text/html; charset=UTF-8"
	isErrorPage="true" pageEncoding="UTF-8"%>
<%@ page import="java.io.*,java.util.*"%>
<%@page isELIgnored="false"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>Abnormal operation</title>
</head>
<body>
	Sorry, the server is busy! Please try again later.
	<br /> Or you can...
	<br />
	<a href="javascript:history.go(-1)">To return to the previous page</a>
	<hr>
	<div style="display: none;">
		<h2>
			<font color=#DB1260>Error Page</font>
		</h2>
		<% Exception e = (Exception) request.getAttribute("exception"); %>
		<p>
			An exception was thrown: <b> <%=e.getClass()%>:<%=e.getMessage()%></b>
		</p>
		访问路径:
		<%=request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/" + request.getAttribute("javax.servlet.forward.request_uri") %><br>
		Servlet路径:
		<%=request.getAttribute("javax.servlet.forward.servlet_path") %>

		<p>With the following stack trace:</p>
		<pre>
<%
	//exception.printStackTrace();
	ByteArrayOutputStream ostr = new ByteArrayOutputStream();
	e.printStackTrace(new PrintStream(ostr));
	out.print(ostr);
%>
</pre>
	</div>
</body>