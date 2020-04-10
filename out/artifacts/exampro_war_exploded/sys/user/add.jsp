<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<head>
	<title>新增用户</title>
	<link rel="stylesheet" type="text/css" href="css/bootstrap.css">
	<link rel="stylesheet" type="text/css" href="css/theme.css">
	<script src="js/jquery.js" type="text/javascript"></script>
</head>

<body class="content1">
	<script>
   		$('#a_leader_txt', parent.document).html('新增用户');
	</script>
	<div class="container-fluid">
		<div class="row-fluid">
			<form method="post" action="UserServlet?obj=addUser">
				<div class="btn-toolbar">
					<input type="submit" class="btn btn-primary" value="保存 ">
					<a href="UserServlet?obj=selectAll&pageNo=1" class="btn">取消</a>
				</div>
				<div class="well">
					<div class="tab-pane active in">
						<label>
							用户角色：
						</label>
						<select name="roleid">
							<c:forEach items="${Rolelist}" var="role">
								<option value="${role.getRoleid()}" >${role.getRolename()}</option>
							</c:forEach>
						</select>
						<label>
							用户账号：
						</label>
						<input type="text" name="username" maxlength="10" required="required">
						<label>
							用户密码：
						</label>
						<input type="password" name="userpwd" maxlength="100"  required="required">
						<label>
							用户姓名：
						</label>
						<input type="text" name="usertruename" maxlength="10"  required="required">
						
						<label>
							用户状态：
						</label>
						<select name="userstate">
							<option value="true">
								正常
							</option>
							<option value="false">
								锁定
							</option>
						</select>
						<div style="color: red">
							${error3}
						</div>
					</div>
				</div>
			</form>
		</div>
	</div>
</body>
</html>