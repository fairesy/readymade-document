var ReadymadeController = (function(){
  
  function init(){
	  InputSender.init();
//		Filter.init();
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
	  var start_year = $("#resume-experience-form .period select[name=start_year] option:selected").val();
	  var start_month = $("#resume-experience-form .period select[name=start_month] option:selected").val();
	  var end_year = $("#resume-experience-form .period select[name=end_year] option:selected").val();
	  var end_month = $("#resume-experience-form .period select[name=end_month] option:selected").val();
	  var name = $("#resume-experience-form input[name=name]").val();
	  var description = $("#resume-experience-form input[name=description]").val();
	  var link = $("#resume-experience-form input[name=link]").val();
	  $("#experience-part .experience:first .period").text(start_year + "." + start_month + " - " + end_year + "." + end_month);
	  $("#experience-part .experience:first .name").text(name);
	  $("#experience-part .experience:first .description").text(description);
	  $("#experience-part .experience:first .link").text(link);
  }
  
  function skillsInputToPrintPart(){
	  
  }

  return {
    "init" : init
  }
})();
