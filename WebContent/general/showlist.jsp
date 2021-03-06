<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>活动列表</title>
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
						<th class="images"></th>
						<th class="name">活动名称名称</th>
						<th class="name">开始时间</th>
						<th class="name">结束时间</th>
						<th class="name">活动状态</th>
						<th class="name">简介</th>
						<th class="edit" style="text-align: center">编辑</th>
					</tr>

					<c:forEach items="${activityList}" var="activity">
						<tr>
							<td class="images">
								<img src="${activity.main_pic}" title=""/>
							</td>
							<td class="name"><c:out value="${activity.name}"/></td>
							<td class="name"><c:out value="${activity.begin_time}"/></td>
							<td class="name"><c:out value="${activity.end_time}"/></td>
							<td class="name">
								<div >
								<span> 
								<c:choose>
								<c:when test="${activity.state == 'online'}">展示中</c:when>
								<c:when test="${activity.state == 'offline'}">未展示</c:when>
								</c:choose>
								</span> 
								</div>
							</td>
							
							
							
                            <td class="name"><c:out value="${activity.briefIntroduction}"/></td>
							<td class="edit" style="padding: 0 8px;">
							<div class="operate">
							<c:choose>
									<c:when test="${activity.state == 'online'}">
									<button class="btn btn-primary btn-sm"
											onclick="location.href='/activity/general/unshow.action?id=${activity.id}';">取消展示</button>
									</c:when>
									<c:when test="${activity.state == 'offline'}">
									<button class="btn btn-primary btn-sm"
											onclick="location.href='/activity/general/show.action?id=${activity.id}';">展示活动</button>
									</c:when>
									</c:choose>
									</div>
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
