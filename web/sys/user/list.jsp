<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<head>
	<title>用户管理</title>
	<link rel="stylesheet" type="text/css" href="css/bootstrap.css">
	<link rel="stylesheet" type="text/css" href="css/theme.css">
	<script src="js/jquery.js" type="text/javascript"></script>
</head>

<body class="content1">
	<div class="container-fluid">
		<div class="row-fluid">
			<form class="form-inline" method="post"
				action="">
				<input class="input-xlarge" placeholder="用户名..." name="sname"
					type="text" value="">
				<input class="btn icon-search" type="submit" value="查询" />
				<a class="btn btn-primary"
					href="UserServlet?obj=selectRole"> <i
					class="icon-plus"></i> 新增 </a>
			</form>
			<div class="well">
				<table class="table">
					<thead>
						<tr>
							<th>
								角色ID
							</th>
							<th>
								用户名
							</th>
							<th>
								用户密码
							</th>
							<th>
								用户真实名字
							</th>
							<th>
								用户状态
							</th>
							<th style="width: 90px;">
								编辑
							</th>
						</tr>
					</thead>
					<tbody>
					<c:forEach items="${pageModel.dataList}" var="user">
						<tr>
							<td>${user.getRoleid()}</td>
							<td>${user.getUsername()}</td>
							<td>${user.getUserpwd()}</td>
							<td>${user.getUsertruename()}</td>
							<td>
								<c:if test="${user.isUserstate()==true}">正常</c:if>
								<c:if test="${user.isUserstate()==false}">禁用</c:if>

							</td>
							<td>
								<a href="UserServlet?obj=selectByUsername&username=${user.getUsername()}">编辑</a>
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
							<a href="UserServlet?obj=selectAll&pageNo=1">首页</a>
						</li>
						<li>
							<a href="UserServlet?obj=selectAll&pageNo=${pageModel.getPrePage()}">上一页</a>
						</li>

						<li>
							<a href="UserServlet?obj=selectAll&pageNo=${pageModel.getNextPage()}">下一页</a>
						</li>
						<li>
							<a href="UserServlet?obj=selectAll&pageNo=${pageModel.getTotalPage()}">末页</a>
						</li>
						</li>
					</ul>
				</div>
			</div>
		</div>
	</div>
</body>
</html>