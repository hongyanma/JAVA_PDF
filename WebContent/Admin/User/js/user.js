$(document).ready(function(){
	//alert(111111);
	showUserList("1");                     //显示第一页用户列表
	showPage();                          //显示页码
	
})
//批量删除
function datadelUser(){
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
			//alert(checkedList);
		});
	$.ajax({
		type:"POST",
		url:"./user.jhtm?method=DeleteUserList",
		data:{"user_list":checkedList.toString()},
		success:function(json){
			alert("删除成功");
			setTimeout("location.reload();",1000);
		    
		}
	})
  }
}
//显示用户列表
var page="1";
function showUserList(page){
	//alert(11111111111111);
	var user_name = $("#user_name").val();
	$.ajax({
		type:"post",
		url:"./user.jhtm?method=getUserList",
		data:{page:page,user_name:user_name},
		dataType: "json",
		success: function(json){
			//alert(111);
			$(".tr_data").html("");
			var state = "";                  //状态文字
			var text = "";  //性别
			var operation = "";              //操作

			for(var i =0;i<json.result.length;i++){
				
				var edittemp = "编辑";
				var editurl = "Admin/User/member-edit.jsp?user_id="+json.result[i].user_id+"&account="+json.result[i].account+"&password="+json.result[i].password+"&mail="+json.result[i].mail+"&color="+json.result[i].color+"&nickname="+json.result[i].nickname+"&role_id="+json.result[i].role_id+"&sex="+json.result[i].sex+"&color="+json.result[i].color+"";
				if( json.result[i].role_id == 1){
					
					state = "录入员";
					operation = '<td class="td-manage"><a title="编辑" href="javascript:;" onclick="member_edit('+'\''+edittemp+'\''+','+'\''+editurl+'\''+',4,510)" class="ml-5" style="text-decoration:none"><i class="Hui-iconfont">&#xe6df;</i></a><a title="删除" href="javascript:;" onclick="member_del(this,'+json.result[i].user_id+')" class="ml-5" style="text-decoration:none"><i class="Hui-iconfont">&#xe6e2;</i></a></td>';
				}
//				else if( json.result[i].role_id == 2){
//					
//					state = "翻译员";
//
//					operation = '<td class="td-manage"><a title="编辑" href="javascript:;" onclick="member_edit('+'\''+edittemp+'\''+','+'\''+editurl+'\''+',4,510)" class="ml-5" style="text-decoration:none"><i class="Hui-iconfont">&#xe6df;</i></a><a title="删除" href="javascript:;" onclick="member_del(this,'+json.result[i].user_id+')" class="ml-5" style="text-decoration:none"><i class="Hui-iconfont">&#xe6e2;</i></a></td>';
//
//				}
				else if( json.result[i].role_id == 2){
					state = "编辑员";

					operation = '<td class="td-manage"><a title="编辑" href="javascript:;" onclick="member_edit('+'\''+edittemp+'\''+','+'\''+editurl+'\''+',4,510)" class="ml-5" style="text-decoration:none"><i class="Hui-iconfont">&#xe6df;</i></a><a title="删除" href="javascript:;" onclick="member_del(this,'+json.result[i].user_id+')" class="ml-5" style="text-decoration:none"><i class="Hui-iconfont">&#xe6e2;</i></a></td>';

				}else if( json.result[i].role_id == 3){
					state = "管理员";

					operation = '<td class="td-manage"><a title="编辑" href="javascript:;" onclick="member_edit('+'\''+edittemp+'\''+','+'\''+editurl+'\''+',4,510)" class="ml-5" style="text-decoration:none"><i class="Hui-iconfont">&#xe6df;</i></a><a title="删除" href="javascript:;" onclick="member_del(this,'+json.result[i].user_id+')" class="ml-5" style="text-decoration:none"><i class="Hui-iconfont">&#xe6e2;</i></a></td>';

				}else if( json.result[i].role_id == 4){
					state = "普通用户";

					operation = '<td class="td-manage"><a title="编辑" href="javascript:;" onclick="member_edit('+'\''+edittemp+'\''+','+'\''+editurl+'\''+',4,510)" class="ml-5" style="text-decoration:none"><i class="Hui-iconfont">&#xe6df;</i></a><a title="删除" href="javascript:;" onclick="member_del(this,'+json.result[i].user_id+')" class="ml-5" style="text-decoration:none"><i class="Hui-iconfont">&#xe6e2;</i></a></td>';

				}
		        if(json.result[i].sex==1){
		        	text="男";
		        }else if(json.result[i].sex==0){
		        	text="女";
		        }
				$(".tr_data").append(
					'<tr class="text-c">'
						+'<th width="25"><input type="checkbox" name="subChk" value="'+json.result[i].user_id+'"></th>'
						+'<td>'+json.result[i].user_id+'</td>'
						+'<td>'+json.result[i].nickname+'</td>'
						+'<td>'+text+'</td>'
						+'<td>'+json.result[i].account+'</td>'
						+'<td>'+json.result[i].password+'</td>'
						+'<td>'+json.result[i].mail+'</td>'
						+'<td class="td-status"><span class="">'+state+'</span></td>'
						+''+operation+''
					+'</tr>')
			}
		}
	})
}


var pageNow = 1;	//显示当前页码
//显示页码
function showPage(){
	
	$.ajaxSetup({                    //设置同步
	    async : false  
	}); 
	
	var pageAll = 0;
	var user_name = $("#user_name").val();
	
	$.post("./user.jhtm", {
		method : "getUserNumber",
		user_name:user_name,
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
				showUserList(curr)     //跳转
			}
		}
	});
	});
}

//根据用户名查找用户
function shouUserByName(page){
	
	showUserList(page);
	showPage();
}

//删除用户状态
function changeUserState(user_id){
	
	$.ajax({
		type: "post",
		url: "./user.jhtm?method=DeleteUser",
		data:{user_id:user_id},
		dataType: "json",
		success: function(json){
			 shouUserList(pageNow);
			 showPage();
		}
	})
}
//获得url参数
function getUrlName(name){
     var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
     var r = window.location.search.substr(1).match(reg);
     if(r!=null)return  unescape(r[2]); return null;
}
