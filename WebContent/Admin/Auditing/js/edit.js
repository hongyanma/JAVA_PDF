var schedule;
var user_id;
user_id = GetQueryString("user_id");
//alert("user_id"+user_id)
$(document).ready(function(){
	show("1");
	//alert(1111111111);
	
	$('#selectSchedule').change(function(){
		schedule = $("#selectSchedule").val();
		//alert(schedule)
	})
	//alert(11);
})

var order_id = "0";
var page="1";
function show(page) {
	
	order_id = GetQueryString("order_id");   //获取订单编号
	//alert(order_id)
	$.getJSON("http://localhost:8080/pdf_2017-07-07/order.jhtm", {
		method:"getSchedulList",
		page:page
	}, function(json){
		var number = 2+json.totalnum;//schedule审核
		var num = number+1;
		var state = ""; 
		
		var schedule = new Array(number);
		 schedule[0]=0;  //已录入
		 schedule[1]=1;  //已翻译
		 schedule[number]=number;//已审核
		for(var i=0;i<=number;i++){
			//var s = new Array(num); //编辑员数
			schedule[i+2]=i+2;
			//alert(111)
			if(schedule[i] == 0){
				state = "已录入";
			}else if(schedule[i] == 1 ){
				state = "已翻译";
			}else if(schedule[i]>=2&&schedule[i]<number){
				state="已编辑"+(i-1);
			}else if(schedule[i] >=number){
				state="已审核";
			}
          $("#selectSchedule").append('<option value="'+schedule[i]+'">'
                  +state+'</option>');
	}
	});
}
var schedule;
function changeSchedule(){
	var text="";
	var order_id = GetQueryString("order_id");   //获取订单编号
	
	$.getJSON("http://localhost:8080/pdf_2017-07-07/order.jhtm", {
		method: "UpdateOrder",
		order_id:order_id,
		user_id:user_id,
		schedule:schedule
	}, function(flag){
		if(flag==0){
			 alert("修改失败");
		}else{
		  alert("修改成功");
		}
          // window.location.href="Admin/Index/index.html";
           //window.location.href = document.referrer;
		});
}
function GetQueryString(name)
{
     var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
     var r = window.location.search.substr(1).match(reg);
     if(r!=null)return  unescape(r[2]); return null;
}