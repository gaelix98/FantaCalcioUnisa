$(document).ready(function() {
	$(".error").hide();
	$("#username").keyup(function verificaUsername(){
		var user=$("#username").val();
		$.get("VerificaUsernameAjax?u="+ encodeURIComponent(user), function(data, status){
			verifica(data);
		});
	});
	
	function verifica(data){
		if (data)
			$("#username").addClass("er").next().show();
		else{
			if($("#username").hasClass("er"))
				$("#username").removeClass("er").next().hide();
		}
	}
});