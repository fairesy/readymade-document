var Filter = (function(){
	function changeCardName($label){
		var top;
		var bottom;
		
		if($label.hasClass("personal")){
			top = "who are";
			bottom = "you?";
		}else if($label.hasClass("education")){
			top = "where did you";
			bottom = "learn?";
		}else if($label.hasClass("experience")){
			top = "any";
			bottom = "experiences?";
		}else if($label.hasClass("skills")){
			top = "and your";
			bottom = "skill set";
		}
		
		$(".card-name .top").text(top);
	    $(".card-name .bottom").text(bottom);
	}
	function init(){
		$(".card .label").on("click", function(e){
			
			InputSender.saveAll(e);
			
			if($(".selected-card")){
				$(".selected-card").removeClass("selected-card");
			}
			$(e.target).closest(".card").addClass("selected-card");

			changeCardName($(e.target).closest(".label"));
			
		});
	}
	return {
		init : init
	}
})();