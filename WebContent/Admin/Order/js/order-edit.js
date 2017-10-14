var schedule;
var user_id;
user_id=GetQueryString("user_id");
var locked=GetQueryString("locked");
var order_id=GetQueryString("order_id");
var schedule=GetQueryString("schedule");
//alert(schedule)
 var  edit_time=  GetQueryString("edit_time"); 
var edits_time ;//alert(edit_time);
//alert(locked)	//alert("user_id"+user_id)
$(document).ready(function(){
	show();
	
	$('#selectSchedule').change(function(){
		edits_time = $("#selectSchedule").val();
		//alert(edits_time)
	})
	//alert(11);
})

	
/*订单-审核*/
function order_change(obj){	
	//alert(1);
	layer.confirm('确认审核？',function(index){
		$(obj).parents("tr").remove(); 		
		layer.msg('已审核!',{icon:1,time:1000}); 
		changeSchedules(order_id);
	});
	//parent.location.reload();
}

//审核订单
function changeSchedules(order_id){

		$.getJSON("../../order.jhtm", {
			method: "ReviewOrder",
			order_id:order_id,
			//schedule:schedule,
			user_id:user_id,
			//judge:1
		}, function(json){
			console.log(json);
			alert("已审核")
			parent.location.reload(); //刷新父窗口中的网页

			window.close();//关闭当前窗窗口
			});
	
}
var order_id = "0";
var page="1";
function show(){
	order_id = GetQueryString("order_id");   //获取order_id
	var text = "";
	
		$.getJSON("../../order.jhtm", {
			method: "getOrderHis",
			order_id:order_id
		}, function(json){
			console.log(json);
	            for(var i=0;i<json.result.length;i++) {
	            	var num = json.result[i].schedule;
	            	var number = json.result[i].edit_time;
	            	var schedule ;
	            	var url;
	                if(json.result[i].edit_time==0&&json.result[i].edit_time!=edit_time&&json.result[i].edition!=1){
	    				text="已录入";
	    				 $("#selectSchedule").append('<option value="'+json.result[i].edit_time+'">'
	   	             		  +'<span>'+json.result[i].modify_time+'</span>&nbsp;&nbsp;&nbsp;&nbsp;'
	   	              		  +'<span style="margin-left:120px;">'+text+'<span>'
	   	             		  +'</option>');
	    			}
	                else if(json.result[i].edition!=1&&json.result[i].edit_time>=1&&json.result[i].edit_time<json.result.length&&json.result[i].edit_time!=edit_time){
	                	text="已编辑"+number+"文件";
	                	//text="已编辑";
	                	 $("#selectSchedule").append('<option value="'+json.result[i].edit_time+'">'
	   	             		  +'<span>'+json.result[i].modify_time+'</span>&nbsp;&nbsp;&nbsp;&nbsp;'
	   	              		  +'<span style="margin-left:120px;">'+text+'<span>'
	   	             		  +'</option>');
	                	
	    			}
	               
	            }
	            
				
		});			
}

//function show(page) {
//	$.getJSON("../../order.jhtm",{
//		method:"getSchedulList",
//		page:page
//	},function(json){		
//		var number = 1+json.totalnum;//schedule审核
//		var num = number+1;
//		var state = ""; 
//		
//		var schedule = new Array(number);
//		 schedule[0]=0;  //已录入
//		// schedule[1]=1;  //已翻译
//		 schedule[number]=number;//已审核
//		for(var i=0;i<=number;i++){
//			//var s = new Array(num); //编辑员数
//			schedule[i+1]=i+1;
//			//alert(111)
//			if(schedule[i] == 0){
//				state = "已录入";
//			}
////			else if(schedule[i] == 1 ){
////				state = "已翻译";
////			}
//			else if(schedule[i]>=1&&schedule[i]<number){
//				//state="已编辑"+(i-1);
//				state="已编辑"+i;
//			}else if(schedule[i] >=number){
//				state="已审核";
//			}
//          $("#selectSchedule").append('<option value="'+schedule[i]+'">'
//        		  +'<span>'+json.result[i].modify_time+'</span>&nbsp;&nbsp;&nbsp;&nbsp;'
//        		  +'<span style="margin-left:120px;">'+state+'<span>'
//        		  +'</option>');
//	}
//	});
//}

function changeSchedule(){
	var text="";
	
	    var order_id = GetQueryString("order_id");   //获取订单编号
	   
	   ///if(locked==1){
			//alert("该订单已被锁定，无法操作")
	//	}else{
	    $.getJSON("../../order.jhtm", {
		method: "withdraw",
		order_id:order_id,
		user_id:user_id,
		edit_time:edits_time
		//schedule:schedule
	},function(flag){
		if(flag!=0){
			 alert("撤回成功");
			 parent.location.reload(); //刷新父窗口中的网页
             window.close();//关闭当前窗窗口
		}else{
		  alert("撤回失败");
		}
		});
	}

function GetQueryString(name)
{
     var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
     var r = window.location.search.substr(1).match(reg);
     if(r!=null)return  unescape(r[2]); return null;
}
