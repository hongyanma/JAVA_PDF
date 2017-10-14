$(document).ready(function(){
	//alert(111111);
	showUserEditList("1"); 
	//showPage();
})

function showUserEditList(page){
	$.ajax({
		type:"post",
		url:"./user.jhtm?method=getUserEditList",
		data:{page:page},
		dataType: "json",
		success: function(json){
			//alert(111);
			$(".tr_data").html("");
			for(var i =0;i<json.result.length;i++){
				$(".tr_data").append(
					'<tr class="text-c">'
						+'<td>'+json.result[i].edit_id+'</td>'
						+'<td>'+json.result[i].nickname+'</td>'
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
	
	$.post("./user.jhtm", {
		method : "getUserEditNumber",
	}, function(json) {
		alert(json);
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
				showUserEditList(curr)     //跳转
			}
		}
	});
	});
}
