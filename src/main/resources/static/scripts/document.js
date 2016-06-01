var routes = {
		"/documents/:documentId/new" : function(documentId){
			ReadymadeController.init();
			$(".print-button").hide();
		},
		"/documents/:documentId/edit" : function(documentId){
			ReadymadeController.init();
			ReadymadeController.loadResumeData();
			Filter.init();
		}
}

var router = Router(routes).configure({
	  html5history: true,
	  convert_hash_in_init: false
	});

router.init("/");