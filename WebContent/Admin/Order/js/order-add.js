
$(function(){
	
})
 

/*添加订单*/
//function addOrder(){
//	
//	var custom = $("#custom").val();          //客户名称
//	var date01 = $("#date01").val();     //接单时间
//	var date03 = $("#date03").val(); //接单截止时间
//	var date02 = $("#date02").val();  //交货时间
//	var orderdescribe = $("#orderdescribe").val();//订单说明
//	var input_text1 = $("#input-text1").val(); //备注
//	var file_name = $("#fileId1");
//	if( custom =="" && orderdescribe=="" || input_text1=="" ){
//		alert("客户名称，订单说明和备注不能为空")
//		return;
//	}
//	alert(11)
//	//var result = true;
//	
//	$.ajax({
//		type:"post",
//		url:"./order.jhtm?method=AddOrder",
//		async:false,
//		data:{
//			user_id:GetQueryString("user_id"),
//			client_name:custom,
//			order_time:date01,
//			end_time:date03,
//			delivery_time:date02,
//			explain:orderdescribe,
//			remark:input_text1,
//			file_name:file_name
//		},
//		datatype:"json",
//		success:function(json){
//			alert(json);
//			//result = json;
//	    	  
//		}
//	})
//	//return result;
//}
