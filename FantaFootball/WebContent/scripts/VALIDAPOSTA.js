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
		$("#form1").find('textarea').each(function() {
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
		if (namef=="titolo")
			return validateTitolo();
		if (namef=="testo")
			return validateTesto();
		
	}

	function validateTitolo(){
		var nameformat = /^.{5,}$/;
		if (document.getElementById("titolo").value.match(nameformat)){
			console.log("corretto");
			return true;
		}
		else{
			document.getElementById("titolo").focus();
			console.log("non corretto");
			return false;
		}
	}

	function validateTesto(){
		var testoformat = /^.{20,}$/;
		var testo = document.getElementById("testo");
		if (testo.value.match(testoformat))
			return true;
		else{
			document.getElementById("testo").focus();
			console.log(document.getElementById("testo").value);
			return false;
		}
	}


});