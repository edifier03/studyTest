(function($) {
	
	// ��¼����
	$("#signin").click(function() {
		$("#form_signin").submit();
	});

	function doSignin() {
		
		var obj = new Object();
		obj.userName = $("#userName").val();
//		obj.password = sha256_digest($("#password").val());
		obj.passWord = $("#password").val();
		var json = JSON.stringify(obj);
		alert(json);
		var isSubmitSuccess = false;
		$.ajax({
			url : $.ApiRoot+'/auth/login',
			data : json,
			async : false,
//			beforeSend : function(request) {
//
//		    	request.setRequestHeader("Content-Type","application/json;charset=utf-8");
//		    	request.setRequestHeader("x-token","111");
//
//		    },
			success : function( data ) {
					$.setSessionToken(data.data.tokenid);
					
					window.location = $.WebRoot + "/index.html";

			}
		});

		//return isSubmitSuccess;
	}

	// window����
	function center() {
		var wrapper = $('#login');
		var top = ($(window).height() - wrapper.outerHeight()) / 2;
		var left = ($(window).width() - wrapper.outerWidth()) / 2;
		wrapper.css({
			'position' : 'absolute',
			'display' : 'block',
			'top' : 120 + 'px',
			'left' : left + 'px'
		});
	}

	// ���ڸı��Сʱ���������
	$(window).resize(function() {
		center();
	});

	// ��ʼ��ʱ������
	center();
	
	var validate_option = {
			
			/** ���ô�����ʾ��λ�� */
			errorPlacement : function( error, element ) {
				error.css({'padding-left': '0px'});
				element.parent().append(error);
			},
			
			/** ��֤�ɹ� */
			success : function( label ) {
				label.closest('.control-group').removeClass('error').addClass('success');
				label.remove();
			},
			
			rules : {
				userName : {
					required : true,
					maxlength:20
				},
				password : {
					required : true,
					maxlength:64
				}
			},

			messages : { 
				userName : {
					required : "�������û���",
					maxlength : "�û��������"
				},
				password : {
					required : "����������",
					maxlength : "���������"
				}
			},

			submit : function( form ) {
				// ��¼����
				return doSignin();
			}
//			,
//
//			submitSuccess : function() {
//				window.location = "/AmsWeb/index.html";
//			},
//			
//			submitFail : function() {
//			}
		};

		/** ready */
		$(document).ready(function() {
			$("#form_signin").validate(validate_option);
			$('#userName').focus();
		});

})(jQuery);
