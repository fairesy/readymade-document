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
			$name_ko.addClass("invalid");
		}else{
			isNameKo = true;
			
			$("#print-part #name").text($name_ko.val());
			
			$name_ko.removeClass("invalid");
		}
		
		if(validator.isNull($name_en.val())){
			$name_en.addClass("invalid");
		}else{
			isNameEn = true;
			$name_en.removeClass("invalid");
		}
		
		if(validator.isNull($email.val())){
			$email.addClass("invalid");
		}else{
			isEmail = true;
			
			$("#print-part #email").text($email.val());
			
			$email.removeClass("invalid");
		}
		
		if(validator.isNull($phone.val())){
			$phone.addClass("invalid");
		}else{
			isPhone = true;
			$phone.removeClass("invalid");
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