$(document).ready(function() {
	$(".error").hide();
	$("#email").change(function verificaUsername(){
		var email=$("#email").val();
		$.get("VerificaEmailAjax?q="+ encodeURIComponent(email), function(data, status){
			verifica(data);
		});
	});
	
	function verifica(data){
		if (data)
			$("#email").addClass("er").next().show();
		else{
			if($("#email").hasClass("er"))
				$("#email").removeClass("er").next().hide();
		}
	}
});