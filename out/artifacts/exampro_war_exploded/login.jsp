<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" type="text/css" href="css/admin.css">
    <link rel="stylesheet" type="text/css" href="css/pintuer.css">
    <link rel="stylesheet" type="text/css" href="css/bootstrap.css">
    <title>学生登录</title>
</head>

<body>
    <div class="bg"></div>
    <div class="container">
        <div class="line bouncein">
            <div class="xs6 xm4 xs3-move xm4-move">
                <div style="height:80px;"></div>
                <div class="media media-y margin-big-bottom">
                </div>
                <form action="UserServlet?obj=login&pageNo=1" method="post" class="login-form">
                        <!--添加隐藏变量-->
                    <input type="hidden" value="login" name="obj">
                    <div class="panel loginbox">
                        <div class="text-center margin-big padding-big-top">
                            <h1>在线考试系统</h1>
                            <div class="form-top-left">
                                <font data-type="student" style="color:#96BAEE;">学生登录</font>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                &nbsp;&nbsp;&nbsp;
                                <font data-type="admin" style="color:#96BAEE;">管理员登录</font><br>
                                <br>
                                <!--错误信息提示-->
                                <p id="mes" style="color: red;">${error}</p>
                            </div>
                        </div>
                        <div class="panel-body" style="padding:30px; padding-bottom:10px; padding-top:10px;">
                            <div class="form-group">
                                <div class="field field-icon-right">
                                    <input type="text" class="input input-big" name="username" placeholder="登录账号" data-validate="required:请填写账号" />
                                    <span class="icon icon-user margin-small"></span>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="field field-icon-right">
                                    <input type="password" class="input input-big" name="userpwd" placeholder="登录密码" data-validate="required:请填写密码" />
                                    <span class="icon icon-key margin-small"></span>
                                </div>
                            </div>
                        </div>
                        <div style="padding:30px;"><button type="submit" class="button button-block bg-main text-big input-big">登录</button></div>
                    </div>
                </form>
            </div>
        </div>
    </div>
    <script src="js/jquery.js"></script>    
    <script src="js/bootstrap.js"></script>
    <script src="js/pintuer.js"></script>
    <script>
		$('.form-top-left a').click(function () {
		   $('.form-top-left').children().removeAttr("style");
		    var type = $(this).css('color', 'red').attr("data-type");
		    if (type == 'student') {
		        $(document).attr("title", "学生登录");
		        $("#mes").html('');
		        $(".login-form").attr("action", "user?cmd=stulogin");
		    }else {
		        $(document).attr("title", "管理员登录");
		        $("#register").html('');
		        $("#mes").html('');
		        $(".login-form").attr("action","user?cmd=login");
		    } 
		}); 
	</script>
</body>
</html>