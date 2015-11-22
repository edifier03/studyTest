(function($) {
/*	function getMsg()
	{
		$.ajax( {  
		    url : '/REST/advertiser/123',  
		    beforeSend : function(request) {
		    	request.setRequestHeader("x-token","111");
		    },
		    success : function(data) {  
		    	$("#abc").text(data.data+"&&"+data.name);	    	
		    } 
		  }); 
	}
*/
/*
function getMsg(){
	$.ajax( { 

		type:"POST",

	    url : 'http://192.168.44.1:8080/REST/advertiser/123', 

	    dataType : "JSON",  

                beforeSend : function(request) {

	    	request.setRequestHeader("x-token","111");

	    },

	    success : function(data) { 

		

	    	$("#abc").text(data.data.name+"&&"+data.name);	    	
 	
	    } ,
	    error : function(data){
		$("#abc").text("error:"+data+"&&"+data.message);
	    }

	  }); 
}*/


function getMsg()

{

//	var obj = new Object();	
//
//	obj.id = "123";
//
//	obj.name = "name234";
//	
//	var tb = new Object();
//	
//	tb.mark = "12";
//	
//	obj.tb = tb;
//	
//	var tlb = new Array();
//	var tlbb = new Object();
//	tlbb.child = "123";
//	tlb.push(tlbb);
//	
//	obj.tlb = tlb;
	

	var obj = new Object();
	obj.userName="zhoutao";
	obj.passWord="123";
	
	$.ajax( { 

		type:"POST",

	    url : 'http://localhost:8080/REST/auth/login', 

	    dataType : "JSON",

	    data : 	JSON.stringify(obj),

	   // jsonpCallback:"callback",

       beforeSend : function(request) {

	    	request.setRequestHeader("Content-Type","application/json;charset=utf-8");
	    	request.setRequestHeader("x-token","111");

	    },

	    success : function(data) { 

		

	    	$("#abc").text(data.data.name);	    	

	    } ,

	    error : function(data){

		$("#abc").text("error:"+data+"&&"+data.responseText);

	    }

	  }); 

}
	
		getMsg();

})(jQuery);