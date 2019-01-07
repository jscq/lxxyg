<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
	<%@include file="/common/common.jsp" %>
	<script type="text/javascript" src="<%=basePath%>/resources/js/sys/function/function.js"></script>
</head>
<body>
<!-- 防止CSRF攻击，必须在请求中添加 -->
<div style="overflow: auto">
<div id="tb" style="padding:2px 5px;" class="top_search">
	<form class="searchForm" id="searchForm" method="post">
		<div class="cell">
	       	<table border="0" cellpadding="0" cellspacing="0">
	           <tr>
	           	 <th width="10%">父级功能名称：</th>
	             <td width="23%">
	             	<input type="hidden" id="parentId" name="function.parent.id" />
					<input onkeydown="if(event.keyCode==13){event.keyCode=0;return false}" type="text" id="functionTree" class="form_text" style="width:245px;"/>
	             </td>
	             <th width="10%">功能名称：</th>
	             <td width="23%"><input onkeydown="if(event.keyCode==13){event.keyCode=0;return false}" id="functionName" name="function.functionName" class="form_text" maxlength="50"/></td>
	             <th width="10%">功能编码：</th>
	             <td width="23%"><input onkeydown="if(event.keyCode==13){event.keyCode=0;return false}" id="functionCode" name="function.functionCode" class="form_text" maxlength="50"/></td>
	           </tr>
	       	</table>
     	</div>
		<ul>
			<li class="top_search_libtn">
				<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-search" onclick="searchFunction();">查询</a>
				<a href="#" class="easyui-linkbutton" iconCls="icon-redo" onclick="resetSearchFrom(searchForm)">清空</a>
			</li>
		</ul>
	</form>
		<a href="#" class="easyui-linkbutton" iconCls="icon-add" onclick="add('<%=basePath%>/function/addFunction','添加功能信息',850,500)" plain="true">添加</a>
		<a href="#" class="easyui-linkbutton" iconCls="icon-edit" onclick="edit();" plain="true">编辑</a>
		<a href="#" class="easyui-linkbutton" iconCls="icon-remove" onclick="del('${_csrf.parameterName}','${_csrf.token}');" plain="true">删除</a>
</div>
<table id="dg"></table>
</div>
</body>
<script>
$(function(){
	
	//去除回车键
	document.onkeydown=function(evt){
		if(evt.keyCode ==13){
			return;
		}
	};
	
	var parameterName = '${_csrf.parameterName}';
	var token = '${_csrf.token}';
	var winHeight = $(window).height();
	$('#dg').datagrid({
		title:'功能管理', //标题
		method:'post',
		iconCls:'icon-search', //图标
		singleSelect:false, //多选
		height:winHeight, //高度
		fitColumns: true, //自动调整各列，用了这个属性，下面各列的宽度值就只是一个比例。
		striped: true, //奇偶行颜色不同
		cache: false,
		remoteSort: false,
		url:"<%=basePath%>/function/listFunctionJson?"+parameterName+"="+token+"&time="+new Date(), //数据来源
		queryParams:{
			'function.functionName':$('#functionName').val(), // 功能名称
			'function.functionCode':$('#functionCode').val(), // 功能编码
			'function.parent.id' : $('#parentId').val()  // 父级功能id
		}, //查询条件
		pagination:true, //显示分页
		rownumbers:true, //显示行号
		pageSize:10,
		pageList:[10,15,20],
		columns:[[
			{field:'ck',checkbox:true,width:'2%',styler:function(value,rowData,rowIndex){
				
				return "height:35px;";
			}}, //显示复选框
			{field:'functionName',title:'功能名称',width:'18%',sortable:true},
			{field:'functionCode',title:'功能编码',width:'18%',sortable:true},
			{field:'functionUrl',title:'链接地址',width:'18%',sortable:true},
			{field:'sn',title:'排序号',width:'18%',sortable:true},
			{field:'createTime',title:'创建时间',width:'18%',sortable:true,formatter:formatterDateByM}
		]],
		toolbar:'#tb',
		onLoadSuccess:function(){
			$('#dg').datagrid('clearSelections'); //一定要加上这一句，要不然datagrid会记住之前的选择状态，删除时会出问题
		},
		onClickRow: function (index, row) {
        	$('#dg').datagrid("unselectAll");
			$('#dg').datagrid('selectRow', index);
       	 }
	});
	
	var url = "<%=basePath%>/function/getFunctionTreeJson?"+parameterName+"="+token+"&time="+new Date();
	//功能树
	$("#functionTree").combotree({
		url: url,
		onClick:function(node){
			if (null != node && '' != node.id) {
				$("#parentId").val(node.id);
			}
		}
	});
});

$(window).resize(function(){
    $('#dg').datagrid('resize',{
    	width:'100%'
    });
});

</script>
</html>