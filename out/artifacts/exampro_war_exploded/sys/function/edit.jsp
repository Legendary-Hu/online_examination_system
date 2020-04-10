<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>编辑系统功能</title>
	<link rel="stylesheet" type="text/css" href="css/bootstrap.css">
	<link rel="stylesheet" type="text/css" href="css/theme.css">
	<script src="js/jquery.js" type="text/javascript"></script>
</head>

<body class="content1">
	<script>
   		$('#a_leader_txt', parent.document).html('编辑系统功能');
	</script>
	<div class="container-fluid">
		<div class="row-fluid">
			<form method="post" action="FunctionServlet?obj=updateFunction">
				<div class="btn-toolbar">
					<input type="submit" class="btn btn-primary" value="保存 ">
					<a href="FunctionServlet?obj=selectAll&pageNo=1" class="btn">取消</a>

				</div>

				<div class="well">
					<div class="tab-pane active in">
						<label>
							父功能名称：
						</label>
						<input type="hidden" name="funid" value="${function.getFunid()}" />
						<input type="hidden" name="funpid" value="${function.getFunpid()}" />
						<input type="text" name="funpname" value="${function.getFunpname()}"
							readonly="readonly">
						<label>
							功能名称：
						</label>
						<input type="text" name="funname" maxlength="10"
							value="${function.getFunname()}">
						<label>
							功能地址：
						</label>
							<c:choose>
								<c:when test="${function.getFunpid()==-1}">
									<input type="text" name="funurl" value="" maxlength="100" readonly="readonly">
								</c:when>
								<c:otherwise>
									<input type="text" name="funurl" value="${function.getFunurl()}" maxlength="100">
								</c:otherwise>
							</c:choose>
						<label>
							功能状态：
						</label>
						<select name="funstate">
							<c:choose>
								<c:when test="${function.isFunstate()==true}">
									<option value="true" selected="selected">正常</option>
									<option value="false">锁定</option>
								</c:when>
								<c:otherwise>
									<option value="true" >正常</option>
									<option value="false" selected="selected">锁定</option>
								</c:otherwise>
							</c:choose>

						</select>
						<div style="color: red">
							${editfun_error}
						</div>
					</div>
				</div>
			</form>
		</div>
	</div>
</body>
</html>