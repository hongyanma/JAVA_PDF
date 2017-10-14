var role_id=GetQueryString("role_id");
var schedule = GetQueryString("schedule");
var locked=GetQueryString("locked");
var file_id = GetQueryString("file_id");
var order_name = GetQueryString("order_name");
var edit_time = GetQueryString("edit_time");
//alert(edit_time)
//alert(file_id);
$(document).ready(function(){
	showOrdermessage(); //显示订单详情信息
	selectedition();  //显示历史文件信息
	$('#selectFile').change(function(){
		$('#testPdf').attr('src',"/pdf_file/"+this.value);
	})
	if(role_id==2){
		document.getElementById("ResumeInfo_Button").style.display="none";
		document.getElementById("upload_Button").style.display="none";
	}else if(role_id==4){
		document.getElementById("ResumeInfo_Button").style.display="none";
		document.getElementById("upload_Button").style.display="none";
	}
	
	if(locked==1&&role_id==3){
		document.getElementById("ResumeInfo_Button").style.display="block";
		document.getElementById("upload_Button").style.display="block";
	}else if(locked==0&&role_id==3){
		document.getElementById("ResumeInfo_Button").style.display="block";
		document.getElementById("upload_Button").style.display="block";
	}
	else if(schedule==2){
		document.getElementById("ResumeInfo_Button").style.display="none";
		document.getElementById("upload_Button").style.display="none";
	}
	
	if(locked==1&&role_id==1){
		document.getElementById("ResumeInfo_Button").style.display="block";
		document.getElementById("upload_Button").style.display="block";
	}else if(locked==0&&role_id==1){
		document.getElementById("ResumeInfo_Button").style.display="block";
		document.getElementById("upload_Button").style.display="block";
	}

//	$("#ResumeInfo_Button").click(function(){
//		if(locked==1){
//			alert("文件已锁定，不能下载");
//		}
//		event.preventDefault();
//   })
})
function validate_form()
{
	   var path = document.getElementById("file_path").value;
       var pos1 = path.lastIndexOf("\\");
       var pos2 = path.lastIndexOf(".");
       var pos = path.substring(pos1 + 1, pos2);
       //alert(path.substring(pos1+1,pos2));
	if(pos!=file_id){
		alert("文件名不一样");
		return false;
	  }else{
		  return true;
	  }
} 
var f=1; 
function readyForDownLoad(){
	var file_id = GetQueryString("file_id");
	//var url=downLoadUrl;
     $.ajax({ 
       type: "POST", 
       url: "../../order.jhtm?method=DownloadForModify", 
       data: {file_id:file_id,order_id:order_id},
       success: function (data) {
           if(data!=null){
        	   alert("url="+data);
              // window.location.href="../../order.jhtm?method=DownloadForModify&file_id="+file_id"&&order_id="+order_id; //执行下载操作
           }else{
               alert('下载错误,文件不存在!!');
           }
       },
       error: function (fn) {
          alert('操作错误!!');
       }
   });
   return false;
}
//下载与修改
function open_disp(){
	//var returnFilePath = "";
	//if(1==f) 
	//{ 
	
//	var file_id = GetQueryString("file_id");  
//	$.ajax({
//		type:"POST",
//		url:"../../order.jhtm?method=DownloadForModify",
//		data:{file_id:file_id,order_id:order_id},
//		success:function(data){
//			alert(data)
//			alert(111111111111111)
//		console.log(data);
//		if(data!=null){
//		alert("url="+data);
//		window.location.href = data;
//		}else{
//		alert("资源获取失败！");
//		}
//		}
//		});
	//if(locked==1){
	//	alert("该订单已被锁定,无法下载与修改")
		//document.getElementById("ResumeInfo_Button").style.display="block"; 
		//document.getElementById("ResumeInfo_Modify_Button").style.display="none"; 
	//}else{
			
		//$.getJSON("../../order.jhtm", {
			//method: "DownloadForModify",
			///file_id:file_id,
			//order_id:order_id		
		//}, function(json){
			//console.log(json);
			//returnFilePath = json;
		//});
		// return returnFilePath;
		//document.getElementById("ResumeInfo_Button").style.display="none"; 
		//document.getElementById("ResumeInfo_Modify_Button").style.display="block"; 
	//}
}
//提交
function open_disps(){

	//if(1==f) 
	//{ 
	document.getElementById("ResumeInfo_Button").style.display="block"; 
	document.getElementById("ResumeInfo_Modify_Button").style.display="none"; 
	//document.getElementById("butn").style.display="none";
	//} 
	//else 
	//{ 
	//document.getElementById("ta2").style.display="none"; 
	//document.getElementById("ta1").style.display="block";  
	//} 
	//f=f*-1; 
}
//取消保存
function SwitchToShow(){
	document.getElementById("ta2").style.display="none"; 
	document.getElementById("ta1").style.display="block";
	document.getElementById("butn").style.display="block";
}
var userId = 0;
var PicUrl = "";
var picName;
//显示信息
function showOrdermessage() {
	
	order_id = GetQueryString("order_id");   //获取order_id
	var file_id = GetQueryString("file_id");  
	//alert(file_id);
	//alert(order_id)
	var statetext = "";
	$.getJSON("../../order.jhtm", {
		method: "GetOrderDetail",
		order_id:order_id
		
	}, function(json){
		console.log(json);
	$("#order_id").html(json.result.order_name);
		$("#clicent_name").html(json.result.client_name);
		$("#order_time").html(json.result.order_time);
		$("#deliever_time").html(json.result.delivery_time);
		$("#time_uu").html(json.result.end_time);
		$("#explin").html(json.result.explain);
		$("#remark").html(json.result.remark);
		$("#luru").html(json.result.nickname);
		//$("#pdf").html('<a id="testPdf" href="pdf.jsp?order_id='+order_id+'"  width="1080" height="600">pdf地址</a>')
    	//$("#pdf").html('<a id="testPdf" onclick="toUrl(/pdf_file/'+json.result.file_path+')"  width="1080" height="600">pdf地址</a>')
		 //$("#pdf").html('<a id="testPdf" class="media" href="/pdf_file/'+json.result.file_path+'">PDF File</a>'); 
		if(json.result.edition==1){
			$("#pdf").html('<iframe id="testPdf" class="" src="/pdf_file/'+json.result.file_path+'" width="1100" height="1000"></iframe>'); 
			//$("#pdf").html('<a class="media" href="/pdf_file/'+json.result.file_path+'" style="margin-left:50px;">查看pdf</a>'); 
		}
		
	});
	
}


/*function toUrl(url){
	window.open(url);
}*/

function selectedition(){
	order_id = GetQueryString("order_id");   //获取order_id
	var text = "";
	$.getJSON("../../order.jhtm", {
		method: "getOrderHis",
		order_id:order_id
	}, function(json){
		console.log(json);
            for(var i=0;i<json.result.length;i++) {
            	var num = json.result[i].edit_time;
            	//alert(num)
            	var schedule ;
            	var url;
            	 if(json.result[i].edit_time==edit_time&&json.result[i].edition==1){
             		text="最新文件";
     			}
            	 else if(json.result[i].edit_time==0&&json.result[i].edition!=1){
    				text="已录入文件";
    			}
                //else if(json.result[i].schedule==1){
    				//text="已翻译文件";
    			//}
                else if(json.result[i].edition!=1&&json.result[i].edit_time>=1&&json.result[i].edit_time<=json.result.length){
    				//text="已编辑"+(num+1)+"文件";
                	text="已编辑"+num+"文件";
                	//text="已编辑";
    			}
            	$("#selectFile").append('<option value="'+json.result[i].file_path+'">'+text+'</option> ')
               //$("#pdfls").append('<li><span>'+text+'<span><a href="/pdf_file/'+json.result[i].file_path+'" style="margin-left:50px;">查看或打印pdf</a></li>');
//              $("#pdfls").append('<li><span>'+text+'<span><a onclick="toUrl(/pdf_file/'+json.result.file_path+')" target="_parent" style="margin-left:50px;">查看或打印pdf</a></li>');
                var file_id = json.result[i].file_id; 
            }
            
			
	});
}


function GetQueryString(name)
{
     var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
     var r = window.location.search.substr(1).match(reg);
     if(r!=null)return  unescape(r[2]); return null;
}