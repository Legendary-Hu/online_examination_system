<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>系统功能列表</title>
	<link rel="stylesheet" type="text/css" href="css/bootstrap.css">
	<link rel="stylesheet" type="text/css" href="css/theme.css">
	<script src="js/jquery.js" type="text/javascript"></script>
</head>

<body class="content1">
	<div class="container-fluid">
		<div class="row-fluid">
			<form class="form-inline" method="post"
				action="">
				<input class="input-xlarge" placeholder="功能名称..." name="sname"
					type="text" value="">
				<input class="btn icon-search" type="submit" value="查询" />
				<a class="btn btn-primary" href="FunctionServlet?obj=toAdd&funpid=0&funpname=无">
					<i class="icon-plus"></i> 新建顶层功能
				</a>
			</form>

			<div class="well">
				<table class="table">
					<thead>
						<tr>
							<th>
								父功能
							</th>
							<th>
								功能名称
							</th>
							<th>
								功能地址
							</th>
							<th>
								功能状态
							</th>
							<th style="width: 90px;">
								编辑
							</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${pageModel.dataList}" var="fun">
							<tr>
								<td>${fun.getFunpname()}</td>
								<td>${fun.getFunname()}</td>
								<td>${fun.getFunurl()}</td>
								<td>
									<c:if test="${fun.isFunstate()==true}">正常</c:if>
									<c:if test="${fun.isFunstate()==false}">锁定</c:if>
								</td>
								<td>
									<c:choose>
										<c:when test="${fun.getFunpid()==0}">
											<a href="FunctionServlet?obj=selectByFunid&funid=${fun.getFunid()}">编辑</a>
											&ensp;
											<a href="FunctionServlet?obj=toAdd&funpid=${fun.getFunid()}&funpname=${fun.getFunname()}">子功能</a>
										</c:when>
										<c:otherwise>
											<a href="FunctionServlet?obj=selectByFunid&funid=${fun.getFunid()}">编辑</a>
										</c:otherwise>
									</c:choose>
								</td>
							</tr>
						</c:forEach>

					</tbody>
				</table>
				<div class="pagination pagination-right">
					<ul>
						<li>
							<a>共计：${pageModel.getTotalPage()}页记录</a>
						</li>
						<li>
							<a href="FunctionServlet?obj=selectAll&pageNo=1">首页</a>
						</li>
						<li>
							<a href="FunctionServlet?obj=selectAll&pageNo=${pageModel.getPrePage()}">上一页</a>
						</li>

						<li>
							<a href="FunctionServlet?obj=selectAll&pageNo=${pageModel.getNextPage()}">下一页</a>
						</li>
						<li>
							<a href="FunctionServlet?obj=selectAll&pageNo=${pageModel.getTotalPage()}">末页</a>
						</li>
					</ul>
				</div>
			</div>
		</div>
	</div>
</body>
</html>