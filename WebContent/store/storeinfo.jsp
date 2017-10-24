<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<html xmlns="http://www.w3.org/1999/xhtml">
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

		<head>
			<title>编辑店铺图文消息</title>
			<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
			<link rel="shortcut icon" href="/images/favicon.ico" type="image/x-icon" />
			<link rel="stylesheet" href="/css/store.css" type="text/css" />
			<link rel="stylesheet" href="/css/layout.css" type="text/css" />
			<link rel="stylesheet" href="/css/ie_style.css" type="text/css" />
			<link rel="stylesheet" type="text/css" href="/css/bootstrap.css" />
		</head>

		<body>
			<div id="main">

				<div id="header"></div>
				<div id="content">
					<div id="header2"></div>
					<div class="wrapper">
							<div class="wrapper">
								<form class="" action="/manager/store/updatedetail.action" method="post" onsubmit="return requestData()">
									<textarea id="textarea" name="detail" rows="8" cols="80" style="display:none"></textarea>
									<div id="div1" style="font-size:1em"></div>
									<div style="width:100%;text-align:center;margin-top:20px">
									<input type="submit" name="" value="提交" class="btn btn-primary btn-lg">
									</div>
 								</form> 
							</div>
						
					</div>
				</div>

				<div id="foot"></div>
			</div>

			<script type="text/javascript" src="http://kefu.puckart.com/mibew/js/compiled/chat_popup.js"></script>
			<script type="text/javascript" src="/js/kf.js"></script>
			<script type="text/javascript" src='/js/jquery-2.1.0.min.js'></script>
			<script type="text/javascript" src='/js/bootstrap.js'></script>
			<script type="text/javascript" src='/rich/wangEditor.js'></script>
			<script type="text/javascript">
			 var intro;
			 var E = window.wangEditor;
			 var editor = new E('#div1');
				$(function() {
					$.ajax({
					    url:"http://magic.puckart.com/manager/store/querydetail.action", 
					    dataType:"json",
					    async: false,
					    success:function(res){
					    	intro = res.detailedIntroduction;
					    	
					    }
					});
					
					editor.customConfig.uploadImgServer = 'http://magic.puckart.com/manager/StoreUpload';
					console.log(intro);
					editor.create();
	                if(null == intro || undefined == intro || ''==intro)
	                {
	                	editor.txt.html('');
	                }else
	                {
	                	editor.txt.html(intro);
	                }
				});
			</script>
			<script type="text/javascript">
				$(function() {
					$('#header').load('/load/header.html');
					$('#header2').load('/load/header2.html');
					$('#foot').load('/load/foot.html');
				});
			</script>
			<script type="text/javascript">
				function requestData() {
					document.getElementById('textarea').innerHTML = editor.txt.html();
					return true;
				}
	
			</script>
		</body>

	</html>
