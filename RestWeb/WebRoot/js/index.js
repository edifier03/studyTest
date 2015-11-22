(function($) {
	
	var cookie_session_token = "ams-session-token";
	var cookie_user_type = "ams-user-type";
	// add by csl on 20141020 start
	var cookie_signin_name = "signin_name";
	// add by csl on 20141020 end
		
	// �л��������״̬
	$('.page-sidebar .sidebar-toggler').click(function() {
		$('body').toggleClass('page-sidebar-closed');
	});
	
	/** ����ҳ�����岿�� */
	function loadPageContent(lnk, content_url) {
		$("#page-content").panel({
			href : content_url,
			onLoad : function() {}
		});
		
		$('ul.page-sidebar-menu li.active').removeClass("active");
		lnk.parent().addClass("active");
	}
	
	// �������
	$('#lnk_service').click(function() {
		loadPageContent($(this), "page/serviceList.html");
	});
	
	// �û�����
	$('#lnk_user').click(function() {
		loadPageContent($(this), "page/userList.html");
	});
	
	// ��ҵ���� 
	$('#lnk_company').click(function() {
		if( $.userType() == $.ROLE_COMPANY)//��ҵ
		{
			loadPageContent($(this), "page/companyInfo.html");
		}else
		{
			loadPageContent($(this), "page/companyList.html");
		}
		
	});
	
	// ��������
	$('#lnk_order').click(function() {
		loadPageContent($(this), "page/orderList.html");
	});
	
	// add by csl start on 20141010
	/** ready */
	//����Ա��ƽ̨����Ա��ʾ�������
	//��ҵ�û��� ��ʾ��������
	$(document).ready(function() {
		// ��¼������
		$("#signinName").text($.signinName());
		
		var userType  = $.userType();
		var sessionToken  = $.sessionToken();
		alert(sessionToken);
		
		
		if(sessionToken == '' || typeof(sessionToken) == "undefined"){
			window.location = $.WebRoot+"/signin.html";
		}
		

	});
	
	// ��ģ̬��show�¼�
	$('#changePasswordModal').on('show',function() {
		$("#form_userRegister").validate(validate_option);
	});
	
	// ��ģ̬��hide�¼�
	$('#changePasswordModal').on('hide',function() {
		
		// ���ԭ�еĴ�����Ϣ
		$('div.alert-error', $('#changePasswordModal')).hide();
		$('div.control-group', $('#changePasswordModal')).removeClass('error');
		$('div.controls span.help-inline', $('#changePasswordModal')).remove();
		
		// �������������
		$('input:password', $('#changePasswordModal')).val('');
	});
	
	// �޸�����
	$('#lnk_changePassword').click(function() {
		
		// ��ʾ�����޸�ģ̬��
		$('#changePasswordModal').modal('show');
	});
	
	// ע����¼
	$('#lnk_signout').click(function() {
		
		$.ajax( { 
		    url : '/AmsManagementApi/user/signout', 
		    data : '{}',  
		    success : function(data) {
				// ���cookie
				$.cookie(cookie_session_token, "");
				$.cookie(cookie_user_type, "");
				$.cookie(cookie_signin_name, "");
				window.location = "/AmsWeb/signin.html";
		    }
		  }); 
	});
	
	
	// ���������¼�
	$('#btn_save').click(function() {
		$("#form_userRegister").submit();
	});
	
	// AJAX�ύ
	function doSave()
	{
		var obj = new Object();
		// ������
		obj.password = sha256_digest($("#password", $('#changePasswordModal')).val());
		// ������
		obj.newPassword = sha256_digest($("#newPassword", $('#changePasswordModal')).val());
		
		var jsonparam = JSON.stringify(obj);
		
		$.ajax({
			  url: '/AmsManagementApi/user/changepassword',
			  data: jsonparam,
			  success: function(data){
			  	  $.alertMessage("���������ɹ�");
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
	}, "�����ʽ����ȷ����ֹ����ȫ���ַ�");
	
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
					required : "������ԭ����"
				},
				newPassword : {
					required : "������������"
				},
				confirmPassword : {
					required : "����������������",
					equalTo : "��������������벻һ��"
				}
			},

			// �ύ����
			submit : function( form ) {
				doSave();
			}
		};
	
})(jQuery);
