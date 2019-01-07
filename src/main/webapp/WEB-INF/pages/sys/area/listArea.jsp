<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
	<%@include file="/common/common.jsp" %>
	<script type="text/javascript" src="<%=basePath%>/resources/js/sys/area/area.js"></script>
</head>
<body>
<!-- 防止CSRF攻击，必须在请求中添加 -->
<div style="overflow: auto">

<div id="tb" style="padding:2px 5px;" class="top_search">
	<form  name="searchForm" id="searchForm" method="post">
		
		<!-- 隐藏的字段 -->
		<input type="hidden" name="parameterName" id="parameterName" value="${_csrf.parameterName}" />
		<input type="hidden" name="token" id="token" value="${_csrf.token}" />
		
		<div class="cell">
	       	<table border="0" cellpadding="0" cellspacing="0">
	           <tr>
	           	 <th width="15%">行政名称：</th>
	             <td width="25%"><input id="name"  type="text" class="form_text" style="width:245px;"/></td>
	             <th width="15%">行政代码：</th>
	             <td width="25%"><input id="code"  type="text" class="form_text" style="width:245px;"/></td>
	           </tr>
	       	</table>
     	</div>
		<ul>
			<li class="top_search_libtn">
				<a href="#" class="easyui-linkbutton" iconCls="icon-search" onclick="searchArea()">查询</a>
				<a href="#" class="easyui-linkbutton" iconCls="icon-redo" onclick="resetSearchFrom(searchForm)">清空</a>
			</li>
		</ul>
	</form>
		<a href="#" class="easyui-linkbutton" iconCls="icon-add" onclick="add('<%=basePath%>/area/addArea','添加行政区域信息',800,350)" plain="true">新增</a>
		<a href="#" class="easyui-linkbutton" iconCls="icon-edit" onclick="edit()" plain="true">编辑</a>
		<a href="#" class="easyui-linkbutton" iconCls="icon-remove" onclick="deleteByArea();" plain="true">删除</a>
</div>
<table id="dg">
	
</table>
</div>
</body>
<script>
$(function(){
	
	var parameterName = $("#parameterName").val();
	var token = $("#token").val();
	
	var winHeight = $(window).height();
	$('#dg').treegrid({
		title:'行政区域管理', //标题
		
		method:'post',
		iconCls:'icon-search', //图标
		singleSelect:false, //多选
		height:winHeight, //高度
		fitColumns: true, //自动调整各列，用了这个属性，下面各列的宽度值就只是一个比例。
		striped: true, //奇偶行颜色不同
		cache: false,
		remoteSort: false,
		url:"<%=basePath%>/area/pageList?"+parameterName+"="+token+"&time="+new Date(), //数据来源
		queryParams:{
			'area.name':$('#name').val(),//行政区域名称
			'area.code':$('#code').val()//编码
		}, //查询条件
		pagination:true, //显示分页
		rownumbers:true, //显示行号
		pageSize:10,
		pageList:[10,15,20],
		columns:[[
			{field:'ck',checkbox:true,width:'2%',styler:function(value,rowData,rowIndex){
				return "height:35px;";
			}}, //显示复选框
			{field:'name',title:'行政名称',width:'20%',sortable:true},
			{field:'code',title:'行政区域代码',width:'15%',sortable:true}
		]],
		toolbar:'#tb',
		onLoadSuccess:function(){
			$('#dg').datagrid('clearSelections'); //一定要加上这一句，要不然datagrid会记住之前的选择状态，删除时会出问题
		},
        idField: 'id',
        treeField: 'name',
        onBeforeLoad: function(row,param){
            if (!row) {    // load top level rows
                param.id = 0;    // set id=0, indicate to load new page rows
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