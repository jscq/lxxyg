<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
<%@include file="/common/common.jsp" %>
<script type="text/javascript" src="<%=basePath%>/resources/js/sys/function/function.js"></script>
</head>
<body style="background-color: #fff">
	
	<div class="wrap_form_middle_right">
	<div class="adjust_box" style="height:425px;">
	<div class="box_inner">
	
	<form class="registerform" id="registerForm" method="post">
	
	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
	<input type="hidden" name="parameterName" id="parameterName" value="${_csrf.parameterName}" />
	<input type="hidden" name="token" id="token" value="${_csrf.token}" />
	<input type="hidden" id="id" value="${function.id }" name="function.id" />
	<input type="hidden" id="hiddenFunctionName" value="${function.functionName }" name="hiddenFunctionName" />
		
		<div class="cell">
			<table border="0" cellpadding="0" cellspacing="0">
		       <tr>
		        	<th><span style="color: red;">*</span>功能名称：</th>
		            <td><input type="text" id="functionName" value="${function.functionName }" name="function.functionName" class="form_text" maxlength="25" reg="Require" tip="请填写功能名称" /></td>
		            
		            <th>链接地址：</th>
		            <td><input type="text" value="${function.functionUrl }" name="function.functionUrl" class="form_text"/></td>
		       </tr> 
		       <tr>
		        	<th>功能编码：</th>
		            <td><input type="text" value="${function.functionCode }" name="function.functionCode" class="form_text" maxlength="25"/></td>
		            
		            <th>排序号：</th>
		            <td><input type="text" value="${function.sn }" name="function.sn" class="form_text"/></td>
		       </tr>
		       <tr>
		       		<th>上级功能：</th>
		            <td>
		            	<input type="hidden" id="editParentId" />
		            	<input type="text" id="functionTree" name="function.parent.id" value="${function.parent.id }" class="form_text" style="width:245px;"/>
		            </td>
		            
		            <th></th>
		            <td></td>
		       </tr>
		   	</table>
	   	</div>
	   	<div class="box_03">
	       	<div class="box_inner_03">
	       		<div class="btn_area_setl btn_area_bg">
	       			<a href="#" onclick="save('registerForm','${_csrf.parameterName}','${_csrf.token}')" class="btn_01">保存<b></b></a>
	   				<a href="#" onclick="loadDataGrid('function');window.parent.closeWinForm();" class="btn_01">取消<b></b></a>
	       		</div>
	       	</div>
	    </div>
	</form>
	</div>
	</div>
	</div>
</body>
<script type="text/javascript">
$(function(){
	var parameterName = '${_csrf.parameterName}';
	var token = '${_csrf.token}';
	var url = "<%=basePath%>/function/getFunctionTreeJson?"+parameterName+"="+token+"&time="+new Date()
	$("#functionTree").combotree({
		url: url,
		onClick:function(node){
			$("#editParentId").val(node.id);
		}
	});
})
</script>
</html>