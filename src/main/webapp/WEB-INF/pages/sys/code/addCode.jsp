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

	<div style="padding:10px" class="cell">
		<table width="100%" style="table-layout:fixed;">
	        <tr>
	            <th style="width:35%;">所属上级：</th>
	            <td style="width:50%;">
	                <select name="parent" id="parent" style="width: 248px; height: 25px; vertical-align: middle;" onchange="changeType(this.value)">
						<option	value=""></option>
						<c:forEach var="item" items="${map.codeList}" varStatus="status">
							<option	value="${item.id}">${item.codeName}</option>
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
	            	<input type="text" id="codeName" name="codeName" style="width:245px" class="form_text" maxlength="1000" reg="Require" tip="请填写数据名称"/>
	            </td>
	        </tr> 
	        <tr>
	            <th style="width:35%;">数据值：</th>
	            <td style="width:50%;">
	            	<input type="text" id="codeValue" name="codeValue" class="form_text" style="width:245px" maxlength="150" disabled="disabled"/>
	            </td>
	        </tr>  
	        <tr>
	            <th style="width:35%;"><span style="color: red;">*</span>数据类型(英文简称)：</th>
	            <td style="width:50%;">
	            	<input type="text" id="codeType" name="codeType" class="form_text" style="width:245px"  reg="Require" tip="请填写数据类型(英文简称)" />
	            </td>
	        </tr> 
	        <tr>
	            <th style="width:35%;">数据描述：</th>
	            <td style="width:50%;">
	            	<input type="text" id="codeDescription" name="codeDescription" class="form_text" style="width:245px"/>
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
</html>