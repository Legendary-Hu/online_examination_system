<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>角色权限分配</title>
	<link rel="stylesheet" type="text/css" href="css/bootstrap.css">
	<link rel="stylesheet" type="text/css" href="css/theme.css">
	<script src="js/jquery.js" type="text/javascript"></script>
	<script>
	function selectparent(obj){
		var ma = $(obj).attr("ma");
		
		var objs = $("[parent='"+ma+"']");
		if($(obj).attr("checked")=="checked"){
			for(var i=0;i<objs.length;i++){
				$(objs[i]).attr("checked","checked");
			}
		}else{
			for(var i=0;i<objs.length;i++){
				$(objs[i]).removeAttr("checked");
			}
		}
	}
	
	function selectchild(obj){
		var parent = $(obj).attr("parent");
		var objs = $("[ma='"+parent+"']");
		if($(obj).attr("checked")=="checked"){
			objs.attr("checked","checked");
		}else{
			var children =  $("[parent='"+parent+"']");
			var bl = false;
			for(var i=0;i<children.length;i++){
				if($(children[i]).attr("checked")=="checked"){
					bl = true;
					break;
				}
			}
			if(!bl){
				objs.removeAttr("checked");
			}
		}
	}
	
	</script>
</head>

<body class="content1">
	<script>
   		$('#a_leader_txt', parent.document).html('角色权限分配(${role.rolename()})');
	</script>
	

	<div class="container-fluid">
		<div class="row-fluid">
			<form method="post" action="">
				<div class="btn-toolbar">
					<input type="submit" class="btn btn-primary" value="保存 ">
					<a href="RoleServlet?obj=selectAll&pageNo=1" class="btn">取消</a>
					<input type="hidden" name="roleid" value=""/>
					<div class="btn-group">
					</div>
				</div>
				<div class="well">
					<c:forEach items="${list}" var="fun">
						<c:if test="${fun.getFunpid()==0}">
							<div class="toc">
								<h3>
									<input type="checkbox" onclick="selectparent(this)" ma="${fun.getFunid()}" name="ckrr" value="${fun.getFunid()}" <c:if test="${fun.isRr()==true}">checked="checked"</c:if> />
										${fun.getFunname()}
								</h3>
								<ul>
									<c:forEach items="${list}" var="child">
										<c:if test="${child.getFunpid()==fun.getFunid()}">
											<li>
												<input type="checkbox" onclick="selectchild(this)" parent="${fun.getFunid()}" name="ckrr" value="${child.getFunid()}" <c:if test="${fun.isRr()==true}">checked="checked"</c:if>/>
												${child.getFunname()}
											</li>
										</c:if>
									</c:forEach>

								</ul>
							</div>
						</c:if>
					</c:forEach>

				</div>
			</form>
		</div>
	</div>
</body>
</html>