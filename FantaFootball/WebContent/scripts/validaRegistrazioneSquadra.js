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
			return validateNameReg();
		if (namef=="cognome")
			return validateSurname();
		if (namef=="username")
			return validateUsername();
		if (namef=="email")
			return validateEmail();
		if (namef=="password")
			return validatePassword();
		if (namef=="bottone")
			return true;
		if (namef=="nome")
			return validateName();
		if (namef=="logoSquadra")
			return validateLogo();
		if (namef =="nomeLega")
			return true;
	}

	function validateNameReg(){
		var nameformat = /^^[A-Za-z ]{2,50}$/;
		if (document.getElementById("nome").value.match(nameformat)){
			console.log("nome corretto");
			return true;
		}

		else{
			document.getElementById("nome").focus();
			console.log("non corretto");
			return false;
		}
	}

	function validateSurname(){
		var surnameformat = /^[A-Za-z ]{2,50}$/;
		if (document.getElementById("cognome").value.match(surnameformat)){
			console.log("cognome corretto");
			return true;
		}
		else{
			document.getElementById("cognome").focus();
			return false;
		}
	}

	function validateUsername(){
		var usernameformat = /^[a-zA-Z0-9]+([a-zA-Z0-9](_|-| )[a-zA-Z0-9])*[a-zA-Z0-9]+$/;
		if (document.getElementById("username").value.match(usernameformat)){
			console.log("username corretto");
			return true;
		}
		else{
			document.getElementById("username").focus();
			return false;
		}
	}

	function validateEmail(){
		var mailformat = /^^([a-zA-Z0-9_\-\.]+)@([a-zA-Z0-9_\-\.]+)\.([a-zA-Z]{2,5})$/;
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
	
	function validateName(){
		var nameformat = /^.{4,50}$/;
		if (document.getElementById("nome").value.match(nameformat)){
			console.log("corretto");
			return true;
		}
		else{
			document.getElementById("nome").focus();
			console.log("non corretto");
			return false;
		}
	}

	function validateLogo(){
		var logoformat = /([^\s]+(\.(jpe?g|png|img|))$)/;
		var logobj = document.getElementById("logoSquadra");
		if (logobj.value.match(logoformat))
			return true;
		else{
			document.getElementById("logoSquadra").focus();
			console.log(document.getElementById("logoSquadra").value);
			return false;
		}
	}


});