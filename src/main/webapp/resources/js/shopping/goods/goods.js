function add(url,title,x,y)
{
	window.parent.initAdd(url,title,x,y);
}

//查询
var searchClassify = function(){
	var queryParams = {
		'classify.classifyName' : $("#classifyName").val(),
		'classify.classifyCode' : $('#classifyCode').val(),
		'classify.classifyStatus' : $('#classifyStatus').val()
	};        	
	$('#dg').datagrid('options').queryParams = queryParams;
	$("#dg").datagrid('load'); 
}

/**
 * 添加功能
 * @param formId
 * @param parameterName
 * @param token
 */
function save(formId,parameterName,token)
{
	//数据有限性判断
	if (validateSubmit(formId)) {
			// 功能名称校验 
			var checkName = verifyClassifyName(parameterName,token);
			
			if(checkName){
				//保存
				var url = Utils.getRootPath() +'/classify/saveOrUpdate';
				$.ajax({
			        type: "POST",
			        url: url,
			        data:$('#'+formId).serialize(),
			        async: false,
			        dataType: 'json',
			        error: function(request) {
			        	$.messager.alert('提示','系统异常,请稍后重新再试!','error');
			        },
			        success: function(result) 
			        {
			        	var message = "";
			        	var id = $("#id").val();
			        	if(id != null && id != undefined && id != ''){
		        			message = "更新";
		        		}else{
		        			message = "保存";
		        		}
			        	
			        	if (null != result && result.status == "OK"){
			        		$.messager.alert('提示',message + '成功','info',function(){
			        			loadDataGrid("classify");
			                	window.parent.closeWinForm();
			        		});
			            } else{
			            	$.messager.alert('提示',message + '失败','error');
			            }
			        }
			    });
			}
	}
}

/**
 * 编辑
 */
function edit(){
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
	var id = rows[0].id;
	add(Utils.getRootPath()+'/classify/editClassify?classify.id='+id,'编辑商品分类',850,500); 
};

  
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
			
			var url = Utils.getRootPath() + '/classify/deleteClassify?'+ parameterName + "=" + token;
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
						$.messager.alert('提示', '删除成功', 'info',function(){
							loadDataGrid("classify");
						});
					} else if (result.status == "ERROR") {
						$.messager.alert('提示', '删除失败', 'error');
					}
				},
			});
		}
	});
}


//验证功能名称唯一性  
function verifyClassifyName(parameterName,token)
{
	var checkFlag = false;
	var returnFlag = false;
	// 父节点id
	var parentId = "";
	//隐藏功能名称
	var hiddenClassifyName = $("#hiddenClassifyName").val();
	var classifyName = $("#classifyName").val();
	classifyName = classifyName.replace(/(^\s*)|(\s*$)/g, "");
	if ((hiddenClassifyName=='')||(hiddenClassifyName != '' && hiddenClassifyName != classifyName)) 
	{
		checkFlag = true;
	}else if(hiddenclassifyName != '' && hiddenclassifyName == classifyName)
	{
		return true;
	}
	
	if(checkFlag)
	{
		if(classifyName!='')
		{

			$.ajax({
				type : "post",
				url : Utils.getRootPath() + '/classify/verifyClassifyName?'+ parameterName + "=" + token,
				data : {
					"classify.classifyName" : classifyName
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
						$.messager.alert('提示', '商品分类已存在，是否继续启用当前分类?', 'warning');
					}
				}
			});
		}
	} 
	return returnFlag;
}


/**
 * 折扣选择
 */
var selectDiscount = function(value){
	if(value == 1){
		$("#goodsOriginalPrice").show();
		$("#discount").show();
		
		$("#zhekou").text("98")
		
	}else if(value == 2){
		$("#goodsOriginalPrice").hide();
		$("#discount").hide();
	}
}


/**
 *  切换余量
 */
var selectStock = function(value){
	if(value == 2){
		$("#stock").show();
	}else if(value == 1){
		$("#stock").hide();
	}
}