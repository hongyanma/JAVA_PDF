<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort()+ path + "/";
    String file_id = request.getParameter("file_id");
    String order_id=request.getParameter("order_id");
    String file_path = request.getParameter("file_path");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<LINK rel="Bookmark" href="/favicon.ico" >
<LINK rel="Shortcut Icon" href="/favicon.ico" />
<!--[if lt IE 9]>
<script type="text/javascript" src="lib/html5.js"></script>
<script type="text/javascript" src="lib/respond.min.js"></script>
<script type="text/javascript" src="lib/PIE_IE678.js"></script>
<![endif]-->
<link rel="stylesheet" type="text/css" href="<%=basePath%>Admin/Mode/static/h-ui/css/H-ui.min.css" />
<link rel="stylesheet" type="text/css" href="<%=basePath%>Admin/Mode/static/h-ui.admin/css/H-ui.admin.css" />
<link rel="stylesheet" type="text/css" href="<%=basePath%>Admin/Mode/lib/Hui-iconfont/1.0.7/iconfont.css" />
<link rel="stylesheet" type="text/css" href="<%=basePath%>Admin/Mode/lib/icheck/icheck.css" />
<link rel="stylesheet" type="text/css" href="<%=basePath%>Admin/Mode/static/h-ui.admin/skin/default/skin.css" id="skin" />
<link rel="stylesheet" type="text/css" href="<%=basePath%>Admin/Mode/static/h-ui.admin/css/style.css" />
<!--[if IE 6]>
<script type="text/javascript" src="http://lib.h-ui.net/DD_belatedPNG_0.0.8a-min.js" ></script>
<script>DD_belatedPNG.fix('*');</script>
<![endif]-->
<!--/meta 作为公共模版分离出去-->
<link href="<%=basePath%>Admin/Mode/lib/webuploader/0.1.5/webuploader.css" rel="stylesheet" type="text/css" />
</head>
<body onUnload="opener.location.reload();">

<div class="cl pd-20" style=" background-color:#5bacb6;">
  <dl style="margin-left:80px; color:#fff">
    <dt><span class="f-18" style="margin-top:30px">订单详情</span>
    <span style="display: block;" class="updateBtns" id="upload_Button">
      <a href = "javascript:void(0)" onclick = "document.getElementById('light').style.display='block';document.getElementById('fade').style.display='block'" style="color:white">上传</a>  
   </span>
    <span style="display: block;" class="updateBtn" id="ResumeInfo_Button">
      <a href="../../order.jhtm?method=DownloadForModify&file_id=<%=file_id%>&order_id=<%=order_id %>" style='color:white;' onclick="window.location.reload">下载与修改</a>    
   </span>	
   
  </dl>
</div>
<div id="light" class="white_content">
      <a href = "javascript:void(0)" onclick = "document.getElementById('light').style.display='none';document.getElementById('fade').style.display='none'">关闭</a>
      <form class="form form-horizontal" action="../../fileupload.jhtm" enctype="multipart/form-data"  method="post" onSubmit="return validate_form()" id="order-add" >
		  <div class="piv">
			 <span>当前文件:<b ><%=file_path %></b></span>
		</div>
		 <div class="piv-1" >
			 <input class="" id="file_path" type="file" accept="application/pdf" name="file_path" />
		</div>
		 <div class="piv-2">
				<input class="btn btn-primary radius" type="submit" value="&nbsp;&nbsp;上传&nbsp;&nbsp;" onclick="location.reload();">
		</div>
	</form>	
</div> 
<div id="fade" class="black_overlay"></div> 
<div class="pd-20">
  <table class="table" id="ta1">
    <tbody>
      <tr>
        <th class="text-r" width="80">订单号：</th>
        <td id="order_id">112300</td>
      </tr>
      <tr>
        <th class="text-r">客户名称：</th>
        <td id="clicent_name"></td>
      </tr>
      <tr>
        <th class="text-r">接单时间：</th>
        <td id="order_time"></td>
      </tr>
      <tr>
        <th class="text-r">交货时间：</th>
        <td id="deliever_time"></td>
      </tr>
      <tr>
        <th class="text-r">订单说明：</th>
        <td id="explin"></td>
      </tr>
      <tr>
        <th class="text-r">订单备注：</th>
        <td id="remark"></td>
      </tr>
      <tr>
        <th class="text-r">录入人：</th>
        <td id="luru">张三</td>
      </tr>
      <tr>
        <th class="text-r" style="width:120px">历史文件提交时间：</th>
        <td id="time_uu">2017-5-9</td>
      </tr>
  <!--     <tr>
        <th class="text-r">审核人：</th>
        <td id="shenhe">刘希</td>
      </tr> -->
      <tr>
        <th class="text-r">历史文件：</th>
        <td>
           <div class="layui-inline">
			<div style="width:250px;margin-left:1px;float:left;">
			 <ul id="pdfls">
			   <li></li>
			   
			 </ul>
		    <select name="selectFile" id="selectFile"> 
		        <!--<option value="1">最新文件</option>    
		        <option value="2">已录入的文件</option>   
		        <option value="3">已翻译的文件</option>   
		        <option value="4">已编辑的文件</option>   
		        <option value="5">已审核的文件</option> -->            
     		  </select> 
		     </div>
		  </div>
		</td>
		
      </tr>
     <!--  <tr>
        <td>：<span id="time_uu">2017-6-05</span></td>
      </tr> -->
      <tr>
          <th class="text-r">备注：</th>
          <td id="remark" style="color:red">如需打印:请鼠标移至pdf显示区</td>
      </tr>
    </tbody>
  </table>	
</div>
<div id="pdf">
 <%--  <iframe src="<%=basePath%>Admin/pdf/web/demo.html" width="1080" height="600"></iframe> --%>>
</div>


<script type="text/javascript" src="<%=basePath%>Admin/Mode/lib/jquery/1.9.1/jquery.min.js"></script> 
<script type="text/javascript" src="<%=basePath%>Admin/Mode/static/h-ui.admin/js/H-ui.admin.js"></script>
<script type="text/javascript" src="<%=basePath%>Admin/Mode/lib/layer/2.1/layer.js"></script>
<script type="text/javascript" src="js/orderdetail-show.js"></script>
<script type="text/javascript" src="<%=basePath%>Admin/Mode/static/h-ui/js/H-ui.js"></script>
<script type="text/javascript" src="<%=basePath%>Admin/Mode/lib/jquery.validation/1.14.0/jquery.validate.min.js"></script> 
<script type="text/javascript" src="<%=basePath%>Admin/Mode/lib/jquery.validation/1.14.0/validate-methods.js"></script> 
<script type="text/javascript" src="<%=basePath%>Admin/Mode/lib/jquery.validation/1.14.0/messages_zh.min.js"></script> 
<script type="text/javascript" src="<%=basePath%>Admin/Mode/plugins/layui/layui.js"></script>

<!-- <script type="text/javascript" src="js/jquery-1.7.js"></script> -->
<!-- <script type="text/javascript" src="js/jquery.media.js"></script> -->
<script language=JavaScript>
//$(function(){
	//myfresh();
//})
//function myfresh(){
	//window.location.reload();
//}
//setTimeout("myfresh()",8000)
  // location.reload(false);                              
</script>
</body>
</html>