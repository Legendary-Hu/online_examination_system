<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>试题功能列表</title>
	<link rel="stylesheet" type="text/css" href="css/bootstrap.css">
	<link rel="stylesheet" type="text/css" href="css/theme.css">
	<script src="js/jquery.js" type="text/javascript"></script>
</head>

<body class="content1">
	<script>
   		$('#a_leader_txt', parent.document).html('试题详情');
	</script>
	<div class="container-fluid">
		<div class="row-fluid">
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
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${list}" var="sub">
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
							</tr>
						</c:forEach>

					</tbody>
				</table>
			</div>
		</div>
	</div>
</body>
</html>