var ReadymadeController = (function(){
  
  function init(){
	  InputSender.init();
		Filter.init();
		$(".print-button").on("click", function(){
			personalInputToPrintPart();
			educationInputToPrintPart();
			experienceInputToPrintPart();
			skillsInputToPrintPart();
			
			window.print(); //promise 적용
		});
  }
  
  function personalInputToPrintPart(){
		console.log($("#resume-personal-form input[name=name_en]").val());
		$("#personal-part .name_en").text($("#resume-personal-form input[name=name_en]").val().toUpperCase());
		$("#personal-part .name_ko").text($("#resume-personal-form input[name=name_ko]").val());
		$("#personal-part .email").text($("#resume-personal-form input[name=email]").val());
		$("#personal-part .phone").text($("#resume-personal-form input[name=phone]").val());
	}
  
  function educationInputToPrintPart(){
	  var name = $("#resume-education-form input[name=name]").val();
	  var major = $("#resume-education-form input[name=major]").val();
	  var state = $("#resume-education-form .state select option:selected").val();
	  var start_year = $("#resume-education-form .period select[name=start_year] option:selected").val();
	  var end_year = $("#resume-education-form .period select[name=end_year] option:selected").val();
	  
	  /*education 첫번째*/
	  $("#education-part .education:first .period").text( start_year + " - " + end_year );
	  $("#education-part .education:first .name").text(name + " " + major + " [" + state + "]");
  }
  
  function experienceInputToPrintPart(){
	  
  }
  
  function skillsInputToPrintPart(){
	  
  }

  return {
    "init" : init
  }
})();
