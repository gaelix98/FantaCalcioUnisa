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
			return validateName();
		if (namef=="cognome")
			return validateSurname();
		if (namef=="username")
			return validateUsername();
		if (namef=="email")
			return validateEmail();
		if (namef=="password")
			return validatePassword();
	}

	function validateName(){
		var nameformat = /^^[A-Za-z ]{2,50}$/;
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

	function validateSurname(){
		var surnameformat = /^[A-Za-z ]{2,50}$/;
		if (document.getElementById("cognome").value.match(surnameformat))
			return true;
		else{
			document.getElementById("cognome").focus();
			return false;
		}
	}

	function validateUsername(){
		var usernameformat = /^[a-zA-Z0-9]+([a-zA-Z0-9](_|-| )[a-zA-Z0-9])*[a-zA-Z0-9]+$/;
		if (document.getElementById("username").value.match(usernameformat))
			return true;
		else{
			document.getElementById("username").focus();
			return false;
		}
	}

	function validateEmail(){
		var mailformat = /^([a-zA-Z0-9_\\-\\.]+)@([a-zA-Z0-9_\\-\\.]+)\\.([a-zA-Z]{2,5})$/;
		if (document.getElementById("email").value.match(mailformat))
			return true;
		else{
			document.getElementById("email").focus();
			return false;
		}
	}

	function validatePassword(){
		var passformat = /^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{5,}$/;
		if (document.getElementById("password").value.match(passformat)){
			return true;
		}
		else{
			document.getElementById("password").focus();
			return false;
		}
	}

});