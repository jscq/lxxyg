<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
	<%@include file="/common/common.jsp" %>
	<script type="text/javascript" src="<%=basePath%>/resources/js/sys/area/area.js"></script>
</head>
<body style="background-color: #fff">
<form class="registerForm" id="registerForm" method="post">

	<input type="hidden" name="parameterName" id="parameterName" value="${_csrf.parameterName}" />
	<input type="hidden" name="token" id="token" value="${_csrf.token}" />

	<div style="padding:10px" class="cell">
		<table width="100%" style="table-layout:fixed;">
	        <tr>
	            <th style="width:35%;">所属上级：</th>
	            <td style="width:50%;">
	               <input type="text" id="areaTree" name="area.name" class="form_text" style="width:245px;height: 80%;box-shadow: inset 0 0 10px #CCC;"/>
	            </td>
	        </tr>
            <tr>
	            <th style="width:35%;"><span style="color: red;">*</span>行政名称：</th>
	            <td style="width:50%;">
	            	<input type="text" id="name" name="name" style="width:245px" class="form_text" maxlength="1000" reg="Require" tip="请填写行政名称"/>
	            </td>
	        </tr> 
	        <tr>
	            <th style="width:35%;"><span style="color: red;">*</span>行政代码值：</th>
	            <td style="width:50%;">
	            	<input type="text" id="code" name="code" class="form_text" style="width:245px" maxlength="150" reg="Require" tip="请填写行政代码值"/>
	            </td>
	        </tr>  
    	</table>
	</div>
	
	<div style="width:100%;height:50px;text-align:center;margin:10px 0">
		 <a href="javascript:void(0);" onclick="saveOrUpdateArea('registerForm')" class="easyui-linkbutton c1" style="width:120px;">提交</a>
		<a href="javascript:void(0);" onclick="window.parent.loadDataGrid('area');window.parent.closeWinForm()" class="easyui-linkbutton c5" style="width:120px">取消</a>
	</div>
</form>
	<script type="text/javascript">
		$(function(){
			var parameterName = '${_csrf.parameterName}';
			var token = '${_csrf.token}';
			var date = new Date();
			var url = "<%=basePath%>/area/getAreaJson?"+parameterName+"="+token+"&time="+date+"&area.id=0";
			
			$('#areaTree').combotree({
				url : url,
				onLoadSuccess : function(node, data) {
					var optionValue = $('#areaTree').attr("optionValue");
					if (optionValue != undefined && optionValue != "") {
						$('#areaTree').combotree('setValues', optionValue);
					}
				},
				onBeforeExpand :function(node,param) {
					 $('#areaTree').combotree('options').url ="<%=basePath%>/area/getAreaJson?"+parameterName+"="+token+"&time="+date+"&area.id="+node.id;	
				}
			});
			
		});
		</script>
</body>
</html>