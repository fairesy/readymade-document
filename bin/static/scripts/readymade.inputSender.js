var InputSender = (function(){
  function sendPersonalData(e){
	  e.preventDefault();
	  console.log("sending personal data");
	  var personalInfo = $("#resume-personal-form").serialize();

	  if(InputValidator.personalValidated()){
		  return AJAX.post("/resume/data/personal", personalInfo);
	  }
  }
  
  function toEducation(){
	  $("#education").addClass("selected-card");
	  $(".card-name .top").text("where did you");
	  $(".card-name .bottom").text("learn?");
  }

  function sendEducationData(e){
	  e.preventDefault();
	  console.log("sending education data")
	  var education = $("#resume-education-form").serialize();
	  
	  if(InputValidator.educationValidated()){
		  return AJAX.post("/resume/data/education", education);
	  }
  }
  
  function toExperience(){
	  $("#experience").addClass("selected-card");
	  $(".card-name .top").text("any");
	  $(".card-name .bottom").text("experiences?");
  }
  
  function sendExperienceData(e){
	  e.preventDefault();
	  console.log("sending experience data");
	  var experience = $("#resume-experience-form").serialize();
	  if(true){//InputValidator.experienceValidated()
		  return AJAX.post("/resume/data/experience", experience);
	  }
  }
  
  function toSkills(){
	  $("#skills").addClass("selected-card");
	  $(".card-name .top").text("and your");
	  $(".card-name .bottom").text("skill set");
  }
  
  function sendSkillsData(e){
	  e.preventDefault();
	  var skillList = "";
	  
	  $("#resume-skills-form input:checked").each(function(index){
		  var skillName = $(this).val() + "-";
		  skillList += skillName;
	  });
	  if(true){//InputValidator.skillsValidated()
		  return AJAX.post("/resume/data/skills", "skills="+skillList);
	  }
  }
  
  function saveAll(e){
	  sendPersonalData(e);
	  sendEducationData(e);
	  sendExperienceData(e);
	  sendSkillsData(e);
	  if(InputValidator.personalValidated() && InputValidator.educationValidated()){
		  $(".print-button").show();
	  }else{
		  $(".print-button").hide();
	  }
  }
  
  function init(){
	  $("#resume-personal-form input[type=submit]").on("click", function(e){
		  sendPersonalData(e).done(toEducation);
	  });
	  $("#resume-education-form input[type=submit]").on("click", function(e){
		  sendEducationData(e).done(toExperience);  
	  });
	  $("#resume-experience-form input[type=submit]").on("click", function(e){
		  sendExperienceData(e).done(toSkills);  
	  });
	  $("#resume-skills-form input[type=submit]").on("click", function(e){
		  sendSkillsData(e).done(function(){
			  window.location.replace("/documents/1/edit");
		  });
	  });
  }
  return {
	  init : init,
	  saveAll : saveAll
  }
})();
