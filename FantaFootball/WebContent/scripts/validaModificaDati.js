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
		if (namef=="email")
			return validateEmail();
		if (namef=="password")
			return validatePassword();
		if (namef=="bottone")
			return true;
	}

	function validateEmail(){
		var mailformat = /^([a-zA-Z0-9_\-\.]+)@([a-zA-Z0-9_\-\.]+)\.([a-zA-Z]{2,5})$/;
		if (document.getElementById("email").value.match(mailformat)){
			console.log("email corretto");
			return true;
		}
		else{
			document.getElementById("email").focus();
			return false;
		}
	}

	function validatePassword(){
		var passformat = /^([A-Za-z0-9]){5,}$/;
		if (document.getElementById("password").value.match(passformat)){
			console.log("password corretto");
			return true;
		}
		else{
			document.getElementById("password").focus();
			return false;
		}
	}

});