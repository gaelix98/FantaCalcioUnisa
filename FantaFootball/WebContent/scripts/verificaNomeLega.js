$(document).ready(function() {
	$(".error").hide();
	$("#nome").keyup(function verificaNomeLega(){
		var lega=$("#nome").val();
		$.get("VerificaNomeLegaAjax?u="+ encodeURIComponent(lega), function(data, status){
			verifica(data);
		});
	});
	
	function verifica(data){
		if (data)
			$("#nome").addClass("er").next().show();
		else{
			if($("#nome").hasClass("er"))
				$("#nome").removeClass("er").next().hide();
		}
	}
});