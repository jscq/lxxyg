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
	add(Utils.getRootPath()+'/goods/editGoods?goods.id='+id,'编辑商品',460,510); 
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
	
	$("#zhekou").text("")
	$("#goodsOriginalPrice").val("")
	$("#goodsDiscount").val("")
	
	if(value == 1){
		$("#originalPrice").show();
		$("#discount").show();
	}else if(value == 2){
		$("#originalPrice").hide();
		$("#discount").hide();
	}
}

/**
 * 计算折扣
 * @returns
 */
function jisuanZheKou(){
	var goodsOriginalPrice = $("#goodsOriginalPrice").val()
	var goodsPrice = $("#goodsPrice").val()

	if(goodsOriginalPrice != '' && goodsOriginalPrice != 0  && goodsPrice != '' && goodsPrice != 0 ){
		$("#zhekou").text(Digit.round(goodsPrice * 10/goodsOriginalPrice,1))
		$("#goodsDiscount").val(Digit.round(goodsOriginalPrice * 10/goodsPrice,1))
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

			$("#registerForm").ajaxSubmit(function(result){
				result = result.replace("<pre>","");
				result = result.replace("<PRE>","");
				result = result.replace("</pre>","");
				result = result.replace("</PRE>","");
				var resultObj = eval('('+result+')');
				
	            if (resultObj.msg == "success"){
	            	$("#goodsImage").val(resultObj.filePath + resultObj.fileName);
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
			
			//var version = $.browser.version;
			
			$("#registerForm").ajaxSubmit(function(result){
				result = result.substring(result.indexOf("{"),result.indexOf("}")+1);
				var resultObj = eval('('+result+')');
				
	            if (resultObj.msg == "success"){
	            	$("#goodsImage").val(resultObj.filePath + resultObj.fileName);
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


//图片路径处理
function getFileName(path){
	var pos1 = path.lastIndexOf('/');
	var pos2 = path.lastIndexOf('\\');
	var pos = Math.max(pos1, pos2);
	return path.substring(pos+1);
}

