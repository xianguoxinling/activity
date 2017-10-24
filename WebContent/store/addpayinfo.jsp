<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html xmlns="http://www.w3.org/1999/xhtml">
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
	<head>
		<title>添加小程序信息</title>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
		<link rel="shortcut icon" href="/images/favicon.ico" type="image/x-icon" />
			<link rel="stylesheet" href="/css/store.css" type="text/css"/>
			<link rel="stylesheet" href="/css/layout.css" type="text/css"/>
			<link rel="stylesheet" href="/css/jquery.validation.css" type="text/css"/>
			<link rel="stylesheet" href="/css/ie_style.css" type="text/css"/>
			<link rel="stylesheet" href="/css/bootstrap.css" media="screen" type="text/css"/>

			<script type="text/javascript" src="/js/jquery-2.1.0.min.js"></script>
			<script type="text/javascript" src="/js/easyform.js"></script>
			<script type="text/javascript" src="/js/bootstrap.js"></script>
			<script type="text/javascript" src="/js/classify.js"></script>

			<script type="text/javascript">
				$(function () {
					$('#header').load('/load/header.html');
					$('#header2').load('/load/header2.html');
					$('#foot').load('/load/foot.html');
				});

				$(document).ready(function () {
					$('#addproduct').easyform();
				});

				$(function () {
					$.post("http://magic.puckart.com/manager/store/querywxpayinfo.action", function (data) {
						if(data.code == 10001){
							return;
						}
						if(data.code == 10002){
							return;
						}
						else if(data.code == 10003){
							alert("登陆超时，请重新登陆！");
							window.location.href="http://magic.puckart.com/manager/store/storelogin.jsp";
							return;
						}
						else if(data.code == 10004){
							return;
						}
						else{
							$("#name").val(data.body);
							$("#app_id").val(data.APP_ID);
							$("#mch_id").val(data.MCH_ID);
							$("#api_key").val(data.API_KEY);
						}
					},"json");
				});
			</script>
		</head>

		<body>
			<div id="main">
				<!-- header -->

				<div id="header"></div>
				<div id="content">
					<div id="header2"></div>
					<div class="wrapper">
						<div class="aside">
							<ul class="nav001">
								<li>
									<a href="/manager/store/addwxinfo.jsp">基本信息设置</a>
								</li>
								<li>
									<a href="/manager/store/addpayinfo.jsp" class="current">支付信息设置</a>
								</li>
								<li>
									<a href="/manager/store/addstorepic.jsp">添加店铺图片</a>
								</li>
								<li>
									<a href="/manager/store/queryshoppic.action">编辑店铺图片</a>
								</li>
								<div class="box">
									<div class="inner">
										<ul class="list1">
											<li>
												<a href="#">微博</a>
											</li>
											<li>
												<a href="#">微信</a>
											</li>
											<li>
												<a href="#">备用链接</a>
											</li>
											<li>
												<a href="#">备用链接</a>
											</li>
										</ul>
									</div>
								</div>
							</div>
							<!-- /.nav -->
							<div class="mainContent">
								<!-- 				<h2>添加微信支付信息</h2> -->
								<div class="txt1"></div>
								<div class="line-hor"></div>
								<div class="wrapper">
									<form id="addproduct" action="/manager/store/createpayinfo.action" method="post" class="validation-form-container" enctype="multipart/form-data">

										<div class="field">
											<label>商户名称</label>
											<div class="ui left labeled input">
												<input id="name" name="name" type="text" easyform="length:1-255;real-time;" message="请使用标准中英文字符" easytip="disappear:lost-focus;theme:blue;"/>

												<div class="ui corner label">
													<i class="asterisk icon">*</i>
												</div>
											</div>
										</div>

										<div class="field">
											<label>AppID(小程序ID)</label>
											<div class="ui left labeled input">
												<input id="app_id" name="app_id" type="text" easyform="length:1-255;real-time;" message="请使用标准中英文字符" easytip="disappear:lost-focus;theme:blue;"/>

												<div class="ui corner label">
													<i class="asterisk icon">*</i>
												</div>
											</div>
										</div>

										<div class="field">
											<label>mch_id(商户号)</label>
											<div class="ui left labeled input">
												<input id="mch_id" name="mch_id" type="text" easyform="length:2-100;real-time;" message="请使用标准中英文字符" easytip="disappear:lost-focus;theme:blue;"/>

												<div class="ui corner label">
													<i class="asterisk icon">*</i>
												</div>
											</div>
										</div>

										<div class="field">
											<label>ApiKey(微信商户平台api安全密钥)</label>
											<div class="ui left labeled input">
												<input id="api_key" name="api_key" type="text" easyform="length:2-100;real-time;" message="请使用标准中英文字符" easytip="disappear:lost-focus;theme:blue;"/>
												<div class="ui corner label">
													<i class="asterisk icon">*</i>
												</div>
											</div>
										</div>

										<input value="提交" type="submit" class="btn btn-default" name="nextpage" style="float: right;">
											<br style="clear: both"/>
										</form>

									</div>
								</div>
							</div>
						</div>
						<!-- footer -->
						<div id="foot"></div>
					</div>

					<script type="text/javascript" src="http://kefu.puckart.com/mibew/js/compiled/chat_popup.js"></script>
					<script type="text/javascript" src="/js/kf.js"></script>
				</body>
			</html>
