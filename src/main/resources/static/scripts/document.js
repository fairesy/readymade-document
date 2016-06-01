var routes = {
		"/documents/:documentId/new" : function(documentId){
			ReadymadeController.init();
		},
		"/documents/:documentId/edit" : function(documentId){
			ReadymadeController.init();
			Filter.init();
		}
}

var router = Router(routes).configure({
	  html5history: true,
	  convert_hash_in_init: false
	});

router.init("/");