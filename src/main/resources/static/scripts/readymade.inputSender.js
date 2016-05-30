var InputSender = (function(){
  function sendPersonalData(e){
	  console.log("sending personal data");
	  e.preventDefault();
	  var personalInfo = $("#resume-personal-form").serialize();
	  console.log(personalInfo);

	  if(InputValidator.personalValidated()){
		  AJAX.post("/resume/data/personal", personalInfo)
		  .done(function(){
		    console.log("개인정보 전송 완료!");
		    $("#education").addClass("selected-card");
		    $(".card-name .top").text("where did you");
		    $(".card-name .bottom").text("learn?");
		  });
	  }

  }

  function sendEducationData(e){
	  e.preventDefault();
	  console.log("sending education data")
	  var education = $("#resume-education-form").serialize();
	  console.log(education);
	  
	  if(true){//InputValidator.educationValidated()
		  AJAX.post("/resume/data/education", education)
		  .done(function(){
		    console.log("education 전송 완료!");
		    $("#experience").addClass("selected-card");
		    $(".card-name .top").text("any");
		    $(".card-name .bottom").text("experiences?");
		  });
	  }
  }
  
  function sendExperienceData(e){
	  e.preventDefault();
	  console.log("sending experience data");
	  var experience = $("#resume-experience-form").serialize();
	  if(true){//InputValidator.experienceValidated()
		  AJAX.post("/resume/data/experience", experience)
		  .done(function(){
		    console.log("experience 전송 완료!");
		    $("#skills").addClass("selected-card");
		    $(".card-name .top").text("and your");
		    $(".card-name .bottom").text("skill set");
		  });
	  }
  }
  
  function sendSkillsData(e){
	  e.preventDefault();
	  var skillList = "";
	  
	  $("#resume-skills-form input:checked").each(function(index){
		  var skillName = $(this).val() + "-";
		  console.log(skillName);
		  skillList += skillName;
	  });
	  console.log(skillList);
	  if(true){//InputValidator.skillsValidated()
		  AJAX.post("/resume/data/skills", skillList)
		  .done(function(){
			  console.log("skills 전송 완료!");
		  });
	  }
  }
  
  function init(){
	  $("#resume-personal-form input[type=submit]").on("click", sendPersonalData);
	  $("#resume-education-form input[type=submit]").on("click", sendEducationData);
	  $("#resume-experience-form input[type=submit]").on("click", sendExperienceData);
	  $("#resume-skills-form input[type=submit]").on("click", sendSkillsData);
  }
  return {
	  init : init
  }
})();

 // th:action="@{/resume/data/personal}" method="post"
