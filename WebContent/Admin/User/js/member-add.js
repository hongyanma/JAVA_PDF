$(document).ready(function(){
	init();
  

})

/*初始化*/
function init(){
	
	layui.use(['form', 'layer', 'layedit', 'laydate'], function() {
			var layer = layui.layer;
			var form = layui.form();
			layedit = layui.layedit,
			laydate = layui.laydate;
		
		 layedit.set({
			  uploadImage: {
			    url: '../../upload.jhtm' //接口url
			    ,type: 'post' //默认post
			  }
			}); 
		 
		 form.render();

		//创建一个编辑器
		  editIndex1 = layedit.build('LAY_demo_editor1'/* ,{
				 uploadImage: {url: '../../upload.jhtm', type: 'post'} */
			 ); 
		  editIndex2 = layedit.build('LAY_demo_editor2'/* ,{
				 uploadImage: {url: '../../upload.jhtm', type: 'post'} */
			 ); 
		/* alert(ayedit.getContent(index)) */
		//自定义验证规则
			form.verify({
				content: function(value) {
					layedit.sync(editIndex1);
					layedit.sync(editIndex2);
				}
			});
			
			//监听提交
			form.on('submit(demo1)', function(data) {
				alert(33)
				var result = addProduct();
				var json = JSON.parse(result);
				alert(json.result)
				if(json.result == true){
					layer.alert('创建成功', {
						title: '结果'
					})
				}else{
					layer.alert('创建失败', {
						title: '结果'
					})
				}
				return false;
			});
	});
}

