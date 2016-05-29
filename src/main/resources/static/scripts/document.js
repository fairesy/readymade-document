var routes = {
		"/documents/:documentId/new" : function(documentId){
			InputSender.init();
			Filter.init();
			$(".print-button").on("click", function(){
				
				window.print();
			});
		},
		"/documents/:documentId/edit" : function(documentId){
			InputSender.init();
			Filter.init();
			$(".print-button").on("click", function(){
				
				window.print();
			});
		}
}

var router = Router(routes).configure({
	  html5history: true,
	  convert_hash_in_init: false
	});

router.init("/");