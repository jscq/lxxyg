//编辑
function edit(title,x,y)
{
	var rows = $('#dg').datagrid('getSelections');
	if(rows.length==0)
	{
		$.messager.alert('提示','请选择需要操作的数据！','warning');
		return;
	}
	if(rows.length>1)
	{
		$.messager.alert('提示','请选择一条数据进行操作！','warning');
		return;
	}
	var row = rows[0];

	if(row.username == "admin"){
		$.messager.alert('提示','无法对超级管理员操作！','warning');
		return;
	}
	var url = Utils.getRootPath() +'/auth/user/gotoEditUsers?id='+row.id;
	add(url,title,x,y);
}



//查询
function searchUser(){
	
	var queryParams = {
		'user.userName':$('#username').val()
	};
	$('#dg').datagrid('options').queryParams = queryParams;
	$("#dg").datagrid('load');
}

// 保存用户信息
function save(formId,parameterName,token)
{
	//数据有限性判断
	if (validateSubmit(formId)) {
		//判断用户名不能重复
		if(verifyUserName(parameterName,token)){
			//保存
			var url = Utils.getRootPath() +'/user/saveOrUpdate';
			$.ajax({
		        type: "post",
		        url:url,
		        data:$('#'+formId).serialize(),
		        async: false,
		        dataType: 'json',
		        success: function(result) 
		        {
		        	if (result.status == "OK"){
		            	$.messager.alert('提示','保存成功','info',function(){
		            		loadDataGrid("user");
		            		window.parent.closeWinForm();
		            	});
		            } else if(result.status == "ERROR"){
		            	$.messager.alert('提示','保存失败，请联系管理员','error');
		            }
		        }
		    });
		}
	}
}



//验证用户名唯一性  
function verifyUserName(parameterName,token)
{
	var checkFlag = false;
	var returnFlag = false;
	//隐藏用户名
	var hiddenUserName = $("#hiddenUserName").val();
	var userName = $("#userName").val();
	userName = userName.replace(/(^\s*)|(\s*$)/g, "");
	if ((hiddenUserName=='')||(hiddenUserName != '' && hiddenUserName != userName)) 
	{
		checkFlag = true;
	}else if(hiddenUserName != '' && hiddenUserName == userName)
	{
		return true;
	}	
	
	if(checkFlag)
	{
		if(userName!='')
		{
			$.ajax({
				type : "post",
				url : Utils.getRootPath() + '/user/verifyUserName?'+ parameterName + "=" + token,
				data : {
					"user.userName" : userName
				},
				async : false,
				dataType : 'json',
				success : function(result) {
					if (result.status == "OK") 
					{
						returnFlag = true;
					}
					else if(result.status == "ERROR") 
					{
						$.messager.alert('提示', '用户名已存在，请重新输入用户名', 'warning');
					}
				}
			});
		}
	}
	return returnFlag;
}


/**
 * 重置密码
 */
function resetPassWord(parameterName,token){
	
	var rows = $('#dg').datagrid('getSelections');
	if (rows.length == 0) {
		$.messager.alert('提示', '请选择需要操作的数据！', 'warning');
		return;
	}
	
	$.messager.confirm('提示', '确定重置密码为888888？', function(r) {
		if (r) {
			var str = '';
			for(var i=0; i<rows.length; i++){
				var row = rows[i];
				
				if (str == '') {
					str = row.id;
				} else {
					str += "," + row.id;
				}
			}
			var url = Utils.getRootPath() + '/user/resetPassWord?'+ parameterName + "=" + token;
			$.ajax({
				type : "post",
				url : url,
				data : {
					"ids" : str
				},
				async : false,
				dataType : 'json',
				success : function(result) {
					if (result.status == "OK") {
						$.messager.alert('提示','重置成功','info',function(){
		            		loadDataGrid("user");
		            	});
					} else if (result.status == "ERROR") {
						$.messager.alert('提示', '重置失败', 'error');
					}
				},
			});
		}
	});
}



/**
 * 删除
 */
function del(parameterName,token){
	
	var rows = $('#dg').datagrid('getSelections');
	if (rows.length == 0) {
		$.messager.alert('提示', '请选择需要操作的数据！', 'warning');
		return;
	}
	
	$.messager.confirm('提示', '是否确定删除？', function(r) {
		if (r) {
			var str = '';
			for(var i=0; i<rows.length; i++){
				var row = rows[i];
				
				if (str == '') {
					str = row.id;
				} else {
					str += "," + row.id;
				}
			}
			var url = Utils.getRootPath() + '/user/deleteUser?'+ parameterName + "=" + token;
			$.ajax({
				type : "post",
				url : url,
				data : {
					"ids" : str
				},
				async : false,
				dataType : 'json',
				success : function(result) {
					if (result.status == "OK") {
						loadDataGrid("user");
					} else if (result.status == "ERROR") {
						$.messager.alert('提示', '删除失败', 'error');
					}
				},
			});
		}
	});
}