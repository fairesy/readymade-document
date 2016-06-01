var ReadymadeController = (function(){
	var $personal_name_en = $("#resume-personal-form input[name=name_en]");
	var $personal_name_ko = $("#resume-personal-form input[name=name_ko]");;
	var $personal_email = $("#resume-personal-form input[name=email]");;
	var $personal_phone = $("#resume-personal-form input[name=phone]");;
	var $education_name = $("#resume-education-form input[name=name]");
	var $education_major = $("#resume-education-form input[name=major]");
	var $experience_name = $("#resume-experience-form input[name=name]");
	var $experience_description = $("#resume-experience-form input[name=description]");
	var $experience_link = $("#resume-experience-form input[name=link]");
	
  function init(){
	  InputSender.init();
	  $(".print-button").on("click", function(){
		  _setPrintPart();
		  $(".print_guide").show();
	  });
	  $(".print_guide .guide button").on("click", function(){
		  $(".print_guide").hide();
		  window.print();
	  })
	  $("#resume-education-form .add").on("click", _addEducation);
  }
  
  function _addEducation(){
	  console.log("학력을 추가합니다!");
  }
  
  function loadResumeData(){
	  var personalData = JSON.parse($("#personal_data").val());
	  var educationData = JSON.parse($("#education_data").val());
	  var experienceData = JSON.parse($("#experience_data").val());
	  var skillsData = JSON.parse($("#skills_data").val());
	  console.log(personalData);
	  console.log(educationData);
	  console.log(experienceData);
	  console.log(skillsData);
	  _setPersonalCard(personalData);
	  _setEducationCard(educationData);
	  _setExperienceCard(experienceData);
	  _setSkillsCard(skillsData);
	  
  }
  function _setPersonalCard(personal){
	  $personal_name_en.val(personal.name_en);
	  $personal_name_ko.val(personal.name_ko);
	  $personal_email.val(personal.email);
	  $personal_phone.val(personal.phone);
  }
  function _setEducationCard(education){
	  $education_name.val(education.name);
	  $education_major.val(education.major);
	  $("#resume-education-form .state select").val(education.state);
	  $("#resume-education-form .period select[name=start_year]").val(education.start_year);
	  $("#resume-education-form .period select[name=end_year]").val(education.end_year);
  }
  function _setExperienceCard(experience){
	  $experience_name.val(experience.name);
	  $experience_description.val(experience.description);
	  $experience_link.val(experience.link);
	  $("#resume-experience-form .period select[name=start_year]").val(experience.start_year);
	  $("#resume-experience-form .period select[name=start_month]").val(experience.start_month);
	  $("#resume-experience-form .period select[name=end_year]").val(experience.end_year);
	  $("#resume-experience-form .period select[name=end_month]").val(experience.end_month);
  }
  function _setSkillsCard(skills){
	  
  }
  
  function _setPrintPart(){
	  _personalInputToPrintPart();
	  _educationInputToPrintPart();
	  _experienceInputToPrintPart();
	  _skillsInputToPrintPart();
  }
  
  function _personalInputToPrintPart(){
		console.log($("#resume-personal-form input[name=name_en]").val());
		$("#personal-part .name_en").text($personal_name_en.val().toUpperCase());
		$("#personal-part .name_ko").text($personal_name_ko.val());
		$("#personal-part .email").text($personal_email.val());
		$("#personal-part .phone").text($personal_phone.val());
	}
  
  function _educationInputToPrintPart(){
	  var name = $education_name.val();
	  var major = $education_major.val();
	  var state = $("#resume-education-form .state select option:selected").val();
	  var start_year = $("#resume-education-form .period select[name=start_year] option:selected").val();
	  var end_year = $("#resume-education-form .period select[name=end_year] option:selected").val();
	  
	  /*education 첫번째*/
	  $("#education-part .education:first .period").text( start_year + " - " + end_year );
	  $("#education-part .education:first .name").text(name + " " + major + " [" + state + "]");
  }
  
  function _experienceInputToPrintPart(){
	  var start_year = $("#resume-experience-form .period select[name=start_year] option:selected").val();
	  var start_month = $("#resume-experience-form .period select[name=start_month] option:selected").val();
	  var end_year = $("#resume-experience-form .period select[name=end_year] option:selected").val();
	  var end_month = $("#resume-experience-form .period select[name=end_month] option:selected").val();
	  var name = $experience_name.val();
	  var description = $experience_description.val();
	  var link = $experience_link.val();
	  $("#experience-part .experience:first .period").text(start_year + "." + start_month + " - " + end_year + "." + end_month);
	  $("#experience-part .experience:first .name").text(name);
	  $("#experience-part .experience:first .description").text(description);
	  $("#experience-part .experience:first .link").text(link);
  }
  
  function _skillsInputToPrintPart(){
	  
  }

  return {
    "init" : init,
    "loadResumeData" : loadResumeData
  }
})();
