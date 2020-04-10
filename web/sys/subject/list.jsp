<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>试题功能列表</title>
	<link rel="stylesheet" type="text/css" href="css/bootstrap.css">
	<link rel="stylesheet" type="text/css" href="css/theme.css">
	<script src="js/jquery.js" type="text/javascript"></script>
</head>

<body class="content1">

	<div class="container-fluid">
		<div class="row-fluid">
			<form class="form-inline" method="post" action="SubjectServlet?obj=selectBySid&sid=${sub.getSid()}">
				<input class="input-xlarge" placeholder="功能名称..." name="scontent"
					type="text" value="">
				<input class="btn icon-search" type="submit" value="查询" />
				<a class="btn btn-primary" href="sys/subject/add.jsp"> <i class="icon-plus"></i> 新增试题</a>
			</form>
			<div class="well">
				<table class="table">
					<thead>
						<tr>
							<th>
								题目ID
							</th>
							<th>
								题干
							</th>
							<th>
								A选项内容
							</th>
							<th>
								B选项内容
							</th>
							<th>
								C选项内容
							</th>
							<th>
								D选项内容
							</th>
							<th>
								标准答案选项
							</th>
							<th>
								题目状态
							</th>
							<th style="width: 90px;">
								编辑
							</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${pageModel.dataList}" var="sub">
							<tr>
								<td>
									${sub.getSid()}
								</td>
								<td>
									${sub.getScontent()}
								</td>
								<td>
									${sub.getSa()}
								</td>
								<td>
									${sub.getSb()}
								</td>
								<td>
									${sub.getSc()}
								</td>
								<td>
										${sub.getSd()}
								</td>
								<td>
										${sub.getSkey()}
								</td>
								<td>
										<c:if test="${sub.isSstate()==true}" >正常</c:if>
										<c:if test="${sub.isSstate()==false}" >禁用</c:if>
								</td>
								<td>
									<a href="SubjectServlet?obj=selectBySid&sid=${sub.getSid()}">编辑</a>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				<div class="pagination pagination-right">
					<ul>
						<li>
							<a>共计：${pageModel.totalPage}页记录</a>
						</li>
						
						<li>
							<a href="SubjectServlet?obj=selectAll&pageNo=1">首页</a>
						</li>
						<li>
							<a href="SubjectServlet?obj=selectAll&pageNo=${pageModel.prePage}">上一页</a>
						</li>
						<li>
							<a href="SubjectServlet?obj=selectAll&pageNo=${pageModel.nextPage}">下一页</a>
						</li>
						<li>
							<a href="SubjectServlet?obj=selectAll&pageNo=${pageModel.totalPage}">末页</a>
						</li>
					</ul>
				</div>
			</div>
		</div>
	</div>
</body>
</html>