<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>在线答题</title>
	<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
	<link rel="stylesheet" type="text/css" href="css/paper.css">
	<script src="js/jquery.js" type="text/javascript"></script>
	<script src="js/bootstrap.js"></script>
	<script src="layer/layer.js"></script>
</head>

<body>
<nav class="navbar navbar-default" role="navigation">
	<!-- Brand and toggle get grouped for better mobile display -->
	<div class="navbar-header">
		<button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-ex1-collapse">
			<span class="sr-only">Toggle navigation</span>
			<span class="icon-bar"></span>
			<span class="icon-bar"></span>
			<span class="icon-bar"></span>
		</button>
		<a class="navbar-brand" href="#">在线考试系统</a>
	</div>

	<!-- Collect the nav links, forms, and other content for toggling -->
	<div class="collapse navbar-collapse navbar-ex1-collapse">
		<ul class="nav navbar-nav">
			<li><a href="">试题列表</a></li>
			<li><a href="">查看错题</a></li>
		</ul>
		<ul class="nav navbar-nav navbar-right">
			<c:choose>
				<c:when test="${user.getUserid() != null}">
					<li>
						<a style="color: black;">
								${user.getUsertruename()}
						</a>
					</li>
					<li>
						<a href="" style="color: black;">注销</a>
					</li>
				</c:when>
				<c:otherwise>
					<li><a href="../login.jsp" style="color: black;">登录</a></li>
				</c:otherwise>
			</c:choose>
		</ul>
	</div>
</nav>
<main class="container">
	<div class="panel panel-default">
		<div class="panel-heading text-center">
			<h3 class="panel-title">
				${pname}
			</h3>
		</div>
		<div class="panel-body">
			<div class="panel panel-default">
				<div class="panel-heading">
					<h4 class="panel-title">
						<a data-toggle="collapse" data-parent="#accordion" href="#Radio">单选题（共 ${count } 题，每题2分）</a>
					</h4>
				</div>
				<form action="" method="POST" role="form">
					<div id="Radio" class="panel-collapse collapse in">
						<div class="panel-body">
							<ol>
								<!-- 用于遍历集合，展示试题信息 -->
								<c:forEach items="${list }" var="paper" varStatus="status">
									<div class="subject" data-i="${status.index}" data-answer="false"
										 data-sid="${paper.getSid()}" data-key="${paper.getSkey()}" data-state="0"
										 data-skey>
										<li>${paper.getScontent() }</li>
										<ol>
											<li><label><input type="radio" value="A" name="${paper.getSid()}">${paper.getSa() }</label></li>
											<li><label><input type="radio" value="B" name="${paper.getSid()}">${paper.getSb() }</label></li>
											<li><label><input type="radio" value="C" name="${paper.getSid()}">${paper.getSc() }</label></li>
											<li><label><input type="radio" value="D" name="${paper.getSid()}">${paper.getSd() }</label></li>
										</ol>
									</div>
								</c:forEach>
							</ol>
							<button class="btn btn-success" type="submit">交卷</button>
						</div>
					</div>
				</form>
			</div>
		</div>

	</div>
</main>
<aside class="processor">
	<section class="time" id="time">
		00时00分00秒
	</section>
	<section class="timu">
		<c:forEach items="${list }" var="paper" varStatus="status">
			<div data-i="${status.index }">${status.index+1}</div>
		</c:forEach>
	</section>
</aside>

<script>
	// 获取题目数量
	var len = $('.subject').length;
	var unanswer = len;

	// 监听点击选项
	$('.subject ol li label').click(function () {
		// 获得本题的div
		var sub = $(this).parent().parent().parent()
		var indexs = sub.data('i')
		// 判断此题是否回答过
		if(sub.data('answer') == false){
			// 没有回答过给processor下的指定题号添加answered样式
			$('.timu').children().eq(indexs).addClass('answered');
			// 未答题目－1
			unanswer--
			sub.data('answer',true)
		}
		// 判断选项是否正确
		if($(this).find(':input').val() == sub.data('key')){
			// 正确给data-state赋值1
			sub.data('state',1)
		}else{
			// 不正确给data-state赋值0
			sub.data('state',0)
		}
		sub.data('skey',$(this).find(':input').val())
		// siblings() 获得匹配集合中每个元素的同胞，通过选择器进行筛选是可选的。
		$(this).parent().addClass('checked').siblings().removeClass('checked')
	})



	// 交卷功能
	// (1)判断是否有未答题目
	function unAnswer(){
		if(unanswer != 0){
			layer.open({
				title:'警告',
				content: "还有"+unanswer+"道题目未做！",
				icon:2,
				end:function(){
					postAnswer();
				}
			});
		}else{
			postAnswer();
		}
	}
	// (2)计算得分
	function getScore(){
		//var spid = n
		$.post({
			url:'StudentPaperServlet?obj=score&userid='+'${user.getUserid()}'+'&spid='+ now.getTime(),
			contentType: false,
			processData: false,
			success: function(res) {
				console.log(res)
				layer.open({
					title:'得分',
					content: res,
					icon:1,
					end:function(){
						location.href ='StudentPaperServlet?obj=studentList&userid='+'${user.getUserid()}';
					}
				})
			},
			error: function(res) {
				console.log('error');

			}
		})

	}
	// (3)提交答案的post请求
	function postAnswer(){
		var index = layer.load(0, {shade: false});
		var pname = '${pname}';
		var userid = '${user.getUserid()}';
		$('.subject').each(function(index){
			var i = index
			var self = $(this)
			var data = {userid:userid,sid:self.data('sid'),studentkey:self.data('skey'),studentstate:self.data('state'),pname:pname,spid:now.getTime()}
			var datas = $.param(data)
			console.log(datas)
			$.post({
				url:'StudentPaperServlet?obj=addStudentPaper&'+ datas,
				contentType: false,
				processData: false,
				success:function(){
					// 在最后一次提交完成后跳转到错题列表
					if(i == len - 1){
						layer.close(index);
						//location.href = basePath+'user/studentPaper?cmd=list';
						getScore();
					}
				}
			})
		})
	}
	// (4)点击交卷
	$('form').submit(function(ev) {
		ev.preventDefault()
		unAnswer();
	})

	// 倒计时功能
	//小于10的数字前面补0
	function p(n){
		return n<10?'0'+n:n;
	}
	//获取当前时间
	var now=new Date();
	//获取结束时间
	var endDate=new Date();
	//设置考试时间（单位分钟）
	endDate.setMinutes(now.getMinutes()+20)
	function getTime(){
		var startDate=new Date();
		var countDown=(endDate.getTime()-startDate.getTime())/1000;
		var h=parseInt(countDown/(60*60)%24);
		var m=parseInt(countDown/60%60);
		var s=parseInt(countDown%60);
		$('.time').html(p(h)+'时'+p(m)+'分'+p(s)+'秒');
		if(countDown<=0){
			document.getElementById('time').innerHTML='考试结束';
			layer.open({
				title:'警告',
				content: '考试时间到，试卷已经提交！',
				icon:5,
				end:function(){
					unAnswer();
				}
			})
		}else{
			setTimeout('getTime()',500);
		}
	}
	getTime()
</script>
</body>
</html>