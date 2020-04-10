<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <base target="main" />
    <title>欢迎使用在线考试管理系统</title>
    <link rel="stylesheet" href="css/pintuer.css">
    <link rel="stylesheet" href="css/admin.css">
    <script src="js/jquery.js"></script>
</head>
<body style="background-color:#f2f9fd;">
    <div class="header bg-main">
        <div class="logo margin-big-left fadein-top">
            <h1><img src="css/images/y.jpg" class="radius-circle rotate-hover" height="50" alt="" />后台管理中心</h1>
        </div>
        <div class="head-l">
            <a href="javascript:void(0);" class="button button-little bg-blue"><span class="icon-user"></span> ${user.getUsername()}(${user.getRolename()})</a> &nbsp;&nbsp;
            <a class="button button-little bg-red" href="UserServlet?obj=logout" target="_self"><span class="icon-power-off"></span> 退出登录</a>
        </div>
    </div>
    <div class="leftnav">
        <div class="leftnav-title"><strong><span class="icon-list"></span>菜单列表</strong></div>
        <h2><span class="icon-briefcase"></span>系统功能</h2>
        <ul id="error-menu" class="nav nav-list collapse" style="display:block">
             <li>
                 <a href="FunctionServlet?obj=selectAll&pageNo=1" target="right"><span class="icon-caret-right"></span>系统功能管理</a>
             </li>
             <li>
                 <a href="UserServlet?obj=selectAll&pageNo=1" target="right"><span class="icon-caret-right"></span>用户管理</a>
             </li>
             <li>
                 <a href="RoleServlet?obj=selectAll&pageNo=1" target="right"><span class="icon-caret-right"></span>角色管理</a>
             </li>
        </ul>
        <h2><span class="icon-briefcase"></span>试题管理</h2>
        <ul id="error-menu" class="nav nav-list collapse" style="display:block">
             <li>
                 <a href="SubjectServlet?obj=selectAll&pageNo=1" target="right"><span class="icon-caret-right"></span>题目管理</a>
             </li>
             <li>
                 <a href="PaperServlet?obj=selectAll&pageNo=1" target="right"><span class="icon-caret-right"></span>试卷管理</a>
             </li>
        </ul>
    </div>
    <script type="text/javascript">
        $(function(){
            $(".leftnav h2").click(function(){
                $(this).next().slideToggle(200);	
                $(this).toggleClass("on"); 
            })
            $(".leftnav ul li a").click(function(){
                $("#a_leader_txt").text($(this).text());
                $(".leftnav ul li a").removeClass("on");
                $(this).addClass("on");
            })
        });
    </script>
    <ul class="bread">
        <li><a href="javascript:void(0);" target="right" class="icon-home"> 首页</a></li>
        <li><a href="javascript:void(0);" target="right" id="a_leader_txt">网站信息</a></li>
    </ul>
    <div class="admin">
        <iframe scrolling="auto" rameborder="0" src="" name="right" width="100%" height="100%"></iframe>
    </div>
</body>
</html>