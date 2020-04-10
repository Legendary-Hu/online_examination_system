<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>角色管理</title>
	<link rel="stylesheet" type="text/css" href="css/bootstrap.css">
	<link rel="stylesheet" type="text/css" href="css/theme.css">
	<script src="js/jquery.js" type="text/javascript"></script>
</head>

<body class="content1">
	<div class="container-fluid">
		<div class="row-fluid">
			<form class="form-inline" method="post"
				action="">
				<input class="input-xlarge" placeholder="角色名称..." name="sname"
					type="text" value="">
				<input class="btn icon-search" type="submit" value="查询" />
				<a class="btn btn-primary"
					href="sys/role/add.jsp"> <i
					class="icon-plus"></i> 新增 </a>
			</form>

			<div class="well">
				<table class="table">
					<thead>
						<tr>
							<th>
								角色名称
							</th>
							<th>
								角色状态
							</th>
							<th style="width: 90px;">
								编辑
							</th>
						</tr>
					</thead>
					<tbody>
					<c:forEach items="${pageModel.dataList}" var="role">
						<tr>
							<td>${role.getRolename()}</td>
							<td>
								<c:if test="${role.isRolestate()==true}">正常</c:if>
								<c:if test="${role.isRolestate()==false}">禁用</c:if>
							</td>
							<td>
								<a href="RoleServlet?obj=selectByRolename&rolename=${role.getRolename()}">编辑</a>
							</td>
							<td>
								<a href="RoleServlet?obj=selectFunRole&roleid=${role.getRoleid()}">权限</a>
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
							<a href="RoleServlet?obj=selectAll&pageNo=1">首页</a>
						</li>
						<li>
							<a href="RoleServlet?obj=selectAll&pageNo=${pageModel.getPrePage()}">上一页</a>
						</li>

						<li>
							<a href="RoleServlet?obj=selectAll&pageNo=${pageModel.getNextPage()}">下一页</a>
						</li>
						<li>
							<a href="RoleServlet?obj=selectAll&pageNo=${pageModel.getTotalPage()}">末页</a>
						</li>

					</ul>
				</div>
			</div>
		</div>
	</div>
</body>
</html>