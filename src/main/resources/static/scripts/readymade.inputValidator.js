var InputValidator = (function(){
	
	function validatePersonalCard(){
		var isValidated = false;
		
		var $name_ko = $(".personal-info input[name=name_ko]");
		var isNameKo = false;
		
		var $name_en = $(".personal-info input[name=name_en]");
		var isNameEn = false;
		
		var $email = $(".personal-info input[name=email]");
		var isEmail = false;
		
		var $phone = $(".personal-info input[name=phone]");
		var isPhone = false;
		
		if(validator.isNull($name_ko.val())){
			$name_ko.css("border-color", "red");
		}else{
			isNameKo = true;
			
			$("#print-part #name").text($name_ko.val());
			
			$name_ko.css("border-color", "#fff");
		}
		
		if(validator.isNull($name_en.val())){
			$name_en.css("border-color", "red");
		}else{
			isNameEn = true;
			$name_en.css("border-color", "#fff");
		}
		
		if(validator.isNull($email.val())){
			$email.css("border-color", "red");
		}else{
			isEmail = true;
			
			$("#print-part #email").text($email.val());
			
			$email.css("border-color", "#fff");
		}
		
		if(validator.isNull($phone.val())){
			$phone.css("border-color", "red");
		}else{
			isPhone = true;
			$phone.css("border-color", "#fff");
		}
		
		if(isNameKo && isNameEn && isEmail && isPhone){
			isValidated = true;
		}
		
		return isValidated;
	}
	
	return {
		personalValidated : validatePersonalCard
	}
})();