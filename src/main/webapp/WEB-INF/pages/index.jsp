<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/common/common.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta name="description" content="overview &amp; stats" />
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />
<title>乐享校园购管理系统</title>
<!-- BOOT STRAP  -->
<link rel="stylesheet" href="<%=basePath%>/resources/assets/css/bootstrap.min.css" />
<link rel="stylesheet" href="<%=basePath%>/resources/assets/css/font-awesome.min.css" />
<link rel="stylesheet" href="<%=basePath%>/resources/assets/css/ace-fonts.css" />
<link rel="stylesheet" href="<%=basePath%>/resources/assets/css/ace.min.css" />
<link rel="stylesheet" href="<%=basePath%>/resources/assets/css/ace-rtl.min.css" />
<link rel="stylesheet" href="<%=basePath%>/resources/assets/css/ace.onpage-help.css" />
<link rel="stylesheet" href="<%=basePath%>/resources/assets/css/ace-skins.min.css" />
<!-- 
<link rel="stylesheet" href="<%=basePath%>/resources/docs/assets/js/themes/sunburst.css" />
-->
<link rel="stylesheet" href="<%=basePath%>/resources/css/pop.css" />

<script type="text/javascript" src="<%=basePath%>/resources/assets/js/ace-extra.min.js"></script>
<script type="text/javascript" src="<%=basePath%>/resources/assets/js/bootstrap.min.js"></script>
<script type="text/javascript" src="<%=basePath%>/resources/assets/js/jquery-ui.custom.min.js"></script>
<script type="text/javascript" src="<%=basePath%>/resources/assets/js/jquery.ui.touch-punch.min.js"></script>
<script type="text/javascript" src="<%=basePath%>/resources/assets/js/jquery.easypiechart.min.js"></script>
<script type="text/javascript" src="<%=basePath%>/resources/assets/js/jquery.sparkline.min.js"></script>
<script type="text/javascript" src="<%=basePath%>/resources/assets/js/flot/jquery.flot.min.js"></script>
<script type="text/javascript" src="<%=basePath%>/resources/assets/js/flot/jquery.flot.pie.min.js"></script>
<script type="text/javascript" src="<%=basePath%>/resources/assets/js/flot/jquery.flot.resize.min.js"></script>
<script type="text/javascript" src="<%=basePath%>/resources/assets/js/ace-elements.min.js"></script>
<script type="text/javascript" src="<%=basePath%>/resources/assets/js/ace.min.js"></script>
<script type="text/javascript" src="<%=basePath%>/resources/assets/js/ace/elements.onpage-help.js"></script>
<script type="text/javascript" src="<%=basePath%>/resources/assets/js/ace/ace.onpage-help.js"></script>
<script type="text/javascript" src="<%=basePath%>/resources/js/yanue.pop.js"></script>
<!--[if lte IE 9]>
	<link rel="stylesheet" href="<%=basePath%>/resources/assets/css/ace-part2.min.css" />
<![endif]-->
<!--[if lte IE 9]>
	<link rel="stylesheet" href="<%=basePath%>/resources/assets/css/ace-ie.min.css" />
<![endif]-->
<!--[if lte IE 8]>
	<script src="<%=basePath%>/resources/assets/js/html5shiv.min.js"></script>
	<script src="<%=basePath%>/resources/assets/js/respond.min.js"></script>
<![endif]-->
<!--[if lte IE 8]>
  	<script src="${base}/resources/assets/js/excanvas.min.js"></script>
<![endif]-->

</head>
<body class="no-skin" style="overflow-y: hidden" onload="init()">
	<!-- 防止CSRF攻击，必须在请求中添加 -->
	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
	
	<%@include file="/common/header.jsp"%>
	
	<div class="main-container" id="main-container">

		<%@include file="/common/menu.jsp"%>

		<div class="main-content" id="content">
			<iframe width="100%" height="100%" id="iframe" name="iframe" scrolling="no" frameborder="0" src=""></iframe>
		</div>
	</div>
	
	<div id="pop" style="display:none;">
		<div id="popHead">
		<a id="popClose" title="关闭">关闭</a>
		<span id="popmsg">消息提示</span>
		</div>
		<div id="popContent">
		<dl>
			<dt id="popTitle"></dt>
		</dl>
		</div>
	</div>

</body>

<script type="text/javascript">
try{ace.settings.check('main-container' , 'fixed');}catch(e){}
try{ace.settings.check('sidebar' , 'fixed');}catch(e){}
try{ace.settings.check('sidebar' , 'collapsed');}catch(e){}

window.jQuery || document.write("<script src='<%=basePath%>/resources/assets/js/jquery.min.js'>"+"<"+"/script>");

if('ontouchstart' in document.documentElement){
	document.write("<script src='<%=basePath%>/resources/assets/js/jquery.mobile.custom.min.js'>"+"<"+"/script>");
}

ace.vars['base'] = '..';

var lastMsg = "";
function init()
{
	ace.settings.navbar_fixed('checked');
	ace.settings.sidebar_fixed('checked'); 
	var clientHeight = $(window).height();
//	var bodyHeight = document.body.offsetHeight;
	var navbarHeight = document.getElementById('navbar').offsetHeight;
	var footerHeight = 10;//document.getElementById('footer').offsetHeight; 
	$('#iframe').attr('height',clientHeight-navbarHeight-footerHeight);
	$('#iframe').attr("src","<%=basePath%>/index/index");
	
	//getLatestMsg(); 
	 
	//setInterval('getLatestMsg()',1*60*1000);  
		
}

function getLatestMsg()
{
	$.ajax({
	        type: "post",
	        url:Utils.getRootPath() +'/member/getLatestMsg?${_csrf.parameterName}=${_csrf.token}',
	        dataType: 'json',
	        success: function(result) 
	        {
	        	if (result.status == "OK"){
	        		if(lastMsg != result.message)
	        		{
		            	new Pop(result.message,"","");
		            	lastMsg = result.message;
	            	}
	            } 
	        }
	        
	    });
}

function msglink(url)
{
	$('#iframe').attr('src',Utils.getRootPath() +url);
}

function link(url)
{
	$('#iframe').attr('src',url);
}


$(function(){ 
	//$('#upPasswordTitle').window('close');
});
function  viewPassword(){
	document.HZGXR.reset();
	
	$('#upPasswordTitle').window({  
		title:'修改密码提示框',
       	closable: true,
		modal: false,
		draggable:false,
		minimizable: false,
		maximizable: false,
		collapsible: false
	});
	$('#upPasswordTitle').window('open');
}

</script>
<%@include file="/common/init.jsp"%>
</html>