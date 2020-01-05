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
		if (namef=="name")
			return validateName();
		if (namef=="logoLega")
			return validateLogo();
		if (namef=="primoPosto")
			return validateprimoPosto();
		if(namef=="secondoPosto")
	        return validatesecondoPosto();
		if(namef="terzoPosto")
			return validateterzoPosto();
		if(namef="quotaMensile")
			return validatequotaMensile();
		if(namef="maxAllenatori")
			return validatemaxAllenatori();
		if(namef="budget")
			return validatebudget();
		if (namef=="nome")
			return validateNameS();
		if (namef=="logoSquadra")
			return validateLogoS();
		
	}  
	
	function validateprimoPosto(){
		var nameformat = /^[0-9]{0,2}*$/;
		if (document.getElementById("primoPosto").value.match(nameformat)){
			console.log("corretto");
			return true;
		}
		else{
			document.getElementById("primoPosto").focus();
			console.log("non corretto");
			return false;
		}
	} 
	
	
	
	function validatesecondoPosto(){
		var nameformat = /^[0-9]{0,2}*$/;
		if (document.getElementById("secondosPosto").value.match(nameformat)){
			console.log("corretto");
			return true;
		}
		else{
			document.getElementById("secondoPosto").focus();
			console.log("non corretto");
			return false;
		}
	}
	
	function validateterzoPosto(){
		var nameformat = /^[0-9]{0,2}*$/;
		if (document.getElementById("terzoPosto").value.match(nameformat)){
			console.log("corretto");
			return true;
		}
		else{
			document.getElementById("terzoPosto").focus();
			console.log("non corretto");
			return false;
		}
	}
	
	function validatequotaMensile(){
		var nameformat = /^[0-9]{0,2}*$/;
		if (document.getElementById("quotaMensile").value.match(nameformat)){
			console.log("corretto");
			return true;
		}
		else{
			document.getElementById("quotaMensile").focus();
			console.log("non corretto");
			return false;
		}
	}

	
	function validatebudget(){
		var nameformat = /^[0-9]{0,2}*$/;
		if (document.getElementById("budget").value.match(nameformat)){
			console.log("corretto");
			return true;
		}
		else{
			document.getElementById("budget").focus();
			console.log("non corretto");
			return false;
		}
	}
	
	
	function validatemaxAllenatori(){
		var nameformat = /^[0-9]{0,2}*$/;
		var test=document.getElementById("maxAllenatori()").value;
		if (test.match(nameformat)){
			if(test>4 && test<9 || test==10){
				
			
			console.log("corretto");
			return true;
			}
		}
		else{
			document.getElementById("maxAllenatori()").focus();
			console.log("non corretto");
			return false;
		}
	}
	
	function validateName(){
		var nameformat = /^.{4,50}$/;
		if (document.getElementById("name").value.match(nameformat)){
			console.log("corretto");
			return true;
		}
		else{
			document.getElementById("name").focus();
			console.log("non corretto");
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
	function validateNameS(){
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

	function validateLogoS(){
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