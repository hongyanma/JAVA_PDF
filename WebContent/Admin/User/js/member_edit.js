
$(document).ready(function(){
	showUserMessage();
	
})
function showUserMessage(){
	var user_id=getUrlName("user_id");
	$.getJSON("../../user.jhtm", {
		method: "UserDetail",
		user_id:user_id
		
	}, function(json){
		console.log(json);
		var test="";
		var text="";
		$("#account").val(json.result.account);
		//alert(account)
		$("#password").val(json.result.password);
		$("#mail").val(json.result.mail);
		$("#c1").val(json.result.color);
		//alert(json.result.color)
		$("#userNickname").val(json.result.nickname);
		//alert(json.result.nickname)
		if(json.result.role_id==1){
			test="录入员 ";
			$("#selectAge").append('<option value="'+json.result.role_id+'">'+test+'</option>');
		}else if(json.result.role_id=2){
			test="编辑员";
			$("#selectAge").append('<option value="'+json.result.role_id+'">'+test+'</option>');
		}else if(json.result.role_id==3){
			test="审核员";
			$("#selectAge").append('<option value="'+json.result.role_id+'">'+test+'</option>');
		}else if(json.result.role_id==4){
			test="普通用户";
			$("#selectAge").append('<option value="'+json.result.role_id+'">'+test+'</option>');
		}
		//alert(json.result.role_id)
		
		if(json.result.sex==0){
			text="女";
		}else if(json.result.sex==1){
			text="男";
		}
		$("#sex").val(json.result.sex);
	});
	
}
//获得url参数
function getUrlName(name){
     var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
     var r = window.location.search.substr(1).match(reg);
     if(r!=null)return  unescape(r[2]); return null;
}
