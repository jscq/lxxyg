<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
	<%@include file="/common/common.jsp" %>
		<script type="text/javascript" src="<%=basePath%>/resources/js/shopping/goods/goods.js"></script>
</head>
<body style="background-color: #fff">
	
	<div class="wrap_form_middle_right">
	<div class="adjust_box" style="height:425px;">
	<div class="box_inner">
	
	<form class="registerform" id="registerForm" method="post">
	
	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
	<input type="hidden" name="parameterName" id="parameterName" value="${_csrf.parameterName}" />
	<input type="hidden" name="token" id="token" value="${_csrf.token}" />
	<input type="hidden" id="id" value="${goods.id }" name="classify.id" />
	
		<div class="cell">
			<table border="0" cellpadding="0" cellspacing="0">
				<tr>
		        	<th style="width:15%;"><span style="color: red;">*</span>所属分类：</th>
		            <td style="width:35%;">
		            	<select name="goods.classify.id" id="classifyId" value="${goods.classify.id }" style="width:90%;vertical-align: middle;">
		            		<option value="">--请选择所属分类--</option>
		            		<c:forEach items="${listClassify}" var="classify">
		            			<c:if test="${goods.classify.id eq classify.id}">
		            				<option value="${classify.id}" selected="selected">--${classify.classifyName}--</option>
		            			</c:if>
		            			<c:if test="${goods.classify.id ne classify.id}">
		            				<option value="${classify.id}">--${classify.classifyName}--</option>
		            			</c:if>
		            		</c:forEach>
		            	</select>
		            </td>
	           </tr> 
	           <tr>  
		            <th style="width:15%;"><span style="color: red;">*</span>商品图片：</th>
		            <td style="width:35%;">
		            	<input type="text" id="goodsImage" value="${goods.goodsImage }" name="goods.goodsImage" />
		            	<input type="file" name="file" id="pic" value="点击上传" onchange="savePic(this)"/>
		            </td>
		       </tr> 
		       <tr>
		        	<th style="width:15%;"><span style="color: red;">*</span>商品名称：</th>
		            <td style="width:35%;"><input type="text" name="goods.goodsName" value="${goods.goodsName }" class="form_text" reg="Require" tip="请填写商品名称" /></td>
		       </tr>
		       <tr>
		      	    <th style="width:15%;">是否有折扣：</th>
		            <td style="width:35%;">
		            	<input name="goods.hasDiscount"  id="hasDiscount" type="radio" value="1"  onclick="selectDiscount(this.value)"/>是
	            		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	            		<input name="goods.hasDiscount"  id="hasDiscount" type="radio" value="2" checked="checked"   onclick="selectDiscount(this.value)"/>否
		            </td>
		       </tr>
		       <tr id="originalPrice" style="display:none;">
		       		<th style="width:15%;"><span style="color: red;">*</span>原价：</th>
		            <td style="width:35%;"><input type="text" name="goods.goodsOriginalPrice" value="${goods.goodsOriginalPrice }" id="goodsOriginalPrice" onblur="jisuanZheKou()" class="form_text"/></td>
		       </tr>
		       <tr>
		       		<th style="width:15%;"><span style="color: red;">*</span>售价：</th>
		            <td style="width:35%;"><input type="text" name="goods.goodsPrice" id="goodsPrice" value="${goods.goodsPrice }" onblur="jisuanZheKou()" class="form_text" reg="Require" tip="请填写销售价格" /></td>
		       </tr>
		       <tr id="discount" style="display:none;">
		       		<th style="width:15%;">折扣：</th>
		            <td style="width:35%;"><input type="hidden" name="goods.goodsDiscount" id="goodsDiscount" /><span id="zhekou">${goods.goodsDiscount }</span>折</td>
		       </tr>
		       <tr>
		      	    <th style="width:15%;"><span style="color: red;">*</span>是否长期销售：</th>
		            <td style="width:35%;">
		            		<input name="goods.goodsSalable"  id="goodsSalable" type="radio" value="1" checked="checked"  onclick="selectStock(this.value)"/>是
		            		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		            		<input name="goods.goodsSalable"  id="goodsSalable" type="radio" value="2"  onclick="selectStock(this.value)"/>否
		            </td>
		       </tr>
		       <tr id="stock" style="display:none;">
		       		<th style="width:15%;"><span style="color: red;">*</span>余量：</th>
		            <td style="width:35%;"><input type="text" name="goods.goodsResidual" value="${goods.goodsResidual }" class="form_text"/></td>
		       </tr>
		   	</table>
	   	</div>
	   	<div class="box_03">
	       	<div class="box_inner_03">
	       		<div class="btn_area_setl btn_area_bg">
	       			<a href="#" onclick="save('registerForm','${_csrf.parameterName}','${_csrf.token}')" class="btn_01">保存<b></b></a>
	   				<a href="#" onclick="window.parent.closeWinForm();" class="btn_01">取消<b></b></a>
	       		</div>
	       	</div>
	    </div>
	</form>
	</div>
	</div>
	</div>
</body>
</html>