var role_id;
var user_id;
role_id=getUrlParam("role_id");
user_id=getUrlParam("user_id");
//alert(role_id);

$(document).ready(function(){ 

})
//管理员、审核员
if(role_id==3){
	navs = [{
		"title": "订单管理",
		"icon": "fa-cubes",
		"spread": false,
	    "href": './Admin/Order/order.html?role_id='+role_id+'&user_id='+user_id+'&menuName=订单管理'
	}, {
		"title": "用户管理",
		"icon": "&#xe613;",
		"spread": false,
		"href": "./Admin/User/user.html?menuName=用户管理"
	}, {
		"title": "审核管理",
		"icon": "&#xe62e;",
		"spread": false,
		"href": './Admin/Auditing/auditing.html?user_id='+user_id+'&menuName=审核管理'
	}];
}else if(role_id==1){  //录入员
	navs = [{
		"title": "订单管理",
		"icon": "fa-cubes",
		"spread": false,
	    "href": './Admin/Order/order.html?role_id='+role_id+'&&menuName=订单管理'
	}];
}else if(role_id==2){  //编辑员
	navs = [{
		"title": "订单管理",
		"icon": "fa-cubes",
		"spread": false,
	    "href": './Admin/Order/order.html?role_id='+role_id+'&&menuName=订单管理'
	}];
}else if(role_id==4){  //普通用户
	navs = [{
		"title": "订单管理",
		"icon": "fa-cubes",
		"spread": false,
	    "href": './Admin/Order/order.html?role_id='+role_id+'&&menuName=订单管理'
	}];
}

function getUrlParam(name) {
	var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)"); // 构造一个含有目标参数的正则表达式对象
	var r = window.location.search.substr(1).match(reg);
	if (r != null)
		return unescape(r[2]);
	return null; // 返回参数值
}


