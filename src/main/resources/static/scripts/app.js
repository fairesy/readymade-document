var routes = {
		"/" : {
			on : function(){
				$(".login-modal").hide();
				$(".join-modal").hide();
				/*TODO htmltopdf api콜로 바꾸기*/
				$(".print-button").on("click", function(){
					
					window.print();
				});
			}
		},
		"/users/login" : {
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
				
			}
		}
}

var router = Router(routes);
router.init("/");
