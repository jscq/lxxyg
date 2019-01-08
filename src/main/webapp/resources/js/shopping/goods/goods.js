function add(url,title,x,y)
{
	window.parent.initAdd(url,title,x,y);
}

//查询
var searchGoods = function(){
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
	console.log($('#'+formId).serialize())
	
	
	//数据有限性判断
	if (validateSubmit(formId)) {
		//保存
		var url = Utils.getRootPath() +'/goods/saveOrUpdate';
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
	        			loadDataGrid("goods");
	                	window.parent.closeWinForm();
	        		});
	            } else{
	            	$.messager.alert('提示',message + '失败','error');
	            }
	        }
	    });
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
	add(Utils.getRootPath()+'/goods/editGoods?goods.id='+id,'编辑商品',850,500); 
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
			
			var url = Utils.getRootPath() + '/goods/deleteGoods?'+ parameterName + "=" + token;
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
							loadDataGrid("goods");
						});
					} else if (result.status == "ERROR") {
						$.messager.alert('提示', '删除失败', 'error');
					}
				},
			});
		}
	});
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


/**
 * 保存图片
 * @param obj
 * @returns
 */
function savePic(obj){
	var imgName = obj.value;
	var pos = imgName.lastIndexOf(".");
	var fileName = imgName.substring(pos+1);
	//处理图片路径
	imgName = getFileName(imgName);
	if(fileName == "jpg" || fileName == "JPG" 
		|| fileName == "jpeg" || fileName == "JPEG" 
		|| fileName == "png" || fileName == "PNG" 
		|| fileName == "bmp" || fileName == "BMP" 
		|| fileName == "gif" || fileName == "GIF"){
		if(navigator.userAgent.indexOf("MSIE")>0) {//是IE
			
			//var version = $.browser.version;
			$("#upform").ajaxSubmit(function(result){
				result = result.replace("<pre>","");
				result = result.replace("<PRE>","");
				result = result.replace("</pre>","");
				result = result.replace("</PRE>","");
				var resultObj = eval('('+result+')');
	            if (resultObj.msg == "success"){
	            	$("#supplyAttName").attr("value", resultObj.fileName);
	            	$("#supplyAttPath").attr("value", resultObj.filePath);
	            	$("#supplyDocName").attr("value", resultObj.documentName);
	            	
	            	$.messager.alert('提示','图片上传成功！','info');
	            	$('#showPic').window('close');
	            	
	            	$("#pmPic").html('');
	            	var imgPath =  Utils.getBasePath() + "/"+resultObj.filePath.substring(resultObj.filePath.indexOf('uploadPic'))+"/" +  resultObj.fileName;
	            	var html='<a href="javascript:void(0)" style="color:blue;" onclick=window.open("'+imgPath+'")>【预览】</a><a href="javascript:void(0)" style="color:blue;" onclick="removePic()")>【删除】</a>';
	            	$('#pmPic').html(html);
	            } else if(resultObj.msg == "fail"){
	            	$.messager.alert('提示','图片文件大小不得小于20K或大于5M！','warning');
	            	file = $("#chooseimg");
	        		file.after(file.clone());
	        		file.remove();
					return;
	            } else if(resultObj.msg == "error"){
	            	$.messager.alert('提示','文件解析错误，请重新上传！','warning');
	            	file = $("#chooseimg" );
	        		file.after(file.clone());
	        		file.remove();
					return;
	            }
			});
		
		}else{//不是IE
			
			$("#upform").ajaxSubmit(function(result){
				result = result.substring(result.indexOf("{"),result.indexOf("}")+1);
				var resultObj = eval('('+result+')');
				
	            if (resultObj.msg == "success"){
	            	$("#supplyAttName").attr("value", resultObj.fileName);
	            	$("#supplyAttPath").attr("value", resultObj.filePath);
	            	$("#supplyDocName").attr("value", resultObj.documentName);
	            	
	            	$.messager.alert('提示','图片上传成功！','info');
	            	$('#showPic').window('close');
	            	
	            	$("#pmPic").html('');
	            	var imgPath =  Utils.getBasePath() + "/"+resultObj.filePath.substring(resultObj.filePath.indexOf('uploadPic'))+"/" +  resultObj.fileName;
	            	var html='<a href="javascript:void(0)" style="color:blue;" onclick=window.open("'+imgPath+'")>【预览】</a><a href="javascript:void(0)" style="color:blue;" onclick="removePic()")>【删除】</a>';
	            	$('#pmPic').html(html);
	            } else if(resultObj.msg == "fail"){
	            	$.messager.alert('提示','图片文件大小不得小于20K或大于5M！','warning');
	            	file = $("#chooseimg");
	        		file.after(file.clone());
	        		file.remove();
					return;
	            } else if(resultObj.msg == "error"){
	            	$.messager.alert('提示','文件解析错误，请重新上传！','warning');
	            	file = $("#chooseimg" );
	        		file.after(file.clone());
	        		file.remove();
					return;
	            }
			});
		}
		
	}else{
		$.messager.alert('提示','请选择一张图片上传！','warning');
		file = $("#chooseimg");
		file.after(file.clone());
		file.remove();
		return;
	}
}


function uploadPic(){
	$("#goodsImage").val("123")
}

