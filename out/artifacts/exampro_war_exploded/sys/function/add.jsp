<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>增加系统功能</title>
	<link rel="stylesheet" type="text/css" href="css/bootstrap.css">
	<link rel="stylesheet" type="text/css" href="css/theme.css">
	<script src="js/jquery.js" type="text/javascript"></script>
</head>

<body class="content1">
	<script>
   		$('#a_leader_txt', parent.document).html('新建系统功能');
	</script>

	<div class="container-fluid">
		<div class="row-fluid">
			<form method="post" action="FunctionServlet?obj=addFun">
				<div class="btn-toolbar">
					<input type="submit" class="btn btn-primary" value="保存 ">
					<a href="FunctionServlet?obj=selectAll&pageNo=1" class="btn">取消</a>
				</div>

				<div class="well">
					<div class="tab-pane active in">
						<label>
							父功能名称：
						</label>
							<input type="hidden" name="funpid" value="${funpid}" />
							<input type="text" name="funpname" value="${funpname}" readonly="readonly">			
						<label>
							功能名称：
						</label>
						<input type="text" name="funname" maxlength="10" required="required">
						<label>
							功能地址：
						</label>
						<c:choose>
							<c:when test="${funpid==0}">
								<input type="text" name="funurl" maxlength="100" required="required" readonly="readonly">
							</c:when>
							<c:otherwise>
								<input type="text" name="funurl" maxlength="100" requires="required">
							</c:otherwise>
						</c:choose>

						<label>
							功能状态：
						</label>
						<select name="funstate">
							<option value="true">
								正常
							</option>
							<option value="false">
								锁定
							</option>
						</select>
						<div style="color: red">
							${addfun_error}
						</div>
					</div>
				</div>
			</form>
		</div>
	</div>
</body>
</html>