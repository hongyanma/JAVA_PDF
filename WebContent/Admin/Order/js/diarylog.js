$(document).ready(function(){
	//alert(11)
	showlogList('1');                     //显示日志列表
	showPage(); //显示页码
})
var pageNow = 1;	//显示当前页码
function showPage(){
	
	$.ajaxSetup({                    //设置同步
	    async : false  
	}); 
	
	var pageAll = 0;
	var nickname = $("#nickname").val();              
	
	$.post("./order.jhtm", {
		method : "getLogNumber",
		nickname:nickname
	}, function(json) {
		//alert(json);
		document.getElementById("all_data").innerHTML = json;          //总数据
		pageAll = Math.ceil(json/10);               //页数
	});
	
	
	layui.config({
		base: './Admin/Mode/plugins/layui/modules/'
	}); 

	layui.use(['icheck', 'laypage','layer'], function() {
		var $ = layui.jquery,
			laypage = layui.laypage,
			layer = parent.layer === undefined ? layui.layer : parent.layer;
		$('input').iCheck({
			checkboxClass: 'icheckbox_flat-green'
	});

	//page
	laypage({
		cont: 'page',
		pages: pageAll //总页数
			,
		groups: 5 //连续显示分页数
			,
		jump: function(obj, first) {
			//得到了当前页，用于向服务端请求对应数据
			var curr = obj.curr;
			if(!first) {
				showlogList(curr)     //跳转
			}
		}
	});
	});
}
function showlogList(page){
	//alert("page"+page);
	var nickname = $("#nickname").val();    
	$.ajax({
		type: "post",
		url: "./order.jhtm?method=GetLogList",
		data:{page:page,nickname:nickname},
		dataType: "json",
		success: function(json){
			//var json = data.result;
			//alert("json");
			//alert(json);
			//var number = 1+json.totalnum;
			$(".tr_data").html("");
			//var state = "";                  //状态文字
			//var spanColor = "";              //状态颜色
			var operation = "";              //操作

			for(var i=0;i<json.result.length;i++){
 				//alert(json.result[i].nickname);
				if( json.result[i].log_state == 0){
					operation = '<td class="td-manage"><a title="删除" href="javascript:;" onclick="log_del(this,'+json.result[i].log_id+')" class="ml-5" style="text-decoration:none"><i class="Hui-iconfont">&#xe6e2;</i></a></td>';
					//operation = '<td class="td-manage"><a style="text-decoration:none" href="javascript:;" onClick="member_huanyuan(this,'+json.userList[i].userId+','+json.userList[i].userState+')" title="还原"><i class="Hui-iconfont">&#xe66b;</i></a> </td>';
				}else if( json.result[i].log_state == 1 ){
					operation = '<td class="td-manage"><a title="删除" href="javascript:;" onclick="log_del(this,'+json.result[i].log_id+')" class="ml-5" style="text-decoration:none"><i class="Hui-iconfont">&#xe6e2;</i></a></td>';

                 }			
				$(".tr_data").append(
					'<tr class="text-c">'
						+'<th width="25"><input type="checkbox" name="subChk" value="'+json.result[i].log_id+'"></th>'
						+'<td>'+json.result[i].log_id+'</td>'
						+'<td>'+json.result[i].nickname+'</td>'
						+'<td>'+json.result[i].operate_time+'</td>'
						+'<td>'+json.result[i].operate_desc+'</td>'
						+''+operation+''
					+'</tr>')
			}
		}
	})
}
function showLog(page){
	showlogList(page);                     //显示日志列表
	showPage(); //显示页码
}
function changeLogState(log_id)
{
	$.ajax({
		type: "post",
		url: "./order.jhtm?method=DeleteLog",
		data:{log_id:log_id},
		dataType: "json",
		success: function(json){
			
		}
	})
}
//批量删除
function datadel(){
	//单选
	//全选
	$("#allChk").click(function(){
		$("input[name='subChk']").prop("checked",this.checked);
	});
	/*批量删除*/
	//判断是否至少选择一项
	var checkedNum = $("input[name='subChk']:checked").length;
	if(checkedNum == 0){
		alert("至少选择一项");
		return;
	}
	//批量选择
	if(confirm("确定要删除所有的选项")){
		var checkedList = new Array();
		$("input[name='subChk']:checked").each(function(){
			checkedList.push($(this).val());
		});
	$.ajax({
		type:"POST",
		url:"./order.jhtm?method=DeleteLogList",
		data:{"loglist":checkedList.toString()},
		success:function(json){
			alert("删除成功");
			setTimeout("location.reload();",1000);
		    
		}
	})
}
}
