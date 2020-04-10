<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<head>
	<title>编辑用户</title>
	<link rel="stylesheet" type="text/css" href="css/bootstrap.css">
	<link rel="stylesheet" type="text/css" href="css/theme.css">
	<script src="js/jquery.js" type="text/javascript"></script>
</head>

<body class="content1">
	<script>
   		$('#a_leader_txt', parent.document).html('编辑用户');
	</script>

	<div class="container-fluid">
		<div class="row-fluid">
			<form method="post" action="UserServlet?obj=updateUser">
				<div class="btn-toolbar">
					<input type="submit" class="btn btn-primary" value="保存 ">
					<a href="UserServlet?obj=selectAll&pageNo=1" class="btn">取消</a>

				</div>

				<div class="well">
					<div class="tab-pane active in">
						<label>
							用户名：
						</label>
						<input type="hidden" name="userid" value="${user.getUserid()}" />
						
						<input type="text" name="username" value="${user.getUsername()}"
							readonly="readonly">
						<label>
							角色类型：
						</label>
						<select name="roleid">
							<c:forEach items="${Rolelist}" var="role">
								<c:choose>
									<c:when test="${role.getRoleid()!=user.getRoleid()}">
										<option value="${role.getRoleid()}" >${role.getRolename()}</option>
									</c:when>
									<c:otherwise>
										<option value="${role.getRoleid()}" selected="selected" >${role.getRolename()}</option>
									</c:otherwise>
								</c:choose>
							</c:forEach>
						</select>

						<label>
							用户密码：
						</label>
						<input type="text" name="userpwd" maxlength="16"
							value="${user.getUserpwd()}">
						<label>
							用户真实名字：
						</label>
						<input type="text" name="usertruename" value="${user.getUsertruename()}"
							maxlength="100">
						<label>
							用户状态：
						</label>
						<select name="userstate">
							<c:choose>
								<c:when test="${user.isUserstate() ==true}">
									<option value="true" selected="selected">正常</option>
									<option value="false">锁定</option>
								</c:when>
								<c:otherwise>
									<option value="true">正常</option>
									<option value="false" selected="selected">锁定</option>
								</c:otherwise>
							</c:choose>
						</select>
						<div style="color: red">
							${error4}
						</div>
					</div>
				</div>
			</form>
		</div>
	</div>
</body>
</html>