<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<head>
	<title>试卷管理</title>
	<link rel="stylesheet" type="text/css" href="css/bootstrap.css">
	<link rel="stylesheet" type="text/css" href="css/theme.css">
	<script src="js/jquery.js" type="text/javascript"></script>
</head>

<body class="content1">
	<div class="container-fluid">
		<div class="row-fluid">
			<form class="form-inline" method="post"
				action="">
				<input class="input-xlarge" placeholder="用户名..." name="pname"
					type="text" value="">
				<input class="btn icon-search" type="submit" value="查询" />
				<a class="btn btn-primary"
					href="sys/paper/add.jsp"> <i
					class="icon-plus"></i> 新增 </a>
			</form>

			<div class="well">
				<table class="table">
					<thead>
						<tr>
							<th>
								试题名称
							</th>
							<th>
								题目数量
							</th>
							
							<th style="width: 90px;">
								操作
							</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${pageModel.dataList}" var="paper">
							<tr>
								<td>
									${paper.getPname()}
								</td>
								<td>
									${paper.getPcount()}
								</td>

								<td>
									<a href="PaperServlet?obj=selectSub&pname=${paper.getPname()}">查看试题</a>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				<div class="pagination pagination-right">
					<ul>
						<li>
							<a>共计：${pageModel.getTotalPage()}条记录</a>
						</li>
						<li>
							<a href="PaperServlet?obj=selectAll&pageNo=1">首页</a>
						</li>
						<li>
							<a href="PaperServlet?obj=selectAll&pageNo=${pageModel.getPrePage()}">上一页</a>
						</li>						
						
						<li>
							<a href="PaperServlet?obj=selectAll&pageNo=${pageModel.getNextPage()}">下一页</a>
						</li>
						<li>
							<a href="PaperServlet?obj=selectAll&pageNo=${pageModel.getTotalPage()}">末页</a>
						</li>
					</ul>
				</div>
			</div>
		</div>
	</div>
</body>
</html>