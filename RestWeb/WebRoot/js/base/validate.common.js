/** 设置验证的默认值 */
$.validator.setDefaults({
	errorElement : 'span', // 错误消息的容器
	errorClass : 'help-inline', // 错误消息样式
	focusInvalid : false, // do not focus the last invalid input
	ignore : "", // 忽略验证的选择器

	/** 设置错误显示的位置 */
	errorPlacement : function( error, element ) {
		error.insertAfter(element);
	},

	/** 焦点离开的处理 */
	onfocusout : function( element ) {
		$(element).valid();
	},

	/** 表单未通过验证时的处理 */
	invalidHandler : function( event, validator ) {
		
		// 'this' refers to the form
    var errors = validator.numberOfInvalids();
    
    if (errors) {
    	
    	// 检测是否有显示错误消息的DIV
  		var div_error = $('div.alert-error', $(this));
  		if ( div_error.size() === 0 ) {
  			// 没有则创建一个，并加入到表单第一个元素
  			div_error = $("<div>").addClass("alert alert-error");
  			$(this).prepend(div_error);
  		}
  		
  		// 显示错误消息
  		div_error.text("输入错误，请按照提示信息修正！").show();
  		
    } else {
    	// 无用
    	// 经测试errors为0时，不会调用invalidHandler函数
    	
    	$('div.alert-error', $(this)).hide();
    }
	},

	/** 给未通过验证的元素加高亮效果 */
	highlight : function( element ) {
		$(element).closest('.help-inline').removeClass('ok');
		$(element).closest('.control-group').removeClass('success').addClass(
				'error');
	},

	/** 去除高亮效果 */
	unhighlight : function( element ) {
		$(element).closest('.control-group').removeClass('error');
	},

	/** 表单元素通过验证时的处理 */
	success : function( label ) {
		label.addClass('valid').addClass('help-inline ok')
				.closest('.control-group').removeClass('error').addClass('success');
	},
	
	confirmBeforeSubmit : false,
	confirmMessage : '',

	/** 验证通过，提交表单的处理 */
	submitHandler : function( form ) {

		// 隐藏错误消息
		$('.alert-error', form).hide();

		// 表单提交前处理
		this.settings.beforeSubmit.call(this, form);
		
		if ( this.settings.confirmBeforeSubmit ) {
			// 确认提交
			$.confirmMessage(this.settings.confirmMessage, this.settings.submit, form);
		} else {
			
			// 提交
			this.settings.submit.call(this, form);
		}
	},

	beforeSubmit : function( form ) {
	}
});