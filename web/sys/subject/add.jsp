<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>增加试题功能</title>
	<link rel="stylesheet" type="text/css" href="../../css/bootstrap.css">
	<link rel="stylesheet" type="text/css" href="../../css/theme.css">
	<script src="../../js/jquery.js" type="text/javascript"></script>
</head>

<body class="content1">
	<script>
   		$('#a_leader_txt', parent.document).html('添加试题功能');
	</script>
	<div class="container-fluid">
		<div class="row-fluid">
			<form method="post" action="../../SubjectServlet?obj=addSubject">
				<div class="btn-toolbar">
					<input type="submit" class="btn btn-primary" value="保存 ">
					<a href="../../SubjectServlet?obj=selectAll&pageNo=1" class="btn">取消</a>
				</div>
				<div class="well">
					<div class="tab-pane active in">
						<label>
							题干：
						</label>
						<input type="text" name="scontent" maxlength="100">
						
						<label>
							A选项内容：
						</label>
						<input type="text" name="sa" maxlength="100">
						<label>
							B选项内容：
						</label>
						<input type="text" name="sb" maxlength="100">
						<label>
							C选项内容：
						</label>
						<input type="text" name="sc" maxlength="100">
						<label>
							D选项内容：
						</label>
						<input type="text" name="sd" maxlength="100">
						<label>
							标准答案选项：
						</label>
						<input type="text" name="skey" maxlength="10">
						<label>
							试题状态：
						</label>
						<select name="sstate">
							<option value="1">
								正常
							</option>
							<option value="0">
								锁定
							</option>
						</select>
						<div style="color: red">
							${subject_add_error}
						</div>
					</div>
				</div>
			</form>
		</div>
	</div>
</body>
</html>