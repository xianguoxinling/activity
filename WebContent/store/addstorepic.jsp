<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>上传商品图片</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<meta http-equiv="Content-Style-Type" content="text/css" />
<link rel="shortcut icon" href="/images/favicon.ico" type="image/x-icon"/>
	<link href="/css/store.css" rel="stylesheet" type="text/css" />
	<link href="/css/layout.css" rel="stylesheet" type="text/css" />
	<link href="/css/ie_style.css" rel="stylesheet" type="text/css" />
	<link href="/css/jquery.validation.css" rel="stylesheet"
		type="text/css" />
	<link href="/css/webuploader.css" rel="stylesheet" type="text/css" />
	<link href="/css/uploadpic.css" rel="stylesheet" type="text/css" />
	<script src="/js/jquery-2.1.0.min.js" type="text/javascript"></script>
	<script src="/js/webuploader.js" type="text/javascript"></script>
	<script type="text/javascript">
		var BASE_HTTP='/manager/FileUpload?store=storepic';
		var BASE_LABEL = '点击上传图片';
		var BASE_FILE_NUM_LIMIT = 16;
	</script>
	<script src="/js/uploadpic.js" type="text/javascript"></script>

	<link rel="stylesheet" href="/css/bootstrap.css" media="screen" />
	<script type="text/javascript" src="/js/bootstrap.js"></script>
	<script type="text/javascript">
		$(function() {
			$('#header').load('/load/header.html');
			$('#header2').load('/load/header2.html');
			$('#foot').load('/load/foot.html');
		});
	</script>
</head>

<body>
	<div id="main">
		<!-- header -->

		<div id="header"></div>
		<!-- content -->
		<div id="content">
			<div id="header2"></div>
			<div class="wrapper">
				<div class="aside">
					<ul class="nav001">
						<li><a href="/manager/store/addwxinfo.jsp" >基本信息设置</a></li>
						<li>
                        <a href="/manager/store/addpayinfo.jsp" >支付信息设置</a>
                        </li>
						<li>
                        <a href="/manager/store/addstorepic.jsp" class="current">添加店铺图片</a>
                        </li>
                        <li>
							<a href="/manager/store/queryshoppic.action">编辑店铺图片</a>
						</li>
					</ul>
					<div class="box">
						<div class="inner">
							<ul class="list1">
								<li><a href="#">微博</a></li>
								<li><a href="#">微信</a></li>
								<li><a href="#">备用链接</a></li>
								<li><a href="#">备用链接</a></li>
							</ul>
						</div>
					</div>
				</div>
				<!-- /.nav -->
				<div class="mainContent">
						<div class="line-hor"></div> <!-- <h3>将图片拖拽至此或点击上传图片</h3> -->
						<form id="addproduct" name="addproduct"
							action="/manager/store/query_main.action" method="post"
							class="validation-form-container" enctype="multipart/form-data">

<%-- 							<input type ="hidden" name="uuid" value="${p_uuid}"/> --%>
<%-- 							<input type="hidden" id="timestamp" value="<c:out value='${timestamp}' />"/> --%>
							<div class="page-container">
								<div id="uploader">
									<div class="queueList">
										<div class="placeholder">
											<div id="filePicker"></div>
											<p>或将照片拖到这里，单次最多可选10张</p>
										</div>
									</div>
									<div class="statusBar" style="display: none;">
										<div class="progress">
											<span class="text">0%</span> <span class="percentage"></span>
										</div>
										<div class="info"></div>
										<div class="btns">
											<div id="filePicker2"></div>
											<div class="uploadBtn">开始上传</div>
										</div>
									</div>
								</div>
							</div>
							<div class="">
								<button type="submit" class="btn btn-default" id="nextpage"
									name="nextpage" style="float: right;">完成</button>
								<br style="clear: both" />
							</div>
						</form>
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
