var routes = {
		"/" : {
			on : function(){
				$(".login-modal").hide();
				$(".join-modal").hide();
//				InputSender.init();
//				Filter.init();
//				
				/*TODO htmltopdf api콜로 바꾸기*/
				$(".print-button").on("click", function(){
					
					window.print();
				});
			}
		},
		"/login" : {
			on : function(){
				console.log("login");
				$(".login-modal").show();
				$(".join-modal").hide();
			}
		},
		"/users/form" : {
			on : function(){
				$(".login-modal").hide();
				$(".join-modal").show();
				//validator로 체크
				//validator통과해야 join 버튼 활성화
			}
		}
}

var router = Router(routes);
router.init("/");
