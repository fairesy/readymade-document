
var routes = {
		"/" : {
			on : function(){
				$(".login-modal").hide();
				$(".join-modal").hide();
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
			}
		}
}

var router = Router(routes);
router.init("/");
