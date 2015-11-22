(function( $ ) {
	
	var cookie_session_token = "ams-session-token";
	var cookie_user_type = "ams-user-type";
	// add by csl on 20141020 start
	var cookie_signin_name = "signin_name";
	// add by csl on 20141020 end
	
	$.extend({
		/** 显示提示消息 */
		alertMessage : function( msg ) {
			
			var alert_div = $('#alertMessage');
			
			if ( alert_div.size() === 0 ) {
			
				alert_div = $('<div id="alertMessage" />').addClass('modal hide fade');
	
				// header
				var header = $('<div>').addClass('modal-header')
					.append($('<h3>').text('操作提示'))
					.appendTo(alert_div);
				// body
				var body = $('<div>').addClass('modal-body')
					.append($("<div style='float:left;font-size:32px;color:#468847;'><span class='fa fa-info-circle'/></div>"))
					.append($("<div style='float:left;line-height:32px;margin-left:10px;'/>").append($('<p>').text(msg)))
					.appendTo(alert_div);
				
				// footer
				var footer = $('<div>').addClass('modal-footer')
					.append($('<button/>').addClass('btn btn-primary')
							.text('确定')
							.attr({"data-dismiss":"modal","aria-hidden":true}))
					.appendTo(alert_div);
			} else {
				// 设置提示消息
				$('.modal-body p', alert_div).text(msg);
			}
			
			alert_div.modal({
				'show' : true
			});
			
		},
		
		/** 显示提示消息 */
		confirmMessage : function( msg, ok, event_data ) {
			
			var msg_div = $('#confirmMessage');
			
			if ( msg_div.size() === 0 ) {

				var header = $('<div>')
					.addClass('modal-header')
					.prepend($('<h3>').text('操作提示'))
					.prepend($('<button>').addClass('close').attr({"data-dismiss":"modal","aria-hidden":true}).html('&times'));
				var body = $('<div>')
					.addClass('modal-body')
					.append($("<div style='float:left;font-size:32px;color:#f90;'><span class='fa fa-question-circle'/></div>"))
					.append($("<div style='float:left;line-height:32px;margin-left:10px;'/>").append($('<p>').text(msg)));
				
				var btn_ok = $('<button/>').addClass('btn btn-primary').text('确定')
					.attr({"data-dismiss":"modal","aria-hidden":true});
				
				// 绑定OK按钮的click事件
				btn_ok.click(event_data, ok);
				
				var footer = $('<div>')
					.addClass('modal-footer')
					.prepend($('<button/>').addClass('btn').text('取消')
							.attr({"data-dismiss":"modal","aria-hidden":true}))
					.prepend(btn_ok);
				
				var msg_div = $("<div id='confirmMessage'/>").addClass('modal hide fade');
				msg_div.append(header).append(body).append(footer);
				
			} else {
				
				// 设置提示消息
				$('.modal-body p', msg_div).text(msg);
				
				// 重新绑定OK按钮的click事件
				var btn_ok = $('.modal-footer>button.btn-primary', msg_div);
				btn_ok.unbind("click").click(event_data, ok);
			}
			
			msg_div.modal('show');
		},
		
		setSessionToken: function(token) {
			return $.cookie(cookie_session_token, token);
		},
		
		sessionToken: function() {
			return $.cookie(cookie_session_token);
		},
		
		setUserType: function(type) {
			return $.cookie(cookie_user_type, type);
		},
		
		userType: function() {
			return $.cookie(cookie_user_type);
		},
		
		// add by csl on 20141020 start
		setSigninName: function(name) {
			return $.cookie(cookie_signin_name, name);
		},
		
		signinName: function() {
			return $.cookie(cookie_signin_name);
		},
		// add by csl on 20141020 end
		
		// 1：系统管理员（ROLE_ADMIN）
		ROLE_ADMIN : "1",
		// 2：平台操作员（ROLE_OPERATOR）
		ROLE_OPERATOR : "2",
		// 3：企业用户（ROLE_COMPANY）
		ROLE_COMPANY : "3"
	});

})(jQuery);

(function( $ ) {
	
	// Ajax返回错误码对应的错误消息定义
	var ajaxErrMsgMap = {};
	// 共通消息
	ajaxErrMsgMap[20001] = "JSON数据解析异常";
	ajaxErrMsgMap[20002] = "IO异常";
	ajaxErrMsgMap[20003] = "数据解析异常";
	ajaxErrMsgMap[20004] = "文件读取异常";
	ajaxErrMsgMap[20005] = "参数必须设置";
	ajaxErrMsgMap[20006] = "参数格式不匹配";
	ajaxErrMsgMap[20007] = "参数的长度超过限制";
	ajaxErrMsgMap[20008] = "AMS-SESSION-TOKEN头没有设置";
	ajaxErrMsgMap[20009] = "未认证的请求";
	// 用户管理消息
	ajaxErrMsgMap[20101] = "注册用户名已存在";
	ajaxErrMsgMap[20102] = "用户名和密码不匹配";
	ajaxErrMsgMap[20103] = "原密码不正确";
	ajaxErrMsgMap[20104] = "修改用户状态失败";
	ajaxErrMsgMap[20105] = "重置用户密码失败";
	ajaxErrMsgMap[20106] = "管理员不允许注销自己";
	ajaxErrMsgMap[20107] = "用户登出失败";
	ajaxErrMsgMap[20108] = "注册用户失败";
	ajaxErrMsgMap[20109] = "注册企业用户时必须指定企业ID";
	ajaxErrMsgMap[20110] = "注册系统管理以及平台操作员时不能指定企业ID";
	ajaxErrMsgMap[20111] = "该用户所属的企业已经被注销,禁止登录"
	// 服务管理消息
	ajaxErrMsgMap[20201] = "输入的服务名与已存在的服务名重复";
	ajaxErrMsgMap[20202] = "更新服务状态失败";
	ajaxErrMsgMap[20203] = "更新指定服务信息失败";
	// 企业管理消息
	ajaxErrMsgMap[20301] = "企业名称重复";
	ajaxErrMsgMap[20302] = "更新企业状态失败";
	ajaxErrMsgMap[20303] = "更新企业失败";
	ajaxErrMsgMap[20304] = "没有传递企业id";
	// 订单管理
	ajaxErrMsgMap[20401] = "服务不存在";
	ajaxErrMsgMap[20402] = "修改服务状态失败";
	ajaxErrMsgMap[20403] = "服务时间范围重复";
	ajaxErrMsgMap[20404] = "服务总额超出范围";	
	ajaxErrMsgMap[20405] = "企业不存在";	
	ajaxErrMsgMap[20406] = "订单不存在";	
	ajaxErrMsgMap[20407] = "单价错误";	

	// 备份jquery的ajax方法
	var _ajax = $.ajax;

	// 重写jquery的ajax方法
	$.ajax = function( opt ) {
		// 备份opt中error和success方法
		var fn = {
			error : function( XMLHttpRequest,
					textStatus, errorThrown ) {
			},
			success : function( data, textStatus ) {
			}
		}
		
		if ( opt.error ) {
			fn.error = opt.error;
		}
		if ( opt.success ) {
			fn.success = opt.success;
		}

		// 扩展增强处理
		var _opt = $.extend({
				type : 'POST',
				contentType : 'application/json;charset=utf-8',
				dataType : 'json'
			}, opt, {
			
			error : function( XMLHttpRequest,
					textStatus, errorThrown ) {
				
				var status = XMLHttpRequest.status;
				var msg = "";
				
				// 错误方法增强处理
				if ( status == 403 ) {
					msg = "您没有被授权使用该功能!";
				} else if ( status == 404 ) {
					msg = "与系统通信失败，请与系统管理员联系!";
				} else if ( status == 500 ) {
					msg = "发生系统内部错误，请与系统管理员联系!";
				}
				
				// 获取显示错误的DIV
				var valid_form = this.form;
//				if ( !valid_form ) {
//					valid_form = $('form:first');
//				}
				
				if ( valid_form ) {
					
					var div_error = $('div.alert-error', valid_form);
		  		if ( div_error.size() === 0 ) {
		  			// 没有则创建一个，并加入到表单第一个元素
		  			div_error = $("<div>").addClass("alert alert-error");
		  			//this.form.prepend(div_error);
		  			valid_form.prepend(div_error);
		  		}
		  		
		  		div_error.text(msg).show();
				} else {
					// 界面上无表单
					// 以弹出框的形式显示错误消息
					
					$.alertMessage(msg);
				}

				fn.error(XMLHttpRequest, textStatus,
						errorThrown);
			},
			success : function( data, textStatus ) {
				// 成功回调方法增强处理
				
				if ($.isPlainObject( data )) {
					
					if (data.meta.success!=true) {
					
						if (data.code === 20010 || data.code === 20008) {
							$('#tokenErrorMsg').modal({
								'show' : true,
								'backdrop' : 'static'
							});
						} else {
							
							var error_msg = ajaxErrMsgMap[data.code];
							
							// 获取显示错误的DIV
							var valid_form = this.form;
//							if ( !valid_form ) {
//								valid_form = $('form:first');
//							}
							
							if ( valid_form ) {
								var div_error = $('div.alert-error', valid_form);
					  		if ( div_error.size() === 0 ) {
					  			// 没有则创建一个，并加入到表单第一个元素
					  			div_error = $("<div>").addClass("alert alert-error");
					  			// this.form.prepend(div_error);
					  			valid_form.prepend(div_error);
					  		}
					  		
					  		// 显示错误消息
					  		div_error.text(error_msg).show();
							} else {
								// 无表单，弹出错误消息
								
								$.alertMessage(error_msg);
							}
						}
						
					} else {
						fn.success(data, textStatus);
					}
				} else {
					fn.success(data, textStatus);
				}
			}
		});

		_ajax(_opt);
	};
})(jQuery);
