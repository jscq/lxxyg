<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
<%@include file="/common/common.jsp" %>
<script type="text/javascript" src="<%=basePath%>/resources/js/sys/code/code.js"></script>
</head>
<body style="background-color: #fff">
<form class="registerForm" id="registerForm" method="post">

	<input type="hidden" name="parameterName" id="parameterName" value="${_csrf.parameterName}" />
	<input type="hidden" name="token" id="token" value="${_csrf.token}" />
    <input type="hidden" name="id" id="codeId" value="${code.id}" />

	<div style="padding:10px" class="cell">
		<table width="100%" style="table-layout:fixed;">
	        <tr>
	            <th style="width:35%;">所属上级：</th>
	            <td style="width:50%;">
	            	<input type="hidden" value="${code.parent.id }" id="tempParent" name="tempParent"/>
	                <select name="parent" id="parent" style="width: 248px; height: 25px; vertical-align: middle;" disabled="disabled">
						<option	value=""></option>
						<c:forEach var="item" items="${map.codeList}" varStatus="status">
							<option	value="${item.id}" <c:if test="${item.id eq code.parent.id }">selected</c:if>>${item.codeName}</option>
						</c:forEach>
						<c:forEach var="item" items="${map.codeList}" varStatus="status">
							<input type="hidden" id="parent${item.id}"  value="${item.codeType}"/>
						</c:forEach>
					</select>
	            </td>
	        </tr>
            <tr>
	            <th style="width:35%;"><span style="color: red;">*</span>数据名称：</th>
	            <td style="width:50%;">
	            	<input type="text" value="${code.codeName }" id="codeName" name="codeName" style="width:245px" class="form_text" maxlength="1000" reg="Require" tip="请填写数据名称"/>
	            </td>
	        </tr>
	        <tr>
	            <th style="width:35%;">数据值：</th>
	            <td style="width:50%;">
	            	<input type="text" value="${code.codeValue }" id="codeValue" name="codeValue" class="form_text" style="width:245px" maxlength="150"/>
	            </td> 
	        </tr>  
	        <tr>
	            <th style="width:35%;"><span style="color: red;">*</span>数据类型(英文简称)：</th>
	            <td style="width:50%;">
	            	<input type="text" value="${code.codeType }" id="codeType" name="codeType" class="form_text" style="width:245px"  reg="Require" tip="请填写数据类型(英文简称)" />
	            </td>
	        </tr> 
	        <tr>
	            <th style="width:35%;">数据描述：</th>
	            <td style="width:50%;">
	            	<input type="text" value="${code.codeDescription }" id="codeDescription" name="codeDescription" class="form_text" style="width:245px"/>
	            </td>
	        </tr>
    	</table>
	</div>
	
	<div style="width:100%;height:50px;text-align:center;margin:10px 0">
		 <a href="javascript:void(0);" onclick="saveOrUpdateCode('registerForm')" class="easyui-linkbutton c1" style="width:120px;">提交</a>
		<a href="javascript:void(0);" onclick="window.parent.loadDataGrid('code');window.parent.closeWinForm()" class="easyui-linkbutton c5" style="width:120px">取消</a>
	</div>
</form>
</body>
<script type="text/javascript">
$(function(){
	var parent = $("#tempParent").val();
	if(parent != ''){
		$("#codeType").attr("disabled",true); 
		$("#codeValue").attr("disabled",false); 
	}else{
		$("#codeType").attr("disabled",true); 
		$("#codeValue").attr("disabled",true); 
	}
});
</script>
</html>