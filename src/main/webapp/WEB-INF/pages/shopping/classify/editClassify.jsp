<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
	<%@include file="/common/common.jsp" %>
	<script type="text/javascript" src="<%=basePath%>/resources/js/shopping/classify/classify.js"></script>
</head>
<body style="background-color: #fff">
	
	<div class="wrap_form_middle_right">
	<div class="adjust_box" style="height:425px;">
	<div class="box_inner">
	
	<form class="registerform" id="registerForm" method="post">
	
	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
	<input type="hidden" name="parameterName" id="parameterName" value="${_csrf.parameterName}" />
	<input type="hidden" name="token" id="token" value="${_csrf.token}" />
	<input type="hidden" id="id" value="${classify.id }" name="classify.id" />
	<input type="hidden" id="hiddenClassifyName" value="${classify.classifyName }" name="hiddenClassifyName" />
		
		<div class="cell">
			<table border="0" cellpadding="0" cellspacing="0">
		       <tr>
		        	<th><span style="color: red;">*</span>分类名称：</th>
		            <td><input type="text" id="classifyName" value="${classify.classifyName }" name="classify.classifyName" class="form_text" maxlength="25" reg="Require" tip="请填写分类名称" /></td>
		            
		            <th>分类编码：</th>
		            <td><input type="text" value="${classify.classifyCode }" name="classify.classifyCode" class="form_text"/></td>
		       </tr> 
		       <tr>
		        	<th>上下架状态编码：</th>
		            <td>
		            	<select name="classifyStatus" value="${classify.classifyStatus} id="classifyStatus" style="width:90%">
	             			<option value="">--请选择--</option>
	             			<option value="0">--上架--</option>
	             			<option value="1">--已下架--</option> 
	             		</select>
		            </td>
		            
		            <th>排序号：</th>
		            <td><input type="text" value="${classify.sn }" name="classify.sn" class="form_text"/></td>
		       </tr>
		   	</table>
	   	</div>
	   	<div class="box_03">
	       	<div class="box_inner_03">
	       		<div class="btn_area_setl btn_area_bg">
	       			<a href="#" onclick="save('registerForm','${_csrf.parameterName}','${_csrf.token}')" class="btn_01">保存<b></b></a>
	   				<a href="#" onclick="loadDataGrid("classify");window.parent.closeWinForm();" class="btn_01">取消<b></b></a>
	       		</div>
	       	</div>
	    </div>
	</form>
	</div>
	</div>
	</div>
</body>
</html>