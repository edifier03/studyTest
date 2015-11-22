(function($) {
	
	var cookie_session_token = "ams-session-token";
	var cookie_user_type = "ams-user-type";
	// add by csl on 20141020 start
	var cookie_signin_name = "signin_name";
	// add by csl on 20141020 end
		
	// 切换侧边栏的状态
	$('.page-sidebar .sidebar-toggler').click(function() {
		$('body').toggleClass('page-sidebar-closed');
	});
	
	/** 加载页面主体部分 */
	function loadPageContent(lnk, content_url) {
		$("#page-content").panel({
			href : content_url,
			onLoad : function() {}
		});
		
		$('ul.page-sidebar-menu li.active').removeClass("active");
		lnk.parent().addClass("active");
	}
	
	// 服务管理
	$('#lnk_service').click(function() {
		loadPageContent($(this), "page/serviceList.html");
	});
	
	// 用户管理
	$('#lnk_user').click(function() {
		loadPageContent($(this), "page/userList.html");
	});
	
	// 企业管理 
	$('#lnk_company').click(function() {
		if( $.userType() == $.ROLE_COMPANY)//企业
		{
			loadPageContent($(this), "page/companyInfo.html");
		}else
		{
			loadPageContent($(this), "page/companyList.html");
		}
		
	});
	
	// 订单管理
	$('#lnk_order').click(function() {
		loadPageContent($(this), "page/orderList.html");
	});
	
	// add by csl start on 20141010
	/** ready */
	//管理员和平台操作员显示服务管理。
	//企业用户， 显示订单管理
	$(document).ready(function() {
		// 登录名设置
		$("#signinName").text($.signinName());
		
		var userType  = $.userType();
		var sessionToken  = $.sessionToken();
		alert(sessionToken);
		
		
		if(sessionToken == '' || typeof(sessionToken) == "undefined"){
			window.location = $.WebRoot+"/signin.html";
		}
		

	});
	
	// 绑定模态框show事件
	$('#changePasswordModal').on('show',function() {
		$("#form_userRegister").validate(validate_option);
	});
	
	// 绑定模态框hide事件
	$('#changePasswordModal').on('hide',function() {
		
		// 清除原有的错误信息
		$('div.alert-error', $('#changePasswordModal')).hide();
		$('div.control-group', $('#changePasswordModal')).removeClass('error');
		$('div.controls span.help-inline', $('#changePasswordModal')).remove();
		
		// 清除密码框的输入
		$('input:password', $('#changePasswordModal')).val('');
	});
	
	// 修改密码
	$('#lnk_changePassword').click(function() {
		
		// 显示密码修改模态框
		$('#changePasswordModal').modal('show');
	});
	
	// 注销登录
	$('#lnk_signout').click(function() {
		
		$.ajax( { 
		    url : '/AmsManagementApi/user/signout', 
		    data : '{}',  
		    success : function(data) {
				// 清楚cookie
				$.cookie(cookie_session_token, "");
				$.cookie(cookie_user_type, "");
				$.cookie(cookie_signin_name, "");
				window.location = "/AmsWeb/signin.html";
		    }
		  }); 
	});
	
	
	// 修正密码事件
	$('#btn_save').click(function() {
		$("#form_userRegister").submit();
	});
	
	// AJAX提交
	function doSave()
	{
		var obj = new Object();
		// 旧密码
		obj.password = sha256_digest($("#password", $('#changePasswordModal')).val());
		// 新密码
		obj.newPassword = sha256_digest($("#newPassword", $('#changePasswordModal')).val());
		
		var jsonparam = JSON.stringify(obj);
		
		$.ajax({
			  url: '/AmsManagementApi/user/changepassword',
			  data: jsonparam,
			  success: function(data){
			  	  $.alertMessage("密码修正成功");
				  $("#password").val("");
				  $("#newPassword").val("");
				  $("#btn_close").click();
			  },
			  form : $("#form_userRegister")
			});
	}
	
	// password
	$.validator.addMethod("password", function( value, element, params ) {
		var re = /^([0-9a-zA-Z@#$%&*()_-{}?])*$/;
		return re.test(value);
	}, "密码格式不正确，禁止输入全角字符");
	
	var validate_option = {
			rules : {
				password : {
					required : true,
					password : true,
					maxlength : 64
				},
				newPassword : {
					required : true,
					password : true,
					maxlength : 64
				},
				confirmPassword : {
					required : true,
					maxlength : 64,
					equalTo : "#newPassword"
				}
			},

			messages : { 
				password : {
					required : "请输入原密码"
				},
				newPassword : {
					required : "请输入新密码"
				},
				confirmPassword : {
					required : "请重新输入新密码",
					equalTo : "两次输入的新密码不一致"
				}
			},

			// 提交请求
			submit : function( form ) {
				doSave();
			}
		};
	
})(jQuery);
