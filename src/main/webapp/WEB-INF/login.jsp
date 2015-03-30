<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>海锚门禁管理系统</title>
<link href="themes/css/login.css" rel="stylesheet" type="text/css" />
</head>

<body>
	<div id="login">
		<div id="login_header">
			<h1 class="login_logo">
				<a href="http://demo.dwzjs.com"> <img src="" />
				</a>
			</h1>
			<div class="login_headerContent">
				<div class="navList">
					<ul>
						<li><a href="#">设为首页</a></li>
						<li><a href="#">反馈</a></li>
						<li><a href="#">帮助</a></li>
					</ul>
				</div>
				<h2 class="login_title">
					<img src="" />
				</h2>
			</div>
		</div>
		<div id="login_content">
			<div class="loginForm">
				<form action="index" method="post">
					<p>
						<label>用户名：</label> <input type="text" name="account" size="20"
							class="login_input" />
					</p>
					<p>
						<label>密码：</label> <input type="password" name="password"
							size="20" class="login_input" />
					</p>
					<!--  <p>
						<label>验证码：</label>
						<input class="code" type="text" size="5" />
						<span><img src="" alt="" width="75" height="24" /></span>
					</p>-->
					<div class="login_bar">
						<input class="sub" type="submit" value=" " />
					</div>
				</form>
			</div>
			<div class="login_banner">
				<img src="" />
			</div>
			<div class="login_main">
				<ul class="helpList">
					<li><a href="#"></a></li>
					<li><a href="#"></a></li>
					<li><a href="#"></a></li>
					<li><a href="#"></a></li>
				</ul>
				<div class="login_inner">
					<p></p>
					<p></p>
					<p></p>
				</div>
			</div>
		</div>
		<div id="login_footer">Copyright &copy; 2015 海锚 Inc. All Rights
			Reserved.</div>
	</div>
</body>
</html>