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
			$name_ko.attr("placeholder", "이름을 입력해주세요!");
			$name_ko.addClass("invalid");
		}else{
			isNameKo = true;
			$("#print-part #name").text($name_ko.val());
			$name_ko.removeClass("invalid");
		}
		
		if(validator.isNull($name_en.val())){
			$name_en.attr("placeholder", "영어이름을 입력해주세요!");
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
		
		var regExp = /^01([0|1|6|7|8|9]?)-?([0-9]{3,4})-?([0-9]{4})$/;
		if(validator.isNull($phone.val())){
			$phone.addClass("invalid");
		}else if( !regExp.test( $phone.val() ) ){
			$phone.val("");
			$phone.attr("placeholder", "잘못된 휴대폰 번호입니다.");
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
	
	function validateEducationCard(){
		var isValidated = false;
		var $name = $("#resume-education-form input[name=name]");
		var isName = false;
		var $major = $("#resume-education-form input[name=major]");
		var isMajor = false;
		var $start_year = $("#resume-education-form .period select[name=start_year] option:selected");
		var $end_year = $("#resume-education-form .period select[name=end_year] option:selected");
		var isPeriod = false;
		
		if(validator.isNull($name.val())){
			$name.addClass("invalid");
		}else{
			isName = true;
			$name.removeClass("invalid");
		}
		if(validator.isNull($major.val())){
			$major.addClass("invalid");
		}else{
			isMajor = true;
			$major.removeClass("invalid");
		}
		
		if(parseInt($start_year.val()) > parseInt($end_year.val())){
			alert("입학하기 전에 졸업하실 순 없어요...!");
		}else{
			isPeriod = true;
		}
		
		if(isName && isMajor && isPeriod){
			isValidated = true;
		}
		
		return isValidated;
	}
	
	return {
		personalValidated : validatePersonalCard,
		educationValidated : validateEducationCard
	}
})();