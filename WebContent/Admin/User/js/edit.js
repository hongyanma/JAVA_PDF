$(document).ready(function(){
	showUsermessage();
})

var userId = 0;
userId = GetQueryString("userId");   //获取userId

var PicUrl = "";
var picName;
//显示用户信息
function showUsermessage() {
	
	userId = GetQueryString("userId");   //获取userId
	$.ajax({
		type: "post",
		url: "../../user.jhtm?method=showUsermessage",
		data:{userId:1},
		dataType: "json",
		success: function(json){
			alert(userId);
			$("#userAccount").val(json.UserMess.userAccount);
			$("#userEmail").val(json.UserMess.userEmail);
			$("#userPhone").val(json.UserMess.userPhone);
			$("#userNickname").val(json.UserMess.userNickname);
			if( json.UserMess.userSex == "女"){     //默认用户为男性，当用户为女性时
				alert("女")
				$("input[name='sex']:eq(0)").attr("checked",'checked');
//				$("#female").attr("checked",true);
				
			}else{
				alert("男")
				$("input[name='sex']:eq(1)").attr("checked",'checked');
//				$("#male").attr("checked",true);
			}
			picName = json.UserMess.userPic;
			PicUrl += "../../Common/Image/" + json.UserMess.userPic;
			$("#userPic").attr("src",PicUrl);
		}
	});
}


function GetQueryString(name)
{
     var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
     var r = window.location.search.substr(1).match(reg);
     if(r!=null)return  unescape(r[2]); return null;
}
