<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>编辑试题功能</title>
	<link rel="stylesheet" type="text/css" href="css/bootstrap.css">
	<link rel="stylesheet" type="text/css" href="/css/theme.css">
	<script src="js/jquery.js" type="text/javascript"></script>
</head>

<body class="content1">
	<script>
   		$('#a_leader_txt', parent.document).html('试题编辑功能管理');
	</script>
	<div class="container-fluid">
		<div class="row-fluid">
			<form method="post" action="SubjectServlet?obj=updateSubject">
				<div class="btn-toolbar">
					<input type="submit" class="btn btn-primary" value="保存 ">
					<a href="SubjectServlet?obj=selectAll&pageNo=1" class="btn">取消</a>
				</div>
				<div class="well">
					<div class="tab-pane active in">
						<label>
							题干：
						</label>
						<input type="hidden" name="sid" value="${subject.getSid()}" />
						<input type="text" name="scontent" value="${subject.getScontent()}"
						maxlength="100"/>
						<label>
							A选项内容：
						</label>
						<input type="text" name="sa" maxlength="100"
							value="${subject.getSa()}">
						<label>
							B选项内容：
						</label>
						<input type="text" name="sb" maxlength="100"
							value="${subject.getSb()}">
						<label>
							C选项内容：
						</label>
						<input type="text" name="sc" maxlength="100"
							value="${subject.getSc()}">
						<label>
							D选项内容：
						</label>
						<input type="text" name="sd" maxlength="100"
							value="${subject.getSd()}">
						<label>
							标准答案选项：
						</label>
						<input type="text" name="skey" maxlength="100"
							value="${subject.getSkey()}">
						<label>
							试题状态：
						</label>
						<select name="sstate">
							<c:choose>
								<c:when test="${subject.isSstate() ==true}">
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
							${error}
						</div>
					</div>
				</div>
			</form>
		</div>
	</div>
</body>
</html>