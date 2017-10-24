<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<head>
<title>创建活动</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="shortcut icon" href="/images/favicon.ico" type="image/x-icon" />
<link rel="stylesheet" href="/css/store.css" type="text/css" />
<link rel="stylesheet" href="/css/layout.css" type="text/css" />
<link rel="stylesheet" href="/css/jquery.validation.css" type="text/css" />
<link rel="stylesheet" href="/css/ie_style.css" type="text/css" />
<link rel="stylesheet" href="/css/bootstrap.css" media="screen" type="text/css" />
<link href="/css/jquery.datetimepicker.css" rel="stylesheet" />
<style>
.input-group{
position: relative;
    display: table;
    border-collapse: separate;
}
</style>			
<script type="text/javascript" src="/js/jquery-2.1.0.min.js"></script>
<script type="text/javascript" src="/js/easyform.js"></script>
<script type="text/javascript" src="/js/bootstrap.js"></script>
<script type="text/javascript" src="/js/classify.js"></script>

<script type="text/javascript">
	$(function() {
		$('#header').load('/load/header.html');
		$('#header2').load('/load/header2.html');
		$('#foot').load('/load/foot.html');
	});

// 	$(document).ready(function() {
// 		$('#addproduct').easyform();
// 	});
</script>
</head>

<body>

	<div id="main">
		<div id="header"></div>
		<div id="content">
			<div id="header2"></div>
			<div class="wrapper">
				<div class="aside">
					<ul class="nav001">
						<li><a class="current">创建活动</a></li>
						<li>
						<div>添加活动图文详情</div>
						</li>
						<li>
						<div>添加活动商品</div>
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

				<div class="mainContent">
					<div class="txt1"></div>
					<div class="line-hor"></div>

					<div class="wrapper">
						<form id="addproduct" action="/activity/general/create.action"
							method="post" class="validation-form-container"
							enctype="multipart/form-data">

							<div class="field">
								<label>活动名称</label>

								<div class="ui left labeled input">
									<input id="art_name" name="name" type="text"
										easyform="length:1-255;real-time;" message="请使用标准中英文字符"
										easytip="disappear:lost-focus;theme:blue;" />

									<div class="ui corner label">
										<i class="asterisk icon">*</i>
									</div>
								</div>
							</div>
							<div class="field input-group">
							
							<input class="form-control" id="begin_time" name="begin_time"
								placeholder="开始时间" type="text" />
							<span class="input-group-addon">至</span>
							<input class="form-control" id="end_time" name="end_time"
								placeholder="结束时间" type="text" />
							</div>


							<div class="field">
								<label>活动描述</label>

								<div class="ui left labeled input">
									<textarea name="brief" id="description"
										style="height: 170px;"></textarea>

									<div class="ui corner label">
										<i class="asterisk icon">*</i>
									</div>
								</div>
							</div>

                            <div class="field">
								<label>活动封面</label>
								<div class="ui left labeled input">
									<input type="file" name="file"></input>
								</div>
							</div>
							
							<input value="下一步" type="submit" class="btn btn-default"
								name="nextpage" style="float: right;"> <br
								style="clear: both" />
						</form>

					</div>
				</div>
			</div>
		</div>
		<!-- footer -->
		<div id="foot"></div>
	</div>

	<script type="text/javascript"
		src="http://kefu.puckart.com/mibew/js/compiled/chat_popup.js"></script>
	<script type="text/javascript" src="/js/kf.js"></script>
	<script src="/js/jquery.datetimepicker.full.js"></script>
	<script type="text/javascript">
		$('#begin_time').datetimepicker({
			format : "Y-m-d",
			timepicker : false
		});
		$('#end_time').datetimepicker({
			format : "Y-m-d",
			timepicker : false
		});
		$.datetimepicker.setLocale('ch');
	</script>
</body>

</html>
