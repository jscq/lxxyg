//刷新iframe中DataGrid
var loadDataGrid = function(type)
{
	if(type=='function')
	{	
		// 系统管理模块---菜单管理
		$(window.parent.document).contents().find("#iframe")[0].contentWindow.searchFunction();
	}else if(type=='code')
	{	
		// 系统管理模块---数据字典管理
		$(window.parent.document).contents().find("#iframe")[0].contentWindow.searchCode();
	}else if(type=='area')
	{	
		// 系统管理模块---行政区域管理
		$(window.parent.document).contents().find("#iframe")[0].contentWindow.searchArea();
	}else if(type=='user')
	{	
		// 系统管理模块---系统用户管理
		$(window.parent.document).contents().find("#iframe")[0].contentWindow.searchUser();
	}
	
	else if(type=='classify')
	{	
		// 电商管理模块---商品分类
		$(window.parent.document).contents().find("#iframe")[0].contentWindow.searchClassify();
	}else if(type=='goods')
	{	
		// 电商管理模块---商品
		$(window.parent.document).contents().find("#iframe")[0].contentWindow.searchGoods();
	}
	
	
}



/**
 * 重置
 */
var resetSearchFrom = function(ff){
	var elements = ff.elements;
    for(var i=0;i<elements.length;i++){
        var element = elements[i];
        if(element.type=="text"){
            element.value = "";
        }else if(element.type=="radio" || element.type=="checkbox"){
        	element.checked = false;
        }else if(element.options!=null){
        	element.options[0].selected  = true;
        }
    }  
    
    // 菜单管理清空树
    $("#parentId").val("");
    
    // 清空专业选择
    $('#majorSelect option').remove();
	$("#majorSelect").append("<option value=''>--请选择--</option>");
};