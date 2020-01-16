$(document).ready(function() {
	$(".error").hide();
	$("#form1").submit(function(){
		var ris = true;
		$("#form1").find('input').each(function() {
			if (!validate($(this).attr("name"))){
				$(this).addClass("er").next().show();
				ris=false;
			}
			else{
				if($(this).hasClass("er"))
					$(this).removeClass('er').next().hide();
			}

		});
		return ris;
	});

	function validate(namef){
		if (namef=="nome") 
			return validateName() && (!($("#nome").hasClass("er")));
		if (namef=="logoLega")
			return validateLogo();
		if (namef=="primoPosto" || namef=="secondoPosto" || namef=="terzoPosto" || namef=="quotaMensile")
			return true;
	}  
	
	
	function validateName(){
		var nameformat = /^.{4,50}$/;
		if (document.getElementById("nome").value.match(nameformat)){
			console.log("nome corretto");
			return true;
		}
		else{
			document.getElementById("nome").focus();
			console.log("nome non corretto");
			return false;
		}
	}

	function validateLogo(){
		var logoformat = /([^\s]+(\.(jpe?g|png|img|))$)/;
		var logobj = document.getElementById("logoLega");
		if (logobj.value.match(logoformat))
			return true;
		else{
			document.getElementById("logoLega").focus();
			console.log(document.getElementById("logoLega").value);
			return false;
		}
	}
	


});