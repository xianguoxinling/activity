<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>资讯列表</title>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
		<meta http-equiv="Content-Style-Type" content="text/css"/>
		<link rel="shortcut icon" href="/images/favicon.ico" type="image/x-icon"/>
			<link href="/css/store.css" rel="stylesheet" type="text/css"/>
			<link href="/css/layout.css" rel="stylesheet" type="text/css"/>
			<link href="/css/jquery.validation.css" rel="stylesheet" type="text/css"/>
			<link href="/css/ie_style.css" rel="stylesheet" type="text/css"/>
			<link rel="stylesheet" href="/css/bootstrap.css" media="screen" type="text/css"/>
			<link rel="stylesheet" href="/css/normalize.css" type="text/css"/>

			<script type="text/javascript" src="/js/jquery-2.1.0.min.js"></script>
			<script type="text/javascript" src="/js/bootstrap.js"></script>

			<script type="text/javascript">
				$(function () {
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
				<div id="header2"></div>

				<table class="cart_product">
					<tr class="bg">
						<th class="name">咨询名称</th>
						<th class="name">创建时间</th>
						<th class="edit" style="text-align: center">编辑</th>
					</tr>

					<c:forEach items="${infoList}" var="info">
						<tr>
							<td class="name"><c:out value="${info.name}"/></td>
							<td class="name"><c:out value="${info.createTime}"/></td>
							<td class="edit" style="padding: 0 8px;">
								<button class="btn btn-primary btn-sm" style="margin-top:8px" onclick="location.href='/activity/information/beginquery.action?id=${info.id}';">编辑资讯内容</button>	
								<button class="btn btn-primary btn-sm" style="margin-top:8px" onclick="location.href='/activity/information/delete.action?id=${info.id}';">删除资讯信息</button>				
							</td>
						</tr>
					</c:forEach>

				</table>

				<div id="foot"></div>
			</div>
			<script type="text/javascript" src="http://kefu.puckart.com/mibew/js/compiled/chat_popup.js"></script>
			<script type="text/javascript" src="/js/kf.js"></script>
		</body>
	</html>
