var role_id;

var user_id=getUrlParam("user_id");
//alert("user_id"+user_id)
//alert(role_id);

$(document).ready(function(){
	showOrderList("1");                     //显示订单列表
	showPage(); //显示页码
	role_id = getUrlParam("role_id");
	if(role_id== 4){  //录入员
		document.getElementById("bath_delete").style.display = "none";
		document.getElementById("add_order").style.display = "none";
		document.getElementById("look").style.display = "none";
		document.getElementById("mm-ll").style.display = "none";
		//document.getElementById("blud_u").style.visibility = "hidden";
	}else if(role_id==2){
		document.getElementById("bath_delete").style.display = "none";
		document.getElementById("add_order").style.display = "none";
		document.getElementById("look").style.display = "none";
		document.getElementById("mm-ll").style.display = "none";
	}else if(role_id==1){
		document.getElementById("bath_delete").style.display = "none";
		document.getElementById("add_order").style.display = "block";
		document.getElementById("look").style.display = "none";
		document.getElementById("mm-ll").style.display = "none";
		//$("td.td-manage").hide();
	}
	//全选
	
	
})
//地址栏传参
function getUrlParam(name) {
	var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)"); // 构造一个含有目标参数的正则表达式对象
	var r = window.location.search.substr(1).match(reg);
	if (r != null)
		return unescape(r[2]);
	return null; // 返回参数值
}
//批量删除
function dataOrderdel(){
	//单选
	//全选
	
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
		url:"./order.jhtm?method=DeleteOrderList",
		data:{"order_list":checkedList.toString()},
		dataType: "json",
		success:function(json){
			
			alert("删除成功");
			//window.location.reload();
			setTimeout("location.reload();",1000);

		}
	})
}
}
var pageNow = 1;	//显示当前页码
function showPage(){
	
	$.ajaxSetup({                    //设置同步
	    async : false  
	}); 
	
	var pageAll = 0;
	var order_id = $("#orderId").val();                //获取订单号
	
	$.post("./order.jhtm", {
		method : "getOrderNumber",
		order_name:order_id,
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
				showOrderList(curr)     //跳转
			}
		}
	});
	});
}


//显示订单列表
var page="1";
function showOrderList(page){
	
	var order_id = $("#orderId").val();                //获取订单号
	//alert("page"+page);
	$.ajax({
		type: "post",
		url: "./order.jhtm?method=getOrderList",
		data:{page:page,order_name:order_id},
		dataType: "json",
		success: function(json){
			//var json = data.result;
			//alert("json");
			//alert(json);
			var number = 1+json.totalnum;//schedule审核
			//var number=json.totalnum;
			//alert(json.totalnum)
			var text="";
			$(".tr_data").html("");
			var state = "";                  //状态文字
			var spanColor = "";              //状态颜色
		    var operation = "";              //操作    
		    var  rewider="";
		    var oper="";
		    var test="";
		    var disabled="";
			for(var i=0;i<json.result.length;i++){
							
				
				 //alert("client_name"+json.result[i].client_name);
				var edittemp = "撤回订单";
				var editurl = "Admin/Order/order-edit.html?order_id="+json.result[i].order_id+"&&user_id="+user_id+"&&edit_time="+json.result[i].edit_time+"&&locked="+json.result[i].locked+"&schedule="+json.result[i].schedule+"";//&&schedule="+json.result[i].schedule+"
				var showtemp= "订单详情";
				var showurl = "Admin/Order/order-detail.jsp?order_id="+json.result[i].order_id+"&&file_id="+json.result[i].file_id+"&&locked="+json.result[i].locked+"&role_id="+role_id+"&schedule="+json.result[i].schedule+"&file_path="+json.result[i].file_path+"&order_name="+json.result[i].order_name+"&&edit_time="+json.result[i].edit_time+"";	
				var num = json.result[i].schedule;
				var nums = json.result[i].edit_time;
				if(json.result[i].locked==0){  //未锁定
					   text="否";
					   disabled='<input  type="checkbox" name="subChk" value="'+json.result[i].order_id+'" class="SelectSingle" />';
					  oper="blud_us";
					   test="";
					}else if(json.result[i].locked==1){  //锁定
						
						text="是";
						oper="blud_u";
						test="无操作";
						disabled='<input type="checkbox" name="subChk" value="'+json.result[i].order_id+'" class="SelectSingle"  disabled="disabled" />';
					}
				if(json.result[i].schedule == 0){
					if(role_id== 1){
						state = "已录入";
						spanColor = "label label-danger radius";
						

					}else if(role_id==2){
						state = "已录入";
						spanColor = "label label-danger radius";
					}else if(role_id==4){
						state = "已录入";
						spanColor = "label label-danger radius";
					}
					else{
						state ="已录入";
						spanColor = "label label-danger radius";
						rewider="dip";
						operation = '<td class="td-manage" ><span id="'+oper+'"> '+test+'<a class="'+rewider+' ml-5"   title="撤回进度" href="javascript:;" onclick="order_edit('+'\''+edittemp+'\''+','+'\''+editurl+'\''+',500,210)" style="text-decoration:none;float:left"><i class="Hui-iconfont">&#xe6df;</i></a>  <a title="删除" href="javascript:;"  onclick="order_del(this,'+json.result[i].order_id+')" class="ml-5" style="text-decoration:none"><i class="Hui-iconfont">&#xe6e2;</i></a></span></td>';
						 
					}
					$(".tr_data").append(
							'<tr class="text-c" id="list">'
								+'<th width="25" id="th">'+disabled+'</th>'
								+'<td><u style="cursor:pointer" class="text-primary" onclick="orderdetail_show('+'\''+showtemp+'\''+','+'\''+showurl+'\''+',4,710)">'+json.result[i].order_name+'</u></td>'
								+'<td>'+json.result[i].client_name+'</td>'
								+'<td>'+json.result[i].order_time+'</td>'
								+'<td>'+json.result[i].delivery_time+'</td>'
								+'<td>'+json.result[i].explain+'</td>'
								+'<td>'+json.result[i].end_time+'</td>'
								+'<td class="td-status"><span class="'+spanColor+'">'+json.result[i].nickname+'<b>'+state+'</b></span></td>'
								+'<td class="td-status"><span class="">'+text+'</span></td>'
								+'<span id="mm-hh" >'+operation+'</span>'
							+'</tr>')	
					//operation = '<td class="td-manage"><a style="text-decoration:none" href="javascript:;" onClick="member_huanyuan(this,'+json.userList[i].userId+','+json.userList[i].userState+')" title="还原"><i class="Hui-iconfont">&#xe66b;</i></a> </td>';
				}
				else if(json.result[i].schedule==1){
					if(role_id== 1){
						//state="已完成第"+(num-1)+"次编辑";
						//state="已完成第"+num+"次编辑";
						state="已编辑"+nums+"次";
						spanColor ="label label-success radius";
						//operation = '<td class="td-manage"  id="blud_u"> <a style="text-decoration:none" onClick="book_shenhe(this,'10001')" href="javascript:;" title="审核">审核</a> <a title="修改状态" href="javascript:;" onclick="order_edit('+'\''+edittemp+'\''+','+'\''+editurl+'\''+',4,510)" class="ml-5" style="text-decoration:none"><i class="Hui-iconfont">&#xe6df;</i></a> </a> <a title="删除" href="javascript:;"  id="kk" onclick="order_del(this,'+json.result[i].order_id+')" class="ml-5" style="text-decoration:none"><i class="Hui-iconfont">&#xe6e2;</i></a></td>';
					
					}else if(role_id== 2){
						//state="已完成第"+(num-1)+"次编辑";
						//state="已完成第"+num+"次编辑";
						state="已编辑"+nums+"次";
						spanColor ="label label-success radius";
						//operation = '<td class="td-manage"  id="blud_u"> <a style="text-decoration:none" onClick="book_shenhe(this,'10001')" href="javascript:;" title="审核">审核</a> <a title="修改状态" href="javascript:;" onclick="order_edit('+'\''+edittemp+'\''+','+'\''+editurl+'\''+',4,510)" class="ml-5" style="text-decoration:none"><i class="Hui-iconfont">&#xe6df;</i></a> </a> <a title="删除" href="javascript:;"  id="kk" onclick="order_del(this,'+json.result[i].order_id+')" class="ml-5" style="text-decoration:none"><i class="Hui-iconfont">&#xe6e2;</i></a></td>';
					
					}else if(role_id== 4){
						//state="已完成第"+(num-1)+"次编辑";
						//state="已完成第"+num+"次编辑";
						state="已编辑"+nums+"次";
						spanColor ="label label-success radius";
						//operation = '<td class="td-manage"  id="blud_u"> <a style="text-decoration:none" onClick="book_shenhe(this,'10001')" href="javascript:;" title="审核">审核</a> <a title="修改状态" href="javascript:;" onclick="order_edit('+'\''+edittemp+'\''+','+'\''+editurl+'\''+',4,510)" class="ml-5" style="text-decoration:none"><i class="Hui-iconfont">&#xe6df;</i></a> </a> <a title="删除" href="javascript:;"  id="kk" onclick="order_del(this,'+json.result[i].order_id+')" class="ml-5" style="text-decoration:none"><i class="Hui-iconfont">&#xe6e2;</i></a></td>';
					
					}
					else{
						//state="已完成第"+(num-1)+"次编辑";
						//state="已完成第"+num+"次编辑";
						state="编辑第"+nums+"次";						
						spanColor ="label label-success radius";
						rewider="dip";
						operation = '<td class="td-manage"><span id="'+oper+'">'+test+'  <a class="'+rewider+' ml-5"   title="撤回进度" href="javascript:;" onclick="order_edit('+'\''+edittemp+'\''+','+'\''+editurl+'\''+',500,210)"  style="text-decoration:none;float:left"><i class="Hui-iconfont">&#xe6df;</i></a> </a> <a title="删除" href="javascript:;"   onclick="order_del(this,'+json.result[i].order_id+')" class="ml-5" style="text-decoration:none"><i class="Hui-iconfont">&#xe6e2;</i></a></span></td>';
						
					}
					$(".tr_data").append(
							'<tr class="text-c" id="list">'
								+'<th width="25" id="th">'+disabled+'</th>'
								+'<td><u style="cursor:pointer" class="text-primary" onclick="orderdetail_show('+'\''+showtemp+'\''+','+'\''+showurl+'\''+',4,510)">'+json.result[i].order_name+'</u></td>'
								+'<td>'+json.result[i].client_name+'</td>'
								+'<td>'+json.result[i].order_time+'</td>'
								+'<td>'+json.result[i].delivery_time+'</td>'
								+'<td>'+json.result[i].explain+'</td>'
								+'<td>'+json.result[i].end_time+'</td>'
								+'<td class="td-status"><span class="'+spanColor+'">'+json.result[i].nickname+'<b>'+state+'</b></span></td>'
								+'<td class="td-status"><span class="">'+text+'</span></td>'
								+'<span id="mm-hh" >'+operation+'</span>'
							+'</tr>')	
					
				}	

			}
		}
	})
}

//根据订单号查找订单
function showOrderById(page){
	showOrderList(page);                     //显示订单列表\
	showPage(); //显示页码
}
//删除订单状态
function changeOrderState(order_id){
	
	$.ajax({
		type: "post",
		url: "./order.jhtm?method=DeleteOrder",
		data:{orderid:order_id},
		dataType: "json",
		success: function(json){
			//alert("删除成功");
			window.location.reload();
			//setTimeout("location.reload();",1000);
		}
	})
}

