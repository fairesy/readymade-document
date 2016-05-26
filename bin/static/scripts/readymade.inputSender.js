var InputSender = (function(){
  function sendPersonalInfo(e){
	  console.log("personal data get");
	  e.preventDefault();
	  var personalInfo = $("#resume-personal-form").serialize();
//	  var url = $("")
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

  function init(){
	  console.log("personal init");
	  $("#resume-personal-form input[type=submit]").on("click", sendPersonalInfo);
  }
  return {
	  init : init
  }
})();

 // th:action="@{/resume/data/personal}" method="post"
