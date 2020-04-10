<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>新增试卷</title>
	<link rel="stylesheet" type="text/css" href="../../css/bootstrap.css">
	<link rel="stylesheet" type="text/css" href="../../css/theme.css">
	<script src="../../js/jquery.js" type="text/javascript"></script>
</head>

<body class="content1">
	<script>
   		$('#a_leader_txt', parent.document).html('新增试卷');
	</script>
	<div class="container-fluid">
		<div class="row-fluid">
			<form method="post" action="../../PaperServlet?obj=addPaper">
				<div class="btn-toolbar">
					<input type="submit" class="btn btn-primary" value="保存 ">
					<a href="../../PaperServlet?obj=selectAll&pageNo=1" class="btn">取消</a>
				</div>
				<div class="well">
					<div class="tab-pane active in">
						<label>
							试卷名称：
						</label>
						<input type="text" name="pname" maxlength="10">
						<label>
							试题数量：
						</label>
						<input type="text" name="scount" maxlength="10">
						<div style="color: red">
							${error2}
						</div>
					</div>
				</div>
			</form>
		</div>
	</div>
</body>
</html>